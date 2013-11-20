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

	public Gerenciador(int numMV, int numB) {
		if ((numMV % 24) == 0 && (numB <= 4 && numB > 0)) {
			numMaquinasVirtuais = numMV;
			turnos = new int[numMaquinasVirtuais];
			mvs = new MaquinaVirtual[numMaquinasVirtuais];
			arena = new Arena(numMV, numB, turnos);
		} else if ((numMV % 24) == 0) {
			System.out
					.println("O número de maquinas virtuais (ou robos) por arena tem que ser multiplo de 24!");
			System.exit(1);
		} else {
			System.out
					.println("O número de bases por arena tem que pertencer ao intervalo [1,4]!");
			System.exit(1);
		}
	}

	// Para o teste com um robo e um cristal
	public Gerenciador() {
		numMaquinasVirtuais = 20;
		turnos = new int[numMaquinasVirtuais];
		mvs = new MaquinaVirtual[numMaquinasVirtuais];
		arena = new Arena(20, 4, turnos);
	}

	public void inicializa() {
		for (int i = 0; i < numMaquinasVirtuais; i++) {
			mvs[i] = new MaquinaVirtual();
		}
		arena.inicializa();
		arena.initGraphics();
		// Para o teste com um robo e um cristal
		arena.insereBases();
		arena.insereExercitos();
		arena.insereCristais(28);
		//arena.insereUmaBase();
		//arena.insereUmRobo();
		//arena.insereUmCristal();
	}

	public void executaMV() {
		long startTime = System.nanoTime();
		arena.draw();
		long endTime = System.nanoTime();
		long duration = endTime-startTime;
		System.out.println("tempo de desenho:" + duration );
		
		// Verifica e atualiza o tempo de espera para realizar uma nova operacao
		for (int i = 0; i < mvs.length; i++) {
			if (turnos[i] == 0)
				mvs[i].passo();
			else if (turnos[i] > 0)
				turnos[i]--;
		}

		// Apaga as respostas processadas anteriormente
		arena.apagaRespostas();
		startTime = System.nanoTime();
		processaOperacoes();
		endTime = System.nanoTime();
		duration = endTime-startTime;
		System.out.println("tempo de processa de respostas" + duration);
		processaOperacoes();

		
		startTime = System.nanoTime();
		arena.atualiza();
		endTime = System.nanoTime();
		duration = endTime-startTime;
		System.out.println("tempo de atualizacao da arena" + duration);
		
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
					// processaMovimento(op, i);
					arena.movimento(i, op.getArgumento());
					break;
				case 2:
					// processaAtaque(op)
					break;
				case 3:
					arena.recolhe(i, op.getArgumento());
					break;
				case 4:
					arena.deposita(i, op.getArgumento());
					// processaDeposito(op)
					break;
				case 5:
					arena.scanCristal(i);
					break;
				case 6:
					// arena.scanRobo(i, 2);
					break;
				case 7:
					arena.scanDir(i);
					break;
				case 8:
					arena.regressoBase(i);
					break;
				default:
					System.out.println("Operacao inválida!");
					break;
				}
			}
		}
	}

	/*
	 * private void processaMovimento(Operacao op, int idvm) { switch
	 * (op.getArgumento()) { case "NW": arena.moveCimaEsq(idvm); break; case
	 * "W": arena.moveEsquerda(idvm); break; case "SW":
	 * arena.moveBaixoEsq(idvm); break; case "SE": arena.moveBaixoDir(idvm);
	 * break; case "E": arena.moveDireita(idvm); break; case "NE":
	 * arena.moveCimaDir(idvm); break; default:
	 * System.out.println("Direcao inválida!"); break; } }
	 */

	public static void main(String[] args) {
		// Gerenciador gerenciador = new Gerenciador(2);
		Gerenciador gerenciador = new Gerenciador(); // Para o teste com um robo
														// e um cristal
		gerenciador.inicializa();
		for (int i = 0; i < 1000000; i++) {
			gerenciador.executaMV();
		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
