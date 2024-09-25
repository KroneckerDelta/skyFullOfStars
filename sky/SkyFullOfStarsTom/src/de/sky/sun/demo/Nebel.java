package de.sky.sun.demo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Nebel extends Star{

	BufferedImage img = null;
	private JPanel panel;
	
	
	public Nebel(int x, int y, int groesse, int frontier, JPanel panel) {
		super(x, y, groesse, frontier);
		// TODO Auto-generated constructor stub
		this.panel = panel;
		loadImage();
	}
	
	
	private void loadImage() {
		try {
		    img = ImageIO.read(new File("src/main/resources/nebel2.jpg"));
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, panel);
		this.move();
	}
}
