package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {
	
	private Stage stage;
	
	private static Start instance;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
        stage.setTitle("Rubric-based Grading System");
        loginPage();
        stage.show();
	}
	
	public Start() {
		instance = this;
	}
	
	public static Start getInstance() {
		return instance;
	}
	
	public void loginPage() {
		try {
			replaceSceneContent("LOGIN.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mainPage() {
		try {
			replaceSceneContent("MAIN.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private Parent replaceSceneContent(String fxml) throws Exception {
		Parent page = (Parent) FXMLLoader.load(Start.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
        } 
        else {
        	scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.getScene().setRoot(page);
            
        }
        stage.sizeToScene();
        return page;
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}
