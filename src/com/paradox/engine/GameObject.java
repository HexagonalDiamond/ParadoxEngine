package com.paradox.engine;

public interface GameObject {
	/**
	 * Initializes extra assets after graphics, sound, input are loaded
	 */
	public abstract void initialize();
	
	/**
	 * Draws graphics
	 */
	public abstract void draw();
	
	/**
	 * Updates state
	 */
	public abstract void update();

	/**
	 * Responds to input
	 */
	public abstract void input();
}
