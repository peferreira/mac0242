package mv.instrucoes;

import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class RCL extends Instrucao {
    public RCL (Empilhavel item) {
	super (item);
    }
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa) {
	if (argumento instanceof Endereco) {
	    pilhaDeDados.push(memoria.getMemoria(argumento));
	    programa.incPonteiroPrograma();
	} else {
	    System.out.println("RCL: Endereco invalido!");
	    System.exit(1);
	}
    }
}
