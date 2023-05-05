package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import application.Profil.orientation;
import application.Profil.sexe;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

//TODO mettre possibilite de liker dans un profil, supprimer un favoris et annuler un match

public class Main extends Application implements Serializable {//classe principale de la vue(gÃ¨re toutes les fenetres)

	Profil p;//profil qui est propose
	Profil p_aux;
	Scene s;//contenu de l'application
	Group grpcomp;//groupe avec tous les composants
	Group grp;//groupe avec le fond d'ecran et tous les composants(grpcomp) et les commandes(grpcommandes)
	Group grpcommandes;//groupe avec les commandes
	Modele modele;//Modele associe
	ProfilPerso profil;
	Lettre l;//commande matchs
	ImageView accueil;//commande accueil
	ImageView fav;//commande favoris
	ImageView retour;//commande retour
	BorderPane commandes;//BorderPane avec les commandes
	ArrayList<String> pos;//liste du chemin suivi avec les valeurs : menu,recherche_profil,profil,recherche,favoris,matchs,def_prof
	static String[] couleur={"#A9CBD7","#CCA9DD","#F4EEB1","#FBAA99","#FAC881","#C4C9C7","#B0F2B6"};

	@Override
	public void start(Stage primaryStage) {
		// Serealization
		primaryStage.setOnCloseRequest(Event->{
			try {
				this.modele.thread.reset();
				FileOutputStream file_out = new FileOutputStream("profil.dat");
				ObjectOutputStream obj = new ObjectOutputStream(file_out);
				
				obj.writeObject(this.modele);
				
				obj.close();
				file_out.close();
				System.out.println("seria ok");
				//System.out.println("Serialization ok, profil perso : " + this.modele.profilPerso.toString());
			}
			catch (IOException e1) {
				e1.printStackTrace();
				//System.out.println("Serialization fail, profil perso : " + this.modele.profilPerso.toString());
			}
		});
		
		//this.modele = null;

		/*try {
			this.modele=new Modele();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}*/
		// Deserealization
		try {
			FileInputStream file_in = new FileInputStream("profil.dat");
			ObjectInputStream obj = new ObjectInputStream(file_in);
			
			this.modele = (Modele)obj.readObject();
			
			this.modele.completion();
			
			obj.close();
			file_in.close();
			System.out.println("deseria ok");
			this.modele.thread.start();
			
			
			//System.out.println("Deserialization ok, profil perso : " + this.modele.profilPerso.toString());
		}
		catch (IOException | ClassNotFoundException e) {//instancié les valeurs des attributs
			try {
				this.modele=new Modele();
				this.modele.thread.start();
				System.out.println("nv mod");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			//System.out.println("IOException Deserialization fail, profil perso : " + this.modele.profilPerso.toString());
		}
		

		try {
			this.pos=new ArrayList<String>();
			this.l=new Lettre();
			this.commandes= new BorderPane();
			this.grpcommandes=new Group();

			//this.commandes.getChildren().add(imageView); 
			System.out.println("prochain profil");
			this.p=this.modele.prochainprofil();
			System.out.println("fin");

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
			accueil.setFitHeight(40);
			accueil.setFitWidth(40);



			Image image1 = new Image(new FileInputStream("images/fleche_retour1.png"));
			retour = new ImageView(image1);
			retour.setFitHeight(40);
			retour.setFitWidth(35);
			
			Image image2 = new Image(new FileInputStream("images/favoris.png"));
			fav = new ImageView(image2);
			fav.setFitHeight(40);
			fav.setFitWidth(40);

			Image image3 = new Image(new FileInputStream("images/test.png"));

			this.l = new Lettre();

			fav.setOnMouseClicked(e ->
			{
				this.pos.add("favoris");
				this.menuderoulant(this.modele.coupdecoeur, false);
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
					affichage_profil(this.p);
				}
				else if(this.pos.get(this.pos.size()-1)=="recherche") {

				}
				else if(this.pos.get(this.pos.size()-1)=="favoris") {
					menuderoulant(this.modele.coupdecoeur,false);
				}
				else if(this.pos.get(this.pos.size()-1)=="matchs") {
					menuderoulant(this.modele.matchs,true);
				}
				else if(this.pos.get(this.pos.size()-1)=="def_prof") {
					definition_profil();
				}
			});

			this.l.setOnMouseClicked(e ->
			{
				this.l.vus();
				this.pos.add("matchs");
				this.menuderoulant(this.modele.matchs, true);
			});



			HBox vb=new HBox();
			Region reg = new Region();
			reg.setPrefWidth(5);
			vb.getChildren().addAll(reg,retour);
			this.commandes.setLeft(vb);
			HBox topright= new HBox();
			topright.getChildren().addAll(l,fav,accueil);
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
			//definition_profil();
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
		try {
			this.p=this.modele.prochainprofil();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		this.fav.setVisible(true);
		this.l.setVisible(true);
		this.grp.getChildren().get(0).setId("recherche");
		if(b) {
			try {
				this.p=this.modele.prochainprofil();
			} catch (IOException e) {
				e.printStackTrace();
			}
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

	public void affichage_profil(Profil profil) {//methode pour afficher un profil
		this.commandes.setCenter(null);
		this.accueil.setVisible(true);
		this.retour.setVisible(true);
		this.fav.setVisible(true);
		this.l.setVisible(true);
		Random r = new Random();
		Paint p=Color.web(couleur[r.nextInt(couleur.length)]);
		this.grp.getChildren().get(0).setId("affichage");
		((BorderPane)this.grp.getChildren().get(0)).setBackground(new Background(new BackgroundFill(p,null,null)));

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
		VBox vb2=new VBox();
		vb2.setBackground(new Background(new BackgroundFill(p,null,null)));
		//vb2.getChildren().addAll(pdp,entete);
		//vb2.getChildren().addAll(imageView,entete);
		
		Image image;
		ImageView imageView;
		try {
			image = new Image(new FileInputStream(profil.photo.split(":")[1]));
			imageView = new ImageView(image);
			imageView.setFitHeight(this.s.getHeight()/3);
			imageView.setFitWidth(this.s.getWidth()/3);
			
			
			this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
				imageView.setFitHeight(this.s.getHeight()/3);
			});
			this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
				imageView.setFitWidth(this.s.getWidth()/3);
			});
			pdp.getChildren().add(imageView);
			vb2.getChildren().addAll(imageView);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pdp.setAlignment(Pos.CENTER);

		Label label=new Label(profil.prenom+" "+profil.nom+", "+profil.age);
		label.setFont(new Font("Serif", 35));
		label.setTextFill(Color.BLACK);
		label.setStyle("-fx-font-weight: bold");
		pdp.getChildren().add(label);
		//entete.setTop(pdp);
		Label labele =new Label(profil.toString());
		labele.setFont(new Font("Serif", 30));
		labele.setTextFill(Color.BLACK);
		labele.setStyle("-fx-font-weight: bold");
		labele.setWrapText(true);
		entete.setCenter(labele);
		ImageView imv=null;
		try {
			Image im = new Image(new FileInputStream("images/favoris_vide.png"));
			imv = new ImageView(im);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(profil.estfav) {
			
		try {
			Image im = new Image(new FileInputStream("images/favoris.png"));
			imv = new ImageView(im);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		}
		imv.setFitHeight(40);
		imv.setFitWidth(40);
		pdp.getChildren().add(imv);
		
		imv.setOnMouseClicked(e ->
		{
			if(this.modele.profilPerso!=null) {
				if(profil.estfav==false) {
					
					/*profil.estfav=false;
					this.modele.coupdecoeur.remove(this.modele.coupdecoeur.size()-1);
					((ImageView) e.getTarget()).setImage(new Image("file:images/favoris_vide.png"));
				}
				else {*/
					
					profil.estfav=true;
					this.modele.coupdecoeur.add(profil);
					((ImageView) e.getTarget()).setImage(new Image("file:images/favoris.png"));
				}
			}else {
				Alert dialog = new Alert(AlertType.INFORMATION);
				dialog.setTitle("Action impossible"); 
				dialog.setHeaderText("Vous n'avez pas encore créé votre profil");
				dialog.showAndWait();
			}
		});
		
		//entete.setTop(panimv);
		//TODO 
		ScrollPane sp=new ScrollPane();
		sp.setStyle("-fx-background-color:transparent;");
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setPrefHeight(this.s.getHeight()/2 -20);
		//sp.setFitToWidth(true);
		vb2.setAlignment(Pos.CENTER);
		//vb2.getChildren().addAll(label,imv);
		Region region=new Region();
		region.setPrefSize(this.s.getWidth(),5 );
		BorderPane bp= new BorderPane();
		bp.setPrefWidth(this.s.getWidth());
		bp.setPrefHeight(this.s.getHeight()/2 +100);
		bp.setCenter(labele);
		bp.setBackground(new Background(new BackgroundFill(p,null,null)));
		sp.setBackground(new Background(new BackgroundFill(p,null,null)));
		sp.setContent(bp);
		//sp.setContent(new Menu_profil(Modele.profilPerso));
		vb2.getChildren().addAll(label,region,imv,sp);
		//sp.setPrefHeight(0)
		
		//vb2.setPrefSize(this.s.getHeight()/2, this.s.getWidth()/2);
		//vb2.resize(this.s.getHeight()/2, this.s.getWidth()/2);
		//sp.setContent(vb2);
		
		
		/*
		//debut
		VBox test=new VBox();
		Label testlab=new Label("hey c'est maximo maxime le plus beau de tous les poissons, je nage comme un requin dans l'eau");
		testlab.setWrapText(true);
		test.getChildren().add(testlab);
		
		
		this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
			test.setPrefWidth(this.s.getWidth());
		});
		sp.setContent(test);
		
		testlab.setFont(new Font("Serif", 35));
		//fin
		//*/
		
		
		this.grpcomp.getChildren().add(vb2);
		this.grpcomp.getChildren().add(grpcommandes);

		//sp.setPrefSize(this.s.getWidth(),this.s.getHeight());
		//sp.setMaxSize(500, 500);
		
		
		this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
			//sp.setPrefWidth(this.s.getWidth());
			region.setPrefSize(this.s.getWidth(),5 );
			bp.setPrefWidth(this.s.getWidth());
		});
		this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
			sp.setPrefHeight(this.s.getHeight()/2 -20);
			region.setPrefSize(this.s.getWidth(),5 );
			bp.setPrefHeight(this.s.getHeight()/2 +100);
		});



	}

	public void menu() {
		this.commandes.setCenter(null);
		this.accueil.setVisible(false);
		this.retour.setVisible(false);
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


		ImageView profil;
		ImageView profil1;
		try {
			ImageView imgv;
			try {
<<<<<<< Updated upstream
				photo_profil_perso = new Image(new FileInputStream(this.modele.profilPerso.photo)); 
				
=======
				photo_profil_perso = new Image(new FileInputStream(this.modele.profilPerso.image)); 

>>>>>>> Stashed changes
				imgv = new ImageView(photo_profil_perso);
			}
			catch(Exception e ) {
				photo_profil_perso = new Image(new FileInputStream("images/pas_de_pdp.jpg"));
				imgv = new ImageView(photo_profil_perso);
			}
			ImageView imageView=imgv;
			ArrayList<Profil> list=new ArrayList<Profil>();

			imageView.setPreserveRatio(true);
			imageView.setFitWidth(this.s.getWidth()/3);

			try {
				list.add(this.modele.prochainprofil());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			profils = new Image(new FileInputStream(list.get(0).photo.split(":")[1]));
			profil = new ImageView(profils);
			profil.setPreserveRatio(true);
			profil.setFitWidth(this.s.getWidth()/3);

			try {
				list.add(this.modele.prochainprofil());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			profils1 = new Image(new FileInputStream(list.get(1).photo.split(":")[1]));
			profil1 = new ImageView(profils1);
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
				this.pos.add("profil");
				this.affichage_profil(list.get(0));
				//aller vers le profil
			});
			profil1.setOnMouseClicked(e ->
			{
				this.pos.add("profil");
				this.affichage_profil(list.get(0));
			});

			imageView.setOnMouseClicked(e ->
			{
				this.pos.add("def_prof");
				this.definition_profil();
				//aller vers la personalisation du profil
			});

			gif1.setOnMouseClicked(e ->
			{
				if(this.modele.profilPerso!=null) {
				//if(true) {
					this.pos.add("recherche_profil");
				positionRecherche(true);
				}else {
					Alert dialog = new Alert(AlertType.INFORMATION);
					dialog.setTitle("Action impossible");
					dialog.setHeaderText("Vous n'avez pas encore créé votre profil");
					dialog.showAndWait();
				}
				
			});

			FadeTransition ft1aller= new FadeTransition(Duration.millis(2000), profil1);
			ft1aller.setFromValue(0);
			ft1aller.setToValue(1);
			ft1aller.setAutoReverse(true);
			ft1aller.setCycleCount(2);

			FadeTransition ft1retour= new FadeTransition(Duration.millis(2000), profil);
			ft1retour.setFromValue(0);
			ft1retour.setToValue(1);
			ft1retour.setAutoReverse(true);
			ft1retour.setCycleCount(2);

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
			Group groupe=new Group();
			groupe.getChildren().addAll(photo,photo2);
			cadre.getChildren().addAll(recherche,groupe);
			//photo.setId("menu");
			photo.toFront();
			photo.setAlignment(Pos.CENTER);
			photo2.setAlignment(Pos.CENTER);
			this.grpcomp.getChildren().clear();
			this.grpcomp.getChildren().add(cadre);
			this.grpcomp.getChildren().add(grpcommandes);


			//profil.setVisible(false);
			//ft1retour.play();
			ft1aller.play();
			ft1retour.playFrom(Duration.seconds(2));

			
			
			ft1retour.setOnFinished(e -> {
				if(this.pos.get(this.pos.size()-1).equals("menu")) {
					//this.p=this.p_aux;
					list.remove(0);
					try {
						list.add(this.modele.prochainprofil());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//this.p_aux=this.modele.prochainprofil();
					profil.setImage(new Image(list.get(1).photo));
					ft1retour.play();//profil et this.p
				}
				else {
					ft1retour.stop();
				}
				//profils1 = new Image(p_aux.photo);
				//profil1 = new ImageView(profils1);

			});
			ft1aller.setOnFinished(e -> {
				if(this.pos.get(this.pos.size()-1).equals("menu")) {
					//this.p=this.p_aux;
					list.remove(0);
					try {
						list.add(this.modele.prochainprofil());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//this.p_aux=this.modele.prochainprofil();
					profil1.setImage(new Image(list.get(1).photo));
					ft1aller.play();//profil1 et p_aux

				}
				else {
					ft1aller.stop();
				}

			});


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}
	
	public void definition_profil() {
		this.commandes.setCenter(null);
		this.accueil.setVisible(true);
		this.retour.setVisible(true);
		this.fav.setVisible(true);
		this.l.setVisible(true);

		this.grp.getChildren().get(0).setId("recherche");

		Menu_profil menu_profil = new Menu_profil(this.modele);
		menu_profil.setPrefSize(this.s.getWidth(),  this.s.getHeight());
		this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
			menu_profil.setPrefWidth(this.s.getWidth());
		});
		
		Image im = new Image("file:images/fond_recherche.png");
		BackgroundSize bSize = new BackgroundSize(1, 1, true, true, false, false);
		BackgroundImage back = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize);
		menu_profil.setBackground(new Background(back));
		//menu_profil.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		ScrollPane scroll=new ScrollPane();
		//scroll.setStyle("-fx-background-color:transparent;");
		//scroll.setStyle("-fx-background-image: url("https://cdn.shopify.com/s/files/1/0431/4909/9167/products/GC-158-480475126_1024x1024.jpg?v=1595830152");
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setId("scroll");
		//sp.setFitToWidth(true);
		//scroll.setBackground(new Background(back));
		BorderPane vb=new BorderPane();


		vb.setBackground(new Background(new BackgroundImage(im,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		vb.setPrefSize(500, 500);
		vb.setCenter(new Label("hey"));
		//scroll.setContent(menu_profil);
		scroll.setContent(menu_profil);
		
		scroll.setPrefSize(this.s.getWidth(),this.s.getHeight());
		
		this.s.widthProperty().addListener((obs, oldVal, newVal) -> {
			scroll.setPrefWidth(this.s.getWidth());
		});
		this.s.heightProperty().addListener((obs, oldVal, newVal) -> {
			scroll.setPrefHeight(this.s.getHeight());
		});
		
		this.grpcomp.getChildren().clear();
		this.grpcomp.getChildren().addAll(scroll,grpcommandes);//scroll

	}
	public void menuderoulant(ArrayList<Profil> l,boolean b) {//liste des profils a afficher et booleen b pour dire ou non s'il s'agit des matchs sinon c'est les favoris
		this.grp.getChildren().get(0).setId("recherche");
		this.accueil.setVisible(true);
		this.retour.setVisible(true);
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
			BorderPane hb1 = new BorderPane();
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
			affichage_profil(this.p);
			});
			Image img;
			if(b) {
				img=new Image(this.modele.matchs.get(i).photo);
			}
			else {
				img=new Image(this.modele.coupdecoeur.get(i).photo);
			}
			ImageView imgv=new ImageView(img);
			imgv.setFitHeight(70);
			imgv.setFitWidth(70);
			//hb1.setAlignment(Pos.CENTER_LEFT);
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
				lab.setFont(new Font("Serif", 30));
				lab.setStyle("-fx-font-weight: bold");
				lab.setTextFill(Color.BLACK);
			}
			BorderPane button=new BorderPane();
			//button.setAlignment(Pos.CENTER_RIGHT);
			Button but=new Button("");
			if(b) {
				but.setText("Annuler");
			}
			else {
				but.setText("Supprimer");
			}
			button.setRight(but);
			HBox hb2 = new HBox();
			hb2.setAlignment(Pos.CENTER_LEFT);
			hb2.getChildren().addAll(reg1,imgv,reg2,lab);
			hb1.setLeft(hb2);
			HBox hb3 = new HBox();
			hb3.setAlignment(Pos.CENTER);
			hb3.getChildren().addAll(button);
			hb1.setRight(hb3);
			vb.getChildren().add(hb1);

			but.setOnMouseClicked(e ->
			{
				if(b) {
					int a =Integer.valueOf(hb1.getId());
					this.modele.matchs.remove(a);
					menuderoulant(this.modele.matchs,b);
				}
				else {
					int a =Integer.valueOf(hb1.getId());
					this.modele.coupdecoeur.remove(a);
					
					menuderoulant(this.modele.coupdecoeur,b);
					
				}
				
			});


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
