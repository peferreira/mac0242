package mv.instrucoes;

import java.util.Random;
import java.util.Stack;

import mv.Memoria;
import mv.Programa;
import mv.empilhaveis.Empilhavel;

public class Instrucao {
	Empilhavel argumento;
	Empilhavel argumento2;

	public Instrucao() {
		argumento = null;
		argumento2 = null;
	}

	public Instrucao(Empilhavel operando) {
		argumento = operando;
		argumento2 = null;
	}

	public Instrucao(Empilhavel operando, Empilhavel operando2) {
		argumento = operando;
		argumento2 = operando2;
	}

	public void executar(Stack<Empilhavel> pilhaDeDados, Memoria memoria,
			Programa programa, Random rand) {
	}
}
