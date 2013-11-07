package mv.instrucoes;

import java.util.Stack;
import java.util.EmptyStackException;

import mv.*;
import mv.empilhaveis.*;

public class POP extends Instrucao {
	public POP() {
		super();
	}

	public void executar(Stack<Empilhavel> pilhaDeDados, Memoria memoria,
			Programa programa) {
		try {
			pilhaDeDados.pop();
		} catch (EmptyStackException excecao) {
			System.out.println("POP: Pilha de dados vazia!");
			System.exit(1);
		}
		programa.incPonteiroPrograma();
	}
}
