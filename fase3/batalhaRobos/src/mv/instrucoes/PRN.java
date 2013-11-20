package mv.instrucoes;

import java.util.Random;
import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class PRN extends Instrucao {
    public PRN () {
	super ();
    } 
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa, Random rand) {
	Empilhavel topo = pilhaDeDados.pop();
	topo.print();
	programa.incPonteiroPrograma();
    }
}
