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
	// booleen aime, valeur de chaque clef de preference

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
		if (this.profilPerso == null) {
			prenom.setPromptText("Prénom");
		}
		else {
			prenom.setText(this.profilPerso.prenom);
		}
		prenom.setFont(Font.font("Arial",12));
		prenom.setPrefSize(95, 10);
		Label tiret = new Label("  -  ");
		TextField nom = new TextField();
		if (this.profilPerso == null) {
			nom.setPromptText("Nom");
		}
		else {
			nom.setText(this.profilPerso.nom);
		}
		nom.setFont(Font.font("Arial",12));
		nom.setPrefSize(95, 10);
		pane_nom.setHgap(5);
		pane_nom.setPadding(new Insets(10));
		pane_nom.getChildren().addAll(label_nom,prenom,tiret,nom);

		// Bloc de choix de l'âge
		FlowPane pane_age = new FlowPane();
		Label label_age = new Label("Votre âge :");
		label_age.setFont(Font.font("Lucida Calligraphy",16));
		label_age.setTextFill(Color.WHITE);
		label_age.setPrefWidth(200);
		TextField age = new TextField();
		if (this.profilPerso == null) {
			age.setPromptText("Âge");
		}
		else {
			age.setText(String.valueOf(this.profilPerso.age));
		}
		age.setFont(Font.font("Arial",12));
		age.setPrefSize(50, 10);
		pane_age.setHgap(5);
		pane_age.setPadding(new Insets(10));
		pane_age.getChildren().addAll(label_age, age);

		// Bloc de choix de l'âge recherchée
		Label label_age_r = new Label("Tranche d'âge recherchée :");
		label_age_r.setFont(Font.font("Lucida Calligraphy",16));
		label_age_r.setTextFill(Color.WHITE);
		label_age_r.setPrefWidth(300);

		FlowPane pane_age_r = new FlowPane();
		TextField age_min = new TextField();
		age_min.setFont(Font.font("Arial",12));
		age_min.setPrefSize(95, 10);
		Label tiret1 = new Label("  -  ");
		TextField age_max = new TextField();
		age_max.setFont(Font.font("Arial",12));
		age_max.setPrefSize(95, 10);
		pane_age_r.setHgap(5);
		pane_age_r.setPadding(new Insets(10));
		pane_age_r.getChildren().addAll(age_min, tiret1,age_max);

		// Bloc de choix du sexe
		FlowPane pane_sexe = new FlowPane();
		Label label_sexe = new Label("Votre sexe :");
		label_sexe.setFont(Font.font("Lucida Calligraphy",16));
		label_sexe.setTextFill(Color.WHITE);
		label_sexe.setPrefWidth(200);
		ChoiceBox<String> choix_sexe = new ChoiceBox<String>();
		if (this.profilPerso == null) {
			choix_sexe.setValue("Sexe");
		}
		else {
			choix_sexe.setValue(this.profilPerso.sex.toString());
		}
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
		if (this.profilPerso == null) {
			metier.setPromptText("Indiquez votre métier");
		}
		else {
			metier.setText(this.profilPerso.metier);
		}
		metier.setFont(Font.font("Arial",12));
		metier.setPrefSize(150, 10);
		pane_metier.setHgap(5);
		pane_metier.setPadding(new Insets(10));
		pane_metier.getChildren().addAll(label_metier, metier);

		// Bloc de séléction de l'orientation sexuelle de l'utilisateur
		FlowPane pane_orientation = new FlowPane();
		Label label_orientation = new Label("Indiquez votre orientation sexuelle :");
		label_orientation.setFont(Font.font("Lucida Calligraphy",16));
		label_orientation.setTextFill(Color.WHITE);
		label_orientation.setPrefWidth(350);		
		ChoiceBox<String> choix_orientation = new ChoiceBox<String>();
		if (this.profilPerso == null) {
			choix_orientation.setValue("Orientation");
		}
		else {
			choix_sexe.setValue(this.profilPerso.ori.toString());
		}
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
		if (this.profilPerso == null) {
			choix_ville.setPromptText("Votre ville");
		}
		else {
			choix_ville.setText(this.profilPerso.ville);
		}
		choix_ville.setPromptText("Votre ville");
		choix_ville.setFont(Font.font("Arial",12));
		choix_ville.setPrefSize(150, 10);
		pane_ville.setPadding(new Insets(10));
		pane_ville.getChildren().addAll(label_ville,choix_ville);


		// Bloc de preférence de distance de recherche de profil
		Label label_distance = new Label("Distance maximale des profils recherchés :");
		label_distance.setFont(Font.font("Lucida Calligraphy",16));
		label_distance.setTextFill(Color.WHITE);
		label_distance.setPrefWidth(400);

		FlowPane pane_distance = new FlowPane();
		TextField distance = new TextField();
		distance.setFont(Font.font("Arial",12));
		distance.setPrefSize(115, 10);

		pane_distance.setPadding(new Insets(10));
		pane_distance.getChildren().addAll(label_distance,distance);


		// Bloc de selection des d'activités				
		FlowPane pane_activite = new FlowPane();
		Label label_activite = new Label("Quelles activités affectionnez-vous le plus ?");
		label_activite.setFont(Font.font("Lucida Calligraphy",16));
		label_activite.setTextFill(Color.WHITE);
		label_activite.setPrefWidth(500);
		label_activite.setPadding(new Insets(10));

		ChoiceBox<String> choix_act1 = new ChoiceBox<String>();
		if (this.profilPerso == null) {
			choix_act1.setValue("Aucune");
		}
		else {
			//choix_act1.setValue(this.profilPerso.preferences.first());
		}
		choix_act1.setPrefSize(120, 10);
		choix_act1.getItems().add("Aucune");
		for (int i=0; i<Preference.preferences.length; i++) {
			choix_act1.getItems().add(Preference.preferences[i]);
		}
		ChoiceBox<String> choix_act2 = new ChoiceBox<String>();
		if (this.profilPerso == null) {
			choix_act2.setValue("Aucune");
		}
		else {
			//choix_act2.setValue(this.profilPerso.preferences.last());
		}
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
		if (this.profilPerso != null) {
			if (this.profilPerso.fumeur) {
				fumeur.setSelected(true);
			}
			else {
				non_fumeur.setSelected(true);
			}
		}
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

		// Bloc de selection choix fumeur recherché
		Label label_fumeur_r = new Label("Recherchez-vous :");
		label_fumeur_r.setFont(Font.font("Lucida Calligraphy",16));
		label_fumeur_r.setTextFill(Color.WHITE);
		label_fumeur_r.setPrefWidth(400);

		FlowPane pane_fumeur_r = new FlowPane();
		CheckBox non_fumeur_r = new CheckBox("Non fumeur");
		non_fumeur_r.setFont(Font.font("Lucida Calligraphy",12));
		non_fumeur_r.setTextFill(Color.WHITE);
		non_fumeur_r.setPrefSize(120, 10);
		CheckBox ind_fumeur_r = new CheckBox("Indifférent");
		ind_fumeur_r.setFont(Font.font("Lucida Calligraphy",12));
		ind_fumeur_r.setTextFill(Color.WHITE);
		ind_fumeur_r.setPrefSize(120, 10);
		non_fumeur_r.setOnMouseClicked(e -> {
			ind_fumeur_r.setSelected(false);
		});
		ind_fumeur_r.setOnMouseClicked(e -> {
			non_fumeur_r.setSelected(false);
		});
		pane_fumeur_r.setHgap(5);
		pane_fumeur_r.setPadding(new Insets(10));
		pane_fumeur_r.getChildren().addAll(non_fumeur_r, ind_fumeur_r);


		// Bouton de validation des préférences
		FlowPane pane_btn = new FlowPane();
		Button btn_profil = new Button("Enregistrer le profil");
		btn_profil.setStyle("-fx-background-color: black; -fx-font: 12 Arial; -fx-text-fill: white;");
		// Definition de l'action de btn_profil
		btn_profil.setOnMouseClicked( e -> {
			Set<Preference> pref = new TreeSet<Preference>();
			Preference pref1 = new Preference(choix_act1.getValue());
			pref.add(pref1);
			Preference pref2 = new Preference(choix_act2.getValue());
			pref.add(pref2);

			if (this.profilPerso==null) {
				if (choix_sexe.getValue()=="Homme") {
					if (choix_orientation.getValue()=="Hétéro") {
						this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.HOMME, metier.getText(), orientation.HETERO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected());
					}
					else if (choix_orientation.getValue()=="Homo") {
						this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.HOMME, metier.getText(), orientation.HOMO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected());
					}
					else if (choix_orientation.getValue()=="Bi") {
						this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.HOMME, metier.getText(), orientation.BI, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected());
					}
				}
				else if (choix_sexe.getValue()=="Femme") {
					if (choix_orientation.getValue()=="Hétéro") {
						this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.FEMME, metier.getText(), orientation.HETERO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected());
					}
					else if (choix_orientation.getValue()=="Homo") {
						this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.FEMME, metier.getText(), orientation.HOMO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected());
					}
					else if (choix_orientation.getValue()=="Bi") {
						this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.FEMME, metier.getText(), orientation.BI, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected());
					}
				}
				else if (choix_sexe.getValue()=="Autre") {
					if (choix_orientation.getValue()=="Hétéro") {
						this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.AUTRE, metier.getText(), orientation.HETERO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected());
					}
					else if (choix_orientation.getValue()=="Homo") {
						this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.AUTRE, metier.getText(), orientation.HOMO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected());
					}
					else if (choix_orientation.getValue()=="Bi") {
						this.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.AUTRE, metier.getText(), orientation.BI, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected());
					}
				}
			}
			else {
				if (choix_sexe.getValue()=="Homme") {
					if (choix_orientation.getValue()=="Hétéro") {
						this.profilPerso.sex = sexe.HOMME;
						this.profilPerso.ori = orientation.HETERO;
					}
					else if (choix_orientation.getValue()=="Homo") {
						this.profilPerso.sex = sexe.HOMME;
						this.profilPerso.ori = orientation.HOMO;
					}
					else if (choix_orientation.getValue()=="Bi") {
						this.profilPerso.sex = sexe.HOMME;
						this.profilPerso.ori = orientation.BI;
					}
				}
				else if (choix_sexe.getValue()=="Femme") {
					if (choix_orientation.getValue()=="Hétéro") {
						this.profilPerso.sex = sexe.FEMME;
						this.profilPerso.ori = orientation.HETERO;
					}
					else if (choix_orientation.getValue()=="Homo") {
						this.profilPerso.sex = sexe.FEMME;
						this.profilPerso.ori = orientation.HOMO;
					}
					else if (choix_orientation.getValue()=="Bi") {
						this.profilPerso.sex = sexe.FEMME;
						this.profilPerso.ori = orientation.BI;
					}
				}
				else if (choix_sexe.getValue()=="Autre") {
					if (choix_orientation.getValue()=="Hétéro") {
						this.profilPerso.sex = sexe.AUTRE;
						this.profilPerso.ori = orientation.HETERO;
					}
					else if (choix_orientation.getValue()=="Homo") {
						this.profilPerso.sex = sexe.AUTRE;
						this.profilPerso.ori = orientation.HOMO;
					}
					else if (choix_orientation.getValue()=="Bi") {
						this.profilPerso.sex = sexe.AUTRE;
						this.profilPerso.ori = orientation.BI;
					}
				}
				this.profilPerso.nom = nom.getText();
				this.profilPerso.prenom = prenom.getText();
				this.profilPerso.age = Integer.parseInt(age.getText());
				this.profilPerso.metier = metier.getText();
				this.profilPerso.ville = choix_ville.getText();
				this.profilPerso.preferences = pref;
				this.profilPerso.fumeur = fumeur.isSelected();
			}
		});


		// Effet ombre sur btn_profil
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

		pane_btn.setAlignment(Pos.BOTTOM_RIGHT);
		pane_btn.getChildren().add(btn_profil);

		// Ajout des éléments
		this.getChildren().addAll(titre, pane_nom, pane_age,label_age_r,pane_age_r, pane_sexe, pane_metier, pane_orientation, pane_ville,pane_distance, label_activite, pane_activite, pane_fumeur, pane_btn,pane_fumeur_r);

	}

}
