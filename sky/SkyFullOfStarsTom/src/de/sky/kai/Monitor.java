package de.sky.kai;

import java.awt.Graphics;

/**
 * @author Kai Fabian
 */
public interface Monitor {

	/**
	 * Schaltet in den Vollbildmodus um. Sollte nur einmal aufgerufen werden!
	 * Zurueck geht es mit {@link Monitor#destroy()}.
	 */
	public void switchToFullScreen();

	/**
	 * Zerstoert das Fenster (Vollbild). Sollte nur einmal am Ende aufgerufen
	 * werden, nachdem mit der Methode {@link Monitor#switchToFullScreen()} in den
	 * Vollbild-Modus geschaltet wurde.
	 */
	public void destroy();

	/**
	 * Liefert das Graphics-Objekt, mit dem gezeichnet werden kann. Nach dem
	 * Zeichnen muss zwingend die Methode {@link Monitor#releaseGraphics()}
	 * aufgerufen werden!
	 * 
	 * @return Das Graphics-Objekt
	 */
	public Graphics acquireGraphics();

	/**
	 * Beendet das Zeichnen. Muss nach {@link Monitor#acquireGraphics()} aufgerufen
	 * werden.
	 */
	public void releaseGraphics();

}
