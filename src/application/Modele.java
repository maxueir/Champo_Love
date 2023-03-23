package application;

import java.util.ArrayList;
import java.util.Set;

public class Modele {//classe Modele du MV(C) 
	
	Profil profilPerso;//profil de l'utilisateur
	int pref_age_min;
	int pref_age_max;
	int pref_distance;
	Set<String> preferences;
	ArrayList<Profil> coupdecoeur;
	ArrayList<Profil> matchs;
	ArrayList<Profil> recales;
	ArrayList<Profil> valides;
	
	public Modele() {
		this.coupdecoeur=new ArrayList<Profil>();
		this.matchs=new ArrayList<Profil>();
		this.recales=new ArrayList<Profil>();
		this.valides=new ArrayList<Profil>();
	}

	

}
