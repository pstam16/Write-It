package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class LoginController {

	@FXML
	private Button loginButton;
	@FXML
	private Label loginMessageLabel;
	@FXML
	private PasswordField passwordField;

	// Controller for pressing the login button
	public void loginButtonAction(ActionEvent e) throws IOException {
		// Receives password input
		String inputPassword = passwordField.getText();

		// Checks if the password is correct and if it is your first time logging in
		if (this.validateLogin(inputPassword) && !isFirstTimeLogin()) {
			// If password authentication is successful and not the first time logging in
			// Redirect the user to the Main Menu
			SceneController sceneController = new SceneController();
			sceneController.switchToMainMenuScene(e);
		}

		// Checks if the password is correct and if it is your first time logging in
		if (this.validateLogin(inputPassword) && isFirstTimeLogin()) {
			// If password authentication is successful and is the first time logging in
			// Redirect the user to Change Password
			SceneController sceneController = new SceneController();
			sceneController.switchToChangePasswordScene(e);
		}

		// Display error message otherwise
		if (!this.validateLogin(inputPassword) && !isFirstTimeLogin()) {
			this.loginButtonError();
		}
	}

	// Displays an error message if:
	// User enters an empty password
	// User enters an incorrect password
	public void loginButtonError() {
		if (passwordField.getText().isEmpty()) {
			loginMessageLabel.setText("Please enter a password!");
		} else {
			loginMessageLabel.setText("Incorrect password!");
		}
	}
	
	public boolean validateLogin(String inputPassword) {
		// TODO:
		// Check database if password from passwordField matches password in database
		// Return true if matches, otherwise false
		return true;
		// TODO: For now manually change this to true/false to go through the menus
	}

	// Check if the user is logging in for the first time
	public boolean isFirstTimeLogin() {
		// TODO: 
		// Check if the user has default password "p"
		// Return true if "p", return false otherwise
		return true;
		// TODO: For now manually change this to true/false to go through the menus
	}

}
