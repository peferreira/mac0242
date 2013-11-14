package comunicacao;

import mv.empilhaveis.Empilhavel;

public abstract class Resposta {
	protected int destinatario;
	
	public int getDestinatario(){
		return destinatario;
	}
	
	public abstract Empilhavel responde();
}
