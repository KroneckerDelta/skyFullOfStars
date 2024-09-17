package de.sky.sun.demo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Star {

	private int x;
	private int y;
	private int z;
	private int groesse;

	private final Random random = new Random();
	private static AtomicInteger counter = new AtomicInteger(1);
	private int frontier;

	public Star(int x, int y, int groesse, int frontier) {
		this.x = x;
		this.y = y;
		this.frontier = frontier;
		this.z = counter.addAndGet(1);
		this.groesse = groesse;
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, groesse, groesse);

	}

	public void move() {

		this.x = (this.x + (this.z/2));
		if (this.x >=frontier ) {
			this.x = 0;
		}
			
	}

	@Override
	public String toString() {
		return "Star [x=" + x + ", y=" + y + ", z=" + z + ", groesse=" + groesse + ", random=" + random + "]";
	}

}
