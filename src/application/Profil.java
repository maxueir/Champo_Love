package application;

import javafx.scene.image.Image;
import java.awt.desktop.AboutHandler;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class Profil implements Comparable<Profil>{//description d'un profil
	boolean avalide;//booleen pour specifier si la personne a valide le profil de l'utilisateur(aleatoire)
	boolean estvalide;//booleen pour specifier si l'utilisateur a valide ce profil
	String photo;
	String nom;
	String prenom;
	int age;
	enum sexe {HOMME,FEMME,AUTRE};
	sexe sex;
	enum orientation {HETERO,HOMO,BI}
	orientation ori;
	enum relation {COURTE,LONGUE}
	relation relation;
	String ville;
	String metier;
	Set<Preference> preferences;
	boolean fumeur;
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
			"Paris","Marseille","Lyon","Toulouse","Nice","Nantes","Montpellier","Strasbourg","Bordeaux","Lille",
			"Rennes","Reims","Toulon","Saint-Etienne","Grenoble","Dijon","Angers","Saint-Denis","Villeurbanne",
			"Nimes","Clermont-Ferrand","Aix-en-Provence","Brest","Tours","Amiens","Limoges","Annecy","Boulogne-Billancourt"
	};
	static String[] metiers = {
			"Médecin","Policier","Infermier","Enseignant","Psychologue","Enqueteur","Avocat","Pilote","Acteur","Dentiste",
			"Infographiste","Mecanicien","Pharamacien","Veterinaire","Photographe","Professeur","Chirurgien","Comptable",
			"Architecte","Journaliste","Concepteur de jeu vidéo","Pompier","Anesthesiste","Designer de mode","Pédiatre","Designer d'interieur",
			"Hygiéniste dentaire","Intervenant en toxicomanie","Ambulancier","Charpentier","Menuisier","Biologiste","Musicien",
			"Physiothérapeute","Plombier","Cuisinier","Serveur","Ingénieur","Massothérapeute","Chauffeur","Athlète","Ecrivain",
			"Coiffeur","Commedien","Osthéopate","Guide touristique","Inspecteur des impôts","Archéologue"
	};

	public Profil(String s) {
		preferences = new TreeSet<Preference>();
		Random random = new Random();
		int pourcentagesexe=random.nextInt(101);
		int pourcentageavalide=random.nextInt(101);
		int pourcentageori=random.nextInt(101);


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
		// Tirage aléatoire de la relation cherchée
		int rrelation = random.nextInt(2);
		if (rrelation==0) {
			this.relation=relation.COURTE;
		}
		else {
			this.relation=relation.LONGUE;
		}
		// Tirage aléatoire si le profil veut des non fumeur
				int rfumeur = random.nextInt(2);
				if (rfumeur==0) {
					this.fumeur=true;
				}
				else {
					this.fumeur=false;
				}
		
	}

	public Profil (String n, String p, int a, sexe s, orientation o, String v, Set<Preference> e, relation r, boolean f) {
		this.nom=n;
		this.prenom=p;
		this.age=a;
		this.sex=s;
		this.ori=o;
		this.ville=v;
		this.preferences=e;
		this.relation=r;
		this.fumeur=f;
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
		Random r = new Random();
		int a = r.nextInt(6);
		if(a==0) {
			return this.prenom.toString()+" "+this.nom.toString()+"\n"+
					this.age+" ans "+"\n"+
					this.ville;
		}
		else if(a==1) {
			return this.prenom.toString()+" "+this.nom.toString()+" "+this.age
					+" ans "+"\n"+
					this.ville+"\n"+
					this.metier+"\n"+
					"J'aime"+pY+" mais je n'aime pas"+pN;
		}
		else if(a==2) {
			return "Je m'appelle "+this.prenom.toString()+" "+this.nom.toString()+", j'ai "+this.age
					+" ans et j'habite à "+
					this.ville+", je suis "+
					this.metier+
					". J'aime"+pY+" mais je n'aime pas"+pN;
		}
		else if(a==3) {
			String s="";
			if(this.sex==sexe.HOMME) {
				s ="un homme de ";
			}
			else {s = "une femme de ";}
			return "Je m'appelle "+this.prenom.toString()+" "+this.nom.toString()+", je suis "+s+this.age
					+" ans."+"\n"+
					"Je viens de "+this.ville+" et je suis "+
					this.metier+"\n"+
					"Je deteste"+pN;
		}
		else if(a==4) {
			return "Originaire de "+this.ville+", j'ai "+this.age+"ans et je suis "+this.metier
					+"\n"+"J'apprecie"+pY;
		}else {
			String s="";
			if(this.sex==sexe.HOMME) {
				if(this.ori==orientation.HETERO) {
					s ="un homme de "+this.age+"ans et j'aime les femmes";}
				else if(this.ori==orientation.BI){
					s ="un homme de "+this.age+"ans et je suis bisexuel";
				}
				else {
					s ="un homme de "+this.age+"ans et j'aime les hommes";
				}
			}
			else {
				if(this.ori==orientation.HETERO) {
					s ="une femme de "+this.age+"ans et j'aime les hommes";}
				else if(this.ori==orientation.BI){
					s ="une femme de "+this.age+"ans et je suis bisexuelle";
				}
				else {
					s ="une femme de "+this.age+"ans et j'aime les femmes";
				}
			}
			return "Je suis "+s+"\n"+
			"Je viens de "+this.ville+" et je suis "+
			this.metier+"\n"+
			"Je suis tombé en amour avec"+pY;

		}

	}

	@Override
	public int compareTo(Profil p) {
		int compatible=0;
		if (this.ori==orientation.HOMO) {
			if (p.sex==this.sex) {
				compatible+=25;
			}
			else {
				compatible-=25;
			}
		}
		else if (this.ori==orientation.HETERO) {
			if ((this.sex==sexe.HOMME && p.sex==sexe.FEMME)||(this.sex==sexe.FEMME && p.sex==sexe.HOMME)|| (this.sex==sexe.AUTRE) ) {
				compatible+=25;
			}
			else {
				compatible-=25;
			}
		}
		else {
			compatible+=25;
		}
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

}
