package controle;

import java.util.LinkedList;
import comunicacao.Resposta;
import mundo.Arena;
import mv.MaquinaVirtual;
import mv.Operacao;

public class Gerenciador {

	private int numMaquinasVirtuais;
	private MaquinaVirtual[] mvs;
	private Arena arena;
	private int[] turnos;

	Gerenciador(int numMV, int maxX, int maxY) {
		numMaquinasVirtuais = numMV;
		arena = new Arena(numMV, maxX, maxY);
	}

	public void inicializa() {
		novasMaquinasVirtuais();
		arena.inicializa();
		arena.initGraphics();
		arena.insereExercito();

	}

	private void novasMaquinasVirtuais() {
		mvs = new MaquinaVirtual[numMaquinasVirtuais];
		turnos = new int[numMaquinasVirtuais];

		for (int i = 0; i < numMaquinasVirtuais; i++) {
			mvs[i] = new MaquinaVirtual();
		}
	}

	public void executaMV() {
		arena.draw();
		for (int i = 0; i < mvs.length; i++) {
			if (turnos[i] == 0)
				mvs[i].passo();
			else if (turnos[i] > 0)
				turnos[i]--;
		}

		// Apaga as respostas processadas anteriormente
		arena.apagaRespostas();
		processaOperacoes();
		arena.atualiza();
		processaRespostas(arena.getRespostas());
	}

	private void processaRespostas(LinkedList<Resposta> respostas) {
		MaquinaVirtual mv;
		for (Resposta r : respostas) {
			mv = mvs[r.getDestinatario()];
			mv.setResposta(r.responde());
		}

	}

	private void processaOperacoes() {
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

	private void processaMovimento(Operacao op, int idvm) {
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
		Gerenciador g = new Gerenciador(1, 20, 20);

		g.inicializa();
		while (true) {
			g.executaMV();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}

	}

}
