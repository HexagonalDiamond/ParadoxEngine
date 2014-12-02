package com.paradox.engine.manager;

import java.util.ArrayList;

import com.paradox.engine.GameObject;

public class GameObjectManager {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public GameObjectManager() {
		
	}
	public ArrayList<GameObject> getObjects() {
		return objects;
	}
	public void addObject(GameObject o) {
		this.objects.add(o);
	}
	public void removeObject(GameObject o) {
		this.objects.remove(o);
	}
	public void draw() {
		for(GameObject o:objects) {
			o.draw();
		}
	}
	public void update() {
		for(GameObject o:objects) {
			o.update();
		}
	}
	public void input() {
		for(GameObject o:objects) {
			o.input();
		}
	}
}
