package mv;

import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;
import java.util.LinkedList;

import mv.empilhaveis.*;
import mv.instrucoes.*;

public class MaquinaVirtual {

	private Stack<Operacao> pilhaDeOperacoes;
	private Stack<Empilhavel> pilhaDeDados;
	private Memoria memoria;
	private Programas programas;
	private Instrucao codigo;
	LinkedList<Programa> listaDeProgramas;
	boolean ultimaResposta;
	Random rand;

	public MaquinaVirtual() {
		rand = new Random(Double.doubleToLongBits(Math.random()));
		pilhaDeOperacoes = new Stack<Operacao>();
		pilhaDeDados = new Stack<Empilhavel>();
		memoria = new Memoria();
		programas = new Programas();
		programas.testaRoboVsCristalVsBase();
		listaDeProgramas = programas.getListaDeProgramas();
	}

	public void passo() {
		for (Programa prg : listaDeProgramas) {
			codigo = prg.getInstrucao();
			while (!(codigo instanceof END) && !(codigo instanceof Especial)) {
				codigo.executar(pilhaDeDados, memoria, prg,rand);
				codigo = prg.getInstrucao();
			}
			if (codigo instanceof Especial) {
				((Especial) codigo).geraOperacao(pilhaDeDados, pilhaDeOperacoes,
						prg);
			}
		}
	}

	public Operacao getOperacao() {
		Operacao op;
		try {
			op = pilhaDeOperacoes.pop();
		} catch(EmptyStackException excecao){
			System.out.println("Não existem operações para processamento!");
			op = null;
		}
		return op;
	}

	public void setResposta(Empilhavel resposta) {
		pilhaDeDados.push(resposta);
	}
}
