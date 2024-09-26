package de.sky.kai;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.sky.start.RectStar;
import lombok.Data;

@Data
public class Tapete {
	private int breite;
	private int hoehe;
	private List<MonitorPosition> monitore = new ArrayList<MonitorPosition>();

	/**
	 * Breite und Höhe in Millimeter
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
	 * @param i
	 * @param j
	 */
	public boolean isVisible(int vonLinks, int vonOben) {
		return this.monitore.stream().anyMatch( m -> m.monitorContainsTapetenPunkt(vonLinks, vonOben));
		
	}
	/**
	 * Gibt den Monitor zurück, auf welchen angezeigt werden soll.
	 * @param i
	 * @param j
	 */
	public Monitor whichMonitor(int vonLinks, int vonOben) {
		
		for (MonitorPosition monitorPosition : monitore) {
			if (monitorPosition.monitorContainsTapetenPunkt(vonLinks, vonOben)) {
				return monitorPosition.getMonitor();
			}
		}
		
		return null;
	}

	public List<Monitor> allMonitore() {
		return this.monitore.stream().map(m -> m.getMonitor()).collect(Collectors.toList());
		
	}

	public int calcX(Monitor m, RectStar star) {

		int px = (star.getTapeteX() - monitorX(m) ) /( m.getPhysicalWidth() / m.getPixelWidth());
		
		return px;
	}

	private int monitorX(Monitor m) {
		return 0;
	}

	public int calcY(Monitor m, RectStar star) {
		int py = (star.getTapeteY() - monitorY(m) ) /( m.getPhysicalHeight() / m.getPixelHeight());
		return py;
	}

	private int monitorY(Monitor m) {
		// TODO Auto-generated method stub
		return 0;
	}

}
