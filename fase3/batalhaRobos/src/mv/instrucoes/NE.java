package mv.instrucoes;

import java.util.Random;
import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class NE extends Instrucao {
    public NE () {
	super();
    }
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa, Random rand) {
	Empilhavel item1 = pilhaDeDados.pop();
	Empilhavel item2 = pilhaDeDados.pop();
	pilhaDeDados.push(item1.equals(item2) ? new Falso() : new Verdadeiro());
	programa.incPonteiroPrograma();
    }
}
