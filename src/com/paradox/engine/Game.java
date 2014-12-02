package com.paradox.engine;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.paradox.engine.util.Observable;
import com.paradox.engine.util.Observer;
 
 
import static org.lwjgl.opengl.GL11.*;

public abstract class Game implements Observer {
	private int width = 800;
	private int height = 600;
	/**
	 * Is the game running
	 */
	private boolean running;
	/**
	 * Loop game 1 frame
	 */
	public void loop() {
		update();
		draw();
		Display.update();
		timing();
		input();
	}

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
		initGraphics(windowName);
		initInput();
	}
	/**
	 * Initialize graphics
	 * @param windowName What to name the window
	 */
	public void initGraphics(String windowName) {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setTitle(windowName);
			Display.setResizable(true);
			
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	/**
	 * Initializes input
	 */
	public void initInput() {
		try {
			Keyboard.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
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

}
