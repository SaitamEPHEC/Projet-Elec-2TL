package vue;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ThermoController;
import model.SerialReader;
import model.Temperature;

public class ThermoVue extends JFrame implements ActionListener,Observer{
	
	private static final long serialVersionUID = 1L;
	protected Temperature model;
	protected ThermoController controller;
	private SerialReader reader;
	
	private JTextField erreurs; // = new JTextField("Les messages d'erreurs apparaissent ici",30);
	private JLabel labelTempActuelle; //= new JLabel("La temp actuelle est : " + model.getTemperatureActuelle() + "°C.");

	
	/**
	 * Constructeur 
	 * @param model
	 * @param controller
	 */
	public ThermoVue(Temperature model, ThermoController controller) {
		this.model = model;
		this.controller = controller;
		reader = new SerialReader(model.getCom().in, model);
		model.addObserver(this); // connexion entre vue et	modele
		
		erreurs = new JTextField("Les messages d'erreurs apparaissent ici",30);
		labelTempActuelle = new JLabel("La temp actuelle est : " + model.getTemperatureActuelle() + "°C.");
		new Thread(reader).start();
		
		JFrame test = new JFrame("Thermometre");
		
		test.setSize(400, 300);
		test.setResizable(false);
		test.setDefaultCloseOperation(EXIT_ON_CLOSE);

		erreurs.setEditable(false);
		
		JButton button2 = new JButton("Exit");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		
//		JLabel labelTempActuelle = new JLabel("La temp actuelle est : " + temperatureUpdated + "°C.");
		
		JLabel labelSeuil = new JLabel("La température seuil est : " + model.getTemperatureSeuil() + "°C.");
		
		JLabel labelSeuilModif = new JLabel("Pour modifier la température seuil, écrivez dans la case ==>");
		
		JTextField texte = new JTextField("",10);
		texte.addActionListener(new ActionListener() { // action perform sur la case texte pour pouvoir utiliser enter
			public void actionPerformed(ActionEvent e) {
				model.setTemperatureSeuil(Integer.parseInt(texte.getText()));
				System.out.println("Nouveau seuil de :" + Integer.parseInt(texte.getText()) + "°C.");
				labelSeuil.setText("La température seuil est : " + Integer.parseInt(texte.getText()) + "°C.");
			}
		});
		

		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() { // action perform sur le bouton submit
			public void actionPerformed(ActionEvent e) {
				model.setTemperatureSeuil(Integer.parseInt(texte.getText()));
				System.out.println("Nouveau seuil de :" + Integer.parseInt(texte.getText()) + "°C.");
				labelSeuil.setText("La température seuil est : " + Integer.parseInt(texte.getText()) + "°C.");
			}
		});
		
		JPanel p = new JPanel(new FlowLayout());
		p.add(labelTempActuelle);
		p.add(labelSeuil);
		p.add(labelSeuilModif);
		p.add(texte);
		p.add(erreurs);
		p.add(button);
		p.add(button2);
		test.add(p);
		
		test.setVisible(true);
	}

	/**
	 * Permet d'afficher XXXXXXXXXXXXXXXX
	 * @param string un string a faire passer
	 */
	public void affiche(String string) {
	}
	
	/**
	 * Permet de mettre a jour la GUI
	 * @param o un observable
	 * @param arg un object
	 */
	public void update(Observable o, Object arg) {
		erreurs.setText(model.getMessage());  //NomDeLaFrame = La Frame où tu veux mettre le message qui affiche l'alerte ou
		 											  //non avec le message adéquat
		labelTempActuelle.setText("La temp actuelle est : " + model.getTemperatureActuelle() + "°C.");
//		setTemperatureActuelle(model.getTemperatureActuelle());
													 
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
