package application;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

public class Modele {//classe Modele du MV(C) 

	ProfilPerso profilPerso;//profil de l'utilisateur
	int pref_age_min;
	int pref_age_max;
	int pref_distance;
	Set<String> preferences;
	ArrayList<ProfilPerso> coupdecoeur;
	ArrayList<ProfilPerso> matchs;
	ArrayList<ProfilPerso> recales;
	ArrayList<ProfilPerso> valides;
	ArrayList<ProfilPerso> ensembleProfilsH;
	ArrayList<ProfilPerso> ensembleProfilsF;
	ArrayList<ProfilPerso> fileAttente;
	

	public Modele() throws InterruptedException {
		this.fileAttente= new ArrayList<ProfilPerso>();
		this.ensembleProfilsH=new ArrayList<ProfilPerso>() ;
		this.ensembleProfilsF=new ArrayList<ProfilPerso>() ;
		this.coupdecoeur=new ArrayList<ProfilPerso>();
		this.matchs=new ArrayList<ProfilPerso>();
		this.recales=new ArrayList<ProfilPerso>();
		this.valides=new ArrayList<ProfilPerso>();
		
		File imFemmes = new File("images/femme");
		File[] filesFemmes = imFemmes.listFiles();
		for (File file : filesFemmes) {
			if (file.isFile()) {
				ensembleProfilsF.add(new ProfilPerso(file.getName()));




			}
		}
		File imHommes = new File("images/homme");
		File[] filesHommes = imHommes.listFiles();
		for (File file : filesHommes) {
			if (file.isFile()) {
				ensembleProfilsH.add(new ProfilPerso(file.getName()));
			}
		}
		
		Service<Void> remplissage_file = new Service<Void>(){

			  @Override
			  protected Task<Void> createTask() {
			    return new Task<Void>(){

			     @Override
			     protected Void call() throws Exception {
			    	 
			        while(true) {
			        	Random r = new Random();
			    		ProfilPerso a;
			    		
			    		if(r.nextBoolean()) {
			    		a =ensembleProfilsH.get(r.nextInt(ensembleProfilsH.size()));
			    		}
			    		else {
			    			
			    			a =ensembleProfilsF.get(r.nextInt(ensembleProfilsF.size()));
			    		}
			    		
			    		int i = DistanceEntreVille.distance("marseille",a.ville);
			    		
			    		while(i>200 || i<0) {
			    			if(r.nextBoolean()) {
					    		a =ensembleProfilsH.get(r.nextInt(ensembleProfilsH.size()));
					    		}
					    		else {
					    			a =ensembleProfilsF.get(r.nextInt(ensembleProfilsF.size()));
					    		}
					    		i = DistanceEntreVille.distance("marseille",a.ville);
			    		}
			    		fileAttente.add(a);
			        }
			      }
			    };
			  }
			};
			remplissage_file.start();
		
		
		
		




	}


	public ProfilPerso prochainprofil() throws IOException {
		Random r = new Random();
		/*Profil a;
		if(r.nextBoolean()) {
		a =this.ensembleProfilsH.get(r.nextInt(this.ensembleProfilsH.size()));
		}
		else {
			a =this.ensembleProfilsF.get(r.nextInt(this.ensembleProfilsF.size()));
		}*/
		/*int i = DistanceEntreVille.distance("bordeaux",a.ville);
		while(i>100 || i<0) {
			
			a =this.ensembleProfils.get(r.nextInt(this.ensembleProfils.size()));
			i = DistanceEntreVille.distance("bordeaux",a.ville);
		}*/
		
		
		//return this.ensembleProfilsH.get(r.nextInt(this.ensembleProfilsH.size()));
		while(fileAttente.size()==0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ProfilPerso a=fileAttente.get(0);
		fileAttente.remove(0);
		return a;
	}
}