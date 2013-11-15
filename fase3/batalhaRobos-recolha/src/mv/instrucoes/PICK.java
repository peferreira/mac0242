package mv.instrucoes;

import java.util.Stack;

import mv.Operacao;
import mv.Programa;
import mv.empilhaveis.Direcao;
import mv.empilhaveis.Empilhavel;

public class PICK extends Especial {
	private final int codigo = 3;

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
			System.out.println("PICK: Argumento tem que ser do tipo direcao!");
			System.exit(1);
		}
	}
}
