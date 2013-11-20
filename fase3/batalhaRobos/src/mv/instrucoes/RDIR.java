package mv.instrucoes;

import java.util.Random;
import java.util.Stack;

import mv.Memoria;
import mv.Programa;
import mv.empilhaveis.Direcao;
import mv.empilhaveis.EAST;
import mv.empilhaveis.Empilhavel;
import mv.empilhaveis.NEAST;
import mv.empilhaveis.NWEST;
import mv.empilhaveis.SEAST;
import mv.empilhaveis.SWEST;
import mv.empilhaveis.WEST;

public class RDIR extends Instrucao {
	public RDIR() {
		super();
	}

	public void executar(Stack<Empilhavel> pilhaDeDados, Memoria memoria,
			Programa programa, Random rand) {
		Direcao[] dirs = { new NEAST(), new EAST(), new SEAST(), new SWEST(),
				new WEST(), new NWEST() };
		int i = rand.nextInt(dirs.length);
		System.out.println("RDIR: " + i);
		pilhaDeDados.push(dirs[i]);
		programa.incPonteiroPrograma();
	}

}
