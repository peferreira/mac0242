package mundo.elementos;

import graficos.Sprite;

import java.awt.Graphics;

public class Base implements Posicionavel {
	private int equipe;
	private int numCristais;
	private Sprite[] sprite;
	private int posJ;
	private int posI;

	public Base(int posI, int posJ) {
		this.posI = posI;
		this.posJ = posJ;
		this.sprite = new Sprite[8];
		this.sprite[0] = graficos.SpriteStore.get().getSprite(
				"graficos/base.png");
		this.sprite[1] = graficos.SpriteStore.get().getSprite(
				"graficos/base1.png");
		this.sprite[2] = graficos.SpriteStore.get().getSprite(
				"graficos/base2.png");
		this.sprite[3] = graficos.SpriteStore.get().getSprite(
				"graficos/base3.png");
		this.sprite[4] = graficos.SpriteStore.get().getSprite(
				"graficos/base4.png");
		this.sprite[5] = graficos.SpriteStore.get().getSprite(
				"graficos/base5.png");
		this.sprite[6] = graficos.SpriteStore.get().getSprite(
				"graficos/base6.png");
		this.sprite[7] = graficos.SpriteStore.get().getSprite(
				"graficos/base7.png");
	}

	public void draw(Graphics g, int x, int y) {
		if (numCristais < 8) {
			sprite[numCristais].draw(g, x , y+5);
		} else
			sprite[7].draw(g, x - 25, y - 25);

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
