package de.sky.kai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kai Fabian
 */
public class MonitorManager {

	private static final String IDPREFIX = "\\Display";
	private static MonitorManager instance = null;

	private HashMap<Integer, MonitorImpl> monitorMap = new HashMap<Integer, MonitorImpl>();

	private MonitorManager() {
		// Mach ne Factory :)
	}

	/**
	 * @return
	 */
	public static synchronized MonitorManager getInstance() {
		if (instance == null) {
			MonitorManager mm = new MonitorManager();

			GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] devs = gEnv.getScreenDevices();

			for (GraphicsDevice gd : devs) {
				if (gd.getType() == GraphicsDevice.TYPE_RASTER_SCREEN) {
					String name = gd.getIDstring();
					if (!name.startsWith(IDPREFIX))
						throw new RuntimeException("Invalid name: '" + name + "', " + gd);
					name = name.substring(IDPREFIX.length());

					Integer id = Integer.valueOf(name);
					mm.monitorMap.put(id, new MonitorImpl(gd));
				}
			}

			instance = mm;
		}

		return instance;
	}

	/**
	 * @param g
	 * @param fontSmall
	 * @param txt
	 * @param x
	 * @param y
	 * @param c1
	 * @param c2
	 */
	private void showText(Graphics g, Font font, String txt, int x, int y, Color c1, Color c2) {
		g.setFont(font);

		g.setColor(c1);
		g.drawString(txt, x, y);

//		g.setColor(Color.WHITE);
//		g.drawString(txt,  x+2, y+2);

		g.setColor(c2);
		g.drawString(txt, x + 1, y + 1);
	}

	/**
	 * Liefert einen konkreten Monitor zurueck. TODO leider konnte ich bisher keine
	 * bessere Stelle finden, um die Groesse des Monitors zu spezifizieren. Ein
	 * mehrfacher Aufruf dieser Methode ueberschreibt jedesmal die Groesse.
	 * 
	 * @param id           Der interne Index des Monitors. Kann ueber den Aufruf der
	 *                     Methode {@link MonitorManager#test(int)} ermittelt
	 *                     werden.
	 * @param physWidthMM  Breite der Anzeigenflaeche in Millimetern.
	 * @param physHeightMM Hoehe der Anzeigenflaeche in Millimetern.
	 * @return
	 */
	public Monitor getMonitor(int id, int physWidthMM, int physHeightMM) {
		MonitorImpl m = monitorMap.get(Integer.valueOf(id));
		m.setPhysicalHeight(physHeightMM);
		m.setPhysicalWidth(physWidthMM);
		return m;
	}

	/**
	 * Zeigt auf allen angeschlossenen Monitoren ihre ID an, ueber die sie
	 * angesprochen werden koennen. TODO die physikalische Groesse eines Monitors
	 * ist erst einmal unbekannt (0 mm), solange bis man via
	 * {@link MonitorManager#getMonitor(int, int, int)} den konkreten Monitor
	 * angefordert hat.
	 * 
	 * Danach beendet sich das Programm mittels System.exit(42).
	 * 
	 * @param displayDurationMs Wieviele Millisekunden das Testbild angezeigt werden
	 *                          soll.
	 */
	public void test(int displayDurationMs) {
		MonitorManager mm = getInstance();
		Font fontBig = new Font("Courier New", Font.BOLD, 64);
		Font fontSmall = new Font("Courier New", Font.BOLD, 32);
		for (Map.Entry<Integer, MonitorImpl> kv : mm.monitorMap.entrySet()) {
			Integer id = kv.getKey();
			Monitor/* Impl */ monitor = kv.getValue();

			monitor.switchToFullScreen();

			Graphics g = monitor.acquireGraphics();
//			DisplayMode m = monitor.gd.getDisplayMode();

			String str = "Display #" + id;
			mm.showText(g, fontBig, str, 100, 100, Color.BLACK, Color.RED);

			str = "Res.: " + monitor.getPixelWidth() + " x " + monitor.getPixelHeight();
			mm.showText(g, fontSmall, str, 100, 200, Color.BLACK, Color.BLUE);

			str = "Size: " + (monitor.getPhysicalHeight() == 0 ? "undefined"
					: monitor.getPhysicalWidth() + " x " + monitor.getPhysicalHeight() + " mm");
			mm.showText(g, fontSmall, str, 100, 250, Color.BLACK, Color.BLUE);

			str = "Spec: " + monitor.getBitsPerPixel() + " bpp @ " + monitor.getRefreshRate() + " Hz";
			mm.showText(g, fontSmall, str, 100, 300, Color.BLACK, Color.BLUE);

			monitor.displayGraphics();
		}

		try {
			Thread.sleep(displayDurationMs);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		for (MonitorImpl monitor : mm.monitorMap.values()) {
			monitor.getGraphicsDevice().setFullScreenWindow(null);
			monitor.dispose();
		}

		System.exit(42);
	}
}
