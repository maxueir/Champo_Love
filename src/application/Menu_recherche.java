package application;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;

public class Menu_recherche extends Group {

	public Menu_recherche() {
		CheckBox checkBox1 = new CheckBox("Check Box1");
		CheckBox checkBox2 = new CheckBox("Check Box2");
        
		Separator separ = new Separator(Orientation.HORIZONTAL);
		HBox hbox = new HBox(checkBox1, separ, checkBox2);
		
		this.getChildren().add(hbox);
		
	}

}
