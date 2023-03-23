package application;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;


public class Main extends Application {//classe principale de la vue(gère toutes les fenetres)
	Profil p;//profil qui est propose
	Scene s;//contenu de l'application
	Group grpcomp;//groupe avec tous les composants
	Group grp;//groupe avec le fond d'ecran et tous les composants(grpcomp) et les commandes(grpcommandes)
	Group grpcommandes;//groupe avec les commandes
	Modele modele;//Modele associe
	Lettre l;//commande matchs
	ImageView accueil;//commande accueil
	ImageView fav;//commande favoris
	ImageView retour;//commande retour 
	ImageView loupe;//commande loupe 
	BorderPane commandes;//BorderPane avec les commandes
	ArrayList<String> pos;//liste du chemin suivi avec les valeurs : menu,recherche_profil,profil,recherche,favoris,matchs
	static String[] couleur={"#A9CBD7","#CCA9DD","#F4EEB1","#FBAA99","#FAC881","#C4C9C7","#B0F2B6"};



	@Override
	public void start(Stage primaryStage) {
		try {
			this.pos=new ArrayList<String>();
			this.l=new Lettre();
			this.commandes= new BorderPane();
			this.grpcommandes=new Group();

			//this.commandes.getChildren().add(imageView);
			this.modele=new Modele();
			this.p=this.modele.prochainprofil();
			
			this.grp=new Group();
			this.grpcomp= new Group();


			Scene scene = new Scene(grp,500,500);
			//Scene scene = new Scene(grp,500,500);
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

			Image image = new Image(new FileInputStream("images/home.png"));
			accueil = new ImageView(image);
			accueil.setFitHeight(50);
			accueil.setFitWidth(50);



			Image image1 = new Image(new FileInputStream("images/fleche_retour.png"));
			retour = new ImageView(image1);
			retour.setFitHeight(40);
			retour.setFitWidth(40);



			Image image2 = new Image(new FileInputStream("images/favoris.png"));
			fav = new ImageView(image2);
			fav.setFitHeight(50);
			fav.setFitWidth(50);

			Image image3 = new Image(new FileInputStream("images/loupe.png"));
			loupe = new ImageView(image3);
			loupe.setFitHeight(40);
			loupe.setFitWidth(40);

			this.l = new Lettre();

			fav.setOnMouseClicked(e ->
			{
				this.pos.add("favoris");
				this.menuderoulant(this.modele.coupdecoeur, false);
			});

			loupe.setOnMouseClicked(e ->
			{
				definition_preferences ();
				this.pos.add("recherche");
				//retour.setVisible(false);
				//this.affichage_profil(this.p);//a changer par la methode d'acceuil
			});

			accueil.setOnMouseClicked(e ->
			{
				menu();
				//this.affichage_profil(this.p);//a changer par la methode d'acceuil
			});

			retour.setOnMouseClicked(e ->
			{

				this.pos.remove(this.pos.size()-1);
				if(this.pos.get(this.pos.size()-1)=="menu") {
					menu();
				}
				else if(this.pos.get(this.pos.size()-1)=="recherche_profil") {
					positionRecherche(false);
				}
				else if(this.pos.get(this.pos.size()-1)=="profil") {
					affichage_profil();
				}
				else if(this.pos.get(this.pos.size()-1)=="recherche") {

				}
				else if(this.pos.get(this.pos.size()-1)=="favoris") {
					menuderoulant(this.modele.coupdecoeur,false);
				}
				else if(this.pos.get(this.pos.size()-1)=="matchs") {
					menuderoulant(this.modele.matchs,true);
				}
			});

			this.l.setOnMouseClicked(e ->
			{
				this.l.vus();
				this.pos.add("matchs");
				this.menuderoulant(this.modele.matchs, true);
			});




			this.commandes.setLeft(retour);
			HBox topright= new HBox();
			topright.getChildren().addAll(l,fav,loupe,accueil);
			this.commandes.setRight(topright);
			this.commandes.setPrefHeight(50);
			this.commandes.setPrefWidth(this.s.getWidth());
			this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
				this.commandes.setPrefWidth(this.s.getWidth());
			});

			this.grpcommandes.getChildren().add(commandes);
			this.grpcomp.getChildren().add(grpcommandes);

			menu();

			//positionRecherche();
			//affichage_profil(this.p);
			//definition_preferences();
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

			this.l.ajouter();
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
		this.p=this.modele.prochainprofil();
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

	public void positionRecherche(boolean b) {//methode pour afficher la position recherche. b un booleen pour indiquer s'il faut changer de profil ou pas
		this.commandes.setCenter(null);
		this.accueil.setVisible(true);
		this.retour.setVisible(true);
		this.loupe.setVisible(true);
		this.fav.setVisible(true);
		this.l.setVisible(true);
		this.grp.getChildren().get(0).setId("recherche");
		if(b) {
			this.p=this.modele.prochainprofil();
		}
		this.grpcomp.getChildren().clear();
		//StackPane rootPane= new StackPane();

		Recherche_profil pane=new Recherche_profil(p,this);
		pane.setPrefSize(this.s.getWidth(), this.s.getHeight() );

		this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
			pane.setPrefWidth(this.s.getWidth());
		});

		this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
			pane.setPrefHeight(this.s.getHeight());
		});



		grpcomp.getChildren().add(pane);
		pane.toBack();

		this.grpcomp.getChildren().add(grpcommandes);




		//Panel.autosize();
		//recherche_profil.autosize();

	}

	public void coupdecoeur() {
		this.modele.coupdecoeur.add(this.p);
	}

	public void affichage_profil() {//methode pour afficher un profil
		this.commandes.setCenter(null);
		this.accueil.setVisible(true);
		this.retour.setVisible(true);
		this.loupe.setVisible(true);
		this.fav.setVisible(true);
		this.l.setVisible(true);

		this.grp.getChildren().get(0).setId("affichage");
		Random r = new Random();
		((BorderPane)this.grp.getChildren().get(0)).setBackground(new Background(new BackgroundFill(Color.web(couleur[r.nextInt(couleur.length)]),null,null)));

		this.grpcomp.getChildren().clear();

		BorderPane entete= new BorderPane();
		entete.setPrefSize(this.s.getWidth(),  this.s.getHeight());
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

		Label label=new Label(this.p.prenom+" "+this.p.nom+", "+this.p.age);
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
		this.grpcomp.getChildren().add(grpcommandes);




	}

	public void menu() {
		this.commandes.setCenter(null);
		this.accueil.setVisible(false);
		this.retour.setVisible(false);
		this.loupe.setVisible(true);
		this.fav.setVisible(true);
		this.l.setVisible(true);
		this.pos.clear();
		this.pos.add("menu");

		this.grp.getChildren().get(0).setId("recherche");
		StackPane cadre = new StackPane();
		HBox photo = new HBox();
		Image photo_profil_perso;
		Image profils;
		Image profils1;
		Image gif;
		try {
			photo_profil_perso = new Image(new FileInputStream("images/premier_profil.jpg"));
			ImageView imageView = new ImageView(photo_profil_perso);
			imageView.setPreserveRatio(true);
			imageView.setFitWidth(this.s.getWidth()/3);

			profils = new Image(new FileInputStream("images/premier_profil.jpg"));
			ImageView profil = new ImageView(profils);
			profil.setPreserveRatio(true);
			profil.setFitWidth(this.s.getWidth()/3);

			profils1 = new Image(new FileInputStream("images/deuxieme_profil.jpg"));
			ImageView profil1 = new ImageView(profils1);
			profil1.setPreserveRatio(true);
			profil1.setFitWidth(this.s.getWidth()/3);

			VBox recherche= new VBox();
			recherche.setAlignment(Pos.BOTTOM_CENTER);

			gif = new Image(new FileInputStream("images/testdecoupe.gif"));
			ImageView gif1 = new ImageView(gif);
			gif1.setPreserveRatio(true);
			gif1.setFitWidth(100);

			Label labele =new Label("Commencer la recherche");
			labele.setFont(new Font("Serif", 20));
			labele.setTextFill(Color.BLACK);
			labele.setStyle("-fx-font-weight: bold");

			profil.setOnMouseClicked(e ->
			{
				//aller vers le profil
			});
			profil1.setOnMouseClicked(e ->
			{
				//aller vers le profil
			});
			imageView.setOnMouseClicked(e ->
			{
				//aller vers la personalisation du profil
			});

			gif1.setOnMouseClicked(e ->
			{
				positionRecherche(true);
				this.pos.add("recherche_profil");
			});
			FadeTransition ft= new FadeTransition(Duration.millis(2000), profil);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setAutoReverse(true);
			ft.setCycleCount(10000);

			FadeTransition ft1= new FadeTransition(Duration.millis(2000), profil1);
			ft1.setFromValue(0);
			ft1.setToValue(1);
			ft1.setAutoReverse(true);
			ft1.setCycleCount(10000);

			cadre.setPrefWidth(this.s.getWidth());
			cadre.setPrefHeight(this.s.getHeight());

			Region reg =new Region();
			reg.setPrefWidth(this.s.getWidth()/6);
			Region reg1 =new Region();
			reg1.setPrefWidth(this.s.getWidth()/2);
			this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
				cadre.setPrefWidth(this.s.getWidth());
				profil.setFitWidth(this.s.getWidth()/3);
				profil1.setFitWidth(this.s.getWidth()/3);
				imageView.setFitWidth(this.s.getWidth()/3);
				reg.setPrefWidth(this.s.getWidth()/6);
				reg1.setPrefWidth(this.s.getWidth()/2);
			});

			this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
				cadre.setPrefHeight(this.s.getHeight());
			});

			recherche.getChildren().addAll(gif1,labele);
			photo.getChildren().add(imageView);

			photo.getChildren().add(reg);
			photo.getChildren().add(profil);
			HBox photo2 = new HBox();
			photo2.getChildren().addAll(reg1,profil1);
			cadre.getChildren().addAll(photo,photo2,recherche);
			//photo.setId("menu");
			//photo.set
			photo.setAlignment(Pos.CENTER);
			photo2.setAlignment(Pos.CENTER);
			this.grpcomp.getChildren().clear();
			this.grpcomp.getChildren().add(cadre);
			this.grpcomp.getChildren().add(grpcommandes);
			ft.play();
			ft1.play();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	
	}
	
	public void definition_preferences () {
		this.commandes.setCenter(null);
		this.accueil.setVisible(true);
		this.retour.setVisible(true);
		this.loupe.setVisible(false);
		this.fav.setVisible(true);
		this.l.setVisible(true);
		
		this.grp.getChildren().get(0).setId("recherche");
		
		Menu_preferences menu_preferences = new Menu_preferences();
		
		this.grpcomp.getChildren().add(menu_preferences);
		

	}
	public void menuderoulant(ArrayList<Profil> l,boolean b) {//liste des profils a afficher et booleen b pour dire ou non s'il s'agit des matchs sinon c'est les favoris
		this.grp.getChildren().get(0).setId("recherche");
		this.accueil.setVisible(true);
		this.retour.setVisible(true);
		this.loupe.setVisible(true);
		if(b) {
			this.fav.setVisible(true);
			this.l.setVisible(false);
		}
		else {
			this.fav.setVisible(false);
			this.l.setVisible(true);
		}
		ScrollPane sp= new ScrollPane();
		VBox vb = new VBox();
		VBox vb1 = new VBox();


		BackgroundSize bSize = new BackgroundSize(1, 1, true, true, false, false);
		Image imagecourante=new Image("file:images/menu_deroulant.jpg");

		this.grpcomp.getChildren().clear();
		
		BorderPane reg= new BorderPane();
		reg.setPrefSize(this.s.getWidth(), 80);
		int taille=0;
		if(b) {
			taille=this.modele.matchs.size();
			Label label= new Label("VOS MATCHS");
			label.setFont(new Font("Serif", 30));
			label.setStyle("-fx-font-weight: bold");
			label.setTextFill(Color.BLACK);
			commandes.setCenter(label);
		}
		else {
			taille=this.modele.coupdecoeur.size();
			Label label= new Label("VOS FAVORIS");
			label.setFont(new Font("Serif", 30));
			label.setStyle("-fx-font-weight: bold");
			label.setTextFill(Color.BLACK);
			commandes.setCenter(label);
		}
		vb1.getChildren().add(reg);
		for(int i=0;i<taille;i++) {
			HBox hb1 = new HBox();
			hb1.setBackground(new Background(new BackgroundImage(imagecourante,
					BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.CENTER,
					bSize)));

			hb1.setPrefSize(this.s.getWidth(), 80);
			
			hb1.setId(String.valueOf(i));
			
			this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
				hb1.setPrefWidth(this.s.getWidth());
			});
			
			hb1.setOnMouseClicked(e ->
			{	if(b) {
				this.p=this.modele.matchs.get(Integer.valueOf(hb1.getId()));
			}
			else {
				this.p=this.modele.coupdecoeur.get(Integer.valueOf(hb1.getId()));
			}
				affichage_profil();
			});
			
			Image img=new Image("file:images/deuxieme_profil.jpg");
			ImageView imgv=new ImageView(img);
			imgv.setFitHeight(70);
			imgv.setFitWidth(70);
			hb1.setAlignment(Pos.CENTER_LEFT);
			Region reg1 = new Region();
			reg1.setPrefWidth(20);
			Region reg2 = new Region();
			reg2.setPrefWidth(20);
			Label lab;
			if(b) {
				lab= new Label(this.modele.matchs.get(i).prenom+" - "+this.modele.matchs.get(i).age);
				lab.setFont(new Font("Serif", 30));
				lab.setStyle("-fx-font-weight: bold");
				lab.setTextFill(Color.BLACK);
			}
			else {
				lab= new Label(this.modele.coupdecoeur.get(i).prenom+" - "+this.modele.coupdecoeur.get(i).age);
				lab.setFont(new Font("Serif", 20));
				lab.setStyle("-fx-font-weight: bold");
				lab.setTextFill(Color.BLACK);
			}
			hb1.getChildren().addAll(reg1,imgv,reg2,lab);
			vb.getChildren().add(hb1);

			

		}
		sp.setContent(vb);
		if(b) {
			sp.setPrefSize(this.s.getWidth(),Math.min(this.modele.matchs.size()*80, this.s.getHeight()-80));
		}
		else {
			sp.setPrefSize(this.s.getWidth(),Math.min(this.modele.coupdecoeur.size()*80, this.s.getHeight()-80));
		}
		//sp.bar
		sp.setStyle("-fx-background-color:transparent;");
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
			sp.setPrefWidth(this.s.getWidth());
		});
		this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
			sp.setPrefHeight(this.s.getHeight()-80);
		});
		
		vb1.getChildren().add(sp);
		if(b&&this.modele.matchs.size()!=0 || !b && this.modele.coupdecoeur.size()!=0) {
		this.grpcomp.getChildren().add(vb1);}
		this.grpcomp.getChildren().add(grpcommandes);
	}

	//public void main(Modele m) {
	//this.m=m;
	//this.launch();
	//}

	public static void main(String[] args) {
		launch();

	}
}
