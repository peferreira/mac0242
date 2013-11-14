package mv.instrucoes;

import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class PRN extends Instrucao {
    public PRN () {
	super ();
    } 
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa) {
	Empilhavel topo = pilhaDeDados.pop();
	topo.print();
	programa.incPonteiroPrograma();
    }
}
