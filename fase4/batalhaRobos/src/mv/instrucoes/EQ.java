package mv.instrucoes;

import java.util.Random;
import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class EQ extends Instrucao {
	public EQ() {
		super();
	}

	public void executar(Stack<Empilhavel> pilhaDeDados, Memoria memoria,
			Programa programa, Random rand) {
		Empilhavel item1 = pilhaDeDados.pop();
		Empilhavel item2 = pilhaDeDados.pop();
		pilhaDeDados.push(item1.equals(item2) ? new Verdadeiro() : new Falso());
		programa.incPonteiroPrograma();
	}
}
