package mundo;

import graficos.Sprite;

//import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Polygon;
//import java.awt.Rectangle;
//import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import mundo.elementos.Bala;
import mundo.elementos.Posicionavel;
//import java.lang.Math;
import mundo.elementos.Robo;
import mundo.elementos.Solo;

/*Representa uma posicao da arena hexagonal do jogo*/
public class Hexagono {
	private ArrayList<Robo> requerentes;
	private ArrayList<Robo> mineradores;
	private Posicionavel ocupante;
	private ArrayList<Bala> ataques;
	private ArrayList<Bala> novosAtaques;
	private Random rand;
	private Solo solo;
	Polygon p = new Polygon();
	BufferedImage bffimage;
	BufferedImage bf;
	int cx, cy;
	int m, n;
	private Sprite sprite;

	public Hexagono(int x, int y, double r, Sprite imagem) {
		m = x;
		n = y;
		cx = x;
		cy = y;
		for (int i = 0; i < 6; i++)
			p.addPoint(x + (int) (r * Math.sin(i * 2 * Math.PI / 6)), y
					+ (int) (r * Math.cos(i * 2 * Math.PI / 6)));
		this.sprite = imagem;
		requerentes = new ArrayList<Robo>();
		mineradores = new ArrayList<Robo>();
		ataques = new ArrayList<Bala>();
		novosAtaques = new ArrayList<Bala>();
		rand = new Random(Double.doubleToLongBits(Math.random()));
		// Por motivo de testes
		solo = new Solo();

	}

	public void draw(Graphics g) {
		sprite.draw(g, m, n);
		if (ocupante != null) {
			ocupante.draw(g, m, n);

		}
		for (int i = 0; i < ataques.size(); i++) {
			ataques.get(i).draw(g, m, n);
		}
	}

	public void adAtaque(Bala b) {
		ataques.add(b);
	}

	public boolean temAtaques() {
		return !ataques.isEmpty();
	}

	public boolean temNovosAtaques() {
		return !novosAtaques.isEmpty();
	}

	public ArrayList<Bala> getAtaques() {
		return ataques;
	}

	public Posicionavel getOcupante() {
		return ocupante;
	}

	public Solo getSolo() {
		return solo;
	}

	public void setSolo(Solo s) {
		solo = s;
	}

	public void adRequerente(Robo requerente) {
		requerentes.add(requerente);
	}

	public int numeroDeRequerentes() {
		return requerentes.size();
	}

	public void delRequerentes() {
		requerentes.clear();
	}

	public Robo getRequerente(int i) {
		return requerentes.get(i);
	}

	public Robo novoOcupante() {
		int i = rand.nextInt(requerentes.size());
		Robo escolhido = requerentes.remove(i);

		// Meramente informativo
		System.out.println("Indice Escolhido:" + i);
		System.out.println("Requerente Escolhido:");
		System.out.println("Id: " + escolhido.getID());
		System.out.println("PosI: " + escolhido.getPosI());
		System.out.println("PosJ: " + escolhido.getPosJ());

		return escolhido;
	}

	public boolean temOcupante() {
		return ocupante != null;
	}

	public void retiraOcupante() {
		ocupante = null;
	}

	public void setOcupante(Posicionavel r) {
		ocupante = r;
	}

	public void adMinerador(Robo mineiro) {
		mineradores.add(mineiro);
	}

	public int numeroDeMineradores() {
		return mineradores.size();
	}

	public void delMineradores() {
		mineradores.clear();
	}

	public Robo getMinerador(int i) {
		return mineradores.get(i);
	}

	public Robo novoMinerador() {
		Robo escolhido;
		ArrayList<Integer> mineradoresAux = new ArrayList<Integer>();
		int i, iEscolhido;

		for (int j = 0; j < mineradores.size(); j++) {
			mineradoresAux.add(j);
		}

		do {
			i = rand.nextInt(mineradoresAux.size());
			iEscolhido = mineradoresAux.remove(i);
			escolhido = mineradores.get(iEscolhido);
		} while (escolhido.temCristal());

		mineradores.remove(escolhido);

		// Meramente informativo
		System.out.println("Indice Escolhido:" + i);
		System.out.println("Minerador Escolhido:");
		System.out.println("Id: " + escolhido.getID());
		System.out.println("PosI: " + escolhido.getPosI());
		System.out.println("PosJ: " + escolhido.getPosJ());

		return escolhido;
	}

	public ArrayList<Bala> getNovosAtaques() {
		return novosAtaques;
	}

	public void addNovoAtaque(Bala b) {
		novosAtaques.add(b);
	}
}
