package application;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import application.Profil.orientation;
import application.Profil.relation;
import application.Profil.sexe;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class Menu_profil extends VBox {
	// scrollbar
	// serealization
	// disposition
	Modele mod;
	
	public Menu_profil(Modele m) {
		this.mod=m;
		
		/*Image imagecourante=new Image("file:images/fond_recherche.png" );
		
		BackgroundSize bSize = new BackgroundSize(1, 1, true, true, false, false);


		this.setBackground(new Background(new BackgroundImage(imagecourante,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		//this.setStyle("-fx-background-color:transparent;");
		
		this.profilPerso = p;*/
		// Parametrage VBox
		this.setPadding(new Insets(40, 10, 10,10));

		// Label titre..
		Label titre = new Label("Paramétrage de votre profil");
		titre.setFont(Font.font("Lucida Calligraphy",28));
		titre.setTextFill(Color.WHITE);
		titre.setPrefWidth(600);
		titre.setPadding(new Insets(20));

		FlowPane pane_nom = new FlowPane();
		
		//pane_nom.setBackground(new Background(new BackgroundFill(paint,null,null)));
		pane_nom.setStyle("-fx-background-color:transparent;");
		
		Label label_nom = new Label("Vos prénom et nom :");
		label_nom.setFont(Font.font("Lucida Calligraphy",16));
		label_nom.setTextFill(Color.WHITE);
		label_nom.setPrefWidth(200);
		TextField prenom = new TextField();
		if (this.mod.profilPerso == null) {
			prenom.setPromptText("Prénom");
		}
		else {
			prenom.setText(this.mod.profilPerso.prenom);
		}
		prenom.setFont(Font.font("Arial",12));
		prenom.setPrefSize(95, 10);
		Label tiret = new Label("  -  ");
		TextField nom = new TextField();
		if (this.mod.profilPerso == null) {
			nom.setPromptText("Nom");
		}
		else {
			nom.setText(this.mod.profilPerso.nom);
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
		if (this.mod.profilPerso == null) {
			age.setPromptText("Âge");
		}
		else {
			age.setText(String.valueOf(this.mod.profilPerso.age));
		}
		age.setFont(Font.font("Arial",12));
		age.setPrefSize(50, 10);
		pane_age.setHgap(5);
		pane_age.setPadding(new Insets(10));
		pane_age.getChildren().addAll(label_age, age);

		// Bloc de choix de l'âge recherchée
		FlowPane pane_age_r = new FlowPane();
		Label label_age_r = new Label("Tranche d'âge recherchée :");
		label_age_r.setFont(Font.font("Lucida Calligraphy",16));
		label_age_r.setTextFill(Color.WHITE);
		label_age_r.setPrefWidth(250);
		TextField age_min = new TextField();
		age_min.setFont(Font.font("Arial",12));
		age_min.setPrefSize(50, 10);
		if (this.mod.profilPerso == null) {
			age_min.setPromptText("Min");
		}
		else {
			age_min.setText(String.valueOf(this.mod.profilPerso.age_min));
		}
		Label tiret1 = new Label("  -  ");
		tiret1.setFont(Font.font("Lucida Calligraphy",16));
		tiret1.setTextFill(Color.WHITE);
		TextField age_max = new TextField();
		age_max.setFont(Font.font("Arial",12));
		age_max.setPrefSize(50, 10);
		if (this.mod.profilPerso == null) {
			age_max.setPromptText("Max");
		}
		else {
			age_max.setText(String.valueOf(this.mod.profilPerso.age_max));
		}
		pane_age_r.setHgap(5);
		pane_age_r.setPadding(new Insets(10));
		pane_age_r.getChildren().addAll(label_age_r, age_min, tiret1,age_max);

		// Bloc de choix du sexe
		FlowPane pane_sexe = new FlowPane();
		Label label_sexe = new Label("Votre sexe :");
		label_sexe.setFont(Font.font("Lucida Calligraphy",16));
		label_sexe.setTextFill(Color.WHITE);
		label_sexe.setPrefWidth(200);
		ChoiceBox<String> choix_sexe = new ChoiceBox<String>();
		if (this.mod.profilPerso == null) {
			choix_sexe.setValue("Sexe");
		}
		else {
			choix_sexe.setValue(this.mod.profilPerso.sex.toString());
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
		if (this.mod.profilPerso == null) {
			metier.setPromptText("Indiquez votre métier");
		}
		else {
			metier.setText(this.mod.profilPerso.metier);
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
		if (this.mod.profilPerso == null) {
			choix_orientation.setValue("Orientation");
		}
		else {
			choix_orientation.setValue(this.mod.profilPerso.ori.toString());
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
		if (this.mod.profilPerso == null) {
			choix_ville.setPromptText("Votre ville");
		}
		else {
			choix_ville.setText(this.mod.profilPerso.ville);
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
		if (this.mod.profilPerso == null) {
			distance.setPromptText("Distance max");
		}
		else {
			distance.setText(String.valueOf(this.mod.profilPerso.distance));
		}
		pane_distance.setPadding(new Insets(10));
		pane_distance.getChildren().addAll(label_distance,distance);


		// Bloc de selection des d'activités				
		FlowPane pane_activite = new FlowPane();
		Label label_activite = new Label("Quelles activités affectionnez-vous le plus ?");
		label_activite.setFont(Font.font("Lucida Calligraphy",16));
		label_activite.setTextFill(Color.WHITE);
		label_activite.setPrefWidth(400);

		ChoiceBox<String> choix_act1 = new ChoiceBox<String>();
		choix_act1.setPrefSize(120, 10);
		choix_act1.getItems().add("Aucune");
		ChoiceBox<String> choix_act2 = new ChoiceBox<String>();
		if (this.mod.profilPerso == null) {
			choix_act1.setValue("Aucune");
			choix_act2.setValue("Aucune");
		}
		else {
			Iterator<Preference> ite = this.mod.profilPerso.preferences.iterator();
			choix_act1.setValue(ite.next().toString().substring(2));
			choix_act2.setValue(ite.next().toString().substring(2));
		}
		choix_act2.setPrefSize(120, 10);
		choix_act2.getItems().add("Aucune");
		for (int i=0; i<Preference.preferences.length; i++) {
			choix_act1.getItems().add(Preference.preferences[i]);
			choix_act2.getItems().add(Preference.preferences[i]);
		}
		pane_activite.setHgap(5);
		pane_activite.setPadding(new Insets(10));
		pane_activite.getChildren().addAll(label_activite, choix_act1, choix_act2);

		// Bloc de selection choix fumeur
		FlowPane pane_fumeur = new FlowPane();
		Label label_fumeur = new Label("Êtes-vous fumeur ?");
		label_fumeur.setFont(Font.font("Lucida Calligraphy",16));
		label_fumeur.setTextFill(Color.WHITE);
		label_fumeur.setPrefWidth(200);
		CheckBox fumeur = new CheckBox("Oui");
		fumeur.setFont(Font.font("Lucida Calligraphy",12));
		fumeur.setTextFill(Color.WHITE);
		fumeur.setPrefSize(50, 10);
		CheckBox non_fumeur = new CheckBox("Non");
		if (this.mod.profilPerso != null) {
			if (this.mod.profilPerso.fumeur) {
				fumeur.setSelected(true);
			}
			else {
				non_fumeur.setSelected(true);
			}
		}
		non_fumeur.setFont(Font.font("Lucida Calligraphy",12));
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
		label_fumeur_r.setPrefWidth(200);

		FlowPane pane_fumeur_r = new FlowPane();
		CheckBox non_fumeur_r = new CheckBox("Non fumeur");
		non_fumeur_r.setFont(Font.font("Lucida Calligraphy",12));
		non_fumeur_r.setTextFill(Color.WHITE);
		non_fumeur_r.setPrefSize(120, 10);
		CheckBox ind_fumeur_r = new CheckBox("Indifférent");
		ind_fumeur_r.setFont(Font.font("Lucida Calligraphy",12));
		ind_fumeur_r.setTextFill(Color.WHITE);
		ind_fumeur_r.setPrefSize(120, 10);
		if (this.mod.profilPerso != null) {
			if (this.mod.profilPerso.fumeur_r) {
				ind_fumeur_r.setSelected(true);
			}
			else {
				non_fumeur_r.setSelected(true);
			}
		}
		non_fumeur_r.setOnMouseClicked(e -> {
			ind_fumeur_r.setSelected(false);
		});
		ind_fumeur_r.setOnMouseClicked(e -> {
			non_fumeur_r.setSelected(false);
		});
		pane_fumeur_r.setHgap(5);
		pane_fumeur_r.setPadding(new Insets(10));
		pane_fumeur_r.getChildren().addAll(label_fumeur_r, non_fumeur_r, ind_fumeur_r);
		
		// Bloc de séléction de la ville de résidence de l'utilisateur
		FlowPane pane_image = new FlowPane();
		Label label_image = new Label("Votre photo de profil :");
		label_image.setFont(Font.font("Lucida Calligraphy",16));
		label_image.setTextFill(Color.WHITE);
		label_image.setPrefWidth(200);		
		Label url_photo = new Label();
		url_photo.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != url_photo
                        && event.getDragboard().hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });
		url_photo.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                	url_photo.setText(db.getFiles().toString().substring(1, db.getFiles().toString().length()-2));
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            }
		});
		if (this.mod.profilPerso == null) {
			url_photo.setText("Glissez votre photo ici");
		}
		else {
			url_photo.setText(this.mod.profilPerso.image);
		}
		url_photo.setFont(Font.font("Arial",12));
		url_photo.setTextFill(Color.WHITE);
		url_photo.setUnderline(true);
		url_photo.setPrefSize(250, 10);
		pane_image.setPadding(new Insets(10));
		pane_image.getChildren().addAll(label_image,url_photo);
		
		// Bouton de validation des préférences
		FlowPane pane_btn = new FlowPane();
		Button btn_profil = new Button("Enregistrer le profil");
		btn_profil.setStyle("-fx-background-color: black; -fx-font: 12 Arial; -fx-text-fill: white;");
		// Definition de l'action de btn_profil
		btn_profil.setOnMouseClicked( e -> {
			Alert alert_btn = new Alert(AlertType.INFORMATION);
			Set<Preference> pref = new TreeSet<Preference>();
			Preference pref1 = new Preference(choix_act1.getValue());
			pref.add(pref1);
			Preference pref2 = new Preference(choix_act2.getValue());
			pref.add(pref2);
			
			if (nom.getText()!=null & prenom.getText()!=null & age.getText()!="" & choix_sexe.getValue()!="Sexe" & metier.getText()!=null & choix_orientation.getValue()!="Orientation" & choix_ville.getText()!=null & /*choix_act1.getValue()!="Aucune" & choix_act2.getValue()!="Aucune" &*/ (fumeur.isSelected() || non_fumeur.isSelected()) & age_min.getText()!="" & age_max.getText()!="" & distance.getText()!="" & (ind_fumeur_r.isSelected() || non_fumeur_r.isSelected()) & url_photo.getText()!="Glissez votre photo ici") {
				if (this.mod.profilPerso==null) {
					if (choix_sexe.getValue()=="Homme") {
						if (choix_orientation.getValue()=="Hétéro") {
							this.mod.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.HOMME, metier.getText(), orientation.HETERO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected(), url_photo.getText());
						}
						else if (choix_orientation.getValue()=="Homo") {
							this.mod.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.HOMME, metier.getText(), orientation.HOMO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected(), url_photo.getText());
						}
						else if (choix_orientation.getValue()=="Bi") {
							this.mod.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.HOMME, metier.getText(), orientation.BI, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected(), url_photo.getText());
						}
					}
					else if (choix_sexe.getValue()=="Femme") {
						if (choix_orientation.getValue()=="Hétéro") {
							this.mod.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.FEMME, metier.getText(), orientation.HETERO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected(), url_photo.getText());
						}
						else if (choix_orientation.getValue()=="Homo") {
							this.mod.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.FEMME, metier.getText(), orientation.HOMO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected(), url_photo.getText());
						}
						else if (choix_orientation.getValue()=="Bi") {
							this.mod.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.FEMME, metier.getText(), orientation.BI, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected(), url_photo.getText());
						}
					}
					else if (choix_sexe.getValue()=="Autre") {
						if (choix_orientation.getValue()=="Hétéro") {
							this.mod.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.AUTRE, metier.getText(), orientation.HETERO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected(), url_photo.getText());
						}
						else if (choix_orientation.getValue()=="Homo") {
							this.mod.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.AUTRE, metier.getText(), orientation.HOMO, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected(), url_photo.getText());
						}
						else if (choix_orientation.getValue()=="Bi") {
							this.mod.profilPerso = new ProfilPerso(nom.getText(), prenom.getText(), Integer.parseInt(age.getText()), sexe.AUTRE, metier.getText(), orientation.BI, choix_ville.getText(), pref, fumeur.isSelected(),Integer.parseInt(age_min.getText()),Integer.parseInt(age_max.getText()),Integer.parseInt(distance.getText()),ind_fumeur_r.isSelected(), url_photo.getText());
						}
					}
					this.mod.fileAttente.clear();
					this.mod.thread.reset();
					this.mod.thread.start();
					
					
					alert_btn.setTitle("Message d'information");
					alert_btn.setHeaderText("Félicitation, vous venez de créer votre profil !");
					alert_btn.showAndWait();
					
				}
				else {
					if (choix_sexe.getValue()=="Homme") {
						if (choix_orientation.getValue()=="Hétéro") {
							this.mod.profilPerso.sex = sexe.HOMME;
							this.mod.profilPerso.ori = orientation.HETERO;
						}
						else if (choix_orientation.getValue()=="Homo") {
							this.mod.profilPerso.sex = sexe.HOMME;
							this.mod.profilPerso.ori = orientation.HOMO;
						}
						else if (choix_orientation.getValue()=="Bi") {
							this.mod.profilPerso.sex = sexe.HOMME;
							this.mod.profilPerso.ori = orientation.BI;
						}
					}
					else if (choix_sexe.getValue()=="Femme") {
						if (choix_orientation.getValue()=="Hétéro") {
							this.mod.profilPerso.sex = sexe.FEMME;
							this.mod.profilPerso.ori = orientation.HETERO;
						}
						else if (choix_orientation.getValue()=="Homo") {
							this.mod.profilPerso.sex = sexe.FEMME;
							this.mod.profilPerso.ori = orientation.HOMO;
						}
						else if (choix_orientation.getValue()=="Bi") {
							this.mod.profilPerso.sex = sexe.FEMME;
							this.mod.profilPerso.ori = orientation.BI;
						}
					}
					else if (choix_sexe.getValue()=="Autre") {
						if (choix_orientation.getValue()=="Hétéro") {
							this.mod.profilPerso.sex = sexe.AUTRE;
							this.mod.profilPerso.ori = orientation.HETERO;
						}
						else if (choix_orientation.getValue()=="Homo") {
							this.mod.profilPerso.sex = sexe.AUTRE;
							this.mod.profilPerso.ori = orientation.HOMO;
						}
						else if (choix_orientation.getValue()=="Bi") {
							this.mod.profilPerso.sex = sexe.AUTRE;
							this.mod.profilPerso.ori = orientation.BI;
						}
					}
					this.mod.profilPerso.nom = nom.getText();
					this.mod.profilPerso.prenom = prenom.getText();
					this.mod.profilPerso.age = Integer.parseInt(age.getText());
					this.mod.profilPerso.metier = metier.getText();
					this.mod.profilPerso.ville = choix_ville.getText();
					this.mod.profilPerso.preferences = pref;
					this.mod.profilPerso.fumeur = fumeur.isSelected();
					this.mod.profilPerso.image = url_photo.getText();
					
					this.mod.fileAttente.clear();
					this.mod.thread.reset();
					this.mod.thread.start();
					alert_btn.setTitle("Message d'information");
					alert_btn.setHeaderText("Les modifications de votre profil ont bien été prisent en compte");
					alert_btn.showAndWait();
				}
			}
			else {
				alert_btn.setTitle("Action impossible");
				alert_btn.setHeaderText("Veuillez remplir tous les champs");
				alert_btn.showAndWait();
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
		this.getChildren().addAll(titre, pane_nom, pane_age, pane_age_r, pane_sexe, pane_metier, pane_orientation, pane_ville, pane_distance, pane_activite, pane_fumeur, pane_fumeur_r, pane_image, pane_btn);
	
	}

}
