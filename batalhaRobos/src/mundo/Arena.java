package mundo;

import mundo.posicionaveis.Posicionavel;

public class Arena {
	private Posicionavel[] moveis;
	private int numRobos;
	private MapaHexagonal mapa;
	private Zeus zeus;

	Arena() {
		moveis = new Posicionavel[100];
		mapa = new MapaHexagonal();
		zeus = new Zeus(mapa);
	}

	void atualiza(){
		zeus.atualiza();
	}
	
	void moveCimaEsq(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI + 1, posJ - 1);
		hex.adiciona(movel);
	}

	void moveEsquerda(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI, posJ - 1);
		hex.adiciona(movel);
	}

	void moveBaixoEsq(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI - 1, posJ - 1);
		hex.adiciona(movel);
	}

	void moveCimaDir(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI + 1, posJ);
		hex.adiciona(movel);
	}

	void moveDireita(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI,posJ + 1);
		hex.adiciona(movel);
	}

	void moveBaixoDir(int movelID) {
		int posI, posJ;
		Posicionavel movel = moveis[movelID];
		posI = movel.getI();
		posJ = movel.getJ();
		Hexagono hex = mapa.getHexagono(posI - 1,posJ);
		hex.adiciona(movel);
	}

}
