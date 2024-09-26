package de.sky.start;

import java.awt.Color;
import java.awt.Graphics;

import lombok.Data;

@Data
public class RectStar implements TapetenOject{

	private int tapeteX;
	private int tapeteY;
	private int width;
	private int height;

	public RectStar(int tapeteX, int tapeteY, int width, int height) {
		this.tapeteX = tapeteX;
		this.tapeteY = tapeteY;
		this.width = width;
		this.height = height;
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g, int px, int py, int pWidth, int pHeight) {
		g.setColor(Color.ORANGE);
		g.fillRect(px, py, pWidth, pHeight);

	}

	@Override
	public void move() {
		setTapeteX(getTapeteX() + 1);
		
	}
}
