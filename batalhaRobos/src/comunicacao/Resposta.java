package comunicacao;

public abstract class Resposta {
	int id;
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public abstract String responde();
}
