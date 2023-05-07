package application;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Profil  implements Comparable<Profil>,Serializable{//description d'un profil
	Modele mod;
	boolean avalide;//booleen pour specifier si la personne a valide le profil de l'utilisateur(aleatoire)
	boolean estvalide;//booleen pour specifier si l'utilisateur a valide ce profil
	// Ce que le profil est
	String photo;
	String nom;
	String prenom;
	int age;
	enum sexe {HOMME,FEMME,AUTRE};
	sexe sex;
	enum orientation {HETERO,HOMO,BI};
	orientation ori;
	String ville;
	int ind;
	String metier;
	Set<Preference> preferences;
	boolean fumeur;
	boolean estfav;
	String image;

	// Ce que le profil recherche
	int age_min;
	int age_max;
	int distance;
	Boolean fumeur_r;
	boolean relation; //true = longue et false=courte

	static String[] noms= {
			"Martin","Bernard","Petit","Thomas","Moreau","Dubois","Richard","Robert","Michel","Durand",
			"Simon","Laurent","Leroy","Lambert","Roux","Lefevre","Girard","David","Morel","Fournier",
			"Rousseau","Bonnet","Garnier","Dupond","Henry","Vincent","Mercier","Blanc","Guerin","Perrin",
			"Andre","Marchand","Legrand","Masson","Chevalier","Morin","Gautier","Barbier","Fontaine","Giraud",
			"Roussel","Noel","Faure","Lemaire","Duval","Aubert","Dumont","Colin","Renard","Joly","Dufour"

	};

	static String[] prenomF={
			"Emma","Louise","Jade","Alice","Chloé","Lina","Mila","Léa","Manon","Rose",
			"Anna","Inès","Camille","Lola","Ambre","Léna","Zoé","Juliette","Julia","Lou",
			"Sarah","Lucie","Mia","Jeanne","Romane","Agathe","Eva","Nina","Charlotte","Inaya",
			"Léonie","Sofia","Margaux","Louna","Clara","Luna","Maëlyse","Olivia","Adèle","Lilou",
			"Clémence","Léana","Lana","Capucine","Elena","Victoria","Aya","Mathilde","Margot","Iris"

	};

	static String[] prenomH = {
			"Gabriel","Louis","Raphaël","Jules","Adam","Lucas","Léo","Hugo","Arthur","Nathan",
			"Liam","Ethan","Maël","Paul","Tom","Sacha","Noah","Gabin","Nolan","Enzo",
			"Mohamed","Aaron","Timéo","Théo","Mathis","Axel","Victor","Antoine","Valentin","Martin",
			"Noé","Eden","Robin","Marius","Rayan","Clément","Baptiste","Maxime","Samuel","Léon",
			"Yanis","Augustin","Eliot","Maxence","Evan","Mathéo","Alexandre","Thomas","Simon","Gaspard"
	};

	static String[] villes = {
			"Paris","Marseille","Lyon","Toulouse","Nice","Nantes","Montpellier","Strasbourg","Bordeaux","Lille",
			"Rennes","Reims","Toulon","Saint-Etienne","Grenoble","Dijon","Angers","Saint-Denis","Villeurbanne",
			"Nimes","Clermont-Ferrand","Aix-en-Provence","Brest","Tours","Amiens","Limoges","Annecy","Boulogne-Billancourt"
	};
	
	static int[] dist = {//28 dist entre la ville correspondante et la ville du profil perso
			-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1
	};

	static String[] metiers = {
			"médecin","policier","infermier","enseignant","psychologue","enqueteur","avocat","pilote","acteur","dentiste",
			"infographiste","mecanicien","pharamacien","veterinaire","photographe","professeur","chirurgien","comptable",
			"architecte","journaliste","concepteur de jeu vidéo","pompier","anesthesiste","designer de mode","pédiatre","designer d'interieur",
			"hygiéniste dentaire","intervenant en toxicomanie","ambulancier","charpentier","menuisier","biologiste","musicien",
			"physiothérapeute","plombier","cuisinier","serveur","ingénieur","massothérapeute","chauffeur","athlète","ecrivain",
			"coiffeur","commedien","osthéopate","guide touristique","inspecteur des impôts","archéologue"
	};

	public Profil(String s,Modele m) {
		this.mod=m;

		this.estfav=false;
		preferences = new TreeSet<Preference>();
		Random random = new Random();
		int pourcentagesexe=random.nextInt(101);
		int pourcentageavalide=random.nextInt(101);
		int pourcentageori=random.nextInt(101);

		// On recupere l'age dans le titre de la photo
		this.age=Integer.valueOf(s.split("_")[1]);

		if(pourcentagesexe>96) {
			this.sex=sexe.AUTRE;
			if (s.split("_")[0].equals("male")){
				this.photo="file:images/homme/"+s;
			}
			else {
				this.photo="file:images/femme/"+s;
			}
		}
		else {
			if (s.split("_")[0].equals("male")){
				this.sex=sexe.HOMME;
				this.photo="file:images/homme/"+s;
			}
			else {
				this.sex=sexe.FEMME;
				this.photo="file:images/femme/"+s;
			}
		}

		this.age=Integer.valueOf(s.split("_")[1]);

		if (pourcentageavalide<41) {
			this.avalide=true;
		}
		else {
			this.avalide=false;
		}

		// Tirage aléatoire d'un nom
		int rnom =random.nextInt(noms.length);
		this.nom=noms[rnom];

		// Tirage aléatoire d'un prénom en fonction du sexe
		if (this.sex==sexe.FEMME) {
			int rprenom = random.nextInt(prenomF.length);
			this.prenom = prenomF[rprenom];
		}
		else if (this.sex==sexe.HOMME) {
			int rprenom = random.nextInt(prenomH.length);
			this.prenom = prenomH[rprenom];
		}
		else {
			int liste = random.nextInt(2);
			if (liste==0) {
				int rprenom = random.nextInt(prenomF.length);
				this.prenom = prenomF[rprenom];
			}
			else {
				int rprenom = random.nextInt(prenomH.length);
				this.prenom = prenomH[rprenom];
			}
		}

		// Tirage aléatoire d'une orientation
		if (pourcentageori<71) {
			this.ori = orientation.HETERO;
		}
		else if (pourcentageori>70 && pourcentageori<86) {
			this.ori = orientation.HOMO;
		}
		else {
			this.ori = orientation.BI;
		}

		// Tirage aléatoire de la ville
		int rville = random.nextInt(villes.length);
		this.ind=rville;
		this.ville = villes[rville];

		// Tirage aléatoire du metier 
		int rmetier = random.nextInt(metiers.length);
		this.metier = metiers[rmetier];

		// Tirage aléatoire des préférences
		int rpref = random.nextInt(3,11);
		while (preferences.size()<rpref) {
			Preference pref = new Preference();
			this.preferences.add(pref);
		}

		// Tirage aléatoire si le profil est fumeur
		this.fumeur= random.nextBoolean();

		// Tirage aléatoire si le profil recherche un non fumeur
		this.fumeur_r= random.nextBoolean();
	}

	@Override
	public String toString() {
		String pY = "";
		String pN ="";
		String l = "";
		Iterator<Preference> iterator = preferences.iterator();
		while (iterator.hasNext()) {
			l+=iterator.next()+"_";
		}
		String[] tab = l.split("_");
		for (int i = 0; i<tab.length; i++) {
			if (tab[i].charAt(0)=='0') {
				if(pN=="") {
					pN += tab[i].substring(1);
				}
				else {
					pN += ","+tab[i].substring(1);
				}
			}
			else if (tab[i].charAt(0)=='1') {
				if(pY=="") {
					pY += tab[i].substring(1);
				}
				else {
					pY +=","+ tab[i].substring(1);
				}
			}
		}
		return "J'habite à "+
		this.ville+" et je suis "+
		this.metier+"."+"\n"+
		"J'aime "+pY+" mais je n'aime pas "+pN;
	}

	@Override
	public int compareTo (Profil p) {
			return this.compareTo2()-p.compareTo2();
		
	}

	public int compareTo2() {
		int compatible = 0;
		if (this.mod.profilPerso!=null) {
			// Compatibilité des ages
			if (this.age<=this.mod.profilPerso.age_min && this.age>=this.mod.profilPerso.age_max) {
				compatible+=1000;
			}
			else {
				int diff=0;
				if (this.age>this.mod.profilPerso.age_min) {
					diff = (this.mod.profilPerso.age_min- this.age)*10;
				}
				else {
					diff = (this.age-this.mod.profilPerso.age_max)*10;
				}
				compatible-=diff;
			}

			// Compatibilité du type de relation
			if (this.relation==this.mod.profilPerso.relation) {
				compatible+=250;
			}
			else {
				compatible-=100;
			} 
			// Compatibilité fumeur
			if (this.fumeur_r==false && this.mod.profilPerso.fumeur==false) {
				compatible+=100;
			}
			else {
				compatible-=200;
			}

			// Compatibilité des preferences
			Iterator<Preference> thisiterator = this.preferences.iterator();
			while (thisiterator.hasNext()) {
				Preference element = thisiterator.next();
				if (this.mod.profilPerso.preferences.contains(element)) {
					compatible+=50;
				}
				else {
					compatible-=10;
				}
			}

			//compatibilité de la localisation
			int distance= dist[this.ind];
			if(distance<=this.mod.profilPerso.distance && distance>=0) {
				compatible+=800;
			}
			else if(distance>=0 && distance>this.mod.profilPerso.distance){
				compatible-=(distance-this.mod.profilPerso.distance)*10;
			}
		}
		return compatible;
	}

	@Override
	public boolean equals(Object o) {
		Profil p= (Profil)o;
		if (this.nom==p.nom && this.prenom==p.prenom && this.age==p.age && this.sex==p.sex && this.ori==p.ori) {
			return true;
		}
		else {
			return false;
		}
	}
}
