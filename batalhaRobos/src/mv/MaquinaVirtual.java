package mv;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

import mv.empilhaveis.*;
import mv.instrucoes.*;

public class MaquinaVirtual {
	
	private Queue<Operacao> filaDeOperacoes;
	private Stack<Empilhavel> pilhaDeDados;
	private Memoria memoria;
	private Programas programas;
	private Instrucao codigo;
	LinkedList<Programa> listaDeProgramas;
	boolean ultimaResposta;
	
	void passo() {
		for (Programa prg : listaDeProgramas) {
			codigo = prg.getInstrucao();
			while (!(codigo instanceof END)) {
				codigo.executar(pilhaDeDados, memoria, prg);
				codigo = prg.getInstrucao();
			}
		}
	}
	
	void retornaOperacao(){
		
	}
	
	void submeteResposta(boolean resposta){
		ultimaResposta = resposta;
	}
	void inicializa() {
		pilhaDeDados = new Stack<Empilhavel>();
		memoria = new Memoria();
		programas = new Programas();
		programas.setListaDeProgramasPadroes();
		listaDeProgramas = programas.getListaDeProgramas();
	}

	void roda() {
		for (Programa prg : listaDeProgramas) {
			codigo = prg.getInstrucao();
			while (!(codigo instanceof END)) {
				codigo.executar(pilhaDeDados, memoria, prg);
				codigo = prg.getInstrucao();
			}
		}

	}

	public static void main(String[] args) {
		MaquinaVirtual m = new MaquinaVirtual();
		m.inicializa();
		m.roda();
	}

}
