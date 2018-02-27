package interfaceGraphique.Alice;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;


import useCase.alice.PersonnageMarvel;

public class AffichagePerso {
	JButton bouton1 = new JButton("Rechercher");

	JPanel panel = new JPanel();
	JPanel pan3 = new JPanel();
	JScrollPane scrollPane = new JScrollPane() ;
	JLabel resu = new JLabel();
	JLabel resuf = new JLabel();

	MyTextPane resultatH = new MyTextPane();

	String recherche = "";
	JPanel pan2 = new JPanel();
	JTextField id = new JTextField(20);
	
	public AffichagePerso(){
		
		Ecouteur listen = new Ecouteur();
		resultatH.setOpaque(true);
		resultatH.setContentType("text/html");
		bouton1.addActionListener(listen);
		
		
		JPanel pan = new JPanel();
		pan.add(id);
		resultatH.setEditable(false);
		JPanel pan1 = new JPanel();
		pan1.add(bouton1);
		id.setText("Exemple : Spider-Man ");
		pan.setAlignmentY(Component.TOP_ALIGNMENT);
		pan1.setAlignmentY(Component.CENTER_ALIGNMENT);
		pan2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pan3.add(pan);
		pan3.add(pan1 );
		
		pan3.setAlignmentY(Component.CENTER_ALIGNMENT);
		scrollPane = new JScrollPane(resultatH);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(resultatH);
		scrollPane.setPreferredSize(new Dimension(700,670));
		pan3.add(scrollPane);
		panel = pan3;
		id.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		    	id.setText("");
		    }
		});	
	}
	
	public class  Ecouteur implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			if (e.getSource().equals(bouton1) ){
				recherche = id.getText();
				
				PersonnageMarvel nouvel = new PersonnageMarvel();
				resultatH.setText(nouvel.getInfo(recherche));
			}
		}
	}

	public class MyTextPane extends JTextPane {
	    public MyTextPane(){
	        super();
	    }
	 
}

	public Component envoie() {
		// TODO Auto-generated method stub
		return pan3;
	}}
