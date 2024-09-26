package de.sky.start;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.sky.kai.Monitor;
import de.sky.kai.MonitorManager;
import de.sky.kai.MonitorPosition;
import de.sky.kai.Tapete;
import de.sky.kai.TimeLoop;

public class MainLoop {

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
		tapete.addMonitorOnPosition(m1, 215, 120);
		tapete.addMonitorOnPosition(m2, 910, 120);

		// TimeLoop nutzen, um einen gleichmaessigen Durchlauf der Hauptschleife zu
		// erzielen
		TimeLoop loop = new TimeLoop(false);

//		Star s1 = new Star(0, 500, 100, 1600);
//		Star s2 = new Star(0, 500, 100, 1600);

		List<RectStar> objects = new ArrayList<RectStar>();
//		objects.add(new RectStar(400, 400, 100, 100));
//		objects.add(new RectStar(1000, 400, 100, 100));
		objects.add(new RectStar(400, 150, 100, 100));
		objects.add(new RectStar(400, 300, 100, 100));

		Map<MonitorPosition, List<RectStar>> toDrawMap = new HashMap<>();
		tapete.getMonitore().forEach(m -> {
			m.getMonitor().switchToFullScreen();
			toDrawMap.put(m, new ArrayList<RectStar>());
		});

		while (true) {
			toDrawMap.values().forEach(v -> v.clear());

			for (RectStar rs : objects) {
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
				List<RectStar> toDraw = toDrawMap.get(mp);

				toDraw.forEach(star -> star.draw(g, tapete.calcX(mp, star), tapete.calcY(mp, star),
						(int) ((float) m.getPixelPerMillimeterX() * (float) star.getWidth()),
						(int) ((float) m.getPixelPerMillimeterY() * (float) star.getHeight())));
				m.displayGraphics();

			}

			objects.forEach(o -> o.setTapeteX(o.getTapeteX() + 1));

			loop.sync(10);

		}
	}

}
