package mundo.elementos;

import graficos.Sprite;

import java.awt.Graphics;

public class Cristal implements Posicionavel{
	private Sprite sprite;
	private int quantidade;
	
	public Cristal(int quantidade){
		this.quantidade = quantidade;
		this.sprite = graficos.SpriteStore.get().getSprite("graficos/cristal.png");
	}
	
	public int custo() {
		return 1;
	}
	
	public void decQuantidade(){
		quantidade--;
	}
	
	public int getQuantidade(){
		return quantidade;
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		sprite.draw(g, x-10, y-10);
		
	}
}
