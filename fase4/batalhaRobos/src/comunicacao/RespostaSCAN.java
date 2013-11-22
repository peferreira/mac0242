package comunicacao;

import mv.empilhaveis.Empilhavel;
import mv.empilhaveis.Verdadeiro;
import mv.empilhaveis.Falso;

public class RespostaSCAN extends Resposta {
	private boolean scan;

	public RespostaSCAN(boolean scan, int id) {
		super.destinatario = id;
		this.scan = scan;
	}

	@Override
	public Empilhavel responde() {
		if (scan) {
			// Meramente informativo!
			System.out.println("robo" + super.destinatario
					+ ": cristal/Robo econtrado!");
			return (new Verdadeiro());
		} else {
			// Meramente informativo!
			System.out.println("robo" + super.destinatario
					+ ": nenhum cristal/robo encontrado!");
			return (new Falso());
		}
	}

}
