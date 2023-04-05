package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Menu_preferences extends VBox {
	
	public Menu_preferences() {
		// Parametrage VBox
		this.setPadding(new Insets(40, 10, 10,10));
		
		// Label titre..
		Label titre = new Label("Selection de vos préférences");
		titre.setFont(Font.font("Lucida Calligraphy",28));
		titre.setTextFill(Color.WHITE);
		titre.setPrefWidth(600);
		titre.setPadding(new Insets(20));
		
		// Bloc de choix de la tranche d'âge
		Label label_age = new Label("Tranche d'âge recherchée :");
		label_age.setFont(Font.font("Lucida Calligraphy",16));
		label_age.setTextFill(Color.WHITE);
		label_age.setPrefWidth(300);
		
		FlowPane pane_age = new FlowPane();
		TextField age_min = new TextField();
		age_min.setPromptText("Âge minimum");
		age_min.setFont(Font.font("Arial",12));
		age_min.setPrefSize(95, 10);
		Label tiret = new Label("  -  ");
		TextField age_max = new TextField();
		age_max.setPromptText("Âge maximum");
		age_max.setFont(Font.font("Arial",12));
		age_max.setPrefSize(95, 10);
		pane_age.setHgap(5);
		pane_age.setPadding(new Insets(10));
		pane_age.getChildren().addAll(age_min, tiret,age_max);
		
		// Bloc de preférence de distance de recherche de profil
		Label label_distance = new Label("Distance maximal des profils recherchés :");
		label_distance.setFont(Font.font("Lucida Calligraphy",16));
		label_distance.setTextFill(Color.WHITE);
		label_distance.setPrefWidth(400);
		
		FlowPane pane_distance = new FlowPane();
		TextField distance = new TextField();
		distance.setPromptText("Distance maximal");
		distance.setFont(Font.font("Arial",12));
		distance.setPrefSize(115, 10);
			
		pane_distance.setPadding(new Insets(10));
		pane_distance.getChildren().addAll(distance);
		
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
		pane_activite.setPadding(new Insets(10));
		pane_activite.getChildren().addAll(choix_act1, choix_act2);
		
		// Bloc de selection choix fumeur
		Label label_fumeur = new Label("Recherchez-vous :");
		label_fumeur.setFont(Font.font("Lucida Calligraphy",16));
		label_fumeur.setTextFill(Color.WHITE);
		label_fumeur.setPrefWidth(400);
				
		FlowPane pane_fumeur = new FlowPane();
		CheckBox non_fumeur = new CheckBox("Non fumeur");
		non_fumeur.setFont(Font.font("Lucida Calligraphy",12));
		non_fumeur.setTextFill(Color.WHITE);
		non_fumeur.setPrefSize(120, 10);
		CheckBox ind_fumeur = new CheckBox("Indifférent");
		ind_fumeur.setFont(Font.font("Lucida Calligraphy",12));
		ind_fumeur.setTextFill(Color.WHITE);
		ind_fumeur.setPrefSize(120, 10);
		pane_fumeur.setHgap(5);
		pane_fumeur.setPadding(new Insets(10));
		pane_fumeur.getChildren().addAll(non_fumeur, ind_fumeur);
		
		// Bouton de validation des préférences
		FlowPane pane_btn = new FlowPane();
		Button btn_preference = new Button("Enregistrer les préférences");
		btn_preference.setStyle("-fx-background-color: black; -fx-font: 12 Arial; -fx-text-fill: white;");
		
		
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
		
		pane_btn.setAlignment(Pos.BOTTOM_RIGHT);
		pane_btn.getChildren().add(btn_preference);
		
		// Ajout des éléments
		this.getChildren().addAll(titre, label_age, pane_age, label_distance, pane_distance, label_activite, pane_activite, label_fumeur, pane_fumeur, pane_btn);

		
				
	}

}
