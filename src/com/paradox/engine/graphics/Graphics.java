package com.paradox.engine.graphics;

import java.awt.Font;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;

public class Graphics {
	/**
	 * Initial width of window
	 */
	private int width = 800;
	/**
	 * Initial hieght of window
	 */
	private int height = 600;
	/**
	 * Default font
	 */
	public TrueTypeFont font;
	
	/**
	 * Draw a line from x1, y1 to x2, y2
	 * @param x1 X value of first point
	 * @param y1 Y value of first point
	 * @param x2 X value of second point
	 * @param y2 Y value of second point
	 * @param width width of line
	 */
	public void drawLine(float x1, float y1, float x2, float y2, int width) {
		GL11.glLineWidth(width); 
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2f(x1, y1);
		GL11.glVertex2f(x2, y2);
		GL11.glEnd();
	}
	
	/**
	 * Draw a rectangle
	 */
	public void drawRect(float x, float y, float w, float h) {
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(x,y);
		    GL11.glVertex2f(x,y+h);
		    GL11.glVertex2f(x+w,y+h);
		    GL11.glVertex2f(x+w,y);
	    GL11.glEnd();
	}

	/**
	 * Draws an arc
	 * @param cx center x
	 * @param cy center y
	 * @param r radius
	 * @param num_segments segments of arc
	 * @param start_angle angle to start at in radians
	 * @param end_angle angle to end at in radians
	 */
	public void drawArc(int cx, int cy, int r, int num_segments, float start_angle, float end_angle) {
		GL11.glBegin(GL11.GL_LINE_STRIP); 
	    for(int ii = 0; ii < num_segments; ii++) 
	    { 
	        float theta = start_angle + (ii * Math.abs(end_angle - start_angle) / num_segments);//get the current angle 

	        float x = (float) (r * Math.cos(theta));//calculate the x component 
	        float y = (float) (r * Math.sin(theta));//calculate the y component 

	        GL11.glVertex2f(x + cx - 1, y + cy);//output vertex 
	    } 
	    GL11. glEnd(); 
	}
	/**
	 * Initializes fonts
	 */
	public void initFonts() {
		Font awtFont = new Font("Arial", Font.BOLD, 12);
		font = new TrueTypeFont(awtFont, true);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	/**
	 * Draws a filled circle
	 * @param f center x
	 * @param g center y
	 * @param r radius
	 */
	public void drawCircle(int f, int g, int r) {
		GL11.glPushMatrix();
		GL11.glTranslatef(f, g, 0);
		GL11.glScalef(r, r, 1);

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(0, 0);
		for(int i = 0; i <= 360; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
		    double angle = Math.PI * 2 * i / 360;
		    GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
		}
		GL11.glEnd();

		GL11.glPopMatrix();
	}
	/**
	 * Initializes OpenGL
	 */
	public void initialize() {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setResizable(true);
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		initFonts();
	}
	
	public void drawText(String text, int x, int y) {
		drawText(text, x, y, this.font);
	}
	
	public void drawText(String text, int x, int y, TrueTypeFont font) {
		int width = 10 + font.getWidth(text);
		int height = 10 + font.getHeight(text);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		font.drawString(x - (width / 2) + 5, y - (height / 2) + 5, text);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
