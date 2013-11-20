package comunicacao;

import mv.empilhaveis.Empilhavel;
import mv.empilhaveis.Falso;
import mv.empilhaveis.Verdadeiro;

public class RespostaPICK extends Resposta {
	boolean pick;
	
	public RespostaPICK(boolean pick, int id) {
		super.destinatario = id;
		this.pick = pick;
	}
	
	public Empilhavel responde(){
		if (pick) {
			// Meramente informativo!
			System.out.println("robo" + super.destinatario + ": recolha de cristal feita com sucesso");
			return (new Verdadeiro());
		} else {
			// Meramente informativo!
			System.out.println("robo" + super.destinatario + ": recolha de cristal não realizado");
			return (new Falso());
		}
	}

}
