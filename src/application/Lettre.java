package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Lettre extends Canvas{
	int nbr=0;

	public Lettre() {
		this.setHeight(45);
		this.setWidth(55);
		this.prefHeight(45);
		this.prefWidth(55);
		afficher();
	}
	
	public void afficher() {

		Image image;
		try {
			image = new Image(new FileInputStream("images/lettre3.png"));
			
			GraphicsContext ctx=this.getGraphicsContext2D();
			ctx.clearRect(0, 0, 65, 55);

			ctx.drawImage(image,5, 10);
			ctx.beginPath();
			if(this.nbr!=0) {
				if(this.nbr>9) {
					ctx.setFill(Color.RED);
					ctx.fillOval(3,28,11,11);
					ctx.setFill(Color.WHITE);
					ctx.setFont(new Font("Serif", 9));
					ctx.fillText("9+",4,37);
				}
				else {
					ctx.setFill(Color.RED);
					ctx.fillOval(3,28,11,11);
					ctx.setFill(Color.WHITE);
					ctx.setFont(new Font("Serif", 10));
					ctx.fillText(String.valueOf(this.nbr),5,37);
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void ajouter() {
		this.nbr++;
		this.afficher();
	}
	public void vus() {
		this.nbr=0;
		this.afficher();
	}
}
