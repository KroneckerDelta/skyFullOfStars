package de.sky.sun.demo;

import javax.swing.*;
import javax.swing.JFrame;

public class Sternenhimmel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int BREITE = 800;
	static final int HOEHE = 600;


	public Sternenhimmel() {
		setTitle("Sternenhimmel");
		setSize(BREITE, HOEHE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(new SternenhimmelPanel());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			var sternenhimmel = new Sternenhimmel();
			sternenhimmel.setVisible(true);
		});
	}
}