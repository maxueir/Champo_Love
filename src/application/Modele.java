package application;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javafx.scene.image.Image;

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
	ArrayList<Profil> ensembleProfils;

	public Modele() {
		this.ensembleProfils=new ArrayList<Profil>() ;
		this.coupdecoeur=new ArrayList<Profil>();
		this.matchs=new ArrayList<Profil>();
		this.recales=new ArrayList<Profil>();
		this.valides=new ArrayList<Profil>();
		File imFemmes = new File("images/femme");
		File[] filesFemmes = imFemmes.listFiles();
		for (File file : filesFemmes) {
			if (file.isFile()) {
					ensembleProfils.add(new Profil(file.getName()));
					
				
				
				
			}
		}
		File imHommes = new File("images/homme");
		File[] filesHommes = imHommes.listFiles();
		for (File file : filesHommes) {
			if (file.isFile()) {
				ensembleProfils.add(new Profil(file.getName()));
			}
		}




	}
	
	
	public Profil prochainprofil() {
		return this.ensembleProfils.get(0);
	}
}