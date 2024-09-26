package de.sky.start;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.sky.kai.Monitor;
import de.sky.kai.MonitorManager;
import de.sky.kai.Tapete;
import de.sky.kai.TimeLoop;

public class MainLoop {

	public MainLoop() {

	}

	private void loop() {

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

		Monitor m1 = mm.getMonitor(2, 520, 325); // disp_eizo_013
		Monitor m2 = mm.getMonitor(0, 325, 520); // disp_eizo_307

		tapete.addMonitorOnPosition(m1, 175, 240);
		tapete.addMonitorOnPosition(m2, 910, 120);

		// TimeLoop nutzen, um einen gleichmaessigen Durchlauf der Hauptschleife zu
		// erzielen
		TimeLoop loop = new TimeLoop(false);

//		Star s1 = new Star(0, 500, 100, 1600);
//		Star s2 = new Star(0, 500, 100, 1600);

		List<RectStar> objects = new ArrayList<RectStar>();
		objects.add(new RectStar(0, 150, 100, 100));
		objects.add(new RectStar(0, 400, 100, 100));

		Map<Monitor, List<RectStar>> toDrawMap = new HashMap<>();
		tapete.allMonitore().forEach(m -> toDrawMap.put(m, new ArrayList<RectStar>()));

		while (true) {
			toDrawMap.values().forEach(v -> v.clear());

			for (RectStar rs : objects) {
				Monitor whichMonitor = tapete.whichMonitor(rs.getTapeteX(), rs.getTapeteY());
				if (whichMonitor != null) {
					toDrawMap.get(whichMonitor).add(rs);
				}
			}
			for (Monitor m : toDrawMap.keySet()) {
				Graphics g = m.acquireGraphics();
				List<RectStar> toDraw = toDrawMap.get(m);

				toDraw.forEach(star -> star.draw(g, tapete.calcX( m, star), tapete.calcY( m, star)));
			}

			loop.sync(10);
		}
	}



}
