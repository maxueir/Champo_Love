package application;
	



import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Recherche_profil extends Application {
	Image imagecourante;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,600);
			scene.getStylesheets().addAll(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("ChampoLove");
			Image img = new Image("file:images/icone.jpg");
			primaryStage.getIcons().add(img);
			root.setId("pane");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
