package sea.goethe.sportspas.model;

public class ProgressModel {
	int id,speak,listen,read,write;
	
	public ProgressModel(){
		//blank constructor
	}
	
	public ProgressModel(int id,int read,int write,int listen,int speak){
		this.id = id;
		this.speak = speak;
		this.listen = listen;
		this.read = read;
		this.write = write;
	}
	
	public ProgressModel(int read,int write,int listen,int speak){
		this.speak = speak;
		this.listen = listen;
		this.read = read;
		this.write = write;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setSpeak(int speak){
		this.speak = speak;
	}
	
	public int getSpeak(){
		return this.speak;
	}
	
	public void setListen(int listen){
		this.listen = listen;
	}
	
	public int getListen(){
		return this.listen;
	}
	
	public void setRead(int read){
		this.read = read;
	}
	
	public int getRead(){
		return this.read;
	}
	
	public void setWrite(int write){
		this.write = write;
	}
	
	public int getWrite(){
		return this.write;
	}
}
