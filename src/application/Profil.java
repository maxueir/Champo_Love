package application;

import java.awt.Image;
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
	Image photo;
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
			"Paris","Marseille","Lyon","Toulouse","Nice","Nantes","Montpellier","Strasbourg","Bordeaux","Lille",
			"Rennes","Reims","Toulon","Saint-Etienne","Le Havre","Grenoble","Dijon","Angers","Saint-Denis","Villeurbanne",
			"Nimes","Clermont-Ferrand","Aix-en-Provence","Le Mans","Brest","Tours","Amiens","Limoges","Annecy","Boulogne-Billancourt"
	};
	static String[] metiers = {
			"Médecin","Policier","Infermier","Enseignant","Psychologue","Enqueteur","Avocat","Pilote","Acteur","Dentiste",
			"Infographiste","Mecanicien","Pharamacien","Veterinaire","Photographe","Professeur","Chirurgien","Comptable",
			"Architecte","Journaliste","Concepteur de jeu vidéo","Pompier","Anesthesiste","Designer de mode","Pédiatre","Designer d'interieur",
			"Hygiéniste dentaire","Intervenant en toxicomanie","Ambulancier","Charpentier","Menuisier","Biologiste","Musicien",
			"Physiothérapeute","Plombier","Cuisinier","Serveur","Ingénieur","Massothérapeute","Chauffeur","Athlète","Ecrivain",
			"Coiffeur","Commedien","Osthéopate","Guide touristique","Inspecteur des impôts","Archéologue"
	};

	public Profil(Image i) {
		preferences = new TreeSet<Preference>();
		Random random = new Random();
		int pourcentagesexe=random.nextInt(101);
		int pourcentageavalide=random.nextInt(101);
		int pourcentageori=random.nextInt(101);
		
		this.photo=i;
		
		File imageFile = new File(String.valueOf(i));
		String nomimage = imageFile.getName();
		this.age=Integer.valueOf(nomimage.split("_")[1]);
		
		if(pourcentagesexe>96) {
			this.sex=sexe.AUTRE;
		}
		else {
			if (nomimage.split("_")[0]=="mal"){
				this.sex=sexe.HOMME;
			}
			else {
				this.sex=sexe.FEMME;
			}
		}
		
		File repHommes = new File("images/femmes");
		File[] filesHommes = repHommes.listFiles();
		for (File file :filesHommes) {
			if (file.isFile()) {
				this.age=Integer.valueOf(file.getName().split("_")[1]);
				this.sex=sexe.HOMME;

			}
		}

		//Tirage aleatoire du avalide
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
			if(this.sex==sex.HOMME) {
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
			if(this.sex==sex.HOMME) {
				if(this.ori==ori.HETERO) {
					s ="un homme de "+this.age+"ans et j'aime les femmes";}
				else if(this.ori==ori.BI){
					s ="un homme de "+this.age+"ans et je suis bisexuel";
				}
				else {
					s ="un homme de "+this.age+"ans et j'aime les hommes";
				}
			}
			else {
				if(this.ori==ori.HETERO) {
					s ="une femme de "+this.age+"ans et j'aime les hommes";}
				else if(this.ori==ori.BI){
					s ="une femme de "+this.age+"ans et je suis bisexuelle";
				}
				else {
					s ="une femme de "+this.age+"ans et j'aime les femmes";
				}
			}
			return "Je suis "+s+"\n"+
			"Je viens de "+this.ville+"et je suis "+
			this.metier+"\n"+
			"Je suis tombé en amour avec"+pY;
		}

	}

	@Override
	public int compareTo(Profil p) {
		int compatibilite=100;
		// Compatibilité sexuelle
		if (this.ori==orientation.HOMO && this.sex!=p.sex) {
			return 0;
		}
		else if (this.ori==orientation.HETERO && this.sex==p.sex) {
			return 0;
		}
		else {

			int distanceVille
		}
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
