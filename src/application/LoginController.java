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

	// When the login button is pressed
	public void loginButtonAction(ActionEvent e) throws IOException {
		// Receive user password input
		String inputPassword = passwordField.getText();

		// Verify password input
		if (this.validateLogin(inputPassword)) {
			// If password is correct
			// redirect user to main menu if it is not their first time logging on
			// else redirect them to change/make a new password
			SceneController sceneController = new SceneController();
			if (!isFirstTimeLogin()) {
				sceneController.switchToMainMenuScene(e);
			} else
				sceneController.switchToChangePasswordScene(e);
		} else {
			// Otherwise show error message
			this.loginButtonError();
		}
	}

	// Displays error message if:
	// Password field is blank
	// Wrong password
	public void loginButtonError() {
		if (passwordField.getText().isEmpty()) {
			loginMessageLabel.setText("Please enter a password!");
		} else {
			loginMessageLabel.setText("Incorrect Password!");
		}
	}

	// Check to see if the password is correct
	public boolean validateLogin(String inputPassword) {
		// TODO:
		// Check database if password from passwordField matches password in database
		// Return true if matches, otherwise false
		return true;
	}

	// Check if the user is logging in for the first time
	public boolean isFirstTimeLogin() {
		// TODO:
		// Check if the user has default password "p"
		// Return true if "p", return false otherwise
		return true;
	}

}