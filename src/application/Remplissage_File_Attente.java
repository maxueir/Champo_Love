package application;

public class Remplissage_File_Attente extends Thread {
	
	public Remplissage_File_Attente(Modele modele) {
		
	}
	
	@Override
	public void run() {
		super.run();
		for(int i=0;i<5;i++) {
			System.out.println("hey");
		}
	}
}
