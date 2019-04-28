package controller;

import model.Thermo;
import vue.ThermoVue;

public class ThermoController {
	
	protected Thermo model;
	protected ThermoVue vue;
	
	public ThermoController(Thermo model) {
		this.model = model;
	}
	public void addView(ThermoVue vue) {
		this.vue = vue;
	}
}
