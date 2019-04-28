package vue;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ThermoController;

public class ThermoVue extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
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
		
		JFrame test = new JFrame("Thermometre");
		
		test.setSize(400, 300);
		test.setResizable(false);
		test.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLayout(new BorderLayout());
		
		
		JButton button2 = new JButton("Exit");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
//		add(button2,BorderLayout.EAST);
		
		JLabel temp = new JLabel("La temp actuelle est : 20°C.");
//		add(temp, BorderLayout.SOUTH);
		
		JLabel temp2 = new JLabel("Pour modifier la limite écrivez dans la case ==>");
		
		JTextField texte = new JTextField("",10);
		texte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(	Integer.parseInt(texte.getText()));
			}
		});
		
//		add(texte, BorderLayout.CENTER);
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(Integer.parseInt(texte.getText()));
			}
		});
//		add(button, BorderLayout.WEST);
		
		JPanel p = new JPanel(new FlowLayout());
		p.add(temp);
		p.add(temp2);
		p.add(texte);
		p.add(button);
		p.add(button2);
		test.add(p);
		
		test.setVisible(true);
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


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
