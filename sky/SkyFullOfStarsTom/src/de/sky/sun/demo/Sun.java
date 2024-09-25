package de.sky.sun.demo;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Sonne ist groß!
 */
public class Sun extends Star {

	public Sun(int x, int y, int frontier) {
		super(x, y, 100, frontier);
	}

	@Override
	protected void chooseColor(Graphics g) {

		g.setColor(Color.yellow);
		;
	}
	
	@Override
		public void move() {
			this.x++;
		}
}
