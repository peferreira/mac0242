package mv.instrucoes;

import java.util.Random;
import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class GE extends Instrucao {
	public GE() {
		super();
	}

	public void executar(Stack<Empilhavel> pilhaDeDados, Memoria memoria,
			Programa programa, Random rand) {
		Empilhavel item1 = pilhaDeDados.pop();
		Empilhavel item2 = pilhaDeDados.pop();
		boolean resultado = false;
		if ((item1 instanceof Numero) && (item2 instanceof Numero)) {
			resultado = ((Numero) item2).valor() >= ((Numero) item1).valor();
		} else if ((item1 instanceof Endereco) && (item2 instanceof Endereco)) {
			resultado = ((Endereco) item2).getEndereco() >= ((Endereco) item1)
					.getEndereco();
		} else if ((item1 instanceof Booleano) && (item2 instanceof Booleano)) {
			resultado = ((Booleano) item2).valor() >= ((Booleano) item1)
					.valor();
		} else {
			System.out.println("GE: Operandos inválidos!");
			System.exit(1);
		}
		pilhaDeDados.push(resultado ? new Verdadeiro() : new Falso());
		programa.incPonteiroPrograma();
	}
}
