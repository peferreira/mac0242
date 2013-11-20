package mv.instrucoes;

import java.util.Stack;

import mv.Operacao;
import mv.Programa;
import mv.empilhaveis.Empilhavel;

public class HOME extends Especial {

	private final int codigo = 8;
	
	public void geraOperacao(Stack<Empilhavel> pilhaDeDados,
			Stack<Operacao> filaDeOperacoes, Programa programa) {
		filaDeOperacoes.add(new Operacao(codigo, ""));
		programa.incPonteiroPrograma();
	}

}
