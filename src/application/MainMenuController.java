package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

	@FXML
	private Button logoutButton;
	@FXML
	private Button changePassButton;
	@FXML 
	private Button createRecommendationButton;
	@FXML
	private Button accountButton;

	// When logout button is pressed
	// Return to main menu
	public void logoutButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToLoginScene(e);
	}

	// When change password button is pressed
	// Redirect to change password
	public void changePassButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToChangePasswordScene(e);
	}

	// When create recommendation button is pressed
	// Redirect to create recommendation scene
	public void createRecommendationButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToCreateRecommendationScene(e);

	// Switch to account info scene
	public void accountButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToAccountInfoScene(e);
	}
}