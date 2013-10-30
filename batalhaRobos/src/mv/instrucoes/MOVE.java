package mv.instrucoes;

import java.util.Stack;

import mv.Operacao;
import mv.Programa;
import mv.empilhaveis.Direcao;
import mv.empilhaveis.Empilhavel;

public class MOVE extends Especial {
	private final int codigo = 1;

	public void geraOperacao(Stack<Empilhavel> pilhaDeDados,
			Stack<Operacao> filaDeOperacoes, Programa programa) {
		Operacao op;
		Empilhavel dir = pilhaDeDados.pop();
		if (dir instanceof Direcao) {
			op = new Operacao(codigo, ((Direcao) dir).getCodigoDir());
			filaDeOperacoes.add(op);
			programa.incPonteiroPrograma();
		} else {
			System.out.println("MOVE: Argumento tem que ser do tipo direcao!");
			System.exit(1);
		}
	}
}
