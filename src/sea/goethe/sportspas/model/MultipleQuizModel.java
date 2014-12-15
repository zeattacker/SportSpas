package sea.goethe.sportspas.model;

public class MultipleQuizModel {
	int id,benar;
	String soal,point,jawaba,jawabb,jawabc,jawabd,tipe;
	
	public MultipleQuizModel(){
		//constructor
	}
	
	public MultipleQuizModel(int id,String soal,String point,String jawaba,String jawabb,String jawabc,String jawabd,int benar,String tipe){
		this.id = id;
		this.soal = soal;
		this.point = point;
		this.jawaba = jawaba;
		this.jawabb = jawabb;
		this.jawabc = jawabc;
		this.jawabd = jawabd;
		this.benar = benar;
		this.tipe = tipe;
	}
	
	public MultipleQuizModel(String soal,String point,String jawaba,String jawabb,String jawabc,String jawabd,int benar,String tipe){
		this.soal = soal;
		this.point = point;
		this.jawaba = jawaba;
		this.jawabb = jawabb;
		this.jawabc = jawabc;
		this.jawabd = jawabd;
		this.benar = benar;
		this.tipe = tipe;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public String getSoal(){
		return this.soal;
	}
	
	public void setSoal(String soal){
		this.soal = soal;
	}
	
	public String getPoint(){
		return this.point;
	}
	
	public void setPoint(String point){
		this.point = point;
	}
	
	public String getJawabA(){
		return this.jawaba;
	}
	
	public void setJawabA(String jawaba){
		this.jawaba = jawaba;
	}
	
	public String getJawabB(){
		return this.jawabb;
	}
	
	public void setJawabB(String jawabb){
		this.jawabb = jawabb;
	}
	
	public String getJawabC(){
		return this.jawabc;
	}
	
	public void setJawabC(String jawabc){
		this.jawabc = jawabc;
	}
	
	public String getJawabD(){
		return this.jawabd;
	}
	
	public void setJawabD(String jawabd){
		this.jawabd = jawabd;
	}
	
	public int getBenar(){
		return this.benar;
	}
	
	public void setBenar(int benar){
		this.benar = benar;
	}
	
	public String getTipe(){
		return this.tipe;
	}
	
	public void setTipe(String tipe){
		this.tipe = tipe;
	}
}
