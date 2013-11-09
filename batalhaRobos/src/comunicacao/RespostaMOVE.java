package comunicacao;

public class RespostaMOVE extends Resposta {
	boolean move;
	public RespostaMOVE(boolean move, int id) {
		super.id = id;
		this.move = move;
	}
	public String responde(){
		if (move)
			return "robo" + super.id + ":movimento realizado com sucesso";
		else
			return "robo" + id + ":movimento não realizado";
	}
	
		
}
