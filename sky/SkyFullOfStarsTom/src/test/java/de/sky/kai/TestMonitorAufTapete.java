package de.sky.kai;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestMonitorAufTapete {
	@Test
	void punktIsVisible() throws Exception {
		Tapete tapete = initMonitore();

		assertTrue(tapete.isVisible(180, 500));
	}

	@Test
	void tpIstooleftAndNotVisible() throws Exception {
		Tapete tapete = initMonitore();

		assertFalse(tapete.isVisible(174, 500));
	}

	@Test
	void tpIstooHighAndNotVisible() throws Exception {
		Tapete tapete = initMonitore();

		assertFalse(tapete.isVisible(200, 489));
	}

	@Test
	void tpIsOnM1() throws Exception {

		Tapete tapete = initMonitore();
		Monitor m1 = tapete.getMonitore().get(0).getMonitor();
		assertEquals(m1, tapete.whichMonitor(200, 500));
	}

	@Test
	void tpIsOnM2() throws Exception {

		Tapete tapete = initMonitore();
		Monitor m1 = tapete.getMonitore().get(1).getMonitor();
		assertEquals(m1, tapete.whichMonitor(1000, 700));
	}

	/**
	 * Test Initialisierung
	 * 
	 * @return
	 */
	private Tapete initMonitore() {
		MonitorManager mm = MonitorManager.getInstance();

		Tapete tapete = new Tapete(1600, 730);

		Monitor m1 = mm.getMonitor(2, 520, 325); // disp_eizo_013
		Monitor m2 = mm.getMonitor(0, 325, 520); // disp_eizo_307

		tapete.addMonitorOnPosition(m1, 175, 490);
		tapete.addMonitorOnPosition(m2, 910, 610);

		return tapete;
	}
}
