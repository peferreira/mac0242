package mv;

import mv.empilhaveis.Endereco;
import mv.instrucoes.*;

public class Programa {

	private Instrucao[] vetorPrograma;
	private final int tamanhoPadrao = 1024;
	private int ponteiroPrograma;
	private int numeroDeInstrucoes;
	
	public Programa() {
		vetorPrograma = new Instrucao[tamanhoPadrao];
		ponteiroPrograma = 0;
		numeroDeInstrucoes = 0;
	}

	public Programa(int tamanho) {
		vetorPrograma = new Instrucao[tamanho];
		ponteiroPrograma = 0;
		numeroDeInstrucoes = 0;
	}

	public void setPonteiroPrograma(Endereco endereco) {
		ponteiroPrograma = endereco.getEndereco();
	}

	public int getPonteiroPrograma() {
		return ponteiroPrograma;
	}

	public void setJPonteiroPrograma(int inc) {
		ponteiroPrograma+= inc;
	}
	
	public void incPonteiroPrograma() {
		ponteiroPrograma++;
	}

	public void addInstrucao(Instrucao codigo) {
		vetorPrograma[numeroDeInstrucoes] = codigo;
		numeroDeInstrucoes++;
	}
	public void addInstrucoes(Instrucao[] vetorPrograma) {
		this.vetorPrograma = vetorPrograma;
		numeroDeInstrucoes = vetorPrograma.length;
	}
	public Instrucao getInstrucao() {
		return vetorPrograma[ponteiroPrograma];
	}

	public int getNumeroDeInstrucoes() {
		return numeroDeInstrucoes;
	}
}
