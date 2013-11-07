package mundo;

import mundo.posicionaveis.Posicionavel;
import mundo.posicionaveis.Robo;

public class Arena {
	private Posicionavel[] moveis;
	private int numRobos;
	private MapaHexagonal mapa;
	private Zeus zeus;

	public Arena() {
		moveis = new Posicionavel[100];
		mapa = new MapaHexagonal();
		zeus = new Zeus(mapa);
		mapa.inicializa();
	}

	public void atualiza(){
		zeus.atualiza();
	}
	
	public void moveCimaEsq(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI - 1, posJ - 1);
		hex.adiciona(movel);
	}

	public void moveEsquerda(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI, posJ - 1);
		hex.adiciona(movel);
	}

	public void moveBaixoEsq(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI + 1, posJ);
		hex.adiciona(movel);
	}

	public void moveCimaDir(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI - 1, posJ);
		hex.adiciona(movel);
	}

	public void moveDireita(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI,posJ + 1);
		hex.adiciona(movel);
	}

	public void moveBaixoDir(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI + 1,posJ + 1);
		hex.adiciona(movel);
	}
	
	public void insereExercito(){
		moveis[0] = new Robo(1,1);
		mapa.setNovoRobo((Robo)moveis[0]);
	}

}
