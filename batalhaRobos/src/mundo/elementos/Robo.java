package mundo.elementos;

public class Robo {

	private int posI;
	private int posJ;
	private int equipe;
	private int id;
	private int vidas;
	
	public Robo(int id) {
		this.id = id;
	}
	
	public void setPosI(int posI) {
		this.posI = posI;
	}

	public void setPosJ(int posJ) {
		this.posJ = posJ;
	}

	public int getPosI() {
		return posI;
	}

	public int getPosJ() {
		return posJ;
	}
	
	public int getVidas() {
		return vidas;
	}

	public void alteraPosicao(int posI, int posJ) {
		this.posI = posI;
		this.posJ = posJ;
	}
	
	public int getEquipe(){
		return equipe;
	}
	
	public void setEquipe(int equipe){
		this.equipe = equipe;
	}
	
	public int getID(){
		return id;
	}

}