package application;
	

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


public class Main extends Application {
	Profil p;
	@Override
	public void start(Stage primaryStage) {
		try {
			p=new Profil();
			BorderPane Panel = new BorderPane();
		    BorderPane recherche_profil = new Recherche_profil(p,this); 
			Panel.setId("panel");
			//recherche_profil.setId("recherche_profil");
			Panel.setCenter(recherche_profil);
			Panel.autosize();
			recherche_profil.autosize();
			
			Scene scene = new Scene(Panel,500,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("ChampoLove");
			Image img = new Image("file:images/icone.jpg");
			primaryStage.getIcons().add(img);
			
			Image curseur=new Image("file:images/flechedecoupee.png");
			Cursor c = ImageCursor.chooseBestCursor(new Image[] {curseur}, 0, 0);
			scene.setCursor(c);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void changerProfil() {
		System.out.println("changement");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
