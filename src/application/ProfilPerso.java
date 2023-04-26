package application;

import java.util.Set;

import application.Profil.orientation;
import application.Profil.relation;
import application.Profil.sexe;

public class ProfilPerso {
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
	Boolean fumeur_r;
	relation relation;
	Set<Preference> preferences_r;
	
	
	
	public ProfilPerso (String n, String p, int a, application.Profil.sexe s,String m, application.Profil.orientation o, String v, Set<Preference> e, boolean f) {
		this.nom=n;
		this.prenom=p;
		this.age=a;
		this.sex=s;
		this.metier=m;
		this.ori=o;
		this.ville=v;
		this.preferences=e;
		this.fumeur=f;
	}
}
