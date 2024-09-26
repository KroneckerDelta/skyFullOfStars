package de.sky.sun.demo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

class SternenhimmelPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private final Random random = new Random();
	static final int ANZAHL_STERNE = 200;

	private List<Star> stars = new ArrayList<Star>();

	public SternenhimmelPanel() {

//		stars.add(new Nebel(100, 20, 0, 0, this));
		for (int i = 0; i < ANZAHL_STERNE; i++) {
			var x = random.nextInt(Sternenhimmel.BREITE);
			var y = random.nextInt(Sternenhimmel.HOEHE);
			var groesse = random.nextInt(3) + 1;
			stars.add(new Star(x, y, groesse, Sternenhimmel.BREITE));
		}
		stars.add(new Sun(100, 100, Sternenhimmel.BREITE));
		stars.add(new Nebel(400, 200, 0, 0, this));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
//		stars.forEach(s -> s.draw(g));
		stars.forEach(s -> s.move());

	}
}