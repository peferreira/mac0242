package mv.instrucoes;

import java.util.Random;
import java.util.Stack;

import mv.*;
import mv.empilhaveis.*;

public class JMP extends Instrucao {
    public JMP (Empilhavel item) {
	super(item);
    }
    public void executar (Stack<Empilhavel> pilhaDeDados, Memoria memoria, Programa programa, Random rand) {
	if (argumento instanceof Endereco) {	
		programa.setJPonteiroPrograma(((Endereco)argumento).getEndereco());
	} else {
		System.out.println("JMP: Argumento tem que ser um endereco!");
		System.exit(1);
	}
    }
}
