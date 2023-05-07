package application;




import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Translate;


public class Recherche_profil extends BorderPane {//Panel qui correspond a un profil pour la fenetre recherche profil
	Image imagecourante;
	Profil profil;
	Boolean dragged=false;//booleen pour savoir si le panel a ete draggé
	int coordsX=-1;//coords X du dernier point clique  
	double posX=0;//coords X du point en haut a gauche du panel
	double posY=0;//coords Y du point en haut a gauche du panel
	Point p;//coords d'un point aleatoire pour la direction
	Main m;//main associe pour appliquer les methodes necessaires
	Boolean choisi=false;//booleen pour savoir si le profil a ete valide ou non
	Boolean fav=false;//booleen pour savoir si le profil a ete mis en favoris ou non
	

	public Recherche_profil(Profil p,Main m) {
		this.m=m;
		int taille=50;
		Label label= new Label(p.prenom+", "+p.age+" ans");
		label.setFont(new Font("Serif", taille));
		label.setStyle("-fx-font-weight: bold");
		label.setTextFill(Color.BLACK);
		label.setOnMouseClicked(e ->
		{
			this.m.pos.add("affichage_profil");
			this.m.affichage_profil(this.m.p);
		});
		this.setBottom(label);
		this.profil=p;
		try {
			profil=this.m.modele.prochainprofil();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Image imagecourante=new Image(p.photo);
		
		BackgroundSize bSize = new BackgroundSize(1, 1, true, true, false, false);


		this.setBackground(new Background(new BackgroundImage(imagecourante,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));





		this.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!((Recherche_profil)event.getSource()).choisi) {
					((Recherche_profil)event.getSource()).dragged=true;
					
					int angle =(int)(event.getSceneX()-((Recherche_profil)event.getSource()).coordsX);
					((Node) event.getSource()).getTransforms().clear();
					double ysommet=((((Recherche_profil)event.getSource()).p.getY())/(((Recherche_profil)event.getSource()).p.getX()))*angle;
					
					if(angle>=0) { 

						((Node) event.getSource()).getTransforms().add(new Translate(angle*1.5,ysommet,0));
						((Recherche_profil)event.getSource()).posX=(angle*1.5);
						((Recherche_profil)event.getSource()).posY=(ysommet);

					}
					else {
						((Node) event.getSource()).getTransforms().add(new Translate(angle*1.5,-ysommet,0));
						((Recherche_profil)event.getSource()).posX=(angle*1.5);
						((Recherche_profil)event.getSource()).posY=-(ysommet);
					}
					
					GaussianBlur flou= new GaussianBlur((Math.abs(angle)/((Recherche_profil)event.getSource()).getWidth())*15);
					((Recherche_profil)event.getSource()).setEffect(flou);
				}
			}
		});

		this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(((Recherche_profil)event.getSource()).dragged && !((Recherche_profil)event.getSource()).choisi) {
					((Recherche_profil)event.getSource()).setEffect(new GaussianBlur(0));
					((Recherche_profil)event.getSource()).dragged=false;
					((Node) event.getSource()).getTransforms().clear();
					int angle =(int)(event.getSceneX()-((Recherche_profil)event.getSource()).coordsX);

					TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5),(Node)event.getSource());

					transition.setFromX(((Recherche_profil)event.getSource()).posX);
					transition.setFromY(((Recherche_profil)event.getSource()).posY);
					if(angle<100 && angle>-100) {
						transition.setToX(0);
						transition.setToY(0);
					}
					else if(angle>=100){
						transition.setToX(((Recherche_profil)event.getSource()).p.getX());
						transition.setToY(((Recherche_profil)event.getSource()).p.getY());
						((Recherche_profil)event.getSource()).m.changerProfil(true);
						((Recherche_profil)event.getSource()).choisi=true;
					}
					else {
						transition.setToX( -((Recherche_profil)event.getSource()).p.getX() );
						transition.setToY(((Recherche_profil)event.getSource()).p.getY());
						((Recherche_profil)event.getSource()).m.changerProfil(false);
						((Recherche_profil)event.getSource()).choisi=true;
					}


					ScaleTransition st = new ScaleTransition(Duration.millis(500), (Node)event.getSource());
					st.setFromX(1);
					st.setFromY(1);
					st.setToX(1/(1-(Math.abs(angle)/((Recherche_profil)event.getSource()).getWidth())/2));
					st.setToY(1/(1-(Math.abs(angle)/((Recherche_profil)event.getSource()).getWidth())/2));
					transition.play();
					transition.setOnFinished(e -> {
						if(((Recherche_profil)transition.getNode()).choisi) {
							((Recherche_profil)transition.getNode()).m.grpcomp.getChildren().remove(((Recherche_profil)transition.getNode()).m.grpcomp.getChildren().size()-2);
						}

					});
					
				}

			}

		});


		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(!((Recherche_profil)event.getSource()).choisi) {

					((Recherche_profil)event.getSource()).coordsX=(int)event.getX();
					Random r = new Random();

					((Recherche_profil)event.getSource()).p=new Point((int)((Recherche_profil)event.getSource()).getWidth(),r.nextInt((int)((Recherche_profil)event.getSource()).getHeight()*2)-(int)((Recherche_profil)event.getSource()).getHeight());

				}
			}
		});
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount()>=2 && !((Recherche_profil)event.getSource()).choisi ) {
					if(!((Recherche_profil)event.getSource()).fav) {
						((Recherche_profil)event.getSource()).m.coupdecoeur();
						((Recherche_profil)event.getSource()).fav=true;
						((Recherche_profil)event.getSource()).m.p.estfav=true;
					}
					Image image;
					try {
						image = new Image(new FileInputStream("images/coeur.png"));
						ImageView imageView = new ImageView(image);
						
						
						imageView.setFitWidth(1);
						imageView.setFitHeight(1);
						
						ScaleTransition st = new ScaleTransition(Duration.millis(500), imageView);
						st.setFromX(1);
						st.setFromY(1);
						st.setToX(500);
						st.setToY(500);
						
						
						((Recherche_profil)event.getSource()).setCenter(imageView);
						FadeTransition ft= new FadeTransition(Duration.millis(500), imageView);
						ft.setFromValue(1);
						ft.setToValue(0);
						
						st.play();
						ft.play();
						
					
					}
					catch(FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});






	}




}
