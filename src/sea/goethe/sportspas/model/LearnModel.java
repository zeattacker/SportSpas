package sea.goethe.sportspas.model;

public class LearnModel {
	int id;
	String kata,kalimat,gambar,music,kategori,tipe;
	
	public LearnModel(){
		//constructor
	}
	
	public LearnModel(int id,String kata,String kalimat,String gambar,String music,String kategori,String tipe){
		this.id = id;
		this.kata = kata;
		this.kalimat = kalimat;
		this.gambar = gambar;
		this.music = music;
		this.kategori = kategori;
	}
	
	public LearnModel(String kata,String kalimat,String gambar,String music,String kategori,String tipe){
		this.kata = kata;
		this.kalimat = kalimat;
		this.gambar = gambar;
		this.music = music;
		this.kategori = kategori;
		this.tipe = tipe;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setKata(String kata){
		this.kata = kata;
	}
	
	public String getKata(){
		return this.kata;
	}
	
	public void setKalimat(String kalimat){
		this.kalimat = kalimat;
	}
	
	public String getKalimat(){
		return this.kalimat;
	}
	
	public void setGambar(String gambar){
		this.gambar = gambar;
	}
	
	public String getGambar(){
		return this.gambar;
	}
	
	public void setMusic(String music){
		this.music = music;
	}
	
	public String getMusic(){
		return this.music;
	}
	
	public void setKategori(String kategori){
		this.kategori = kategori;
	}
	
	public String getKategori(){
		return this.kategori;
	}
	
	public void setTipe(String tipe){
		this.tipe = tipe;
	}
	
	public String getTipe(){
		return this.tipe;
	}
}
