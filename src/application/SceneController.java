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
	
	// Switches application to the Login Scene
	public void switchToLoginScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Login Page");
		stage.setScene(scene);
		stage.show();
	}

	// Switches application to the Main Menu Scene
	public void switchToMainMenuScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainMenuScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Main Menu");
		stage.setScene(scene);
		stage.show();
	}
	
	// Switches application to the Change Password Scene
	public void switchToChangePasswordScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ChangePasswordScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Change Password");
		stage.setScene(scene);
		stage.show();
	}
}