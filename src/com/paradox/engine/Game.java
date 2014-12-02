package com.paradox.engine;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.paradox.engine.graphics.Graphics;
import com.paradox.engine.manager.GameObjectManager;
import com.paradox.engine.util.Observable;
import com.paradox.engine.util.Observer;
 
 
import org.lwjgl.opengl.GL11.*;

public abstract class Game implements Observer, GameObject {
	/**
	 * Graphics object
	 */
	protected Graphics graphics = new Graphics();;
	
	/**
	 * Game object manager
	 */
	protected GameObjectManager gom = new GameObjectManager();
	
	/**
	 * Is the game running
	 */
	private boolean running;
	/**
	 * Loop game 1 frame
	 */
	protected void loop() {
		gom.update();
		gom.draw();
		Display.update();
		timing();
		gom.update();
		gom.input();
	}
	
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
		graphics.initialize();
		Display.setTitle(windowName);
		gom.addObject(this);
		initInput();
		initialize(); //should always be last call
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
