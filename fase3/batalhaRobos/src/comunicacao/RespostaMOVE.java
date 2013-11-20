package comunicacao;

import mv.empilhaveis.Empilhavel;
import mv.empilhaveis.Falso;
import mv.empilhaveis.Verdadeiro;

public class RespostaMOVE extends Resposta {
	boolean move;
	public RespostaMOVE(boolean move, int id) {
		super.destinatario = id;
		this.move = move;
	}
	public Empilhavel responde(){
		if (move) {
			// Meramente informativo!
			/*System.out.println("robo" + super.destinatario + ":movimento realizado com sucesso");*/
			return (new Verdadeiro());
		} else {
			// Meramente informativo!
			/*System.out.println("robo" + super.destinatario + ":movimento não realizado");*/
			return (new Falso());
		}
	}
}
