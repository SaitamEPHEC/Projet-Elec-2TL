package model;

import java.util.Observable;

public class Temperature extends Observable {
	private int temperatureActuelle;
	private int temperatureSeuil;
	private COMListener com;
	private SerialWriter writer;
	String message = "Les messages d'erreurs apparaissent ici";
	
	/**
	 * Constructeur à vide
	 */
	public Temperature() {
		super();
	}
	
	/**
	 * Constructeur avec 2 parametres
	 * @param temperatureActuelle
	 * @param temperatureSeuil
	 */
	public Temperature(int temperatureActuelle, int temperatureSeuil) {
		try {
			this.temperatureActuelle = temperatureActuelle;
			this.temperatureSeuil = temperatureSeuil;
			com = new COMListener("COM4");
			writer = new SerialWriter(com.out);
			new Thread(writer).start();
		} catch (Exception e) {
        	e.printStackTrace();
		}
	}
	
	public void traiteData(int x) {
    	if(temperatureActuelle<temperatureSeuil){
    		message = "Température dans les normes";
    		setChanged();
			notifyObservers();
    	}
    	if(temperatureActuelle>temperatureSeuil){
    		message = "Supérieur au seuil maximum !";
    		setChanged();
			notifyObservers();
    	}
   	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTemperatureActuelle() {
		return temperatureActuelle;
	}

	public void setTemperatureActuelle(int temperatureActuelle) {
		this.temperatureActuelle = temperatureActuelle;
		setChanged();
		notifyObservers();
	}

	public int getTemperatureSeuil() {
		return temperatureSeuil;
	}

	public void setTemperatureSeuil(int temperatureSeuil) {
		this.temperatureSeuil = temperatureSeuil;
		writer.send(temperatureSeuil);
		setChanged();
		notifyObservers();
		
	}

	public COMListener getCom() {
		return com;
	}

	public void setCom(COMListener com) {
		this.com = com;
	}
	
	
	
}
