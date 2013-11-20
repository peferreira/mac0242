package mundo.elementos;

import graficos.Sprite;

import java.awt.Graphics;

public class Base implements Posicionavel {
	private int equipe;
	private int numCristais;
	private Sprite[] starSprite;
	private Sprite baseSprite;
	private int posJ;
	private int posI;

	public Base(int posI, int posJ,String corBase) {
		this.posI = posI;
		this.posJ = posJ;
		this.starSprite = new Sprite[7];
		this.baseSprite = graficos.SpriteStore.get().getSprite(
				"graficos/base"+corBase+".png");
		this.starSprite[0] = graficos.SpriteStore.get().getSprite(
				"graficos/estrela1.png");
		this.starSprite[1] = graficos.SpriteStore.get().getSprite(
				"graficos/estrela2.png");
		this.starSprite[2] = graficos.SpriteStore.get().getSprite(
				"graficos/estrela3.png");
		this.starSprite[3] = graficos.SpriteStore.get().getSprite(
				"graficos/estrela4.png");
		this.starSprite[4] = graficos.SpriteStore.get().getSprite(
				"graficos/estrela5.png");
		this.starSprite[5] = graficos.SpriteStore.get().getSprite(
				"graficos/estrela6.png");
		this.starSprite[6] = graficos.SpriteStore.get().getSprite(
				"graficos/estrela7.png");
	}

	public void draw(Graphics g, int x, int y) {
		baseSprite.draw(g, x, y+5);
		if (numCristais > 0 && numCristais < 8) {
			starSprite[numCristais-1].draw(g, x , y+5);
		}
		
	}

	public void addCristal() {
		numCristais++;
	}

	public int getNumDeCristais() {
		return numCristais;
	}

	public int getEquipe() {
		return equipe;
	}
	
	public void setEquipe(int equipe) {
		this.equipe = equipe;
	}

	public int getPosI() {
		return posI;
	}

	public int getPosJ() {
		return posJ;
	}
}
