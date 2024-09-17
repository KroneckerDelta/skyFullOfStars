package de.sky.sun.demo;


import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class Sternenhimmel extends JFrame {

	private static final int BREITE = 800;
	private static final int HOEHE = 600;
	private static final int ANZAHL_STERNE = 200;

	public Sternenhimmel() {
		setTitle("Sternenhimmel");
		setSize(BREITE, HOEHE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(new SternenhimmelPanel());
	}

	class SternenhimmelPanel extends JPanel {
		private final Random random = new Random();
		private List<Star> stars = new ArrayList<Star>();

		public SternenhimmelPanel() {
			for (int i = 0; i < ANZAHL_STERNE; i++) {
				var x = random.nextInt(BREITE);
				var y = random.nextInt(HOEHE);
				var groesse = random.nextInt(3) + 1;
				stars.add(new Star(x, y, groesse));
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.setBackground(Color.BLACK);
			stars.forEach(s -> s.draw(g));
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			var sternenhimmel = new Sternenhimmel();
			sternenhimmel.setVisible(true);
		});
	}
}