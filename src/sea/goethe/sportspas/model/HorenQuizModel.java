package sea.goethe.sportspas.model;

public class HorenQuizModel {
	int id;
	String music,jawaban;
	
	public HorenQuizModel(){
		//blank contstructor
	}
	
	public HorenQuizModel(int id,String music,String jawaban){
		this.id = id;
		this.music = music;
		this.jawaban = jawaban;
	}
	
	public HorenQuizModel(String music,String jawaban){
		this.music = music;
		this.jawaban = jawaban;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public String getMusic(){
		return this.music;
	}
	
	public void setMusic(String music){
		this.music = music;
	}
	
	public String getJawaban(){
		return this.jawaban;
	}
	
	public void setJawaban(String jawaban){
		this.jawaban = jawaban;
	}
}
