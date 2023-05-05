package application;

import java.io.Serializable;
import java.util.Random;


public class Preference implements Comparable<Preference>, Serializable {//classe pour definir la preference de chaque profil
	String pref;
	Boolean aime;
	static String[] preferences = {
		"la musique","le rap","le jazz","le hip-hop","la pop","le rock","la musique classique","le R&B","le slam","le reggae","l'electro","le latino","le blues","le metal","la K-pop",
		"la mer","le surf","jouer au volley","faire du velo","la natation","la plongée","le kite-surf",
		"la montagne","skier","faire du snow","le parapente","le deltaplane","faire du VTT","la randonnée","l'escalade",
		"les animaux","les chats","les chiens","les lapins","les hamsters","les oiseaux","les poissons","les furets","les chevaux","les animaux de la ferme",
		"le sport","la muscu","courrir","le tennis","la dance","dancer","le ping-pong","le badminton","l'aviron","le canoë","le yoga","le pilate","l'équitation",
		"la moto","voyager","la cuisine","cuisiner","la lecture","l'écritue","les films","les séries","les voitures","netflix and chill","manger au resto","faire de l'urbex","jardiner",
		"faire du shopping","les tatouages","le gaming","les jeux de société","les mangas","les animés","prendre des photos","poser pour des photos","chanter","le cinéma","le théâtre"
	};
	
	// Constructeur des preferences pour la génération des profils aléatoires
	public Preference() {
		Random random = new Random();
		this.aime = random.nextBoolean();
		int rpref = random.nextInt(preferences.length);
		this.pref = preferences[rpref];
	}
	
	// Constructeur des preferences pour l'utilisateur
	public Preference(String pref) {
		this.pref=pref;
		this.aime = true;
	}
	
	@Override
	public boolean equals(Object o) {
		Preference p = (Preference) o;
		if (this.pref.equals(p.pref)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		if (this.aime==true) {
			return "1 "+this.pref;
		}
		else {
			return "0 "+this.pref;
		}
	}

	@Override
	public int compareTo(Preference o) {
		if (this.aime!=o.aime) {
			return -10;
		}
		else{
			return 10;
		}
	}
}


