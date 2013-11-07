package mv.instrucoes;

import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class MUL extends Instrucao {
	public MUL() {
		super();
	}

	public void executar(Stack<Empilhavel> pilhaDeDados, Memoria memoria,
			Programa programa) {
		Empilhavel item1 = pilhaDeDados.pop();
		Empilhavel item2 = pilhaDeDados.pop();
		double resultado;
		if ((item1 instanceof Numero) && (item2 instanceof Numero)) {
			resultado = ((Numero) item2).valor() * ((Numero) item1).valor();
			if (((Numero) item2) instanceof Real
					|| ((Numero) item1) instanceof Real) {
				pilhaDeDados.push(new Real(resultado));
			} else {
				pilhaDeDados.push(new Inteiro((int) resultado));
			}
			programa.incPonteiroPrograma();
		} else {
			System.out.println("MUL: Todos os operandos devem ser numeros!");
			System.exit(1);
		}
	}
}
