package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Lettre extends Canvas{
	
	
	public Lettre() {
		//this.setHeight(55);
		//this.setWidth(55);
		this.prefHeight(400);
		this.prefWidth(200);
		Image image;
		try {
			image = new Image(new FileInputStream("images/lettre.png"));
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);
			
			GraphicsContext ctx=this.getGraphicsContext2D();
			
			 ctx.beginPath();
		        //gc.arcTo(200, 200,5,0, Math.PI*2);
		        ctx.fillOval(50,50,30,30);
		        ctx.setFill(Color.RED);
			ctx.lineTo(50, 50);
			ctx.drawImage(image, 10, 10);
	        //ctx.drawImage(image, 220, 50, 100, 70);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}
