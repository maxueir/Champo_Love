package application;
	

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane Panel = new BorderPane();
		    BorderPane recherche_profil = new Recherche_profil(); 
			Panel.setId("panel");
			recherche_profil.setId("recherche_profil");
			Panel.setCenter(recherche_profil);
			
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
