package mundo.elementos;

import graficos.Sprite;

import java.awt.Graphics;

public class Base implements Posicionavel {
	int equipe;
	int numCristais;
	private Sprite sprite;

	public Base(int equipe){
		this.equipe = equipe;
		this.sprite = graficos.SpriteStore.get().getSprite("graficos/base.png");
		}
	
	public void draw(Graphics g, int x, int y) {
		sprite.draw(g, x-25, y-25);
		
	}

	public void addCristal(){
		numCristais++;
	}
	
	public int getNumDeCristais(){
		return numCristais;
	}
}
