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
		this.setHeight(55);
		this.setWidth(65);
		this.prefHeight(55);
		this.prefWidth(65);
		afficher();


	}
	public void afficher() {

		Image image;
		try {
			image = new Image(new FileInputStream("images/lettre2.png"));

			GraphicsContext ctx=this.getGraphicsContext2D();
			ctx.clearRect(0, 0, 65, 55);

			ctx.drawImage(image,5, 10);
			ctx.beginPath();
			if(this.nbr!=0) {
				if(this.nbr>9) {
					ctx.setFill(Color.RED);
					ctx.fillOval(5,37,11,11);
					ctx.setFill(Color.WHITE);
					ctx.setFont(new Font("Serif", 9));
					ctx.fillText("9+",6,45);
				}
				else {
					ctx.setFill(Color.RED);
					ctx.fillOval(5,37,11,11);
					ctx.setFill(Color.WHITE);
					ctx.setFont(new Font("Serif", 10));
					ctx.fillText(String.valueOf(this.nbr),7,45);
				}
			}


		} catch (FileNotFoundException e) {
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
