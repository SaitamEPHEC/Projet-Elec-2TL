package controller;

import vue.ThermoVue;

public class ThermoController {
	
	protected XXXX model;
	protected ThermoVue vue;
	
	public ThermoController(XXXX model) {
		this.model = model;
	}
	public void addView(ThermoVue vue) {
		this.vue = vue;
	}
}
