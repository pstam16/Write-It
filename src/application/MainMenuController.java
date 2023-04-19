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
	private Button searchRecommendationButton;
	@FXML
	private Button accountButton;
	@FXML 
	private Button editButton;
	@FXML 
	private Button deleteRecommendationButton;

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
	}

	// When search recommendation button is pressed
	// Redirect to search recommendation scene
	public void searchRecommendationButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToSearchRecommendationScene(e);
	}

	// Switch to account info scene
	public void accountButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToAccountInfoScene(e);
	}

	public void editButtonAction(ActionEvent event) {}

	public void deleteButtonAction(ActionEvent event) {}
}