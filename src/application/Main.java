package application;
	

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


public class Main extends Application {
	Profil p;
	Scene s;
	StackPane root;
	@Override
	public void start(Stage primaryStage) {
		try {
			p=new Profil();
			BorderPane Panel = new BorderPane();
		    BorderPane recherche_profil = new Recherche_profil(p,this); 
			StackPane rootPane= new StackPane();
			this.root=rootPane;
			rootPane.getChildren().add(Panel);
		    Panel.setId("panel");
			//recherche_profil.setId("recherche_profil");
			Panel.setCenter(recherche_profil);
			Panel.autosize();
			recherche_profil.autosize();
			
			Scene scene = new Scene(rootPane,500,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("ChampoLove");
			Image img = new Image("file:images/icone.jpg");
			primaryStage.getIcons().add(img);
			
			Image curseur=new Image("file:images/flechedecoupee.png");
			Cursor c = ImageCursor.chooseBestCursor(new Image[] {curseur}, 0, 0);
			scene.setCursor(c);
			this.s=scene;
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void changerProfil(boolean b) {//booleen d'information si le profil a ete valide ou non
		System.out.println("changement");
		this.p=new Profil();
		BorderPane Panel = new BorderPane();
	    BorderPane recherche_profil = new Recherche_profil(p,this); 
	    Panel.setCenter(recherche_profil);
		Panel.autosize();
		recherche_profil.autosize();
		this.root.getChildren().add(Panel);
		//this.root.getChildren().get(this.root.getChildren().size()-1).toBack();
		
		//System.out.println(this.root.getChildren());
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
