package lab2.ime.usp;

public class Programa {
	Instrucao[] vetorPrograma;
	int ponteiro;
	Programa(Instrucao[] vetorProg) {
		this.vetorPrograma = vetorProg;
	}
	public void setPonteiro(int novaPosicao) {
		ponteiro = novaPosicao;
	}
	
	public int getPonteiro() {
		return ponteiro;
	}
	
	public Instrucao getInstrucao(int posicao) {
		return vetorPrograma[posicao];
	}
}
