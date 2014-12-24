package sea.goethe.sportpas;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sea.goethe.sportpas.lib.ChoiceGambarAdapter;
import sea.goethe.sportpas.lib.ChoiceTextAdapter;
import sea.goethe.sportpas.lib.DatabaseHandler;
import sea.goethe.sportspas.model.MultipleQuizModel;
import sea.goethe.sportspas.model.ProgressModel;
import sea.goethe.sportspas.model.TFQuizModel;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class TipePertama extends ActionBarActivity {
	private ChoiceGambarAdapter cgAdapter = new ChoiceGambarAdapter();
	private GridView listJawaban;
	private int idTFnya = -1;
	private RadioGroup groupTF;
	private Button btnWeiter;
	private LinearLayout garisPembatas, bagianSpeak, layoutScrabbleText;
	private ImageButton btnSpeak;
	private TextView textSoal, textLevel, textMenit, textDetik, textHint,
			speakSoal;
	private ImageView imgSoal, imgSpeakStatus;
	private EditText textJawabanScrabble;
	private ScrollView listjawabanb;
	private boolean statusHoren = false;
	public static int score = 0;
	private DatabaseHandler dh;
	private int idnya = 0;
	private List<MultipleQuizModel> mqm;
	private List<MultipleQuizModel> mqmt;
	private List<TFQuizModel> tqm;
	private MultipleQuizModel currentMQM;
	private MultipleQuizModel currentMQMT;
	private TFQuizModel currentTF;
	private ArrayList<String> jawabannya = new ArrayList<String>();
	public static MediaPlayer soundBenar, soundSalah;
	private long startTime = 0L;
	private Handler myHandler = new Handler();
	long timeInMillies = 0L;
	long timeSwap = 0L;
	long finalTime = 0L;
	private String hasilCakap = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tipe_pertama);

		score = 0;

		dh = new DatabaseHandler(getApplicationContext());

		startTime = SystemClock.uptimeMillis();
		myHandler.postDelayed(updateTimerMethod, 0);

		mqm = dh.getAllMP("gambar");
		mqmt = dh.getAllMP("text");
		tqm = dh.getAllTF();

		currentMQM = mqm.get(idnya);
		currentMQMT = mqmt.get(idnya);
		currentTF = tqm.get(idnya);

		listJawaban = (GridView) findViewById(R.id.listJawaban);
		btnWeiter = (Button) findViewById(R.id.btnWeiter);
		textSoal = (TextView) findViewById(R.id.textSoal);
		textLevel = (TextView) findViewById(R.id.textLevel);
		textMenit = (TextView) findViewById(R.id.textMenit);
		textDetik = (TextView) findViewById(R.id.textDetik);
		speakSoal = (TextView) findViewById(R.id.speakSoal);
		imgSoal = (ImageView) findViewById(R.id.imgSoal);
		imgSpeakStatus = (ImageView) findViewById(R.id.imgSpeakStatus);
		groupTF = (RadioGroup) findViewById(R.id.groupTF);
		listjawabanb = (ScrollView) findViewById(R.id.listJawabanb);
		garisPembatas = (LinearLayout) findViewById(R.id.garisPembatas);
		textHint = (TextView) findViewById(R.id.textHint);
		btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
		bagianSpeak = (LinearLayout) findViewById(R.id.bagianSpeak);
		layoutScrabbleText = (LinearLayout) findViewById(R.id.layoutScrabbleText);
		textJawabanScrabble = (EditText) findViewById(R.id.textJawabanScrabble);

		soundBenar = MediaPlayer.create(this, R.raw.sound_benar);
		soundSalah = MediaPlayer.create(this, R.raw.sound_salah);

		setQuestionView("gambar");
		btnWeiter.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (cgAdapter.getSelectedAnswer() >= 0) {
					if (cgAdapter.getSelectedAnswer() == currentMQM.getBenar()) {
						score += Integer.parseInt(currentMQM.getPoint());
						soundBenar.start();
						updateProgress(10, 5, 0, 0);
					} else {
						soundSalah.start();
					}

					if (idnya < dh.countRowMP("gambar")) {
						jawabannya.clear();
						cgAdapter.setSelectedAnswer(-1);

						currentMQM = mqm.get(idnya);

						checkPosisiID();
					} else {
						pindahScore();
					}
				} else if (ChoiceTextAdapter.mSelectedPosition >= 0) {
					if (ChoiceTextAdapter.mSelectedPosition == currentMQMT
							.getBenar()) {
						score += Integer.parseInt(currentMQMT.getPoint());
						soundBenar.start();
						updateProgress(10, 5, 0, 0);
					} else {
						soundSalah.start();
					}

					if (idnya < dh.countRowMP("gambar")) {
						jawabannya.clear();
						ChoiceTextAdapter.mSelectedPosition = -1;

						currentMQMT = mqmt.get(idnya);

						checkPosisiID();
					} else {
						pindahScore();
					}
				} else if (idTFnya > -1) {
					if (idTFnya == currentTF.getJawaban()) {
						score += Integer.parseInt(currentMQMT.getPoint());
						soundBenar.start();
						updateProgress(10, 5, 0, 0);
					} else {
						soundSalah.start();
					}

					if (idnya < dh.countRowMP("gambar")) {
						if (idTFnya > -1) {
							groupTF.clearCheck();
						}

						idTFnya = -1;

						currentTF = tqm.get(idnya);

						checkPosisiID();
					} else {
						pindahScore();
					}
				} else if (!textJawabanScrabble.getText().toString()
						.equalsIgnoreCase("")) {
					if (textJawabanScrabble.getText().toString()
							.equalsIgnoreCase("Schach")) {
						score += 10;
						soundBenar.start();
					} else {
						soundSalah.start();
					}

					if (idnya < dh.countRowMP("gambar")) {
						textJawabanScrabble.setText("");
						updateProgress(10, 20, 0, 0);
						checkPosisiID();
					} else {
						pindahScore();
					}
				} else if (!hasilCakap.equalsIgnoreCase("")) {
					if (hasilCakap.equalsIgnoreCase(speakSoal.getText()
							.toString())) {
						score += 10;
						imgSpeakStatus
								.setBackgroundResource(R.drawable.img_answer_true);
						soundBenar.start();
						updateProgress(0, 0, 0, 85);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						imgSpeakStatus
								.setBackgroundResource(R.drawable.img_answer_false);
						soundSalah.start();
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (idnya < dh.countRowMP("gambar")) {
						hasilCakap = "";
						checkPosisiID();
					} else {
						pindahScore();
					}
				} else if (statusHoren) {
					if (idnya < dh.countRowMP("gambar")) {
						updateProgress(0, 20, 10, 0);
						checkPosisiID();
					} else {

					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Noch keine Antwort gew�hlt", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tipe_pertama, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setQuestionView(String tipe) {
		if (tipe.equalsIgnoreCase("gambar")) {
			// tambah jawaban
			jawabannya.add(currentMQM.getJawabA());
			jawabannya.add(currentMQM.getJawabB());
			jawabannya.add(currentMQM.getJawabC());
			jawabannya.add(currentMQM.getJawabD());

			// masukkan dalam adapter
			cgAdapter = new ChoiceGambarAdapter(getApplicationContext(),
					jawabannya);

			// mematikan tampilan
			imgSoal.setVisibility(View.INVISIBLE);
			garisPembatas.setVisibility(View.INVISIBLE);
			textHint.setVisibility(View.INVISIBLE);
			groupTF.setVisibility(View.INVISIBLE);
			listjawabanb.setVisibility(View.INVISIBLE);
			textJawabanScrabble.setVisibility(View.INVISIBLE);
			layoutScrabbleText.setVisibility(View.INVISIBLE);

			// membuka tampilan
			RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			rp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			rp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
			rp.setMargins(0, 10, 0, 10);
			textSoal.setLayoutParams(rp);
			textSoal.setTextColor(getResources().getColor(R.color.hoverColor));
			textSoal.setVisibility(View.VISIBLE);
			textSoal.setText(currentMQM.getSoal());
			listJawaban.setVisibility(View.VISIBLE);
			textLevel.setText("Stufe " + (idnya + 1));
			RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			p.addRule(RelativeLayout.BELOW, R.id.textSoal);
			p.setMargins(0, 10, 0, 10);
			listJawaban.setLayoutParams(p);
			listJawaban.setAdapter(cgAdapter);
		} else if (tipe.equalsIgnoreCase("text")) {
			// add jawaban
			jawabannya.add(currentMQMT.getJawabA());
			jawabannya.add(currentMQMT.getJawabB());
			jawabannya.add(currentMQMT.getJawabC());
			jawabannya.add(currentMQMT.getJawabD());

			// yang dihilangkan
			garisPembatas.setVisibility(View.INVISIBLE);
			textHint.setVisibility(View.INVISIBLE);
			groupTF.setVisibility(View.INVISIBLE);
			textSoal.setVisibility(View.INVISIBLE);
			listjawabanb.setVisibility(View.INVISIBLE);
			textJawabanScrabble.setVisibility(View.INVISIBLE);
			layoutScrabbleText.setVisibility(View.INVISIBLE);

			// yang ditampilkan
			imgSoal.setVisibility(View.VISIBLE);
			listJawaban.setVisibility(View.VISIBLE);
			imgSoal.setBackgroundResource(getResources().getIdentifier(
					currentMQMT.getSoal(), "drawable", getPackageName()));
			textLevel.setText("Stufe " + (idnya + 1));
			RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			p.addRule(RelativeLayout.BELOW, R.id.imgSoal);
			p.setMargins(0, 20, 0, 10);
			listJawaban.setLayoutParams(p);
			listJawaban.setAdapter(new ChoiceTextAdapter(
					getApplicationContext(), jawabannya));
		} else if (tipe.equalsIgnoreCase("truefalse")) {
			// yang dihilangkan
			listJawaban.setVisibility(View.INVISIBLE);
			listjawabanb.setVisibility(View.INVISIBLE);
			textJawabanScrabble.setVisibility(View.INVISIBLE);
			layoutScrabbleText.setVisibility(View.INVISIBLE);

			// yang ditampilkan
			imgSoal.setVisibility(View.VISIBLE);
			textSoal.setVisibility(View.VISIBLE);
			textSoal.setTextColor(Color.BLACK);
			RelativeLayout.LayoutParams txSoal = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			txSoal.addRule(RelativeLayout.BELOW, R.id.imgSoal);
			txSoal.addRule(RelativeLayout.CENTER_HORIZONTAL,
					RelativeLayout.TRUE);
			txSoal.setMargins(0, 10, 0, 10);
			textSoal.setLayoutParams(txSoal);
			imgSoal.setBackgroundResource(getResources().getIdentifier(
					currentTF.getGambar(), "drawable", getPackageName()));
			textSoal.setText(currentTF.getSoal());
			garisPembatas.setVisibility(View.VISIBLE);
			textHint.setVisibility(View.VISIBLE);
			groupTF.setVisibility(View.VISIBLE);
			textLevel.setText("Stufe " + (idnya + 1));
			groupTF.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub
					RadioButton rb = (RadioButton) groupTF
							.findViewById(checkedId);
					idTFnya = groupTF.indexOfChild(rb);
				}
			});

		} else if (tipe.equalsIgnoreCase("horen")) {
			// yang dihilangkan
			imgSoal.setVisibility(View.INVISIBLE);
			listJawaban.setVisibility(View.INVISIBLE);
			textJawabanScrabble.setVisibility(View.INVISIBLE);
			layoutScrabbleText.setVisibility(View.INVISIBLE);
			textHint.setVisibility(View.INVISIBLE);

			// yang ditampilkan
			RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			rp.addRule(RelativeLayout.BELOW, R.id.toolbar);
			rp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
			rp.setMargins(0, 10, 0, 10);
			textSoal.setLayoutParams(rp);
			textSoal.setText("Schreib was du h�rst unten ?");
			textSoal.setTextColor(Color.BLACK);
			textSoal.setVisibility(View.VISIBLE);
			RelativeLayout.LayoutParams rps = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			rps.addRule(RelativeLayout.BELOW, R.id.textSoal);
			rps.setMargins(0, 10, 0, 5);
			garisPembatas.setLayoutParams(rps);
			garisPembatas.setVisibility(View.VISIBLE);
			listjawabanb.setVisibility(View.VISIBLE);
			textLevel.setText("Stufe " + (idnya + 1));
			groupTF.setVisibility(View.INVISIBLE);

			// ini buat controller speak dan check nya
			ImageButton btnPlayHoren1 = (ImageButton) findViewById(R.id.btnPlayHoren1);
			ImageButton btnPlayHoren2 = (ImageButton) findViewById(R.id.btnPlayHoren2);
			ImageButton btnPlayHoren3 = (ImageButton) findViewById(R.id.btnPlayHoren3);
			final EditText txtHoren1 = (EditText) findViewById(R.id.textHorenJawab1);
			final EditText txtHoren2 = (EditText) findViewById(R.id.textHorenJawab2);
			final EditText txtHoren3 = (EditText) findViewById(R.id.textHorenJawab3);
			final ImageView imgHoren1 = (ImageView) findViewById(R.id.imgHorenStatus1);
			final ImageView imgHoren2 = (ImageView) findViewById(R.id.imgHorenStatus2);
			final ImageView imgHoren3 = (ImageView) findViewById(R.id.imgHorenStatus3);

			khususHoren(btnPlayHoren1, txtHoren1, imgHoren1);
			khususHoren(btnPlayHoren2, txtHoren2, imgHoren2);
			khususHoren(btnPlayHoren3, txtHoren3, imgHoren3);

		} else if (tipe.equalsIgnoreCase("speak")) {
			// yang dihilangkan
			imgSoal.setVisibility(View.INVISIBLE);
			groupTF.setVisibility(View.INVISIBLE);
			listjawabanb.setVisibility(View.INVISIBLE);
			listJawaban.setVisibility(View.INVISIBLE);
			textJawabanScrabble.setVisibility(View.INVISIBLE);
			layoutScrabbleText.setVisibility(View.INVISIBLE);

			// yang ditampilkan
			RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			rp.addRule(RelativeLayout.BELOW, R.id.toolbar);
			rp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
			rp.setMargins(0, 10, 0, 10);
			textSoal.setLayoutParams(rp);
			textSoal.setText("Schreib was du h�rst unten ?");
			textSoal.setTextColor(Color.BLACK);
			textSoal.setVisibility(View.VISIBLE);
			garisPembatas.setVisibility(View.VISIBLE);
			btnSpeak.setVisibility(View.VISIBLE);
			bagianSpeak.setVisibility(View.VISIBLE);
			textLevel.setText("Stufe " + (idnya + 1));
			btnSpeak.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					promptSpeechInput();
				}
			});
		} else if (tipe.equalsIgnoreCase("scrabble")) {
			// yang di hilangkan
			textSoal.setVisibility(View.INVISIBLE);
			groupTF.setVisibility(View.INVISIBLE);
			listJawaban.setVisibility(View.INVISIBLE);
			listjawabanb.setVisibility(View.INVISIBLE);
			bagianSpeak.setVisibility(View.INVISIBLE);
			btnSpeak.setVisibility(View.INVISIBLE);

			// yang ditampilkan
			imgSoal.setVisibility(View.VISIBLE);
			imgSoal.setBackgroundResource(R.drawable.img_sport_3);
			RelativeLayout.LayoutParams ps = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			ps.addRule(RelativeLayout.BELOW, R.id.imgSoal);
			ps.setMargins(0, 5, 0, 0);
			garisPembatas.setLayoutParams(ps);
			garisPembatas.setVisibility(View.VISIBLE);
			textHint.setVisibility(View.VISIBLE);
			textHint.setText("ordnen Sie die W�rter mit passenden Bilder unten ?");
			textJawabanScrabble.setVisibility(View.VISIBLE);
			layoutScrabbleText.setVisibility(View.VISIBLE);
			layoutScrabbleText.setGravity(Gravity.CENTER);
			textLevel.setText("Stufe " + (idnya + 1));
			String[] cobalagi = { "S", "c", "z", "a", "w", "h", "m" };
			final TextView tv[] = new TextView[cobalagi.length];

			for (int i = 0; i < cobalagi.length; i++) {
				LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);
				tp.setMargins(5, 5, 5, 5);
				tv[i] = new TextView(this);
				tv[i].setText(cobalagi[i]);
				tv[i].setId(i);
				tv[i].setPadding(15, 5, 15, 8);
				tv[i].setLayoutParams(tp);
				tv[i].setTextSize(16f);
				tv[i].setBackgroundResource(R.drawable.bg_text_tutul);
				tv[i].setTextColor(Color.WHITE);
				layoutScrabbleText.addView(tv[i]);
				final String textz = tv[i].getText().toString();
				tv[i].setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String sebelumnya = textJawabanScrabble.getText()
								.toString();
						sebelumnya += textz;
						textJawabanScrabble.setText(sebelumnya);
					}
				});
			}
		}
		idnya++;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitByBackKey();

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	protected void exitByBackKey() {
		new AlertDialog.Builder(this)
				.setMessage("Geben Sie auf ?")
				.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

					// do something when the button is clicked
					public void onClick(DialogInterface arg0, int arg1) {
						Intent i = new Intent(TipePertama.this,
								ScoreActivity.class);
						i.putExtra("SCORE", score);
						i.putExtra("TIME",
								textMenit.getText() + "" + textDetik.getText());
						startActivity(i);
						finish();
					}
				})
				.setNegativeButton("Nein",
						new DialogInterface.OnClickListener() {

							// do something when the button is clicked
							public void onClick(DialogInterface arg0, int arg1) {
							}
						}).show();

	}

	private Runnable updateTimerMethod = new Runnable() {

		public void run() {
			timeInMillies = SystemClock.uptimeMillis() - startTime;
			finalTime = timeSwap + timeInMillies;

			int seconds = (int) (finalTime / 1000);
			int minutes = seconds / 60;
			seconds = seconds % 60;
			if (minutes > 0) {
				textMenit.setText(String.format("%02d", minutes));
			}
			textDetik.setText(String.format("%02d", seconds));
			myHandler.postDelayed(this, 0);
		}

	};

	/**
	 * Showing google speech input dialog
	 * */
	private void promptSpeechInput() {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
				Locale.GERMANY.toString());
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
				getString(R.string.speech_prompt));
		try {
			startActivityForResult(intent, 100);
		} catch (ActivityNotFoundException a) {
			Toast.makeText(getApplicationContext(),
					getString(R.string.speech_not_supported),
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Receiving speech input
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 100: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> result = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				hasilCakap = result.get(0);
				TextView tx = (TextView) findViewById(R.id.yangDiomong);
				tx.setText(result.get(0));
			}
			break;
		}

		}
	}

	private void checkPosisiID() {
		if (idnya == 0 || idnya == 1 || idnya == 2) {
			setQuestionView("gambar");
		} else if (idnya == 3 || idnya == 4) {
			setQuestionView("text");
		} else if (idnya == 5 || idnya == 6) {
			setQuestionView("truefalse");
		} else if (idnya == 7) {
			setQuestionView("scrabble");
		} else if (idnya == 8) {
			setQuestionView("horen");
		} else if (idnya == 9) {
			setQuestionView("speak");
		}
	}

	private void pindahScore() {
		Intent i = new Intent(TipePertama.this, ScoreActivity.class);
		i.putExtra("SCORE", score);
		i.putExtra("TIME", textMenit.getText() + "" + textDetik.getText());
		timeSwap += timeInMillies;
		myHandler.removeCallbacks(updateTimerMethod);
		startActivity(i);
		finish();
	}

	private void khususHoren(ImageButton btnPlay, final EditText textHoren,
			final ImageView imgHoren) {
		btnPlay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"Sound not found , the answer is : SportSpas",
						Toast.LENGTH_SHORT).show();
			}
		});

		textHoren
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {

					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						// TODO Auto-generated method stub
						if (actionId == EditorInfo.IME_ACTION_SEARCH
								|| actionId == EditorInfo.IME_ACTION_DONE
								|| event.getAction() == KeyEvent.ACTION_DOWN
								&& event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
							statusHoren = true;
							if (textHoren.getText().toString()
									.equalsIgnoreCase("SportSpas")) {
								score += 4;
								imgHoren.setBackgroundResource(R.drawable.img_answer_true);
								soundBenar.start();

								updateProgress(0, 10, 30, 0);
							} else {
								imgHoren.setBackgroundResource(R.drawable.img_answer_false);
								soundSalah.start();
							}

							textHoren.setEnabled(false);
						}
						return false;
					}
				});
	}
	
	private void updateProgress(int read,int write,int listen,int speak){
		ProgressModel pm = dh.getProgress(1);
		read += pm.getRead();
		write += pm.getWrite();
		listen += pm.getListen();
		speak += pm.getSpeak();
		dh.updateProgress(new ProgressModel(1,read, write, listen, speak));
	}

}
