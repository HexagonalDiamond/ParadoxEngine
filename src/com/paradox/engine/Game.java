package com.paradox.engine;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.paradox.engine.util.Observable;
import com.paradox.engine.util.Observer;
 
 
import org.lwjgl.opengl.GL11.*;

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
		initialize(); //should always be last call
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
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
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
	
	public static void main(String[] args) {
		
	}
}
