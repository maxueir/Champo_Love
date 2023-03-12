package application;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class Profil implements Comparable<Profil>{//description d'un profil
	boolean avalide;//booleen pour specifier si la personne a valide le profil de l'utilisateur(aleatoire)
	boolean estvalide;//booleen pour specifier si l'utilisateur a valide ce profil
	String nom;
	String prenom;
	int age;
	enum sexe {HOMME,FEMME,AUTRE};
	sexe sex;
	enum orientation {HETERO,HOMO,BI}
	orientation ori;
	String ville;
	String metier;
	Set<Preference> preferences;
	String image;
	
	static String[] noms= {
			"Martin","Bernard","Petit","Thomas","Moreau","Dubois","Richard","Robert","Michel","Durand",
			"Simon","Laurent","Leroy","Lambert","Roux","Lefebvre","Girard","David","Morel","Fournier",
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
			"Paris","Marseille","Lyon","Toulouse","Nice","Nantes","Montpellier","Strasbourg","Bordeaux","Lille"
	};
	static String[] metiers = {
			"Médecin","Policier","Infermier","Enseignant","Psychologue","Enqueteur","Avocat","Pilote","Acteur","Dentiste",
			"Infographiste","Mecanicien","Pharamacien","Veterinaire","Photographe","Professeur","Chirurgien","Comptable",
			"Architecte","Journaliste","Concepteur de jeu vidéo","Pompier","Anesthesiste","Designer de mode","Pédiatre","Designer d'interieur",
			"Hygiéniste dentaire","Intervenant en toxicomanie","Ambulancier","Charpentier","Menuisier","Biologiste","Musicien",
			"Physiothérapeute","Plombier","Cuisinier","Serveur","Ingénieur","Massothérapeute","Chauffeur","Athlète","Ecrivain",
			"Coiffeur","Commedien","Osthéopate","Guide touristique","Inspecteur des impôts","Archéologue"
	};

	public Profil() {
		
		preferences = new TreeSet<Preference>();
		Random random = new Random();
		int pourcentageA=random.nextInt(101);
		int pourcentageB=random.nextInt(101);
		int pourcentageC=random.nextInt(101);
		
		//Tirage aleatoire du avalide
		if (pourcentageA<41) {
			this.avalide=true;
		}
		else {
			this.avalide=false;
		}
		
		// Tirage aléatoire d'un nom
		int rnom =random.nextInt(noms.length);
		this.nom=noms[rnom];

		// Tirage aléatoire d'un sexe
		if (pourcentageA<48) {
			this.sex = sexe.FEMME;
		}
		else if (pourcentageA>47 && pourcentageA<93) {
			this.sex = sexe.HOMME;
		}
		else {
			this.sex = sexe.AUTRE;
		}
	
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
		// Tirage aléatoire d'un age
		if(pourcentageB<51) {
			this.age = random.nextInt(18,26);
		}
		else if(pourcentageB>50 && pourcentageB<71){
			this.age = random.nextInt(25,36);
		}
		else if(pourcentageB>70 && pourcentageB<91){
			this.age = random.nextInt(35,46);
		}
		else if(pourcentageB>90 && pourcentageB<95){
			this.age = random.nextInt(45,51);
		}
		else {
			this.age = random.nextInt(50,100);
		}

		// Tirage aléatoire d'une orientation
		if (pourcentageC<71) {
			this.ori = orientation.HETERO;
		}
		else if (pourcentageC>70 && pourcentageC<86) {
			this.ori = orientation.HOMO;
		}
		else {
			this.ori = orientation.BI;
		}
		
		// Tirage aléatoire de la ville
		int rville = random.nextInt(villes.length);
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
		
		
	}

	public Profil (String n, String p, int a, sexe s, orientation o, String v, Set<Preference> e) {
		this.nom=n;
		this.prenom=p;
		this.age=a;
		this.sex=s;
		this.ori=o;
		this.ville=v;
		this.preferences=e;
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
				pN += tab[i].substring(1);
			}
			else if (tab[i].charAt(0)=='1') {
				pY += tab[i].substring(1);
			}
		}
		return "Nom : "+this.nom.toString()+"\n"+
				"Prénom : "+ this.prenom.toString()+"\n"+
				"Age : "+ this.age+" ans "+"\n"+
				"Sexe : "+this.sex.toString()+"\n"+
				"Orientation sexuelle : "+this.ori.toString()+"\n"+
				"Ville : "+this.ville+"\n"+
				"Metier : "+this.metier+"\n"+
				"Préférences : "+"J'aime"+pY+" mais je n'aime pas"+pN;
	}

	@Override
	public int compareTo(Profil p) {

		return 0;
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

	public static void main(String[] args) {

		Set<Profil> ensemble = new HashSet<Profil>();
		Profil p = new Profil();
		ensemble.add(p);
		System.out.println(ensemble.toString());
	}
}
