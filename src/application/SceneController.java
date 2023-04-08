package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	// Scene Controller is for switching scenes in the application
	
	public void switchToLoginScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Login Screen");
		stage.setScene(scene);
		stage.show();
	}

	public void switchToMainMenuScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainMenuScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Main Menu");
		stage.setScene(scene);
		stage.show();
	}

	public void switchToChangePasswordScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ChangePasswordScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Change Password");
		stage.setScene(scene);
		stage.show();
	}
	
    public void switchToCreateRecommendationScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CreateRecommendationScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Create Recommendation");
        stage.setScene(scene);
        stage.show();
    }
}