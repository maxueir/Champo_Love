package application;
	

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;


public class Main extends Application {//classe principale de la vue(g?re toutes les fenetres)
	Profil p;//profil qui est propose
	Scene s;//contenu de l'application
	Group grpcomp;//groupe avec tous les composants
	Group grp;//groupe avec le fond d'ecran et tous les composants(grpcomp) et les commandes(grpcommandes)
	Group grpcommandes;//groupe avec les commandes
	Modele modele;
	String pos="menu";//menu,recherche_profil,profil,recherche,favoris,matchs
	static String[] couleur={"#A9CBD7","#CCA9DD","#F4EEB1","#FBAA99","#FAC881","#C4C9C7","#B0F2B6"};
	
	  
	  
	@Override
	public void start(Stage primaryStage) {
		try {
			this.grpcommandes=new Group();
			Image image = new Image(new FileInputStream("images/home.png"));
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);
			imageView.setOnMouseClicked(e ->
			{
				this.affichage_profil(this.p);//a changer par la methode d'acceuil
			});
			BorderPane bp=new BorderPane();
			bp.setTop(imageView);
			
			this.p=new Profil();
			this.modele=new Modele();
			
			this.grp=new Group();
			this.grpcomp= new Group();
			
			
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
			
			BorderPane Panel = new BorderPane();
			
			Panel.setPrefSize(this.s.getHeight() , this.s.getWidth());
			
			this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
				Panel.setPrefWidth(this.s.getWidth());
			});

			this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
				Panel.setPrefHeight(this.s.getHeight());
			});
			
			this.grp.getChildren().addAll(Panel,grpcomp);
			
			primaryStage.show();
			this.grpcommandes.getChildren().add(bp);
			this.grpcomp.getChildren().add(grpcommandes);
			
			lettre();
			//positionRecherche();
			//affichage_profil(this.p);
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
		this.p.estvalide=b;
		if(b&&this.p.avalide) {
			this.modele.matchs.add(p);
			/*
			BorderPane match = new BorderPane();
			Image image;
			try {
				image = new Image(new FileInputStream("images/test.png"));
				 ImageView imageView = new ImageView(image);
				//Setting the image view
		           
				 
		            //Setting the position of the image
		            imageView.setX(50);
		            imageView.setY(25);
		 
		            //setting the fit height and width of the image view
		            imageView.setFitHeight(455);
		            imageView.setFitWidth(500);
		 
		            //Setting the preserve ratio of the image view
		            imageView.setPreserveRatio(true);
		            match.setCenter(imageView);
		            //this.grprecherche.getChildren().add(imageView);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}*/
			 
            
			
		}
		this.p=new Profil();
		Recherche_profil pane=new Recherche_profil(p,this);
		pane.setPrefSize(this.s.getWidth() ,this.s.getHeight() );
		
		this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
			pane.setPrefWidth(this.s.getWidth());
		});

		this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
			pane.setPrefHeight(this.s.getHeight());
		});
		this.grpcomp.getChildren().addAll(pane);
		pane.toBack();
		//StackPane rootPane= new StackPane();
	    
		
		//((BorderPane)this.grp.getChildren().get(0)).setCenter();
		//((BorderPane)this.grp.getChildren().get(0)).setCenter(new Recherche_profil(p,this));
		
		//this.grp.getChildren().add(Panel);
	
	}
	public void positionRecherche() {//methode pour afficher la position recherche
		this.grp.getChildren().get(0).setId("recherche");
		this.p=new Profil();
		//StackPane rootPane= new StackPane();
		
		Recherche_profil pane=new Recherche_profil(p,this);
		pane.setPrefSize(this.s.getHeight() , this.s.getWidth());
		
		this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
			pane.setPrefWidth(this.s.getWidth());
		});

		this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
			pane.setPrefHeight(this.s.getHeight());
		});
		
		
		
		grpcomp.getChildren().add(pane);
		pane.toBack();
		
		
		
	    
		
		
		//Panel.autosize();
		//recherche_profil.autosize();
		
	}
	
	public void coupdecoeur() {
		this.modele.coupdecoeur.add(this.p);
	}
	
	public void affichage_profil(Profil p) {//methode pour afficher un profil
		
		this.grp.getChildren().get(0).setId("affichage");
		Random r = new Random();
		((BorderPane)this.grp.getChildren().get(0)).setBackground(new Background(new BackgroundFill(Color.web(couleur[r.nextInt(couleur.length)]),null,null)));
		
		this.grpcomp.getChildren().clear();
		this.grpcomp.getChildren().add(grpcommandes);
		BorderPane entete= new BorderPane();
		entete.setPrefSize(this.s.getHeight(), this.s.getWidth());
		this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
			entete.setPrefWidth(this.s.getWidth());
		});

		this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
			entete.setPrefHeight(this.s.getHeight());
		});
		VBox pdp= new VBox();
		
		Image image;
		try {
			image = new Image(new FileInputStream("images/premier_profil.jpg"));
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(this.s.getHeight()/2);
			imageView.setFitWidth(this.s.getWidth()/2);
			this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
				imageView.setFitHeight(this.s.getHeight()/2);
			});
			this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
				imageView.setFitWidth(this.s.getWidth()/2);
			});
			pdp.getChildren().add(imageView);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pdp.setAlignment(Pos.CENTER);
		
		Label label=new Label(p.prenom+" "+p.nom+", "+p.age);
		label.setFont(new Font("Serif", 35));
		label.setTextFill(Color.BLACK);
		label.setStyle("-fx-font-weight: bold");
		pdp.getChildren().add(label);
		entete.setTop(pdp);
		Label labele =new Label(this.p.toString());
		labele.setFont(new Font("Serif", 35));
		labele.setTextFill(Color.BLACK);
		labele.setStyle("-fx-font-weight: bold");
		labele.setWrapText(true);
		entete.setCenter(labele);

       ScrollPane sp=new ScrollPane();
       sp.setFitToWidth(true);
       sp.setContent(entete);
		this.grpcomp.getChildren().add(sp);
		
		
		
		
	}
	
	//public void main(Modele m) {
		//this.m=m;
		//this.launch();
	//}
	
	public void lettre() {
		BorderPane lettre= new BorderPane();
		Image image;
		ImageView imageView;
		try {
			image = new Image(new FileInputStream("images/lettre.png"));
			imageView = new ImageView(image);
			final Canvas canvas = new Canvas(10,10);
	        GraphicsContext gc = canvas.getGraphicsContext2D();
	        gc.beginPath();
	        //gc.arcTo(200, 200,5,0, Math.PI*2);
	        gc.fillOval(50,50,30,30);
	        gc.setFill(Color.RED);
	        lettre.getChildren().addAll(canvas);
	        this.grp.getChildren().add(lettre);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		launch();

	}
}
