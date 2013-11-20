package comunicacao;

import mv.empilhaveis.EAST;
import mv.empilhaveis.Empilhavel;
import mv.empilhaveis.NEAST;
import mv.empilhaveis.NWEST;
import mv.empilhaveis.SEAST;
import mv.empilhaveis.SWEST;
import mv.empilhaveis.WEST;

public class RespostaHOMED extends Resposta {
	private String dir;

	public RespostaHOMED(String dir, int id) {
		super.destinatario = id;
		this.dir = dir;
	}

	@Override
	public Empilhavel responde() {
		Empilhavel emp;
		switch (dir) {
		case "NE":
			emp = (new NEAST());
			break;
		case "NW":
			emp = (new NWEST());
			break;
		case "E":
			emp = (new EAST());
			break;
		case "W":
			emp = (new WEST());
			break;
		case "SW":
			emp = (new SWEST());
			break;
		case "SE":
			emp = (new SEAST());
			break;
		default:
			System.out.println("robo" + super.destinatario
					+ ": exilado da base!");
			return null;
		}
		System.out.println("robo" + super.destinatario + ": direcao " + dir
				+ " sugerida para o regresso à base!");
		return emp;
	}
}
