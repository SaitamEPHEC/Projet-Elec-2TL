package model;

import java.util.Observable;

public class Temperature extends Observable {
	private int temperatureActuelle;
	private int temperatureSeuil;
	
	/**
	 * Constructeur à vide
	 */
	public Temperature() {
		super();
	}
	
	/**
	 * Constructeur
	 * @param temperature
	 * @param max
	 */
	public Temperature(int temperatureActuelle, int temperatureSeuil) {
		this.temperatureActuelle = temperatureActuelle;
		this.temperatureSeuil = temperatureSeuil;
	}

	public int getTemperature() {
		return temperatureActuelle;
	}

	public void setTemperature(int temperature) {
		this.temperatureActuelle = temperature;
	}

	public int getMax() {
		return temperatureSeuil;
	}

	public void setMax(int max) {
		this.temperatureSeuil = max;
	}	
}
