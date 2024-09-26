package de.sky.kai;

/**
 * @author Kai Fabian
 */
public class TimeLoop {

	private long lastTS = 0;

	private boolean abort = false;

	private long sumDiffs = 0;
	private long numDiffs = 0;
	private long minDiff = Long.MAX_VALUE;
	private long maxDiff = Long.MIN_VALUE;

	/**
	 * @param abortIfTimeExceeded true wenn das Programm abbrechen soll, wenn die
	 *                            Schleife nicht schnell genug durchlaufen werden
	 *                            konnte, false wenn es einfach weiterlaufen soll.
	 */
	public TimeLoop(boolean abortIfTimeExceeded) {
		abort = abortIfTimeExceeded;
	}

	/**
	 * Haelt solange an, bis die angegebene Anzahl an Millisekunden erreicht wurde.
	 * Kann genutzt werden, um in einer (Game-)Loop immer die gleiche Zeit pro
	 * Schleifendurchlauf zu benoetigen.
	 * 
	 * @param milliseconds
	 */
	public void sync(int milliseconds) {
		if (lastTS != 0) {
			long diff = System.currentTimeMillis() - lastTS;
			if (diff > milliseconds && abort == true)
				throw new RuntimeException(
						"Loop cycle took longer (" + diff + " ms) than specified (" + milliseconds + " ms)");
			else if (diff < milliseconds) {
				try {
					Thread.sleep(milliseconds - diff);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}

			sumDiffs += diff;
			numDiffs++;
			if (minDiff > diff)
				minDiff = diff;
			if (maxDiff < diff)
				maxDiff = diff;

//			System.out.print(" " + (System.currentTimeMillis() - lastTS));
		}
		lastTS = System.currentTimeMillis();
	}

	public long getMaximum() {
		return maxDiff;
	}

	public long getMinimum() {
		return minDiff;
	}

	public long getAverage() {
		return sumDiffs / numDiffs;
	}

//	public static void main(String[] args) throws InterruptedException {
//		Random r = new Random();
//		TimeLoop l = new TimeLoop(false);
//		for (int i = 0; i < 10; i++) {
//			System.out.print(System.currentTimeMillis());
//			Thread.sleep(10 + r.nextInt(21));
//			l.sync(10);
//			System.out.println();
//		}
//
//		System.out.println("AVR: " + l.getAverage());
//		System.out.println("MIN: " + l.getMinimum());
//		System.out.println("MAX: " + l.getMaximum());
//	}

}
