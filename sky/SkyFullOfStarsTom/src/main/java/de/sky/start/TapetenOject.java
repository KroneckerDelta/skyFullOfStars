package de.sky.start;

import java.awt.Graphics;

public interface TapetenOject {
	public void draw(Graphics g, int px, int py, int pWidth, int pHeight) ;

	public int getTapeteX();

	public int getTapeteY();

	public int getWidth();

	public int getHeight();

	public void move();

}
