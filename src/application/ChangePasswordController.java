package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class ChangePasswordController {
	@FXML
	private Button changePassButton;
	@FXML
	private Label currentPassLabel;
	@FXML
	private Label newPassLabel;
	@FXML
	private Label confirmPassLabel;
	@FXML
	private PasswordField currentPassField;
	@FXML
	private PasswordField newPassField;
	@FXML
	private PasswordField confirmPassField;

	// When the change password button is pressed
	public void changePassButtonAction(ActionEvent e) throws IOException {
		// Receive user input for current, new, and confirm password
		String currentPassword = currentPassField.getText();
		String newPassword = newPassField.getText();
		String confirmPassword = confirmPassField.getText();

		// Verify password input
		LoginController loginController = new LoginController();
		if (loginController.validateLogin(currentPassword) && this.passwordConfirmation(newPassword, confirmPassword)) {
			// If current password is correct and new and confirm password match
			// Redirect to main menu
			SceneController sceneController = new SceneController();
			sceneController.switchToMainMenuScene(e);
		} else {
			// Otherwise show error message
			this.passButtonError(currentPassword, newPassword, confirmPassword);
		}
	}

	// Displays error message if:
	// Password fields are blank
	// Current password is wrong
	// New and confirm passwords do not match
	public void passButtonError(String currentPassword, String newPassword, String confirmPassword) {
		LoginController loginController = new LoginController();
		if (!loginController.validateLogin(currentPassword)) {
			currentPassLabel.setText("Incorrect password!");
		} else if (currentPassword.isEmpty()) {
			currentPassLabel.setText("Please enter a password!");
		} else {
			currentPassLabel.setText("");
		}

		if (newPassword.isEmpty())
			newPassLabel.setText("Please enter a password!");
		else
			newPassLabel.setText("");

		if (confirmPassword.isEmpty()) {
			confirmPassLabel.setText("Please enter a password");
		} else if (!passwordConfirmation(newPassword, confirmPassword)) {
			confirmPassLabel.setText("Passwords do not match!");
		} else
			currentPassLabel.setText("");
	}

	// Checks to see if confirm password matches with new password
	public boolean passwordConfirmation(String newPassword, String confirmPassword) {
		if (newPassword.isEmpty() || confirmPassword.isEmpty())
			return false;
		return (confirmPassword.equals(newPassword));

	}
}
