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

	Profil profilPerso;//profil de l'utilisateur
	int pref_age_min;
	int pref_age_max;
	int pref_distance;
	Set<String> preferences;
	ArrayList<Profil> coupdecoeur;
	ArrayList<Profil> matchs;
	ArrayList<Profil> recales;
	ArrayList<Profil> valides;
	ArrayList<Profil> ensembleProfilsH;
	ArrayList<Profil> ensembleProfilsF;
	ArrayList<Profil> fileAttente;
	

	public Modele() throws InterruptedException {
		this.fileAttente= new ArrayList<Profil>();
		this.ensembleProfilsH=new ArrayList<Profil>() ;
		this.ensembleProfilsF=new ArrayList<Profil>() ;
		this.coupdecoeur=new ArrayList<Profil>();
		this.matchs=new ArrayList<Profil>();
		this.recales=new ArrayList<Profil>();
		this.valides=new ArrayList<Profil>();
		
		File imFemmes = new File("images/femme");
		File[] filesFemmes = imFemmes.listFiles();
		for (File file : filesFemmes) {
			if (file.isFile()) {
				ensembleProfilsF.add(new Profil(file.getName()));




			}
		}
		File imHommes = new File("images/homme");
		File[] filesHommes = imHommes.listFiles();
		for (File file : filesHommes) {
			if (file.isFile()) {
				ensembleProfilsH.add(new Profil(file.getName()));
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
			    		Profil a;
			    		
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


	public Profil prochainprofil() throws IOException {
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
		Profil a=fileAttente.get(0);
		fileAttente.remove(0);
		return a;
	}
}