package mv.instrucoes;

import java.util.Stack;

import mv.Operacao;
import mv.Programa;
import mv.empilhaveis.Direcao;
import mv.empilhaveis.Empilhavel;

public class DEP extends Especial {
	private final int codigo = 4;
	
	public void geraOperacao(Stack<Empilhavel> pilhaDeDados,
			Stack<Operacao> filaDeOperacoes, Programa programa) {
		Operacao op;
		Empilhavel dir = pilhaDeDados.pop();
		// Meramente informativo
		dir.print();
		if (dir instanceof Direcao) {
			op = new Operacao(codigo, ((Direcao) dir).getDir());
			filaDeOperacoes.add(op);
			programa.incPonteiroPrograma();
		} else {
			System.out.println("DEP: Argumento tem que ser do tipo direcao!");
			System.exit(1);
		}
	}

}
