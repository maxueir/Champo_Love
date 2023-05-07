package application;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import application.Profil.orientation;
import application.Profil.sexe;

public class ProfilPerso implements Serializable {
	
	boolean avalide;//booleen pour specifier si la personne a valide le profil de l'utilisateur(aleatoire)
	boolean estvalide;//booleen pour specifier si l'utilisateur a valide ce profil
	
	// Ce que le profil est
	String photo;
	String nom;
	String prenom;
	int age;
	public sexe sex;
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
	boolean fumeur_r;
	boolean relation;
	
	public ProfilPerso (String n, String p, int a, application.Profil.sexe s,String m, application.Profil.orientation o, String v, Set<Preference> e, boolean f, int amin, int amax, int dis, boolean fr, boolean re, String im) {
		this.nom=n;
		this.prenom=p;
		this.age=a;
		this.sex=s;
		this.metier=m;
		this.ori=o;
		this.ville=v;
		this.preferences=e;
		this.fumeur=f;
		this.age_max=amax;
		this.age_min=amin;
		this.distance=dis;
		this.fumeur_r=fr;
		this.image = im;
		this.relation = re;
	}
	
	@Override
	public String toString() {
		Iterator<Preference> ite = this.preferences.iterator();
		return this.nom + " " + this.prenom + ", " + this.age + "ans " + this.sex + " " + this.metier + " " + this.ori + " " + this.ville + " " + ite.next().toString().substring(2) + " " + ite.next().toString().substring(2) + " " + this.fumeur + " " + this.fumeur_r + " " + this.age_min + "ans " + this.age_max + "ans " + this.distance + " " + this.image;
	}
}
