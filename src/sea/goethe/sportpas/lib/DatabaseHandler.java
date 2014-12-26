package sea.goethe.sportpas.lib;

import java.util.ArrayList;
import java.util.List;

import sea.goethe.sportspas.model.LearnModel;
import sea.goethe.sportspas.model.MultipleQuizModel;
import sea.goethe.sportspas.model.ProgressModel;
import sea.goethe.sportspas.model.ScoreModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DatabaseHandler extends SQLiteOpenHelper {
	private static final int DB_VERSION = 1;

	private static final String DB_NAME = "sportspas_db";

	// KEY TABLE
	private static final String TBL_MULTIPLE_CHOICE = "multiple_choice";
	private static final String TBL_TRUEFALSE = "truefalse";
	private static final String TBL_SCORE = "score";
	private static final String TBL_HOREN = "horen";
	private static final String TBL_LEARN = "learn";
	private static final String TBL_PROGRESS = "progress";

	// KEY COLUMN
	private static final String KEY_ID = "id";
	private static final String KEY_JAWABA = "jawaban_a";
	private static final String KEY_JAWABB = "jawaban_b";
	private static final String KEY_JAWABC = "jawaban_c";
	private static final String KEY_JAWABD = "jawaban_d";
	private static final String KEY_BENAR = "benar";
	private static final String KEY_SOAL = "soal";
	private static final String KEY_POINT = "point";
	private static final String KEY_TIPE = "tipe";
	private static final String KEY_KATEGORI = "kategori";
	private static final String KEY_SCORE = "score";
	private static final String KEY_TGL = "tgl";
	private static final String KEY_TIME = "time";
	private static final String KEY_GAMBAR = "gambar";
	private static final String KEY_MUSIC = "music";
	private static final String KEY_KATA = "kata";
	private static final String KEY_KALIMAT = "kalimat";
	private static final String KEY_READ = "read";
	private static final String KEY_WRITE = "write";
	private static final String KEY_SPEAK = "speak";
	private static final String KEY_LISTEN = "listen";

	public DatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE_MP = "CREATE TABLE " + TBL_MULTIPLE_CHOICE + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_SOAL + " TEXT,"
				+ KEY_JAWABA + " TEXT," + KEY_JAWABB + " TEXT," + KEY_JAWABC
				+ " TEXT," + KEY_JAWABD + " TEXT," + KEY_BENAR + " INTEGER,"
				+ KEY_POINT + " TEXT," + KEY_TIPE + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_MP);

		String CREATE_TABLE_TF = "CREATE TABLE " + TBL_TRUEFALSE + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_GAMBAR + " TEXT," + KEY_SOAL
				+ " TEXT," + KEY_BENAR + " INTEGER" + ")";
		db.execSQL(CREATE_TABLE_TF);

		String CREATE_TABLE_HOREN = "CREATE TABLE " + TBL_HOREN + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_MUSIC + " TEXT," + KEY_BENAR
				+ " TEXT" + ")";
		db.execSQL(CREATE_TABLE_HOREN);

		String CREATE_TABLE_SCORE = "CREATE TABLE " + TBL_SCORE + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_SCORE + " INTEGER," + KEY_TGL
				+ " TEXT," + KEY_TIPE + " TEXT," + KEY_TIME + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_SCORE);

		String CREATE_TABLE_LEARN = "CREATE TABLE " + TBL_LEARN + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_KATA + " TEXT," + KEY_KALIMAT
				+ " TEXT," + KEY_GAMBAR + " TEXT," + KEY_MUSIC + " TEXT,"
				+ KEY_KATEGORI + " TEXT," + KEY_TIPE + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_LEARN);

		String CREATE_TABLE_PROGRESS = "CREATE TABLE " + TBL_PROGRESS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_READ + " INTEGER,"
				+ KEY_WRITE + " INTEGER," + KEY_LISTEN + " INTEGER,"
				+ KEY_SPEAK + " INTEGER" + ")";
		db.execSQL(CREATE_TABLE_PROGRESS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TBL_MULTIPLE_CHOICE);
		db.execSQL("DROP TABLE IF EXISTS " + TBL_TRUEFALSE);
		db.execSQL("DROP TABLE IF EXISTS " + TBL_HOREN);
		db.execSQL("DROP TABLE IF EXISTS " + TBL_SCORE);
		db.execSQL("DROP TABLE IF EXISTS " + TBL_LEARN);
		db.execSQL("DROP TABLE IF EXISTS " + TBL_PROGRESS);

		onCreate(db);
	}

	// Score DAO
	public void addScore(ScoreModel sm) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(KEY_SCORE, sm.getScore());
		cv.put(KEY_TIPE, sm.getTipe());
		cv.put(KEY_TGL, sm.getTGL());
		cv.put(KEY_TIME, sm.getTime());

		db.insert(TBL_SCORE, null, cv);
		db.close();
	}

	public void updateScore(ScoreModel sm) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(KEY_SCORE, sm.getScore());
		cv.put(KEY_TIPE, sm.getTipe());
		cv.put(KEY_TGL, sm.getTGL());
		cv.put(KEY_TIME, sm.getTime());

		db.update(TBL_SCORE, cv, KEY_ID + " = ?",
				new String[] { String.valueOf(sm.getID()) });
	}

	public ScoreModel getScore(int id, String tipe) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(KEY_SCORE, new String[] { KEY_ID, KEY_SCORE,
				KEY_TIPE, KEY_TGL, KEY_TIME }, KEY_ID + " = ?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		return new ScoreModel(cursor.getInt(cursor.getColumnIndex(KEY_ID)),
				cursor.getInt(cursor.getColumnIndex(KEY_SCORE)),
				cursor.getString(cursor.getColumnIndex(KEY_TGL)),
				cursor.getString(cursor.getColumnIndex(KEY_TIME)),
				cursor.getString(cursor.getColumnIndex(KEY_TIPE)));
	}

	public List<ScoreModel> getAllScore(String tipe) {
		ArrayList<ScoreModel> scoreList = new ArrayList<ScoreModel>();
		String selectQuery;
		// Select All Query
		if (tipe.isEmpty()) {
			selectQuery = "SELECT  * FROM " + TBL_SCORE;
		} else {
			selectQuery = "SELECT  * FROM " + TBL_SCORE + " WHERE " + KEY_TIPE
					+ " = '" + tipe + "'";
		}

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				ScoreModel sm = new ScoreModel();
				sm.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
				sm.setScore(cursor.getInt(cursor.getColumnIndex(KEY_SCORE)));
				sm.setTGL(cursor.getString(cursor.getColumnIndex(KEY_TGL)));
				sm.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
				sm.setTipe(cursor.getString(cursor.getColumnIndex(KEY_TIPE)));
				// Adding contact to list

				scoreList.add(sm);
			} while (cursor.moveToNext());
		}

		// return contact list
		return scoreList;
	}

	public int getMaxScore() {
		SQLiteDatabase db = this.getReadableDatabase();
		final SQLiteStatement stmt = db.compileStatement("SELECT MAX("
				+ KEY_SCORE + ") FROM " + TBL_SCORE);

		return (int) stmt.simpleQueryForLong();
	}

	public String getMinTime() {
		SQLiteDatabase db = this.getReadableDatabase();
		final SQLiteStatement stmt = db.compileStatement("SELECT MIN("
				+ KEY_TIME + ") FROM " + TBL_SCORE);

		return stmt.simpleQueryForString();
	}

	// Multiple Choice Quize DAO
	public void addMP(MultipleQuizModel mp) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(KEY_SOAL, mp.getSoal());
		cv.put(KEY_JAWABA, mp.getJawabA());
		cv.put(KEY_JAWABB, mp.getJawabB());
		cv.put(KEY_JAWABC, mp.getJawabC());
		cv.put(KEY_JAWABD, mp.getJawabD());
		cv.put(KEY_BENAR, mp.getBenar());
		cv.put(KEY_POINT, mp.getPoint());
		cv.put(KEY_TIPE, mp.getTipe());

		db.insert(TBL_MULTIPLE_CHOICE, null, cv);
		db.close();
	}

	public void updateMP(MultipleQuizModel mp) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(KEY_SOAL, mp.getSoal());
		cv.put(KEY_BENAR, mp.getBenar());
		cv.put(KEY_JAWABA, mp.getJawabA());
		cv.put(KEY_JAWABB, mp.getJawabB());
		cv.put(KEY_JAWABC, mp.getJawabC());
		cv.put(KEY_JAWABD, mp.getJawabD());
		cv.put(KEY_POINT, mp.getPoint());
		cv.put(KEY_TIPE, mp.getTipe());

		db.update(TBL_MULTIPLE_CHOICE, cv, KEY_ID + " = ?",
				new String[] { String.valueOf(mp.getID()) });
	}

	public List<MultipleQuizModel> getAllMP(String tipe) {
		ArrayList<MultipleQuizModel> mulQuizList = new ArrayList<MultipleQuizModel>();
		String selectQuery;
		// Select All Query
		if (tipe.isEmpty()) {
			selectQuery = "SELECT  * FROM " + TBL_MULTIPLE_CHOICE;
		} else {
			selectQuery = "SELECT  * FROM " + TBL_MULTIPLE_CHOICE + " WHERE "
					+ KEY_TIPE + " = '" + tipe + "'";
		}

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				MultipleQuizModel mulQuiz = new MultipleQuizModel();
				mulQuiz.setID(cursor.getInt(0));
				mulQuiz.setSoal(cursor.getString(1));
				mulQuiz.setJawabA(cursor.getString(2));
				mulQuiz.setJawabB(cursor.getString(3));
				mulQuiz.setJawabC(cursor.getString(4));
				mulQuiz.setJawabD(cursor.getString(5));
				mulQuiz.setBenar(cursor.getInt(6));
				mulQuiz.setPoint(cursor.getString(7));
				mulQuiz.setTipe(cursor.getString(8));
				// Adding contact to list

				mulQuizList.add(mulQuiz);
			} while (cursor.moveToNext());
		}

		// return contact list
		return mulQuizList;
	}

	public MultipleQuizModel getSingleRandomMP(String tipe) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_MULTIPLE_CHOICE
				+ " WHERE " + KEY_TIPE + " = '" + tipe
				+ "' ORDER BY RANDOM() LIMIT 1", null);

		if (cursor != null)
			cursor.moveToFirst();

		MultipleQuizModel mulQuiz = new MultipleQuizModel();
		mulQuiz.setID(cursor.getInt(0));
		mulQuiz.setSoal(cursor.getString(1));
		mulQuiz.setJawabA(cursor.getString(2));
		mulQuiz.setJawabB(cursor.getString(3));
		mulQuiz.setJawabC(cursor.getString(4));
		mulQuiz.setJawabD(cursor.getString(5));
		mulQuiz.setBenar(cursor.getInt(6));
		mulQuiz.setPoint(cursor.getString(7));
		mulQuiz.setTipe(cursor.getString(8));

		return mulQuiz;
	}

	public int countRowMP(String tipe) {
		int row = 0;
		String selectQuery = "SELECT  * FROM " + TBL_MULTIPLE_CHOICE
				+ " WHERE " + KEY_TIPE + " = '" + tipe + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row = cursor.getCount();
		return row;
	}

	// Learn DAO
	public void addLearn(LearnModel lm) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(KEY_KATA, lm.getKata());
		cv.put(KEY_KALIMAT, lm.getKalimat());
		cv.put(KEY_GAMBAR, lm.getGambar());
		cv.put(KEY_MUSIC, lm.getMusic());
		cv.put(KEY_KATEGORI, lm.getKategori());
		cv.put(KEY_TIPE, lm.getTipe());

		db.insert(TBL_LEARN, null, cv);
		db.close();
	}

	public void updateLearn(LearnModel lm) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(KEY_KATA, lm.getKata());
		cv.put(KEY_KALIMAT, lm.getKalimat());
		cv.put(KEY_GAMBAR, lm.getGambar());
		cv.put(KEY_MUSIC, lm.getMusic());
		cv.put(KEY_KATEGORI, lm.getKategori());
		cv.put(KEY_TIPE, lm.getTipe());

		db.update(TBL_LEARN, cv, KEY_ID + " = ?",
				new String[] { String.valueOf(lm.getID()) });
	}

	public List<LearnModel> getAllLearn(String kategori, String tipe) {
		ArrayList<LearnModel> learnList = new ArrayList<LearnModel>();
		String selectQuery = "";

		if (kategori.equalsIgnoreCase("") && tipe.equalsIgnoreCase("")) {
			selectQuery = "SELECT  * FROM " + TBL_LEARN;
		} else {
			selectQuery = "SELECT * FROM " + TBL_LEARN + " WHERE "
					+ KEY_KATEGORI + " = '" + kategori + "' AND " + KEY_TIPE
					+ " = '" + tipe + "'";
		}

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				LearnModel learn = new LearnModel();
				learn.setID(cursor.getInt(0));
				learn.setKata(cursor.getString(1));
				learn.setKalimat(cursor.getString(2));
				learn.setGambar(cursor.getString(3));
				learn.setMusic(cursor.getString(4));
				learn.setKategori(cursor.getString(5));
				learn.setTipe(cursor.getString(6));
				// Adding contact to list

				learnList.add(learn);
			} while (cursor.moveToNext());
		}

		// return contact list
		return learnList;
	}
	
	//Progress DAO
	public void addProgress(ProgressModel pm) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(KEY_READ, pm.getRead());
		cv.put(KEY_WRITE, pm.getWrite());
		cv.put(KEY_LISTEN, pm.getListen());
		cv.put(KEY_SPEAK, pm.getSpeak());

		db.insert(TBL_PROGRESS, null, cv);
		db.close();
	}

	public void updateProgress(ProgressModel pm) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(KEY_READ, pm.getRead());
		cv.put(KEY_WRITE, pm.getWrite());
		cv.put(KEY_LISTEN, pm.getListen());
		cv.put(KEY_SPEAK, pm.getSpeak());

		db.update(TBL_PROGRESS, cv, KEY_ID + " = ?",
				new String[] { String.valueOf(pm.getID()) });
	}
	
	public ProgressModel getProgress(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TBL_PROGRESS, new String[] { KEY_ID, KEY_READ,
				KEY_WRITE, KEY_LISTEN, KEY_SPEAK }, KEY_ID + " = ?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		return new ProgressModel(cursor.getInt(0), cursor.getInt(cursor.getColumnIndex(KEY_READ)), cursor.getInt(cursor.getColumnIndex(KEY_WRITE)), cursor.getInt(cursor.getColumnIndex(KEY_LISTEN)), cursor.getInt(cursor.getColumnIndex(KEY_SPEAK)));
	}
	
}
