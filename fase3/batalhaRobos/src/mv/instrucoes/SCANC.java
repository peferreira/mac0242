package mv.instrucoes;

import java.util.Stack;
import mv.Operacao;
import mv.Programa;
import mv.empilhaveis.Empilhavel;

public class SCANC extends Especial {
	private final int codigo = 5;

	public void geraOperacao(Stack<Empilhavel> pilhaDeDados,
			Stack<Operacao> filaDeOperacoes, Programa programa) {
		Operacao op;
		op = new Operacao(codigo, "");
		filaDeOperacoes.add(op);
		programa.incPonteiroPrograma();
	}

}