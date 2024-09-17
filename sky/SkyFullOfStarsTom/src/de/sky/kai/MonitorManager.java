package de.sky.kai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kai Fabian
 */
public class MonitorManager {

	/**
	 * 
	 */
	static class MonitorImpl extends Frame implements Monitor {

		private static final long serialVersionUID = 3551071724718610914L;
		private GraphicsDevice gd = null;

		public MonitorImpl(GraphicsDevice gd) {
			this.gd = gd;
		}

		@Override
		public void switchToFullScreen() {
			setUndecorated(true);
			gd.setFullScreenWindow(this);
			createBufferStrategy(2);
		}

		@Override
		public void destroy() {
			gd.setFullScreenWindow(null);
			dispose();
		}

		@Override
		public Graphics acquireGraphics() {
			return getBufferStrategy().getDrawGraphics();
		}

		@Override
		public void releaseGraphics() {
			getBufferStrategy().show();
			getBufferStrategy().getDrawGraphics().dispose();
		}
	};

	private static final String IDPREFIX = "\\Display";
	private static MonitorManager instance = null;

	private HashMap<Integer, MonitorImpl> monitorMap = new HashMap<Integer, MonitorImpl>();

	private MonitorManager() {
		// Mach ne Factory :)
	}

	/**
	 * @return
	 */
	private static synchronized MonitorManager getInstance() {
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
	 * Zeigt auf allen angeschlossenen Monitoren ihre ID an, ueber die sie
	 * angesprochen werden koennen.
	 * 
	 * @param milliseconds Wieviele Millisekunden das Testbild angezeigt werden
	 *                     soll.
	 */
	public static void test(int milliseconds) {
		MonitorManager mm = getInstance();
		Font font = new Font("Verdana", Font.BOLD, 64);
		for (Map.Entry<Integer, MonitorImpl> kv : mm.monitorMap.entrySet()) {
			Integer id = kv.getKey();
			MonitorImpl monitor = kv.getValue();

			monitor.switchToFullScreen();

			Graphics g = monitor.acquireGraphics();
			g.setFont(font);
			g.setColor(Color.BLUE);
			g.drawString("Display #" + id, 100, 100);
			monitor.releaseGraphics();
		}

		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		for (MonitorImpl monitor : mm.monitorMap.values()) {
			monitor.gd.setFullScreenWindow(null);
			monitor.dispose();
		}
	}

	public static void main(String[] args) {
		MonitorManager.test(5555);
	}
}
