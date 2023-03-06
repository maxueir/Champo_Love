package application;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class Profil implements  Comparable<Profil>{
	String nom;
	String prenom;
	int age;
	enum sexe {HOMME,FEMME,AUTRE};
	sexe sex;
	enum orientation {HETERO,HOMO,BI}
	orientation ori;

	public Profil(String n, String p, int a, sexe s, orientation o) {
		this.nom=n;
		this.prenom=p;
		this.age=a;
		this.sex=s;
		this.ori=o;
	}
	
	@Override
	public String toString() {
		return this.nom.toString()+" "+ this.prenom.toString()+" "+ this.age+" ans "+this.sex.toString()+" "+this.ori.toString();
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
		String[] prenomF = {
				"Emma","Louise","Jade","Alice","Chlo�","Lina","Mila","L�a","Manon","Rose",
				"Anna","In�s","Camille","Lola","Ambre","L�na","Zo�","Juliette","Julia","Lou",
				"Sarah","Lucie","Mia","Jeanne","Romane","Agathe","Eva","Nina","Charlotte","Inaya",
				"L�onie","Sofia","Margaux","Louna","Clara","Luna","Ma�lyse","Olivia","Ad�le","Lilou",
				"Cl�mence","L�ana","Lana","Capucine","Elena","Victoria","Aya","Mathilde","Margot","Iris"

		};
		String[] prenomH = {
				"Gabriel","Louis","Rapha�l","Jules","Adam","Lucas","L�o","Hugo","Arthur","Nathan",
				"Liam","Ethan","Ma�l","Paul","Tom","Sacha","Noah","Gabin","Nolan","Enzo",
				"Mohamed","Aaron","Tim�o","Th�o","Mathis","Axel","Victor","Antoine","Valentin","Martin",
				"No�","Eden","Robin","Marius","Rayan","Cl�ment","Baptiste","Maxime","Samuel","L�on",
				"Yanis","Augustin","Eliot","Maxence","Evan","Math�o","Alexandre","Thomas","Simon","Gaspard"
		};
		String[] noms= {
				"Martin","Bernard","Petit","Thomas","Moreau","Dubois","Richard","Robert","Michel","Durand",
				"Simon","Laurent","Leroy","Lambert","Roux","Lefebvre","Girard","David","Morel","Fournier",
				"Rousseau","Bonnet","Garnier","Dupond","Henry","Vincent","Mercier","Blanc","Guerin","Perrin",
				"Andre","Marchand","Legrand","Masson","Chevalier","Morin","Gautier","Barbier","Fontaine","Giraud",
				"Roussel","Noel","Faure","Lemaire","Duval","Aubert","Dumont","Colin","Renard","Joly","Dufour"

		};

		Set<Profil> ensemble = new HashSet<Profil>();

		while (ensemble.size()<10) {
			
			// Tirage al�atoir d'un nom
			Random random = new Random();
			int rnom =random.nextInt(noms.length);
			String no=noms[rnom];
			
			// Tirage al�atoir d'un sexe
			int rsexe = random.nextInt(sexe.values().length);
			sexe s = sexe.values()[rsexe];
			
			// Tirage al�atoir d'un pr�nom en fonction du sexe
			String pre="";
			if (s==sexe.FEMME) {
				int rprenom = random.nextInt(prenomF.length);
				pre = prenomF[rprenom];
			}
			else if (s==sexe.HOMME) {
				int rprenom = random.nextInt(prenomH.length);
				pre = prenomH[rprenom];
			}
			else {
				int liste = random.nextInt(2);
				if (liste==0) {
					int rprenom = random.nextInt(prenomF.length);
					pre = prenomF[rprenom];
				}
				else {
					int rprenom = random.nextInt(prenomH.length);
					pre = prenomH[rprenom];
				}
			}
			// Tirage al�atoir d'un age
			int rage = random.nextInt(18,100);
			
			// Tirage al�atoir d'une orientation
			int rori =random.nextInt(orientation.values().length);
			orientation o = orientation.values()[rori];
			
				
			Profil p = new Profil(no, pre, rage, s, o);
			ensemble.add(p);
		}
		//System.out.println(ensemble.toString());
	}

}