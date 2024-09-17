package de.sky.sun.demo;

import javax.swing.*;
import javax.swing.JFrame;

public class Sternenhimmel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int BREITE = 2800;
	static final int HOEHE = 1000;
	private SternenhimmelPanel comp;

	public Sternenhimmel() {
		setTitle("Sternenhimmel");
		setSize(BREITE, HOEHE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		comp = new SternenhimmelPanel();
		add(comp);
		
	}

	public void start() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						repaint();
						Thread.sleep(25);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		};
		new Thread(runnable).start();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			var sternenhimmel = new Sternenhimmel();
			sternenhimmel.setVisible(true);
			sternenhimmel.start();
		});
	}
}