package mundo;

import java.util.LinkedList;
import java.util.List;

import comunicacao.Resposta;

import mundo.posicionaveis.Posicionavel;
import mundo.posicionaveis.Robo;

public class Arena {
	private Posicionavel[] moveis;
	private int numRobos;
	private MapaHexagonal mapa;
	private Zeus zeus;
	private LinkedList<Resposta> respostas;

	public Arena() {
		moveis = new Posicionavel[100];
		mapa = new MapaHexagonal();
		this.respostas = new LinkedList<Resposta>();
		zeus = new Zeus(mapa , respostas);
		mapa.inicializa();
	}

	public LinkedList<Resposta> getRespostas(){
		return respostas;
	}
	public void apagaRespostas(){
		respostas.clear();
	}
	public void atualiza(){
		zeus.atualiza();
	}
	
	public void inicializa(){
		FabricaRobos fr = new FabricaRobos();
		fr.criaRobos();
		moveis = fr.getRobos();
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
	
	/*public void insereExercito(){
		moveis[0] = new Robo(1,1);
		mapa.setNovoRobo((Robo)moveis[0]);
		moveis[1] = new Robo(1,1);
		mapa.setNovoRobo((Robo)moveis[1]);

	}*/
	
	
	public Posicionavel[] getMoveis() {
		return moveis;
	}

}
