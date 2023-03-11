package application;

import java.util.Random;

public class Preference implements Comparable<Preference> {//classe pour definir la preference de chaque profil
	Boolean aime;
	String pref;
	static String[] preferences = {
		"la musique","le rap","le jazz","l'hip-hop","la pop","le rock","la musique classique","le R&B","le slam","le reggae","l'electro","le latino","le blues","le metal","la K-pop",
		"la mer","le surf","jouer au volley","faire du velo","la natation","la plong�e","le kitsurf",
		"la montagne","skier","faire du snow","le parapente","le deletaplan","faire du VTT","la randonn�e","l'escalade",
		"les animaux","les chats","les chiens","les lapins","les hamst�res","les oiseaux","les poissons","les furets","les chevaux","les animaux de la ferme",
		"le sport","la muscu","courrir","le tennis","la dance","dancer","le ping-pong","le badminton","l'aviron","le cano�","le yoga","le pilate","l'�quitation",
		"la moto","voyager","la cuisine","cuisiner","la lecture","l'�critue","les films","les s�ries","les voitures","Netflix and chill","manger au resto","faire de l'urbex","jardiner",
		"faire du shopping","les tatouages","le gaming","les jeux de soci�t�","les mangas","les anim�s","prendre des photos","poser pour des photos","chanter","cin�ma","th��tre"
	};
	public Preference() {
		Random random = new Random();
		this.aime = random.nextBoolean();
		int rpref = random.nextInt(preferences.length);
		this.pref = preferences[rpref];
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
		else if (this.aime==o.aime) {
			return 10;
		}
		else {
			return 0;
		}
	}
}


