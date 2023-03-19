package application;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Menu_preferences extends VBox {
	
	public Menu_preferences() {
		// Parametrage VBox
		this.setPadding(new Insets(80,40, 10,10));
		this.setSpacing(10);
		
		// Label titre..
		Label titre = new Label("Selection de vos pr�f�rences");
		
		// Bloc de choix de la tranche d'�ge
		Label label_age = new Label("Tranche d'�ge recherch�e");
		
		FlowPane pane_age = new FlowPane();
		TextField age_min = new TextField("�ge minimum");
		Label tiret = new Label("-");
		TextField age_max = new TextField("�ge maximum");
		pane_age.setHgap(5);
		pane_age.getChildren().addAll(age_min, tiret,age_max);
		
		// Bloc de selection des d'activit�s
		Label label_activite = new Label("Activit�s recherch�es (deux choix maximum)");
		
		FlowPane pane_activite = new FlowPane();
		ChoiceBox<String> choix_act1 = new ChoiceBox<String>();
		choix_act1.getItems().add("Aucune");
		for (int i=0; i<Preference.preferences.length; i++) {
			choix_act1.getItems().add(Preference.preferences[i]);
		}
		ChoiceBox<String> choix_act2 = new ChoiceBox<String>();
		choix_act2.getItems().add("Aucune");
		for (int i=0; i<Preference.preferences.length; i++) {
			choix_act2.getItems().add(Preference.preferences[i]);
		}
		pane_activite.setHgap(5);
		pane_activite.getChildren().addAll(choix_act1, choix_act2);
		
		// Bouton de validation des pr�f�rences
		Button btn_preference = new Button("Enregistrer les pr�f�rences");
		
		// Ajout des �l�ments
		this.getChildren().addAll(titre, label_age, pane_age, label_activite, pane_activite, btn_preference);
		
				
	}

}
