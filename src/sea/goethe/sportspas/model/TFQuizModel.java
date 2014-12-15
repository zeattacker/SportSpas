package sea.goethe.sportspas.model;

public class TFQuizModel {
	int id,jawaban;
	String gambar,soal;
	
	public TFQuizModel(){
		//constructor
	}
	
	public TFQuizModel(int id,String gambar,String soal,int jawaban){
		this.id = id;
		this.gambar = gambar;
		this.soal = soal;
		this.jawaban = jawaban;
	}
	
	public TFQuizModel(String gambar,String soal,int jawaban){
		this.gambar = gambar;
		this.soal = soal;
		this.jawaban = jawaban;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setGambar(String gambar){
		this.gambar = gambar;
	}
	
	public String getGambar(){
		return this.gambar;
	}
	
	public void setSoal(String soal){
		this.soal = soal;
	}
	
	public String getSoal(){
		return this.soal;
	}
	
	public void setJawaban(int jawaban){
		this.jawaban = jawaban;
	}
	
	public int getJawaban(){
		return this.jawaban;
	}
}
