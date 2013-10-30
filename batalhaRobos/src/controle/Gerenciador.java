package controle;

import mundo.Arena;
import mv.MaquinaVirtual;
import mv.Operacao;

public class Gerenciador {
	private MaquinaVirtual[] mvs;
	private Arena arena;
	
	public Gerenciador(){
		
		
	}
	void init(){
		arena = new Arena();
		arena.insereExercito();
		mvs = new MaquinaVirtual[1];
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
		processaOperacao();
		arena.atualiza();
	}

	void processaOperacao() {
		Operacao op;
		for (int i = 0; i < mvs.length; i++) {
			op = mvs[i].retornaOperacao();
			switch (op.getArgumento()) {
				case 1:
					//NWEST
					arena.moveCimaEsq(i);
					break;
				case 2:
					//WEST
					arena.moveEsquerda(i);
					break;
				case 3:
					//SWEST
					arena.moveBaixoEsq(i);
					break;
				case 4:
					//SEAST
					arena.moveBaixoDir(i);
					break;
				case 5:
					//EAST
					arena.moveDireita(i);
					break;
				case 6:
					//NEAST:
					arena.moveCimaDir(i);
					break;
				default:
					System.out.println("Operacao inválida!");
					break;
			}
		}
	}

	public static void main(String[] args) {
		Gerenciador gerenciador = new Gerenciador();
		gerenciador.init();
		for (int i = 0; i < 4; i++){
			gerenciador.executaVM();
		}
	}

}
