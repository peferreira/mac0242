package controle;

import mundo.Arena;
import mv.MaquinaVirtual;
import mv.Operacao;

public class Gerenciador {

	private MaquinaVirtual[] mvs;
	private Arena arena;

	public Gerenciador() {
		arena = new Arena();
		mvs = new MaquinaVirtual[1];
	}

	void init() {
		arena.insereExercito();
		novasMaquinasVirtuais(1);
	}

	void novasMaquinasVirtuais(int n) {
		for (int i = 0; i < n; i++) {
			mvs[i] = new MaquinaVirtual();
		}
	}

	void executaVM() {
		for (int i = 0; i < mvs.length; i++) {
			mvs[i].passo();
		}
		processaOperacoes();
		arena.atualiza();
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
		gerenciador.init();
		for (int i = 0; i < 4; i++) {
			gerenciador.executaVM();
		}

	}

}
