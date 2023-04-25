package application;

import java.util.Set;
import java.util.TreeSet;

import application.Profil.orientation;
import application.Profil.sexe;
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
	
	ProfilPerso profilPerso;
	// A rajouter : image
	
	public Menu_profil(ProfilPerso p) {
		
		this.profilPerso = p;
		
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
		age.setPromptText("Âge");
		age.setFont(Font.font("Arial",12));
		age.setPrefSize(50, 10);
		pane_age.setHgap(5);
		pane_age.setPadding(new Insets(10));
		pane_age.getChildren().addAll(label_age, age);
		
		// Bloc de choix du sexe
		FlowPane pane_sexe = new FlowPane();
		Label label_sexe = new Label("Votre sexe :");
		label_sexe.setFont(Font.font("Lucida Calligraphy",16));
		label_sexe.setTextFill(Color.WHITE);
		label_sexe.setPrefWidth(200);
		ChoiceBox<String> choix_sexe = new ChoiceBox<String>();
		choix_sexe.setPrefSize(120, 10);
		choix_sexe.getItems().add("Homme");
		choix_sexe.getItems().add("Femme");
		choix_sexe.getItems().add("Autre");
		pane_sexe.setHgap(5);
		pane_sexe.setPadding(new Insets(10));
		pane_sexe.getChildren().addAll(label_sexe,choix_sexe);
		
		// Bloc de choix du métier
		FlowPane pane_metier = new FlowPane();
		Label label_metier = new Label("Votre métier :");
		label_metier.setFont(Font.font("Lucida Calligraphy",16));
		label_metier.setTextFill(Color.WHITE);
		label_metier.setPrefWidth(200);
		TextField metier = new TextField();
		metier.setPromptText("Indiquez votre métier");
		metier.setFont(Font.font("Arial",12));
		metier.setPrefSize(150, 10);
		pane_metier.setHgap(5);
		pane_metier.setPadding(new Insets(10));
		pane_metier.getChildren().addAll(label_metier, metier);
		
		// Bloc de séléction de la ville de résidence de l'utilisateur
		FlowPane pane_orientation = new FlowPane();
		Label label_orientation = new Label("Indiquez votre orientation sexuelle :");
		label_orientation.setFont(Font.font("Lucida Calligraphy",16));
		label_orientation.setTextFill(Color.WHITE);
		label_orientation.setPrefWidth(350);		
		ChoiceBox<String> choix_orientation = new ChoiceBox<String>();
		choix_orientation.setPrefSize(120, 10);
		choix_orientation.getItems().add("Hétéro");
		choix_orientation.getItems().add("Homo");
		choix_orientation.getItems().add("Bi");
		label_orientation.setPadding(new Insets(10));
		pane_orientation.getChildren().addAll(label_orientation,choix_orientation);
		
		// Bloc de séléction de la ville de résidence de l'utilisateur
		FlowPane pane_ville = new FlowPane();
		Label label_ville = new Label("Où habitez-vous :");
		label_ville.setFont(Font.font("Lucida Calligraphy",16));
		label_ville.setTextFill(Color.WHITE);
		label_ville.setPrefWidth(200);		
		TextField choix_ville = new TextField();
		choix_ville.setPromptText("Votre ville");
		choix_ville.setFont(Font.font("Arial",12));
		choix_ville.setPrefSize(150, 10);
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
		CheckBox fumeur = new CheckBox("Oui");
		fumeur.setTextFill(Color.WHITE);
		fumeur.setPrefSize(50, 10);
		CheckBox non_fumeur = new CheckBox("Non");
		non_fumeur.setTextFill(Color.WHITE);
		non_fumeur.setPrefSize(50, 10);
		fumeur.setOnMouseClicked(e -> {
			non_fumeur.setSelected(false);
		});
		non_fumeur.setOnMouseClicked(e -> {
			fumeur.setSelected(false);
		});
		pane_fumeur.setHgap(5);
		pane_fumeur.setPadding(new Insets(10));
		pane_fumeur.getChildren().addAll(label_fumeur,fumeur,non_fumeur);
		
		// Bouton de validation des préférences
		FlowPane pane_btn = new FlowPane();
		Button btn_profil = new Button("Enregistrer le profil");
		btn_profil.setStyle("-fx-background-color: black; -fx-font: 12 Arial; -fx-text-fill: white;");
		btn_profil.setOnMouseClicked( e -> {
			Set<Preference> pref = new TreeSet<Preference>();
			Preference pref1 = new Preference(choix_act1.getValue());
			pref.add(pref1);
			Preference pref2 = new Preference(choix_act2.getValue());
			pref.add(pref1);
			pref.add(pref2);
			
			//if (this.profilPerso==null) {
			if (choix_sexe.getValue()=="Homme") {
				if (choix_orientation.getValue()=="Hétéro") {
					this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.HOMME, metier.getText(), orientation.HETERO, choix_ville.getText(), pref, fumeur.isSelected());
				}
				else if (choix_orientation.getValue()=="Homo") {
					this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.HOMME, metier.getText(), orientation.HOMO, choix_ville.getText(), pref, fumeur.isSelected());
				}
				else if (choix_orientation.getValue()=="Bi") {
					this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.HOMME, metier.getText(), orientation.BI, choix_ville.getText(), pref, fumeur.isSelected());
				}
			}
			else if (choix_sexe.getValue()=="Femme") {
				if (choix_orientation.getValue()=="Hétéro") {
					this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.FEMME, metier.getText(), orientation.HETERO, choix_ville.getText(), pref, fumeur.isSelected());
				}
				else if (choix_orientation.getValue()=="Homo") {
					this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.FEMME, metier.getText(), orientation.HOMO, choix_ville.getText(), pref, fumeur.isSelected());
				}
				else if (choix_orientation.getValue()=="Bi") {
					this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.FEMME, metier.getText(), orientation.BI, choix_ville.getText(), pref, fumeur.isSelected());
				}
			}
			else if (choix_sexe.getValue()=="Autre") {
				if (choix_orientation.getValue()=="Hétéro") {
					this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.AUTRE, metier.getText(), orientation.HETERO, choix_ville.getText(), pref, fumeur.isSelected());
				}
				else if (choix_orientation.getValue()=="Homo") {
					this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.AUTRE, metier.getText(), orientation.HOMO, choix_ville.getText(), pref, fumeur.isSelected());
				}
				else if (choix_orientation.getValue()=="Bi") {
					this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.AUTRE, metier.getText(), orientation.BI, choix_ville.getText(), pref, fumeur.isSelected());
				}
			}
		});
		
		
	    // Effet ombre sur btn_preference
		DropShadow shadow = new DropShadow();
		btn_profil.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent e) {
	        	btn_profil.setEffect(shadow);
	        }
	    });
		btn_profil.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent e) {
	        	btn_profil.setEffect(null);
	        }
	    });
	    
	    // Definition de l'action de btn_preference
		btn_profil.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				
			}
		});
		
		pane_btn.setAlignment(Pos.BOTTOM_RIGHT);
		pane_btn.getChildren().add(btn_profil);
		
		// Ajout des éléments
		this.getChildren().addAll(titre, pane_nom, pane_age, pane_sexe, pane_metier, pane_orientation, pane_ville, label_activite, pane_activite, pane_fumeur, pane_btn);
		
	}
			
}
