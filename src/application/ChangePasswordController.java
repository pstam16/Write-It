package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class ChangePasswordController {

	@FXML
	private Button changePasswordButton;
	@FXML
	private Label currentPasswordLabel;
	@FXML
	private Label newPasswordLabel;
	@FXML
	private Label confirmPasswordLabel;
	@FXML
	private PasswordField currentPasswordField;
	@FXML
	private PasswordField newPasswordField;
	@FXML
	private PasswordField confirmPasswordField;

	public void changePasswordButtonAction(ActionEvent e) throws IOException {
		// Receives input for the current, new, and confirm password
		String currentPassword = currentPasswordField.getText();
		String newPassword = newPasswordField.getText();
		String confirmPassword = confirmPasswordField.getText();

		// Checks if the password is correct and if password confirmation matches
		LoginController loginController = new LoginController();
		if (loginController.validateLogin(currentPassword) && confirmPassword(newPassword, confirmPassword)) {
			// User enters correct current password and correctly confirms a new password
			// Redirect them to main menu and change their password with the new password
			SceneController sceneController = new SceneController();
			sceneController.switchToMainMenuScene(e);
			// TODO: Change password in database with new password
		}

		// Display error messages otherwise
		this.changePasswordButtonError(currentPassword, newPassword, confirmPassword);
	}

	// Checks if new and confirm passwords match
	public boolean confirmPassword(String newPassword, String confirmPassword) {
		if (newPassword.isEmpty())
			return false;
		if (confirmPassword.isEmpty())
			return false;
		return (confirmPassword.equals(newPassword));
	}

	// Displays error messages when pressing Change Password button
	public void changePasswordButtonError(String currentPass, String newPass, String confirmPass) {
		LoginController loginController = new LoginController();
		// Current Password Errors:
		// If incorrect, tell user that it is incorrect
		// If blank, tell user to enter a password
		// Otherwise, display no error
		if (!loginController.validateLogin(currentPass)) {
			currentPasswordLabel.setText("Incorrect Password!");
		} else if (currentPass.isEmpty()) {
			currentPasswordLabel.setText("Please enter a password!");
		} else
			currentPasswordLabel.setText("");

		// New Password Errors:
		// If blank, tell user to enter a password
		// Otherwise, display no error
		if (newPass.isEmpty()) {
			newPasswordLabel.setText("Please enter a password!");
		} else
			newPasswordLabel.setText("");

		// Confirm Password Errors:
		// If new and confirm passwords don't match, tell the user
		// If blank, tell user to enter a password
		// Otherwise, display no error
		if (confirmPass.isEmpty()) {
			confirmPasswordLabel.setText("Please enter a password!");
		} else if (!confirmPassword(newPass, confirmPass)) {
			confirmPasswordLabel.setText("Passwords do not match!");
		} else
			confirmPasswordLabel.setText("");
	}
}
