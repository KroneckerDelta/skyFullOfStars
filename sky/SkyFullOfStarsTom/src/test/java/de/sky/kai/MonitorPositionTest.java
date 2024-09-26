package de.sky.kai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonitorPositionTest {

	@Test
	void obenLinksX() {
		MonitorManager mm = MonitorManager.getInstance();
		MonitorPosition monitorPosition = new MonitorPosition(mm.getMonitor(0, 1000, 2000), 5, 10);
		assertEquals(5, monitorPosition.getLinksObenX());
	}
	@Test
	void obenLinksY() {
		MonitorManager mm = MonitorManager.getInstance();
		MonitorPosition monitorPosition = new MonitorPosition(mm.getMonitor(0, 1000, 2000), 5, 10);
		assertEquals(10, monitorPosition.getLinksObenY());
	}
	@Test
	void untenRechtsX() {
		MonitorManager mm = MonitorManager.getInstance();
		MonitorPosition monitorPosition = new MonitorPosition(mm.getMonitor(0, 1000, 2000), 5, 10);
		assertEquals(1005, monitorPosition.getRechtsUntenX());
	}
	@Test
	void untenRechtsY() {
		MonitorManager mm = MonitorManager.getInstance();
		MonitorPosition monitorPosition = new MonitorPosition(mm.getMonitor(0, 1000, 2000), 5, 10);
		assertEquals(2010, monitorPosition.getRechtsUntenY());
	}

}
