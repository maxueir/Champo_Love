package application;

import java.util.ArrayList;

public class Modele {//classe Modele du MV(C) 
	
	Profil profilPerso;//profil de l'utilisateur
	ArrayList<Profil> coupdecoeur;
	ArrayList<Profil> matchs;
	ArrayList<Profil> recales;
	ArrayList<Profil> valides;
	
	public Modele() {
		this.coupdecoeur=new ArrayList<Profil>();
		for(int i=0;i<10;i++) {
			this.coupdecoeur.add(new Profil());
		}
		
		this.matchs=new ArrayList<Profil>();
		this.recales=new ArrayList<Profil>();
		this.valides=new ArrayList<Profil>();
	}

	

}
