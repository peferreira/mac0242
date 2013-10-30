package mv;

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

import mv.empilhaveis.*;
import mv.instrucoes.*;

public class MaquinaVirtual {

	private Stack<Operacao> filaDeOperacoes;
	private Stack<Empilhavel> pilhaDeDados;
	private Memoria memoria;
	private Programas programas;
	private Instrucao codigo;
	LinkedList<Programa> listaDeProgramas;
	boolean ultimaResposta;

	public MaquinaVirtual() {
		filaDeOperacoes = new Stack<Operacao>();
		pilhaDeDados = new Stack<Empilhavel>();
		memoria = new Memoria();
		programas = new Programas();
		programas.testaMoveEast();
		listaDeProgramas = programas.getListaDeProgramas();
	}

	public void passo() {
		for (Programa prg : listaDeProgramas) {
			codigo = prg.getInstrucao();
			while (!(codigo instanceof END) && !(codigo instanceof Especial)) {
				codigo.executar(pilhaDeDados, memoria, prg);
				codigo = prg.getInstrucao();
			}
			if (codigo instanceof Especial) {
				((Especial) codigo).geraOperacao(pilhaDeDados, filaDeOperacoes, prg);
			}
		}
	}

	public Operacao retornaOperacao() {
		return filaDeOperacoes.pop();
	}

	public void submeteResposta(boolean resposta) {
		ultimaResposta = resposta;
	}

	/*
	 * void roda() { for (Programa prg : listaDeProgramas) { codigo =
	 * prg.getInstrucao(); while (!(codigo instanceof END)) {
	 * codigo.executar(pilhaDeDados, memoria, prg); codigo = prg.getInstrucao();
	 * } }
	 * 
	 * }
	 * 
	 * public static void main(String[] args) { MaquinaVirtual m = new
	 * MaquinaVirtual(); m.inicializa(); m.roda(); }
	 */
}
