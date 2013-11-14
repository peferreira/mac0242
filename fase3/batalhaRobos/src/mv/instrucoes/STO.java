package mv.instrucoes;

import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class STO extends Instrucao {
    public STO (Empilhavel item) {
	super (item);
    }
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa ) {
	Empilhavel topo = pilhaDeDados.pop();
	if (argumento instanceof Endereco) {
	    memoria.setMemoria(topo, argumento);
	    programa.incPonteiroPrograma();
	} else {
	    System.out.println("STO: Endereco invalido!");
	    System.exit(1);
	}
    }
}
