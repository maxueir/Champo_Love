package application;

import java.util.Set;
import java.util.TreeSet;

import application.Profil.relation;
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
	
	ProfilPerso profilPerso;
	
	public Menu_preferences(ProfilPerso p) {
		this.profilPerso = p;
		
		// Parametrage VBox
		this.setPadding(new Insets(40, 10, 10,10));
		
		// Label titre..
		Label titre = new Label("Selection de vos préférences");
		titre.setFont(Font.font("Lucida Calligraphy",28));
		titre.setTextFill(Color.WHITE);
		titre.setPrefWidth(600);
		titre.setPadding(new Insets(20));
		
		// Bloc de séléction du type de relation
		FlowPane pane_relation = new FlowPane();
		Label label_relation = new Label("Quel type de relation cherchez-vous :");
		label_relation.setFont(Font.font("Lucida Calligraphy",16));
		label_relation.setTextFill(Color.WHITE);
		label_relation.setPrefWidth(350);		
		ChoiceBox<String> choix_relation = new ChoiceBox<String>();
		choix_relation.setPrefSize(120, 10);
		choix_relation.getItems().add("Courte");
		choix_relation.getItems().add("Longue");
		pane_relation.setVgap(10);
		pane_relation.getChildren().addAll(label_relation,choix_relation);
		
		// Bloc de choix de la tranche d'âge
		Label label_age = new Label("Tranche d'âge recherchée :");
		label_age.setFont(Font.font("Lucida Calligraphy",16));
		label_age.setTextFill(Color.WHITE);
		label_age.setPrefWidth(300);
		
		FlowPane pane_age = new FlowPane();
		TextField age_min = new TextField();
		age_min.setFont(Font.font("Arial",12));
		age_min.setPrefSize(95, 10);
		Label tiret = new Label("  -  ");
		TextField age_max = new TextField();
		age_max.setFont(Font.font("Arial",12));
		age_max.setPrefSize(95, 10);
		pane_age.setHgap(5);
		pane_age.setPadding(new Insets(10));
		pane_age.getChildren().addAll(age_min, tiret,age_max);
		
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
		non_fumeur.setOnMouseClicked(e -> {
			ind_fumeur.setSelected(false);
		});
		ind_fumeur.setOnMouseClicked(e -> {
			non_fumeur.setSelected(false);
		});
		pane_fumeur.setHgap(5);
		pane_fumeur.setPadding(new Insets(10));
		pane_fumeur.getChildren().addAll(non_fumeur, ind_fumeur);
		
		// Valeur par défaut des champs de choix des préférences
		if (this.profilPerso == null) {
			choix_relation.setValue("Relation");
			age_min.setPromptText("Âge minimum");
			age_max.setPromptText("Âge maximum");
			distance.setPromptText("Distance maximale");
		}
		else {
			choix_relation.setValue(this.profilPerso.relation.toString());
			age_min.setText(String.valueOf(this.profilPerso.age_min));
			age_max.setText(String.valueOf(this.profilPerso.age_max));
			distance.setText(String.valueOf(this.profilPerso.distance));
			ind_fumeur.setSelected(this.profilPerso.fumeur_r);
			non_fumeur.setSelected(!this.profilPerso.fumeur_r);
		}
		
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
		btn_preference.setOnMouseClicked( e -> {
			this.profilPerso.age_min = Integer.parseInt(age_min.getText());
			this.profilPerso.age_max = Integer.parseInt(age_max.getText());
			this.profilPerso.distance = Integer.parseInt(distance.getText());
			this.profilPerso.fumeur_r = ind_fumeur.isSelected();
			if (choix_relation.getValue()=="Courte") {
				this.profilPerso.relation = relation.COURTE;
			}
			else if (choix_relation.getValue()=="Longue") {
				this.profilPerso.relation = relation.LONGUE;
			}
		});
		
		pane_btn.setAlignment(Pos.BOTTOM_RIGHT);
		pane_btn.getChildren().add(btn_preference);
		
		// Ajout des éléments
		this.getChildren().addAll(titre, pane_relation, label_age, pane_age, pane_distance, label_fumeur, pane_fumeur, pane_btn);

		
				
	}

}
