package mundo.posicionaveis;

public class Robo implements Posicionavel {

	private int posI;
	private int posJ;
	int equipe;
	int idRobo;
	public Robo(int posI, int posJ, int equipe, int idRobo) {
		this.posI = posI;
		this.posJ = posJ;
		this.idRobo = idRobo;
	}

	public int getI() {
		return posI;
	}

	public int getJ() {
		return posJ;
	}

	public void alteraPosicao(int posI, int posJ) {
		this.posI = posI;
		this.posJ = posJ;
	}
	
	public int getEquipe(){
		return equipe;
	}
	
	public int getID(){
		return idRobo;
	}

}