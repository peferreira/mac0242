package mv.instrucoes;

import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class JIF extends Instrucao {
    public JIF (Empilhavel item) {
	super(item);
    }
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa) {
	Empilhavel item = pilhaDeDados.pop();
	if (item instanceof Falso) {
		if (argumento instanceof Endereco) {	
			programa.setPonteiroPrograma(((Endereco)argumento));
		} else {
			System.out.println("JIF: Argumento tem que ser um endereco!");
			System.exit(1);
		}
	} else if (item instanceof Verdadeiro) {
		programa.incPonteiroPrograma();
	} else {
	    System.out.println("JIF: Topo da pilha não é um booleano!");
	    System.exit(1);
	}
    }
}
