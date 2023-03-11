package application;
	

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


public class Main extends Application {//classe principale de la vue(gère toutes les fenetres)
	Profil p;//profil qui est propose
	Scene s;
	//StackPane root;
	Group grprecherche;//groupe avec tous les profils qui bougent(pour la fenetre recherche_profil)
	Group grp;//groupe de tous les composants
	Modele m;
	
	  
	  
	@Override
	public void start(Stage primaryStage) {
		try {
			this.grp=new Group();
			
			
			Scene scene = new Scene(grp,500,500);
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
			
			positionRecherche();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void changerProfil(boolean b) {//booleen d'information si le profil a ete valide ou non
		/*System.out.println("changement"+b);
		this.p=new Profil();
		BorderPane Panel = new BorderPane();
	    BorderPane recherche_profil = new Recherche_profil(p,this); 
	    Panel.setCenter(recherche_profil);
		Panel.autosize();
		recherche_profil.autosize();
		System.out.println(this.grp.getLayoutX());
		this.grp.getChildren().add(Panel);
		this.grp.getChildren().get(this.grp.getChildren().size()-1).toBack();
		this.grp.getChildren().*/
		//System.out.println(this.grprecherche.getChildren());
		this.p=new Profil();
		Recherche_profil pane=new Recherche_profil(p,this);
		pane.setPrefSize(this.s.getWidth() ,this.s.getHeight() );
		
		this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
			pane.setPrefWidth(this.s.getWidth());
		});

		this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
			pane.setPrefHeight(this.s.getHeight());
		});
		this.grprecherche.getChildren().addAll(pane);
		pane.toBack();
		//StackPane rootPane= new StackPane();
	    
		
		//((BorderPane)this.grp.getChildren().get(0)).setCenter();
		//((BorderPane)this.grp.getChildren().get(0)).setCenter(new Recherche_profil(p,this));
		
		//this.grp.getChildren().add(Panel);
	
	}
	public void positionRecherche() {
		this.p=new Profil();
		//StackPane rootPane= new StackPane();
	    
	   
	    BorderPane Panel = new BorderPane();
		Panel.setId("panel");
		Panel.setPrefSize(this.s.getHeight() , this.s.getWidth());
		
		Recherche_profil pane=new Recherche_profil(p,this);
		pane.setPrefSize(this.s.getHeight() , this.s.getWidth());
		
		this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
			Panel.setPrefWidth(this.s.getWidth());
			pane.setPrefWidth(this.s.getWidth());
		});

		this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
			Panel.setPrefHeight(this.s.getHeight());
			pane.setPrefHeight(this.s.getHeight());
		});
		
		
		this.grprecherche= new Group();
		grprecherche.getChildren().add(pane);
		
		
		this.grp.getChildren().addAll(Panel,grprecherche);
	    
		
		
		//Panel.autosize();
		//recherche_profil.autosize();
		
	}
	
	public void coupdecoeur() {
		this.m.coupdecoeur.add(this.p);
	}
	
	public void affichage_profil(Profil p) {
		
	}
	
	public void main(Modele m) {
		this.m=m;
		launch();
	}
}
