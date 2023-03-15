package application;

import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class Menu_recherche extends Group {

	public Menu_recherche() {
		CheckBox checkBox1 = new CheckBox("Check Box1");
		CheckBox checkBox2 = new CheckBox("Check Box2");
        
		
		FlowPane bloc1 = new FlowPane(checkBox1, checkBox2);
		bloc1.setHgap(10);
		bloc1.setVgap(10);
		
		this.getChildren().add(bloc1);
		
		
	}

}
