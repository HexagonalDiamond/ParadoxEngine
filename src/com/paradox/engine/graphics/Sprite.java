package com.paradox.engine.graphics;

import com.paradox.engine.GameObject;

public abstract class Sprite implements GameObject {
	protected Graphics graphics;
	public Sprite(Graphics g) {
		this.graphics = g;
	}
}
