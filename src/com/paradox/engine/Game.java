package com.paradox.engine;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.paradox.engine.graphics.Graphics;
import com.paradox.engine.util.Observable;
import com.paradox.engine.util.Observer;
 
 
import org.lwjgl.opengl.GL11.*;

public abstract class Game {
	
	/**
	 * Is the game running
	 */
	private boolean running;
	
	/**
	 * Frame number
	 */
	protected int frameNumber;
	
	/**
	 * Loop game 1 frame
	 */
	protected void loop() {
		input();
		update();
		draw();
		Display.update();
		timing();
		frameNumber++;
	}
	
	protected abstract void draw();
	protected abstract void update();
	protected abstract void input();
	
	
	/**
	 * Maintains FPS and runs other timing related functions
	 */
	public void timing() {
		Display.sync(60);
	}
	
	/**
	 * Quits the game
	 */
	protected void quitGame() {
		running = false;
		Display.destroy();
		Keyboard.destroy();
		System.exit(0);
	}
	
	/**
	 * Initialize game
	 * @param windowName What to name the window
	 */
	public void init(String windowName) {
		Graphics.initialize();
		Display.setTitle(windowName);
		initInput();
	}
	
	/**
	 * Initializes input
	 */
	private void initInput() {
		try {
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	/**	
	 * Runs the game
	 * @param windowName What to name the window
	 */
	public void run(String windowName) {
		running = true;
		init(windowName);
		while(running && !Display.isCloseRequested()) {
			loop();
		}
	}
	
	public static void main(String[] args) {
		
	}
}
