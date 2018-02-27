package interfaceGraphique;
import useCase.MainClass;
import useCase.alice.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.*;

import interfaceGraphique.Alice.AffichageComic;
import interfaceGraphique.Alice.AffichagePerso;

public class OngletAlice{
	JTabbedPane ongletA  = new JTabbedPane();
	JPanel pan = new JPanel();

		public OngletAlice(){
			AffichageComic  ong1 = new AffichageComic();
			//pan =(ong1.envoie());
			ongletA.add("AffichageComic", ong1.envoie());
			
			AffichagePerso  ong2 = new AffichagePerso();
			//pan =(ong1.envoie());
			ongletA.add("AffichagePerso", ong2.envoie());
			
		}

		
	public  JTabbedPane envoieAlice(){
		//new OngletAlice();
		return ongletA;
	}
	public static void main(String[] args) {
		JFrame fen = new JFrame();
		JTabbedPane fullonglet  = new JTabbedPane();
		OngletAlice al = new OngletAlice();
		AffichageComic affiComic = new AffichageComic();
		AffichagePerso affiPerso = new AffichagePerso();
		
		//fullonglet.addTab("MABITE", al.pan);
		fullonglet.addTab("AffichageComic", affiComic.envoie());
		fullonglet.addTab("AffichagePerso",affiPerso.envoie());
		
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
		fen.getContentPane().add(fullonglet);
		//On definit une taille suffisante pour tout afficher.
		fen.setSize(1000, 1000);
	}
		
}
