package application;

import javafx.scene.image.Image;
import java.awt.desktop.AboutHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Identity;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;




public class Profil  implements Comparable<Profil>{//description d'un profil
	ProfilPerso profilPerso;
	
	boolean avalide;//booleen pour specifier si la personne a valide le profil de l'utilisateur(aleatoire)
	boolean estvalide;//booleen pour specifier si l'utilisateur a valide ce profil
	// Ce que le profil est
	String photo;
	String nom;
	String prenom;
	int age;
	enum sexe {HOMME,FEMME,AUTRE};
	public sexe sex;
	enum orientation {HETERO,HOMO,BI};
	orientation ori;
	String ville;
	String metier;
	Set<Preference> preferences;
	boolean fumeur;
	String image;
	


	// Ce que le profil recherche
	int age_min;
	int age_max;
	int distance;
	Boolean fumeur_r;
	enum relation {COURTE,LONGUE};
	relation relation;
	
	
	
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
			"médecin","policier","infermier","enseignant","psychologue","enqueteur","avocat","pilote","acteur","dentiste",
			"infographiste","mecanicien","pharamacien","veterinaire","photographe","professeur","chirurgien","comptable",
			"architecte","journaliste","concepteur de jeu vidéo","pompier","anesthesiste","designer de mode","pédiatre","designer d'interieur",
			"hygiéniste dentaire","intervenant en toxicomanie","ambulancier","charpentier","menuisier","biologiste","musicien",
			"physiothérapeute","plombier","cuisinier","serveur","ingénieur","massothérapeute","chauffeur","athlète","ecrivain",
			"coiffeur","commedien","osthéopate","guide touristique","inspecteur des impôts","archéologue"
	};


	public Profil(String s) {
		this.profilPerso=Modele.profilPerso;
		
		preferences = new TreeSet<Preference>();
		Random random = new Random();
		int pourcentagesexe=random.nextInt(101);
		int pourcentageavalide=random.nextInt(101);
		int pourcentageori=random.nextInt(101);

		// On recupere l'age dans le nom de la photo
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
	public int compareTo (Profil p) {
		/*int i =0;
		if (this.score>p.score) {
			i+=this.score;
		}
		else if (this.score<p.score) {
			i-=this.score;
		}
		return i;*/
		return this.compareTo2()-p.compareTo2();
	}
	
	
	public int compareTo2() {
		
		int compatible=0;
		if (this.profilPerso!=null) {
			// Compatibilité des sexe
			if (this.ori!=null) {
				if (this.ori==orientation.HOMO) {
					if (this.profilPerso.sex==this.sex) {
						compatible+=25;
					}
					else {
						compatible-=1000;
					}
				}
				else if (this.ori==orientation.HETERO) {
					if ((this.sex==sexe.HOMME && this.profilPerso.sex==sexe.FEMME)||(this.sex==sexe.FEMME && this.profilPerso.sex==sexe.HOMME)|| (this.sex==sexe.AUTRE) ) {
						compatible+=25;
					}
					else {
						compatible-=1000;
					}
				}
				else {
					compatible+=25;
				}
			}

			// Compatibilité des ages
			if (this.age!=0) {
				if (this.profilPerso.age>this.age_min && this.profilPerso.age<this.age_max) {
					compatible+=25;
				}
				else {
					int diff=0;
					if (this.profilPerso.age<this.age_min) {
						diff = this.age_min-this.profilPerso.age;
					}
					else {
						diff = this.profilPerso.age-this.age_max;
					}
					compatible-=diff;

				}
			}

			// Compatibilité du type de relation
			if (this.relation!=null) {
				if (this.relation==this.profilPerso.relation) {
					compatible+=25;
				}
				else {
					compatible-=25;
				}
			}

			// Compatibilité fumeur
			if (this.fumeur_r==false && this.profilPerso.fumeur==false) {
				compatible+=10;
			}
			else {
				compatible-=10;
			}

			// Compatibilité des preferences
			Iterator<Preference> thisiterator = this.preferences.iterator();
			while (thisiterator.hasNext()) {
				Preference element = thisiterator.next();
				if (this.profilPerso.preferences.contains(element)) {
					compatible+=5;
				}
				else {
					compatible-=5;
				}
			}

			// Compatibilité de la localition
			if (this.distance!=0) {
				int dist=0;
				try {
					dist=DistanceEntreVille.distance(this.ville,this.profilPerso.ville);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (dist<this.distance) {
					compatible+=25;
				}
				else {
					compatible-=25;
				}
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
