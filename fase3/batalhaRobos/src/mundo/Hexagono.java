package mundo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;



//import java.lang.Math;
import mundo.elementos.Robo;
import mundo.elementos.Solo;

/*Representa uma posicao da arena hexagonal do jogo*/
public class Hexagono {
	private ArrayList<Robo> requerentes;
	private Robo ocupante;
	private Random  rand;
	private Solo solo;
	Polygon p = new Polygon();
	BufferedImage bffimage;
	BufferedImage bf;
	int cx, cy;

	public Hexagono(int x, int y, double r, BufferedImage bffimage) {
		
		cx = x-5;
		cy = y-5;
		for (int i = 0; i < 6; i++)
			p.addPoint(x + (int) (r * Math.sin(i * 2 * Math.PI / 6)),
					   y + (int) (r * Math.cos(i * 2 * Math.PI / 6)));
		this.bffimage = bffimage;
		requerentes = new ArrayList<Robo>();
		rand = new Random();
		
		// Por motivo de testes
		solo = new Solo();
	}
	
	public void draw(Graphics g) { 
		Graphics2D g2d = (Graphics2D) g;
		Rectangle r = new Rectangle(0,0,100,100);
		g2d.setPaint(new TexturePaint(bffimage, r));
		g2d.fill(p);
		if(ocupante != null) {
			ocupante.draw(g, cx, cy);
			System.out.println("tem ocupante I:" + ocupante.getPosI() +"e J:"+ ocupante.getPosJ());
		}
		
	}

	public Robo getOcupante() {
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
		System.out.println("Robo Escolhido:");
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

	public void setOcupante(Robo r) {
		ocupante = r;
	}
}
