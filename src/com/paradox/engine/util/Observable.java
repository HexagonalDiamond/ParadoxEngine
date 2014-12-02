package com.paradox.engine.util;

import java.util.ArrayList;

/**
 * Object available to be observed
 */
public class Observable {
	/**
	 * List of observers
	 */
	private ArrayList<Observer> observers;
	/**
	 * initializes observable and creates listeners
	 */
	public Observable() {
		observers = new ArrayList<Observer>();
	}
	/**
	 * Adds observer
	 * @param o observer
	 */
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	/**
	 * Notifies observers
	 */
	public void notifyObservers(Object[] args) {
		for(Observer o:this.observers) {
			o.update(this, args);
		}
	}
}
