package comunicacao;

import mv.empilhaveis.Empilhavel;
import mv.empilhaveis.Falso;
import mv.empilhaveis.Verdadeiro;

public class RespostaDEP extends Resposta {

	private boolean dep;
	
	public RespostaDEP(boolean dep, int id) {
		super.destinatario = id;
		this.dep = dep;
	}
	@Override
	public Empilhavel responde() {
		if(dep){
			System.out.println("robo" + super.destinatario + ": deposito de cristal feita com sucesso");
			return (new Verdadeiro());
		}
		else{
			System.out.println("robo" + super.destinatario + ": deposito de cristal não realizado");
			return (new Falso());
		}
	}

}
