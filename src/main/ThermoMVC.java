package main;

import controller.ThermoController;
import model.Thermo;
import vue.ThermoVue;

public class ThermoMVC {
	
	public ThermoMVC() {
			
			//Cr�ation du mod�le
			Thermo modele = new Thermo();
			
			//Cr�ation du controleur
			//Le contr�leur doit avoir une r�f�rence vers le mod�le pour pouvoir le commander
			ThermoController controleur = new ThermoController(modele);
			
			//Creation de la vue GUI
			//La vue doit connaitre son controleur et avoir une reference vers le modele pour pouvoir l'observer
			ThermoVue vueGui = new ThermoVue(modele, controleur);
			
			//On donne la reference a  la vue pour le controleur
			controleur.addView(vueGui);
			
		}
		
		public static void main(String args[]) {
			//Schedule a job for the event-dispatching thread:
			//creating and showing this application's GUI.
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new ThermoMVC();
				}
			});
		}
}
