package de.sky.kai;

import java.util.ArrayList;
import java.util.List;

import de.sky.start.RectStar;
import de.sky.start.TapetenOject;
import lombok.Data;

@Data
public class Tapete {
	private int breite;
	private int hoehe;
	private List<MonitorPosition> monitore = new ArrayList<MonitorPosition>();

	/**
	 * Breite und Höhe in Millimeter
	 * 
	 * @param breite
	 * @param hoehe
	 */
	public Tapete(int breite, int hoehe) {
		this.breite = breite;
		this.hoehe = hoehe;
	}

	public void addMonitorOnPosition(Monitor monitor, int vonLinks, int vonOben) {
		this.monitore.add(new MonitorPosition(monitor, vonLinks, vonOben));

	}

	/**
	 * Guckz, ob der Pixel anzeigt werden soll oder nicht.
	 * 
	 * @param i
	 * @param j
	 */
	public boolean isVisible(int vonLinks, int vonOben) {
		return this.monitore.stream().anyMatch(m -> m.monitorContainsTapetenPunkt(vonLinks, vonOben));

	}

	/**
	 * Gibt den Monitor zurück, auf welchen angezeigt werden soll.
	 * 
	 * @param i
	 * @param j
	 */
	public MonitorPosition whichMonitor(int vonLinks, int vonOben) {

		for (MonitorPosition monitorPosition : monitore) {
			if (monitorPosition.monitorContainsTapetenPunkt(vonLinks, vonOben)) {
				return monitorPosition;
			}
		}

		return null;
	}

	public int calcX(MonitorPosition mp, TapetenOject star) {

		Monitor m = mp.getMonitor();
		int result = calculateMonitorPixel(star.getTapeteX(), mp.getLinksObenX(), m.getPhysicalWidth(),
				m.getPixelWidth());

//		float startX = star.getTapeteX() - mp.getLinksObenX();
//		float widthFactor = (float) m.getPhysicalWidth() / (float)m.getPixelWidth();
//		
////		float px = (star.getTapeteX() - mp.getLinksObenX()) / (float) ((float) m.getPhysicalWidth() / (float)m.getPixelWidth());
//		float px = startX / widthFactor;

		return (int) result;
	}

	public int calculateMonitorPixel(int objectTapete, int monitorstart, float physical, float pixel) {
		float start = objectTapete - monitorstart;
		float widthFactor = physical / pixel;

		float px = start / widthFactor;

		return (int) px;
	}

	public int calcY(MonitorPosition mp, TapetenOject star) {
		Monitor m = mp.getMonitor();

		int result = calculateMonitorPixel(star.getTapeteY(), mp.getLinksObenY(), m.getPhysicalHeight(),
				m.getPixelHeight());

//		float py = (star.getTapeteY() - mp.getLinksObenY()) / (float) ((float)m.getPhysicalHeight() / (float)m.getPixelHeight());
		return result;
	}

}
