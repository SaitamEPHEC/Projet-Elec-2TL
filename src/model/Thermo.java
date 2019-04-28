package model;

import java.util.Observable;

public class Thermo extends Observable {
	private double temperature;
	private double max;
	private double min;
	
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
	public Thermo(double temperature, double max, double min) {
		this.temperature = temperature;
		this.max = max;
		this.min = min;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}
	
	
	
}
