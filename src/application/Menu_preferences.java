package application;

import java.io.FileInputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

public class Menu_preferences extends VBox {
	
	public Menu_preferences() {
		// Parametrage VBox
		this.setPadding(new Insets(80, 10, 10,10));
		this.setSpacing(20);
		
		// Label titre..
		Label titre = new Label("Selection de vos préférences");
		titre.setFont(Font.font("Lucida Calligraphy",28));
		titre.setTextFill(Color.WHITE);
		titre.setPrefWidth(600);
		
		// Bloc de choix de la tranche d'âge
		Label label_age = new Label("Tranche d'âge recherchée :");
		label_age.setFont(Font.font("Lucida Calligraphy",16));
		label_age.setTextFill(Color.WHITE);
		label_age.setPrefWidth(300);
		
		FlowPane pane_age = new FlowPane();
		TextField age_min = new TextField("âge minimum");
		age_min.setFont(Font.font("Arial",12));
		age_min.setPrefSize(100, 10);
		Label tiret = new Label("  -  ");
		TextField age_max = new TextField("âge maximum");
		age_max.setFont(Font.font("Arial",12));
		age_max.setPrefSize(100, 10);
		pane_age.setHgap(5);
		
		// Suppression du texte contenu dans age_min ou age_max au click dans le TextField
		age_min.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	age_min.clear();
            }
        });
		age_max.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	age_max.clear();
            }
        });
        
		pane_age.getChildren().addAll(age_min, tiret,age_max);
		
		this.setSpacing(50);
		
		// Bloc de selection des d'activités
		Label label_activite = new Label("Activités recherchées (deux choix maximum) :");
		label_activite.setFont(Font.font("Lucida Calligraphy",16));
		label_activite.setTextFill(Color.WHITE);
		label_activite.setPrefWidth(400);
				
		FlowPane pane_activite = new FlowPane();
		ChoiceBox<String> choix_act1 = new ChoiceBox<String>();
		choix_act1.setPrefSize(120, 10);
		choix_act1.getItems().add("Aucune");
		for (int i=0; i<Preference.preferences.length; i++) {
			choix_act1.getItems().add(Preference.preferences[i]);
		}
		ChoiceBox<String> choix_act2 = new ChoiceBox<String>();
		choix_act2.setPrefSize(120, 10);
		choix_act2.getItems().add("Aucune");
		for (int i=0; i<Preference.preferences.length; i++) {
			choix_act2.getItems().add(Preference.preferences[i]);
		}
		pane_activite.setHgap(5);
		pane_activite.getChildren().addAll(choix_act1, choix_act2);
		
		
		// Bouton de validation des préférences
		Button btn_preference = new Button("Enregistrer les préférences");
		btn_preference.setStyle("-fx-background-color: black; -fx-font: 12 Arial; -fx-text-fill: white;");
		btn_preference.setPrefSize(200, 10);
		
        // Effet ombre sur btn_preference
		DropShadow shadow = new DropShadow();
        btn_preference.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	btn_preference.setEffect(shadow);
            }
        });
        btn_preference.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	btn_preference.setEffect(null);
            }
        });
        
        // Definition de l'action de btn_preference
		btn_preference.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				
			}
		});
		
		// Ajout des éléments
		this.getChildren().addAll(titre, label_age, pane_age, label_activite, pane_activite, btn_preference);

		
				
	}

}
