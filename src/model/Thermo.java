package model;

import java.util.Observable;

public class Thermo extends Observable {
	private int temperature;
	private int max;
	private int min;
	
	/**
	 * Constructeur à vide
	 */
	public Thermo() {
		super();
	}
	
	/**
	 * Constructeur
	 * @param temperature
	 * @param max
	 * @param min
	 */
	public Thermo(int temperature, int max, int min) {
		this.temperature = temperature;
		this.max = max;
		this.min = min;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	
	
	
}
