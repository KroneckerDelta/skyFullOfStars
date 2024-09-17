package de.sky.sun.demo;

import java.awt.Color;
import java.awt.Graphics;

public class Star {

	private int x;
	private int y;
	private int groesse;

	public Star(int x, int y, int groesse) {
		this.x = x;
		this.y = y;
		this.groesse = groesse;
		// TODO Auto-generated constructor stub
	}

	public void draw (Graphics g){
		 g.setColor(Color.WHITE);
		g.fillOval(x, y, groesse, groesse);

	}
}
