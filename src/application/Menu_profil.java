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

public class Menu_profil extends VBox {
	
	public Menu_profil() {
		// Parametrage VBox
		this.setPadding(new Insets(40, 10, 10,10));
		
		// Label titre..
		Label titre = new Label("Paramétrage de votre profil");
		titre.setFont(Font.font("Lucida Calligraphy",28));
		titre.setTextFill(Color.WHITE);
		titre.setPrefWidth(600);
		titre.setPadding(new Insets(20));
		
		FlowPane pane_nom = new FlowPane();
		Label label_nom = new Label("Vos prénom et nom :");
		label_nom.setFont(Font.font("Lucida Calligraphy",16));
		label_nom.setTextFill(Color.WHITE);
		label_nom.setPrefWidth(200);
		TextField prenom = new TextField();
		prenom.setPromptText("Prénom");
		prenom.setFont(Font.font("Arial",12));
		prenom.setPrefSize(95, 10);
		Label tiret = new Label("  -  ");
		TextField nom = new TextField();
		nom.setPromptText("Nom");
		nom.setFont(Font.font("Arial",12));
		nom.setPrefSize(95, 10);
		pane_nom.setHgap(5);
		pane_nom.setPadding(new Insets(10));
		pane_nom.getChildren().addAll(label_nom,prenom,tiret,nom);
		
		// Bloc de choix de la tranche d'âge
		FlowPane pane_age = new FlowPane();
		Label label_age = new Label("Votre âge :");
		label_age.setFont(Font.font("Lucida Calligraphy",16));
		label_age.setTextFill(Color.WHITE);
		label_age.setPrefWidth(200);
		TextField age = new TextField();
		age.setPromptText("Âge minimum");
		age.setFont(Font.font("Arial",12));
		age.setPrefSize(95, 10);
		pane_age.setHgap(5);
		pane_age.setPadding(new Insets(10));
		pane_age.getChildren().addAll(label_age, age);
		
		// Bloc de séléction de la ville de résidence de l'utilisateur
		FlowPane pane_ville = new FlowPane();
		Label label_ville = new Label("Où habitez-vous :");
		label_ville.setFont(Font.font("Lucida Calligraphy",16));
		label_ville.setTextFill(Color.WHITE);
		label_ville.setPrefWidth(200);		
		ChoiceBox<String> choix_ville = new ChoiceBox<String>();
		choix_ville.setPrefSize(120, 10);
		for (int i=0; i<Profil.villes.length; i++) {
			choix_ville.getItems().add(Profil.villes[i]);
		}
		pane_ville.setPadding(new Insets(10));
		pane_ville.getChildren().addAll(label_ville,choix_ville);
		
		// Bloc de selection des d'activités				
		FlowPane pane_activite = new FlowPane();
		Label label_activite = new Label("Quelles activités affectionnez-vous le plus ?");
		label_activite.setFont(Font.font("Lucida Calligraphy",16));
		label_activite.setTextFill(Color.WHITE);
		label_activite.setPrefWidth(500);
		label_activite.setPadding(new Insets(10));
		
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
		FlowPane pane_fumeur = new FlowPane();
		Label label_fumeur = new Label("Êtes-vous fumeur ?");
		label_fumeur.setFont(Font.font("Lucida Calligraphy",16));
		label_fumeur.setTextFill(Color.WHITE);
		label_fumeur.setPrefWidth(200);
		CheckBox fumeur = new CheckBox("Je suis non fumeur");
		fumeur.setTextFill(Color.WHITE);
		fumeur.setPrefSize(200, 10);
		fumeur.setOnMouseClicked(e -> {
			if (fumeur.isSelected()) {
				fumeur.setText("Je suis fumeur");
			}
			else {
				fumeur.setText("Je suis non fumeur");
			}
		});
		pane_fumeur.setHgap(5);
		pane_fumeur.setPadding(new Insets(10));
		pane_fumeur.getChildren().addAll(label_fumeur,fumeur);
		
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
		this.getChildren().addAll(titre, pane_nom, pane_age, pane_ville, label_activite, pane_activite, pane_fumeur, pane_btn);
		
	}	
			
}
