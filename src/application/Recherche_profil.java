package application;
	



import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Vector;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;


public class Recherche_profil extends BorderPane {
	Image imagecourante;
	
	
	public Recherche_profil() {
		imagecourante=new Image("file:images/premier_profil.jpg");
		ImageView imageView = new ImageView(imagecourante);
		imageView.setX(0); 
	    imageView.setY(0);
	    imageView.setFitHeight(500); 
	    imageView.setFitWidth(500);
	    this.getChildren().add(imageView);
	    imageView.setOnMouseDragged(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	int a=10;
	        	int b=(int) Math.sqrt(((250 - event.getX())*(250 - event.getX())) + ((500 - event.getY())*(500 - event.getY())));
	        	int c=(int) Math.sqrt(((250 - event.getX())*(250 - event.getX())) + ((490 - event.getY())*(490 - event.getY())));
	        	int angle= (int)Math.acos((a*a + b*b - c*c)/(2*a*b));
	        	//if(event.get)
	        	imageView.getTransforms().add(new Rotate(-angle,250,500));
	        	
	        }
	    });
	    
		/*
	    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

	    this.setBackground(new Background(new BackgroundImage(image2,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize)));
	    */
	}
	
	
	
	
}
