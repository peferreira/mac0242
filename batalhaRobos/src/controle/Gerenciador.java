package controle;

import java.util.LinkedList;
import java.util.List;

import comunicacao.Resposta;

import mundo.Arena;
import mv.MaquinaVirtual;
import mv.Operacao;
import mundo.posicionaveis.Posicionavel;

public class Gerenciador {

	private MaquinaVirtual[] mvs;
	private Arena arena;
	private int[] turnos;
	public Gerenciador() {
		arena = new Arena();
		
	}

	void inicializa() {
		arena.inicializa();
		novasMaquinasVirtuais(arena.getMoveis());
	}

	void novasMaquinasVirtuais(Posicionavel[] moveis) {
		mvs = new MaquinaVirtual[moveis.length];
		turnos = new int[moveis.length];
		
		for (int i = 0; i < moveis.length; i++) {
			mvs[i] = new MaquinaVirtual();
		}
	}

	void executaVM() {
		for (int i = 0; i < mvs.length; i++) {
			if(turnos[i] == 0)
				mvs[i].passo();
			else if(turnos[i] > 0)
				turnos[i]--;
		}
		processaOperacoes();
		arena.atualiza();
		processaRespostas();
	}

	void processaRespostas(){
		LinkedList<Resposta> respostas = arena.getRespostas();
		for(Resposta r: respostas){
			System.out.println(r.responde());
		}
		if (respostas != null){
			arena.apagaRespostas();
		}
	}
	
	
	void processaOperacoes() {
		Operacao op;
		for (int i = 0; i < mvs.length; i++) {
			op = mvs[i].getOperacao();
			if (op != null) {
				switch (op.getCodigo()) {
				case 1:
					processaMovimento(op, i);
					break;
				case 2:
					// processaAtaque(op)
					break;
				case 3:
					// processaRecolha(op)
					break;
				case 4:
					// processaDeposito(op)
					break;
				default:
					System.out.println("Operacao inválida!");
					break;
				}
			}
		}
	}

	void processaMovimento(Operacao op, int idvm) {
		switch (op.getArgumento()) {
		case "NW":
			arena.moveCimaEsq(idvm);
			break;
		case "W":
			arena.moveEsquerda(idvm);
			break;
		case "SW":
			arena.moveBaixoEsq(idvm);
			break;
		case "SE":
			arena.moveBaixoDir(idvm);
			break;
		case "E":
			arena.moveDireita(idvm);
			break;
		case "NE":
			arena.moveCimaDir(idvm);
			break;
		default:
			System.out.println("Direcao inválida!");
			break;
		}
	}

	public static void main(String[] args) {
		Gerenciador gerenciador = new Gerenciador();
		gerenciador.inicializa();
		for (int i = 0; i < 4; i++) {
			gerenciador.executaVM();
		}

	}

}
