package application;
	

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane Panel = new BorderPane();
		    BorderPane recherche_profil = new Recherche_profil();
			
			Panel.setCenter(recherche_profil);
			Scene scene = new Scene(Panel,500,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//Panel.setId("pane");
			primaryStage.setScene(scene);
			primaryStage.setTitle("ChampoLove");
			Image img = new Image("file:images/icone.jpg");
			primaryStage.getIcons().add(img);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
