package application;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IllegalFormatCodePointException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import application.Profil.orientation;
import application.Profil.sexe;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;

public class Modele implements Serializable {//classe Modele du MV(C) 

	ProfilPerso profilPerso;//profil de l'utilisateur
	ArrayList<Profil> coupdecoeur;
	ArrayList<Profil> matchs;
	transient Service<Void> thread;
	transient ArrayList<Profil> listeProfilsH;
	transient ArrayList<Profil> listeProfilsF;
	transient PriorityQueue<Profil> fileAttente;


	public Modele() throws InterruptedException {
		this.fileAttente= new PriorityQueue<Profil>();
		this.listeProfilsH=new ArrayList<Profil>() ;
		this.listeProfilsF=new ArrayList<Profil>() ;
		this.coupdecoeur=new ArrayList<Profil>();
		this.matchs=new ArrayList<Profil>();
		
		File imFemmes = new File("images/femme");
		File[] filesFemmes = imFemmes.listFiles();
		for (File file : filesFemmes) {
			if (file.isFile()) {
				listeProfilsF.add(new Profil(file.getName(),this));




			}
		}
		File imHommes = new File("images/homme");
		File[] filesHommes = imHommes.listFiles();
		for (File file : filesHommes) {
			if (file.isFile()) {
				listeProfilsH.add(new Profil(file.getName(),this));
			}
		}

		this.thread = new Service<Void>(){

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>(){

					@Override
					protected Void call() {
						
						Random r = new Random();
						if(profilPerso==null) {
							for(int i=0;i<150;i++) {
								if (r.nextBoolean()) {
									fileAttente.add(listeProfilsF.get(r.nextInt(listeProfilsF.size())));  

								}
								else {
									fileAttente.add(listeProfilsH.get(r.nextInt(listeProfilsH.size())));
								}
							}
						}
						else if((profilPerso.sex==sexe.HOMME && profilPerso.ori==orientation.HETERO)||(profilPerso.sex==sexe.FEMME && profilPerso.ori==orientation.HOMO)|| profilPerso.sex==sexe.AUTRE){
							for(int i=0;i<listeProfilsF.size();i++) {
								fileAttente.add(listeProfilsF.get(i));
							}
							
						}
						else if ((profilPerso.sex==sexe.HOMME && profilPerso.ori==orientation.HOMO)||(profilPerso.sex==sexe.FEMME && profilPerso.ori==orientation.HETERO)|| profilPerso.sex==sexe.AUTRE){
							for(int i=0;i<listeProfilsH.size();i++) {
								fileAttente.add(listeProfilsH.get(i));
							}
						}
						return null;
					}
				};
			}
		};

		this.thread.start();
		//








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
		Profil p=fileAttente.peek();
		fileAttente.remove();
		return p;
	}
	
	@Override
	public String toString() {
		return this.profilPerso.toString();
	}
}