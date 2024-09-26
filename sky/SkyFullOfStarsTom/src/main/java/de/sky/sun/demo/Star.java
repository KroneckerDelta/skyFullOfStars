package de.sky.sun.demo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import de.sky.start.TapetenOject;

public class Star implements TapetenOject{

	protected int x;
	protected int y;
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



	protected void chooseColor(Graphics g) {
		g.setColor(Color.BLUE);
	}

	public void move() {

		this.x = ((this.x + (this.z / 20))) / 1;
		if (this.x >= frontier) {
			this.x = 0;
		}

	}

	@Override
	public String toString() {
		return "Star [x=" + x + ", y=" + y + ", z=" + z + ", groesse=" + groesse + ", random=" + random + "]";
	}

	@Override
	public void draw(Graphics g, int px, int py, int pWidth, int pHeight) {
		chooseColor(g);
		g.fillOval(px, py, pWidth, pHeight);
		
	}

	@Override
	public int getTapeteX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getTapeteY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return groesse;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return groesse;
	}

}
