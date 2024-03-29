package controle;

import java.util.LinkedList;
import java.util.Scanner;

import mundo.Arena;
import mv.MaquinaVirtual;
import mv.Operacao;

import comunicacao.Resposta;

public class Gerenciador {

	private int numMaquinasVirtuais;
	private MaquinaVirtual[] mvs;
	private Arena arena;
	private int[] turnos;
	private LinkedList<Integer> listaDaMorte;

	public Gerenciador(int numMV, int numB) {
		if ((numMV <= 24) && (numB <= 4 && numB > 0)) {
			listaDaMorte = new LinkedList<Integer>();
			numMaquinasVirtuais = numMV;
			turnos = new int[numMaquinasVirtuais];
			mvs = new MaquinaVirtual[numMaquinasVirtuais];
			arena = new Arena(numMV, numB, turnos, listaDaMorte);
		} else if ((numMV > 24)) {
			System.out
					.println("O número de maquinas virtuais (ou robos) tem que ser menor do que 24");
			System.exit(1);
		} else {
			System.out
					.println("O número de bases por arena tem que pertencer ao intervalo [1,4]!");
			System.exit(1);
		}
	}

	// Para o teste com um robo e um cristal
	public Gerenciador() {
		listaDaMorte = new LinkedList<Integer>();
		numMaquinasVirtuais = 24;
		turnos = new int[numMaquinasVirtuais];
		mvs = new MaquinaVirtual[numMaquinasVirtuais];
		arena = new Arena(24, 4, turnos, listaDaMorte);
	}

	public void inicializa(int numRobos, int numEquipes) {
		int numDaMv = 0;
		for (int k = 1; k <= numEquipes; k++) {
			String s = Integer.toString(k);
			for (int i = 0; i < numRobos; i++) {

				mvs[numDaMv] = new MaquinaVirtual(s);
				mvs[numDaMv].defineProg();
				numDaMv++;
			}
		}
		arena.inicializa();
		arena.initGraphics();
		// Para o teste com um robo e um cristal
		arena.insereBases();
		arena.insereExercitos();
		arena.insereCristais(28);
		// arena.insereUmaBase();
		// arena.insereUmRobo();
		// arena.insereUmCristal();
		// arena.insereDoisRobosInimigos();
	}

	public void executaMV() {

		long startTime = System.nanoTime();
		arena.draw();

		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println("tempo de desenho:" + duration);

		// Verifica e atualiza o tempo de espera para realizar uma nova operacao
		mataMVS();
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
		duration = endTime - startTime;
		System.out.println("tempo de processa de respostas" + duration);
		processaOperacoes();

		startTime = System.nanoTime();
		arena.atualiza();
		endTime = System.nanoTime();
		duration = endTime - startTime;
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
					arena.ataque(i, op.getArgumento());
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
					arena.scanRobo(i, 4);
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

	private void mataMVS() {
		for (Integer i : listaDaMorte) {
			mvs[i].desativarMV();
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
		int numEquipes;
		int numRobos;
		Scanner s = new Scanner(System.in);
		System.out.println("Introduza o numero de equipes(max 4):");
		numEquipes = s.nextInt();
		System.out.println("Introduza o numero de robos(max 6):");
		numRobos = s.nextInt();

		System.out
				.println("Edite os ficheiros no diretorio /src/parser que sejam da forma: programa(numero).txt .");
		System.out
				.println("Exemplo: Se o numero de equipes for 2 entao os ficheiros a serem editados sao programa1.txt e programa2.txt.");
		System.out
				.println("OBS: O numero no nome dos programas � o identificador de cada player!");
		System.out
				.println("Digite qualquer inteiro e de enter quando os ficheiros estiverem prontos!");
		s.nextInt();
		s.close();

		Gerenciador gerenciador = new Gerenciador(numRobos * numEquipes,
				numEquipes); // Para o teste com um robo

		gerenciador.inicializa(numRobos, numEquipes);
		/*
		 * for (int i = 0; i < 1000000; i++) { gerenciador.executaMV();
		 * 
		 * try { Thread.sleep(1000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 */
		while (true) {
			if (gerenciador.arena.baseVencedor() == null) {
				gerenciador.executaMV();
			} else {
				gerenciador.arena.removeExercitosPerdedores();
				gerenciador.executaMV();
				System.out.println("O VENCEDOR E A EQUIPE "
						+ (gerenciador.arena.baseVencedor().getEquipe() + 1) + "!");
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
