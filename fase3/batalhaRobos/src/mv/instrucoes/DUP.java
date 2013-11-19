package mv.instrucoes;

import java.util.Random;
import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class DUP extends Instrucao {
	public DUP() {
		super();
	}

	public void executar(Stack<Empilhavel> pilhaDeDados, Memoria memoria,
			Programa programa, Random rand) {
		Empilhavel clone, topo;
		topo = pilhaDeDados.pop();
		clone = topo.clona();
		pilhaDeDados.push(clone);
		pilhaDeDados.push(topo);
		programa.incPonteiroPrograma();
	}
}
