package sea.goethe.sportspas.model;

public class ScoreModel {
	int id,score;
	String tipe,waktu,tgl;
	
	public ScoreModel(){
		//blank constructur
	}
	
	public ScoreModel(int id,int score,String tgl,String waktu,String tipe){
		this.id = id;
		this.score = score;
		this.tgl = tgl;
		this.waktu = waktu;
		this.tipe = tipe;
	}
	
	public ScoreModel(int score,String tgl,String waktu,String tipe){
		this.score = score;
		this.tgl = tgl;
		this.waktu = waktu;
		this.tipe = tipe;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void setTipe(String tipe){
		this.tipe = tipe;
	}
	
	public String getTipe(){
		return this.tipe;
	}
	
	public String getTGL(){
		return this.tgl;
	}
	
	public void setTGL(String tgl){
		this.tgl = tgl;
	}
	
	public String getTime(){
		return this.waktu;
	}
	
	public void setTime(String waktu){
		this.waktu = waktu;
	}
}
