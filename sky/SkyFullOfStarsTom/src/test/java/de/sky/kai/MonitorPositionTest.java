package de.sky.kai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonitorPositionTest {

	private static final int BREITE = 1000;
	private static final int HOEHE = 2000;
	private static final int START_X = 5;
	private static final int START_Y = 10;

	@Test
	void obenLinksX() {
		MonitorPosition monitorPosition = initMonitor();
		assertEquals(5, monitorPosition.getLinksObenX());
	}

	@Test
	void obenLinksY() {
		MonitorPosition monitorPosition = initMonitor();
		assertEquals(10, monitorPosition.getLinksObenY());
	}

	@Test
	void untenRechtsX() {
		MonitorPosition monitorPosition = initMonitor();
		assertEquals(1005, monitorPosition.getRechtsUntenX());
	}

	@Test
	void untenRechtsY() {
		MonitorPosition monitorPosition = initMonitor();
		assertEquals(2010, monitorPosition.getRechtsUntenY());
	}

	// #############################################
	// TP Inside Tests Rechts/Links
	// #############################################

	@Test
	void tpIsInside() throws Exception {
		MonitorPosition monitorPosition = initMonitor();
		assertTrue(monitorPosition.monitorContainsTapetenPunkt(500, 1900));
	}

	@Test
	public void tpIsTooLeft() throws Exception {
		MonitorPosition monitorPosition = initMonitor();
		assertFalse(monitorPosition.monitorContainsTapetenPunkt(4, 1900));
	}

	@Test
	public void tpIsLeftInside() throws Exception {
		MonitorPosition monitorPosition = initMonitor();
		assertTrue(monitorPosition.monitorContainsTapetenPunkt(5, 1900));
	}

	@Test
	public void tpIsTooRight() throws Exception {
		MonitorPosition monitorPosition = initMonitor();
		assertFalse(monitorPosition.monitorContainsTapetenPunkt(1005, 1900));
	}

	@Test
	public void tpIsRightInside() throws Exception {
		MonitorPosition monitorPosition = initMonitor();
		assertTrue(monitorPosition.monitorContainsTapetenPunkt(1004, 1900));
	}

	// #############################################
	// TP Inside Tests Oben/Unten
	// #############################################
	
	
	
	@Test
	void tpIsTooUp() throws Exception {
		MonitorPosition monitorPosition = initMonitor();
		assertFalse(monitorPosition.monitorContainsTapetenPunkt(500, 9));
	}
	@Test
	void tpIsUpIn() throws Exception {
		MonitorPosition monitorPosition = initMonitor();
		assertTrue(monitorPosition.monitorContainsTapetenPunkt(500, 10));
	}
	@Test
	void tpIsTooDeep() throws Exception {
		MonitorPosition monitorPosition = initMonitor();
		assertFalse(monitorPosition.monitorContainsTapetenPunkt(500, 2010));
	}
	@Test
	void tpIsDeepIn() throws Exception {
		MonitorPosition monitorPosition = initMonitor();
		assertTrue(monitorPosition.monitorContainsTapetenPunkt(500, 2009));
	}
	
	
	private MonitorPosition initMonitor() {
		MonitorManager mm = MonitorManager.getInstance();
		MonitorPosition monitorPosition = new MonitorPosition(mm.getMonitor(0, BREITE, HOEHE), START_X, START_Y);
		return monitorPosition;
	}
}
