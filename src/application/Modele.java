package application;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import application.Profil.orientation;
import application.Profil.sexe;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class Modele implements Serializable {//classe Modele du MV(C) 

	ProfilPerso profilPerso;//profil de l'utilisateur
	ArrayList<Profil> coupdecoeur;
	ArrayList<Profil> matchs;
	transient Service<Void> thread;
	transient Service<Void> distance_thread;
	transient ArrayList<Profil> listeProfilsH;
	transient ArrayList<Profil> listeProfilsF;
	transient PriorityQueue<Profil> fileAttente;

	public void completion() {
		this.fileAttente= new PriorityQueue<Profil>();
		this.listeProfilsH=new ArrayList<Profil>() ;
		this.listeProfilsF=new ArrayList<Profil>() ;
		
		this.distance_thread = new Service<Void>(){

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>(){

					@Override
					protected Void call() {
						dist();
						return null;
					}
				};
			}
		};
		this.distance_thread.start();
		
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
	}

	public Modele() throws InterruptedException {
		this.fileAttente= new PriorityQueue<Profil>();
		this.listeProfilsH=new ArrayList<Profil>() ;
		this.listeProfilsF=new ArrayList<Profil>() ;
		this.coupdecoeur=new ArrayList<Profil>();
		this.matchs=new ArrayList<Profil>();
		
		this.distance_thread = new Service<Void>(){

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>(){

					@Override
					protected Void call() {
						dist();
						return null;
					}
				};
			}
		};
		
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
	}
	
	public void dist() {
		for (int i=0;i<Profil.villes.length;i++) {
			try {
				Profil.dist[i]=DistanceEntreVille.distance(Profil.villes[i],profilPerso.ville);
				/*if(Profil.dist[i]==-1) {
					System.out.println(Profil.villes[i]);
				}*/
				
			}
			catch(IOException e) {
				Profil.dist[i]=-1;
			}
			//System.out.println(i);
		}
	}

	public Profil prochainprofil() throws IOException {
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