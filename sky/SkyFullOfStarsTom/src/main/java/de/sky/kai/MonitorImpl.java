package de.sky.kai;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;

/**
 * @author Kai Fabian
 */
class MonitorImpl extends Frame implements Monitor {

	private static final long serialVersionUID = 3551071724718610914L;

	private GraphicsDevice gd = null;
	private int physWidth = 0;
	private int physHeight = 0;
	private float pixelPerMillimeterX = 0f;
	private float pixelPerMillimeterY = 0f;

	public MonitorImpl(GraphicsDevice gd) {
		this.gd = gd;
	}

	@Override
	public void switchToFullScreen() {
		setIgnoreRepaint(true);
		setUndecorated(true);
		gd.setFullScreenWindow(this);
		createBufferStrategy(2);
	}

	@Override
	public void destroy() {
		gd.setFullScreenWindow(null);
		dispose();
	}

	@Override
	public Graphics acquireGraphics() {
		return getBufferStrategy().getDrawGraphics();
	}

	@Override
	public void displayGraphics() {
		getBufferStrategy().show();
		getBufferStrategy().getDrawGraphics().dispose();
	}

	@Override
	public int getPixelWidth() {
		return gd.getDisplayMode().getWidth();
	}

	@Override
	public int getPixelHeight() {
		return gd.getDisplayMode().getHeight();
	}

	@Override
	public int getPhysicalWidth() {
		return physWidth;
	}

	public void setPhysicalWidth(int w) {
		physWidth = w;
		pixelPerMillimeterX = (float) getPixelWidth() / (float) physWidth;
		System.out.println("X :> " + pixelPerMillimeterX);
	}

	@Override
	public int getPhysicalHeight() {
		return physHeight;
	}

	public void setPhysicalHeight(int h) {
		physHeight = h;
		pixelPerMillimeterY = (float) getPixelHeight() / (float) physHeight;
		System.out.println("Y :> " + pixelPerMillimeterY);
	}

	@Override
	public int getBitsPerPixel() {
		return gd.getDisplayMode().getBitDepth();
	}

	@Override
	public int getRefreshRate() {
		return gd.getDisplayMode().getRefreshRate();
	}

	GraphicsDevice getGraphicsDevice() {
		return gd;
	}

	@Override
	public float getPixelPerMillimeterX() {
		return pixelPerMillimeterX;
	}

	@Override
	public float getPixelPerMillimeterY() {
		return pixelPerMillimeterY;
	}
};
