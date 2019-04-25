package vue;

import java.util.Observable;

import controller.ThermoController;

public class ThermoVue {
	protected XXXX model;
	protected ThermoController controller;
	
	/**
	 * Constructeur 
	 * @param model
	 * @param controller
	 */
	ThermoVue(XXXX model, ThermoController controller) {
		this.model = model;
		this.controller = controller;
		model.addObserver(this); // connexion entre vue et	modele
	}
	
	
	@Override
	/**
	 * Permet d'afficher un string dans le chat
	 * @param string un string a faire passer
	 */
	public void affiche(String string) {
		
	}
	
	@Override
	/**
	 * Permet de mettre a jour la GUI
	 * @param o un observable
	 * @param arg un object
	 */
	public void update(Observable o, Object arg) {
		
	}
}
