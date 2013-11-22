package comunicacao;

import mv.empilhaveis.Empilhavel;
import mv.empilhaveis.Falso;
import mv.empilhaveis.Verdadeiro;

public class RespostaHOME extends Resposta {
	private boolean home;

	public RespostaHOME(boolean home, int id) {
		super.destinatario = id;
		this.home = home;
	}

	@Override
	public Empilhavel responde() {
		if (home) {
			// Meramente informativo!
			System.out.println("robo" + super.destinatario
					+ ": base econtrada!");
			return (new Verdadeiro());
		} else {
			// Meramente informativo!
			System.out.println("robo" + super.destinatario
					+ ": base por encontrar!");
			return (new Falso());
		}
	}

}
