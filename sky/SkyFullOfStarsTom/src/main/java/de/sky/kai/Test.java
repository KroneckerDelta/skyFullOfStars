package de.sky.kai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.sky.sun.demo.Nebel;
import de.sky.sun.demo.Star;

public class Test {

	public static void main(String[] args) throws InterruptedException {

		// Alles beginnt mit dem MonitorManager
		MonitorManager mm = MonitorManager.getInstance();

//		mm.test(5555); // Anzeige aller Monitore, danach exit

		// Zu nutzenden Monitor angeben
		Monitor m = mm.getMonitor(0, 800, 340); // god_disp_190
//		Monitor m = mm.getMonitor(0, 520, 325); // disp_eizo_013
//		Monitor m = mm.getMonitor(0, 325, 520); // disp_eizo_307

//		Monitor m1 = mm.getMonitor(2, 1920, 1200); // disp_eizo_013
//		Monitor m2 = mm.getMonitor(0, 800,1280); // disp_eizo_307

		// TimeLoop nutzen, um einen gleichmaessigen Durchlauf der Hauptschleife zu
		// erzielen
		TimeLoop loop = new TimeLoop(false);

		// Den Monitor in FullScreen schalten
		m.switchToFullScreen();

		// Dies ist die Hauptschleife des Spieles / Programmes

		for (int i = 0; i < 1000; i++) {
			// Vom Monitor die Zeichenflaeche geben lassen
			Graphics g = m.acquireGraphics();

			// Den gewuenschten Inhalt erzeugen
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, m.getPixelWidth(), m.getPixelHeight());

			g.setFont(new Font("Courier New", Font.BOLD, 32));
			g.setColor(Color.RED);
			g.drawString("Ahuga", 100 + i, 200);

			// Zeichnen beendet, jetzt Zeichenflaeche anzeigen
			m.displayGraphics();

			// Durchlaufzeit der Hauptschleife angleichen
			loop.sync(10);
		}

//		Thread.sleep(1111);

		// Den Monitor zurueck in den normalen Modus schalten
		m.destroy();

		// Statistiken ueber die Laufzeit der Hauptschleife anzeigen
		System.out.println("AVR: " + loop.getAverage());
		System.out.println("MIN: " + loop.getMinimum());
		System.out.println("MAX: " + loop.getMaximum());
	}

}
