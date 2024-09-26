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

}
