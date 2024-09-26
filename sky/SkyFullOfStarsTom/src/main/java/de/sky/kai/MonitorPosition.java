package de.sky.kai;

import lombok.Data;

@Data
public class MonitorPosition {

	private Monitor monitor;

	private int linksObenX;
	private int linksObenY;
	private int rechtsUntenX;
	private int rechtsUntenY;

	public MonitorPosition(Monitor monitor, int vonLinks, int vonOben) {
		this.monitor = monitor;
		this.linksObenX = vonLinks;
		this.linksObenY = vonOben;
		this.rechtsUntenX = vonLinks + this.monitor.getPhysicalWidth();
		this.rechtsUntenY = vonOben + this.monitor.getPhysicalHeight();
	}
	/**
	 * Prüft, ob ein TapetenPunkt auf diesen Monitor angezeigt wird.
	 * @param tpX
	 * @param tpY
	 * @return
	 */
	public boolean monitorContainsTapetenPunkt(int tpX, int tpY) {
		// 
		if (tpX< linksObenX || tpX >= rechtsUntenX) {
			return false;
		}
		if (tpY < linksObenY || tpY >= rechtsUntenY ) {
			return false;
		}
		
		return true;
	}
}
