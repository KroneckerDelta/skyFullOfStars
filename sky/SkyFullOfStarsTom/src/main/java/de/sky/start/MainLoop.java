package de.sky.start;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.sky.kai.Monitor;
import de.sky.kai.MonitorManager;
import de.sky.kai.MonitorPosition;
import de.sky.kai.Tapete;
import de.sky.kai.TimeLoop;
import de.sky.sun.demo.Nebel;
import de.sky.sun.demo.Star;
import de.sky.sun.demo.Sternenhimmel;
import de.sky.sun.demo.Sun;

public class MainLoop {
	static final int ANZAHL_STERNE = 200;
	private static final Random random = new Random();
	static final int BREITE = 2800;
	static final int HOEHE = 1000;
	public MainLoop() {

	}

	/**
	 * Läuft so nur, wenn man drei Monitore hat!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Alles beginnt mit dem MonitorManager
		MonitorManager mm = MonitorManager.getInstance();

		Tapete tapete = new Tapete(1600, 730);

		Monitor m1 = mm.getMonitor(2, 518, 324);
//		Monitor m1 = mm.getMonitor(2, 324, 518);
		Monitor m2 = mm.getMonitor(0, 324, 518);

//		tapete.addMonitorOnPosition(m1, 175, 240);
		tapete.addMonitorOnPosition(m1, 120, 230);
		tapete.addMonitorOnPosition(m2, 860, 145);

		// TimeLoop nutzen, um einen gleichmaessigen Durchlauf der Hauptschleife zu
		// erzielen
		TimeLoop loop = new TimeLoop(false);

//		Star s1 = new Star(0, 500, 100, 1600);
//		Star s2 = new Star(0, 500, 100, 1600);

		
		
		
		List<TapetenOject> objects = new ArrayList<>();
		objects.add(new RectStar(400, 400, 100, 100));
		objects.add(new RectStar(1000, 400, 100, 100));
		objects.add(new RectStar(200, 250, 100, 100));
		objects.add(new RectStar(900, 250, 100, 100));

		objects.add(new Sun(100, 300, BREITE));

		for (int i = 0; i < ANZAHL_STERNE; i++) {
			var x = random.nextInt(BREITE);
			var y = random.nextInt(HOEHE);
			var groesse = random.nextInt(3) + 1;
			objects.add(new Star(x, y, groesse, BREITE));
		}
		
		
		
		
		Map<MonitorPosition, List<TapetenOject>> toDrawMap = new HashMap<>();
		tapete.getMonitore().forEach(m -> {
			m.getMonitor().switchToFullScreen();
			toDrawMap.put(m, new ArrayList<TapetenOject>());
		});

		while (true) {
			toDrawMap.values().forEach(v -> v.clear());

			for (TapetenOject rs : objects) {
//				System.out.println(String.format("Drawable x %s y %s", rs.getTapeteX(), rs.getTapeteY()));
				MonitorPosition whichMonitor = tapete.whichMonitor(rs.getTapeteX(), rs.getTapeteY());
				if (whichMonitor != null) {
					toDrawMap.get(whichMonitor).add(rs);
				}
			}
			for (MonitorPosition mp : toDrawMap.keySet()) {
				Monitor m = mp.getMonitor();
				Graphics g = m.acquireGraphics();
				// Den gewuenschten Inhalt erzeugen
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, m.getPixelWidth(), m.getPixelHeight());
				List<TapetenOject> toDraw = toDrawMap.get(mp);
				toDraw.forEach(star -> star.draw(g, tapete.calcX(mp, star), tapete.calcY(mp, star),
						(int) ((float) m.getPixelPerMillimeterX() * (float) star.getWidth()),
						(int) ((float) m.getPixelPerMillimeterY() * (float) star.getHeight())));
				m.displayGraphics();

			}

			objects.forEach(o -> o.move());

			loop.sync(10);

		}
	}

}
