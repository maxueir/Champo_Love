package application;

import java.util.ArrayList;

public class Modele {//classe Modele du MV(C) 
	Profil profilPerso;//profil de l'utilisateur
	ArrayList<Profil> coupdecoeur;
	
	public Modele() {
		this.coupdecoeur=new ArrayList<Profil>();
	}

	public static void main(String[] args) {
		Modele modele= new Modele();
		Main main = new Main();
		main.main(modele);

	}

}
