package application;
import java.net.*;
import java.util.Random;
import java.io.*;

public class DistanceEntreVille {
    public static final double R = 6371; // Rayon de la Terre en kilomètres
    
<<<<<<< Updated upstream
    public static void main(String[] args) throws Exception {
    	//int a=distance("Paris","Albi");
        //System.out.println(a);
    	/*
    	Random r=new Random();
    	String b = Profil.villes[r.nextInt(Profil.villes.length)];
    	String c = Profil.villes[r.nextInt(Profil.villes.length)];
        int a=distance(b,c);
        System.out.println(a);
        while(a!=-1) {
        	b = Profil.villes[r.nextInt(Profil.villes.length)];
        	c = Profil.villes[r.nextInt(Profil.villes.length)];
        	a=distance(Profil.villes[r.nextInt(Profil.villes.length)],Profil.villes[r.nextInt(Profil.villes.length)]);
            System.out.println(a);
        }
        System.out.println(a+b+c);*/
    }
    static public int distance(String aa, String b) throws IOException{
=======
    public static void main(String[] args) {
		System.out.println(distance("toulon","reims"));
	}
    
    static public int distance(String aa, String b){
    	try {
>>>>>>> Stashed changes
    	URL url = new URL("https://api-adresse.data.gouv.fr/search/?q="+aa);
        URLConnection con = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String m =in.readLine();
        //System.out.println(m);
        /*
        String p=in.readLine();
        if(p.charAt(0)=='{') {
        	m=p;
        }*/
        
        
        String coordsA="";
        try {
        coordsA=((m.split("coordinates")[1]).split("]")[0].substring(3));
        }
        catch(Exception e) {
        	return -1;
        }
        in.close();
        
        
        URL url1 = new URL("https://api-adresse.data.gouv.fr/search/?q="+b);
        URLConnection con1 = url1.openConnection();
        BufferedReader in1 = new BufferedReader(new InputStreamReader(con1.getInputStream()));
        String h=in1.readLine();
        //System.out.println(h);
        
        /*
        String o=in.readLine();
        if(o.charAt(0)=='{') {
        	h=o;
        }*/
        
        String coordsB="";
        try {
        coordsB=((h.split("coordinates")[1]).split("]")[0].substring(3));
        }
        catch(Exception e) {
        	return -1;
        }
        in1.close();
        
    
        	double lat1=Double.parseDouble(coordsA.split(",")[1]);
        	double lon1=Double.parseDouble(coordsA.split(",")[0]);
        	double lat2=Double.parseDouble(coordsB.split(",")[1]);
        	double lon2=Double.parseDouble(coordsB.split(",")[0]);
            double dLat = Math.toRadians(lat2 - lat1);
            double dLon = Math.toRadians(lon2 - lon1);
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(dLon / 2) * Math.sin(dLon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = R * c;
            
            return ((int)Math.round(distance));
    	}
    	catch(IOException e) {
    		return -1;
    	}
        
}
}

