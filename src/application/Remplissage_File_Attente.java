package application;

public class Remplissage_File_Attente extends Thread {
	
	public Remplissage_File_Attente(Modele modele) {
		
	}
	
	@Override
	public void run() {
		super.run();
		while (true) {
			try {
				sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("hey");
		}
	}
}
