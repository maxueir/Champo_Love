package application;
	



import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Vector;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
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
	Boolean dragged=false;
	int coordsX=-1;
	
	public Recherche_profil() {
		
		Image imagecourante=new Image("file:images/premier_profil.jpg");
		/*imageView = new ImageView(imagecourante);
		imageView.setX(0); 
	    imageView.setY(0);
	    
	    this.getChildren().add(imageView);*/
	    this.setOnMouseDragged(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	//System.out.println(((Recherche_profil)event.getSource()).coordsX);
	        	((Recherche_profil)event.getSource()).dragged=true;
	        	/*int a=10;
	        	int b=(int) Math.sqrt(((250 - event.getX())*(250 - event.getX())) + ((500 - event.getY())*(500 - event.getY())));
	        	int c=(int) Math.sqrt(((250 - event.getX())*(250 - event.getX())) + ((490 - event.getY())*(490 - event.getY())));
	        	int angle= (int)Math.acos((a*a + b*b - c*c)/(2*a*b));
	        	
	        	
	        	System.out.println(((Node) event.getSource()));
	        	
	        	
	        	((Node) event.getSource()).getTransforms().add(new Rotate(-angle,(((BorderPane)event.getSource()).getWidth())/2,((BorderPane)event.getSource()).getHeight()));
	        	*/
	        	int angle =(int)(event.getSceneX()-((Recherche_profil)event.getSource()).coordsX);
	        	//System.out.println(event.getSceneX());
	        	((Node) event.getSource()).getTransforms().clear();
	        	((Node) event.getSource()).getTransforms().add(new Rotate(angle/5 ,(((BorderPane)event.getSource()).getWidth())/2,((BorderPane)event.getSource()).getHeight()));
	        	
	        	
	        }
	    });
	    
	    this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(((Recherche_profil)event.getSource()).dragged) {
					((Recherche_profil)event.getSource()).dragged=false;
					//((Node) event.getSource()).getTransforms().clear();
					//RotateTransition transition = new RotateTransition(Duration.seconds(1),(Node)event.getSource());
					//transition.X
					//transition.setToAngle(90);
				//	transition.setAxis(new Point3D(42,69,49));
					//transition.play();
					//transition
				}
				
			}
	    	
	    });
	    
	    
	    this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
					((Recherche_profil)event.getSource()).coordsX=(int)event.getX();
					System.out.println(((Recherche_profil)event.getSource()).coordsX);
				
			
			}
	    });
	    
	    
	    
	    
		/*
	    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

	    this.setBackground(new Background(new BackgroundImage(imagecourante,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize)));*/
	    
	}
	
	
	
	
}
