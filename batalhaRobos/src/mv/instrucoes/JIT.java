package mv.instrucoes;

import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class JIT extends Instrucao {
    public JIT (Empilhavel item) {
	super(item);
    }
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa) {
	Empilhavel item = pilhaDeDados.pop();
	if (item instanceof Verdadeiro) {
		if (argumento instanceof Endereco) {	
			programa.setPonteiroPrograma(((Endereco)argumento));
		} else {
			System.out.println("JIT: Argumento tem que ser um endereco!");
			System.exit(1);
		}
	} else if (item instanceof Falso) {
		programa.incPonteiroPrograma();
	} else {
	    System.out.println("JIT: Topo da pilha não é um booleano!");
	    System.exit(1);
	}
    }
}
