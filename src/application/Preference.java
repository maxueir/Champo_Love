package application;

import java.util.Random;

public class Preference implements Comparable<Preference> {//classe pour definir la preference de chaque profil
	Boolean aime;
	String pref;
	static String[] preferences = {
		"La musique","Le rap","Le jazz","Le hip-hop","La pop","Le rock","La musique classique","Le R&B","Le slam","Le reggae","L'electro","Le latino","Le blues","Le metal","La K-pop",
		"La mer","Le surf","Jouer au volley","Faire du velo","La natation","La plongée","Le kite-surf",
		"La montagne","Skier","Faire du snow","Le parapente","Le deltaplane","Faire du VTT","La randonnée","L'escalade",
		"Les animaux","Les chats","Les chiens","Les lapins","Les hamstères","Les oiseaux","Les poissons","Les furets","Les chevaux","Les animaux de la ferme",
		"Le sport","La muscu","Courrir","Le tennis","La dance","Dancer","Le ping-pong","Le badminton","L'aviron","Le canoë","Le yoga","Le pilate","L'équitation",
		"La moto","Voyager","La cuisine","Cuisiner","La lecture","L'écritue","Les films","Les séries","Les voitures","Netflix and chill","Manger au resto","Faire de l'urbex","Jardiner",
		"Faire du shopping","Les tatouages","Le gaming","Les jeux de société","Les mangas","Les animés","Prendre des photos","Poser pour des photos","Chanter","Le cinéma","Le théâtre"
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
		else{
			return 10;
		}
	}
}


