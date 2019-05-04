package controller;

import model.Temperature;
import vue.ThermoVue;

public class ThermoController {
	
	protected Temperature model;
	protected ThermoVue vue;
	
	public ThermoController(Temperature model) {
		this.model = model;
	}
	
	public void addView(ThermoVue vue) {
		this.vue = vue;
	}
	
	
}
