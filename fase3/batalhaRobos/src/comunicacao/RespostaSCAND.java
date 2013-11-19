package comunicacao;

import mv.empilhaveis.Empilhavel;
import mv.empilhaveis.SEAST;
import mv.empilhaveis.SWEST;
import mv.empilhaveis.NEAST;
import mv.empilhaveis.NWEST;
import mv.empilhaveis.EAST;
import mv.empilhaveis.WEST;
import mv.empilhaveis.Falso;

public class RespostaSCAND extends Resposta {
		private String dir;

		public RespostaSCAND(String dir, int id) {
			super.destinatario = id;
			this.dir = dir;
		}

		@Override
		public Empilhavel responde() {
			switch (dir) {
			case "NE":
				System.out.println("robo" + super.destinatario
						+ ": cristal/robo encontrado na direcao " + dir);
				return (new NEAST());
			case "NW":
				System.out.println("robo" + super.destinatario
						+ ": cristal/robo encontrado na direcao " + dir);
				return (new NWEST());
			case "E":
				System.out.println("robo" + super.destinatario
						+ ": cristal/robo encontrado na direcao " + dir);
				return (new EAST());
			case "W":
				System.out.println("robo" + super.destinatario
						+ ": cristal/robo encontrado na direcao " + dir);
				return (new WEST());
			case "SW":
				System.out.println("robo" + super.destinatario
						+ ": cristal/robo encontrado na direcao " + dir);
				return (new SWEST());
			case "SE":
				System.out.println("robo" + super.destinatario
						+ ": cristal/robo encontrado na direcao " + dir);
				return (new SEAST());
			default:
				System.out.println("robo" + super.destinatario
						+ ": nenhum cristal/robo encontrado!");
				return (new Falso());
			}
		}


}
