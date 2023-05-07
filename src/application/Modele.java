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

	static ProfilPerso profilPerso;//profil de l'utilisateur
	ArrayList<Profil> coupdecoeur;
	ArrayList<Profil> matchs;
	transient PriorityQueue<Profil> fileAttente;
	transient ArrayList<Profil> listeProfilsH;
	transient ArrayList<Profil> listeProfilsF;
<<<<<<< Updated upstream
	transient Service<Void> remplissage_file;
	
=======
	transient PriorityQueue<Profil> fileAttente;

	public void completion() {
		this.fileAttente= new PriorityQueue<Profil>();
		this.listeProfilsH=new ArrayList<Profil>() ;
		this.listeProfilsF=new ArrayList<Profil>() ;
		
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
<<<<<<< HEAD
=======
		
		this.thread = new Service<Void>(){
>>>>>>> a9385ea7294aad6e67e6460e6618ca8107db675b

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
<<<<<<< HEAD
									fileAttente.add(listeProfilsF.get(r.nextInt(listeProfilsF.size())));
=======
									fileAttente.add(listeProfilsF.get(r.nextInt(listeProfilsF.size())));  
>>>>>>> a9385ea7294aad6e67e6460e6618ca8107db675b
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

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
				listeProfilsF.add(new Profil(file.getName()));




=======
				listeProfilsF.add(new Profil(file.getName(),this));
>>>>>>> Stashed changes
			}
		}
		
		File imHommes = new File("images/homme");
		File[] filesHommes = imHommes.listFiles();
		for (File file : filesHommes) {
			if (file.isFile()) {
				listeProfilsH.add(new Profil(file.getName()));
			}
		}

<<<<<<< Updated upstream
		remplissage_file = new Service<Void>(){

=======
		this.thread = new Service<Void>(){
>>>>>>> Stashed changes
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>(){
					@Override
					protected Void call() {
						Random r = new Random();
						if(profilPerso==null) {
							for(int i=0;i<150;i++) {
								if (r.nextBoolean()) {
<<<<<<< HEAD
									fileAttente.add(listeProfilsF.get(r.nextInt(listeProfilsF.size())));  
=======
									fileAttente.add(listeProfilsF.get(r.nextInt(listeProfilsF.size())));
>>>>>>> a9385ea7294aad6e67e6460e6618ca8107db675b
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
<<<<<<< Updated upstream

		remplissage_file.start();
		//








=======
>>>>>>> Stashed changes
	}

	public Profil prochainprofil() throws IOException {
<<<<<<< Updated upstream
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
		while(/*fileAttente==null ||*/ fileAttente.size()==0) {
			
=======
		while(fileAttente.size()==0) {
>>>>>>> Stashed changes
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
		String cdc = "Coups de coeur : ";
		for (int i=0; i<this.coupdecoeur.size(); i++) {
			cdc += this.coupdecoeur.toString() + ", ";
		}
		String m = "Matchs";
		for (int i=0; i<this.matchs.size(); i++) {
			m += this.matchs.toString() + ", ";
		}
		return Modele.profilPerso.toString() + " " + cdc + m;
	}
}