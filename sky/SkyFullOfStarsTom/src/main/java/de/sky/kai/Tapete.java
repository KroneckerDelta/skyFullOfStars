package de.sky.kai;

import java.util.ArrayList;
import java.util.List;

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

}
