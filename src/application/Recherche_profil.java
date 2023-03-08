package application;
	



import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;


public class Recherche_profil extends BorderPane {
	Image imagecourante;
	Profil profil;
	Boolean dragged=false;
	int coordsX=-1;
	double posX=0;
	double posY=0;
	Point p;
	
	public Recherche_profil(Profil p,Main m) {
		int taille=50;
		Label label= new Label(p.prenom+", "+p.age+"ans");
		label.setFont(new Font("Serif", taille));
		label.setStyle("-fx-font-weight: bold");
		this.setBottom(label);
		this.profil=p;
		profil=new Profil();
		Image imagecourante=new Image("file:images/premier_profil.jpg");
		/*imageView = new ImageView(imagecourante);
		imageView.setX(0); 
	    imageView.setY(0);
	    
	    this.getChildren().add(imageView);*/
	    
	    BackgroundSize bSize = new BackgroundSize(1, 1, true, true, false, false);
	    

	    this.setBackground(new Background(new BackgroundImage(imagecourante,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize)));
	    
	    
	    
	    
	    
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
	        	double ysommet=((((Recherche_profil)event.getSource()).p.getY())/(((Recherche_profil)event.getSource()).p.getX()))*angle;
	        	System.out.println(((Recherche_profil)event.getSource()).p.getY());
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
	        	System.out.println(Math.abs(angle)/((Recherche_profil)event.getSource()).getWidth());
	        	GaussianBlur flou= new GaussianBlur((Math.abs(angle)/((Recherche_profil)event.getSource()).getWidth())*15);
	        	((Recherche_profil)event.getSource()).setEffect(flou);
	        	//Scale scale= new Scale(1-(Math.abs(angle)/((Recherche_profil)event.getSource()).getWidth())/2,1-(Math.abs(angle)/((Recherche_profil)event.getSource()).getWidth())/2);
	            //((Node)event.getSource()).getTransforms().add(scale);
	        	
	        	//((Recherche_profil)event.getSource()).millieu.rotate();
	        }
	    });
	    
	    this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(((Recherche_profil)event.getSource()).dragged) {
					//System.out.println(((Node) event.getSource()).get );
					((Recherche_profil)event.getSource()).setEffect(new GaussianBlur(0));
					((Recherche_profil)event.getSource()).dragged=false;
					((Node) event.getSource()).getTransforms().clear();
					int angle =(int)(event.getSceneX()-((Recherche_profil)event.getSource()).coordsX);
					
					TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5),(Node)event.getSource());
					
					transition.setFromX(((Recherche_profil)event.getSource()).posX);
					transition.setFromY(((Recherche_profil)event.getSource()).posY);
					transition.setToX(0);
					transition.setToY(0);
					
					
					ScaleTransition st = new ScaleTransition(Duration.millis(500), (Node)event.getSource());
				     st.setFromX(1);
				     st.setFromY(1);
				     st.setToX(1/(1-(Math.abs(angle)/((Recherche_profil)event.getSource()).getWidth())/2));
				     st.setToY(1/(1-(Math.abs(angle)/((Recherche_profil)event.getSource()).getWidth())/2));
				     //st.setCycleCount();
				     //st.setAutoReverse(true);
				 
				     
				
					//Scale scale= new Scale(1-(Math.abs(angle)/((Recherche_profil)event.getSource()).getWidth())/2,1-(Math.abs(angle)/((Recherche_profil)event.getSource()).getWidth())/2);
		            //((Node)event.getSource()).getTransforms().add(scale);
					//st.play();
					transition.play();
					//((Node) event.getSource()).getTransforms().clear();
					//RotateTransition transition = new RotateTransition(Duration.seconds(1),(Node)event.getSource());
					//transition.X
					//transition.setToAngle(90);
				//	transition.setAxis(new Point3D(42,69,49));
					//transition.play();
					//transition
					/*
					 Path path = new Path();  
					    //path.getElements().add (new MoveTo (150, 70));  
					    //path.getElements().add (new CubicCurveTo (240f, 230f, 500f, 340f, 600, 50f));  
					    float centreX=2;
					    float centreY=2;
					    System.out.println(centreX);
					    ((Node) event.getSource()).getTransforms().clear();
					    path.getElements().add(new MoveTo(0,500));
					    path.getElements().add(new ArcTo(250,250,0,250,250,false,true));
					    //path.getElements().add(new ArcTo(250,250,180,250,250,true,true));
					    path.setStroke(Color.BLUE);
					    
					    ((Recherche_profil)event.getTarget()).getChildren().add(path);
					   
					    
					    path.setVisible(true); 
					    
					    //path.or
					    
					    PathTransition pathTransition = new PathTransition();  
					     
					    //Setting duration for the PathTransition  
					    pathTransition.setDuration(Duration.seconds(0.5 ));  
					    //pathTransition.setAutoReverse(true);
					      
					    //Setting Node on which the path transition will be applied   
					    pathTransition.setNode(((Recherche_profil)event.getSource()));  
					      
					    //setting path for the path transition   
					    pathTransition.setPath(path);  
					      
					    //setting orientation for the path transition   
					    //pathTransition.setOrientation(OrientationType.NONE);
					    pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);  
					      
					    //setting up the cycle count   
					    //pathTransition.setCycleCount(10);  
					      
					    //setting auto reverse to be true   
					    pathTransition.setAutoReverse(true);  
					  
					    //Playing path transition   
					    //((Node) event.getSource()).getTransforms().add(new Rotate(180));
			        	
					    pathTransition.play();
					    //pathTransition.*/
					
					/*
					RotateTransition rotate = new RotateTransition();  
			          
			        //Setting Axis of rotation   
			        rotate.setAxis(new Point3D(0,0,250)); 
			        //rotate
			          
			        // setting the angle of rotation   
			        rotate.setByAngle(100);
			        rotate.setFromAngle(50);
			        rotate.setToAngle(180);
			        rotate.axisProperty();
			        System.out.println(rotate.getAxis());
			          
			        //setting cycle count of the rotation   
			        //rotate.setCycleCount();  
			          
			        //Setting duration of the transition   
			        rotate.setDuration(Duration.millis(1000));  
			          
			        //the transition will be auto reversed by setting this to true   
			        rotate.setAutoReverse(true);  
			              
			        //setting Rectangle as the node onto which the   
			// transition will be applied  
			        rotate.setNode(((Recherche_profil)event.getSource()));  
			          
			        //playing the transition   
			        rotate.play(); */
				}
				
			}
	    	
	    });
	    
	    
	    this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
					((Recherche_profil)event.getSource()).coordsX=(int)event.getX();
					Random r = new Random();
					
					((Recherche_profil)event.getSource()).p=new Point((int)((Recherche_profil)event.getSource()).getWidth(),r.nextInt((int)((Recherche_profil)event.getSource()).getHeight()*2)-(int)((Recherche_profil)event.getSource()).getHeight());
					
			
			}
	    });
	    
	    
	    
	    
		
	    
	}
	
	
	
	
}
