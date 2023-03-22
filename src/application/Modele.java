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
				Image image;
				Profil p;
				try {
					image = new Image(new FileInputStream("images/femme/"+file.getName()));
					p = new Profil(image,file.getName());
					ensembleProfils.add(p);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		/*File imHommes = new File("file:images/homme");
		File[] filesHommes = imHommes.listFiles();
		for (File file : filesHommes) {
			if (file.isFile()) {
				Image image =new Image(file.getName());
				Profil p = new Profil(image);
				ensembleProfils.add(p);
			}
		}*/
		System.out.println(ensembleProfils.size());




	}
	
	
	public Profil prochainprofil() {
		return this.ensembleProfils.get(0);
	}
}