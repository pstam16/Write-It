package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MainMenuController implements Initializable {
	
	@FXML 
	private Text welcomeMessage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String name = getName();
		
		if (!name.isEmpty()) {
			welcomeMessage.setText(getTime() + name);
		}
		else {
			welcomeMessage.setText(getTime());
		}
	}
	
	// Returns user's name stored in database
	public String getName() {
		try {
			DatabaseManager db = new DatabaseManager();
			String s = db.getSingleStringVar("user", "name").toString();
			String[] name = s.split(" ");
			return name[0];
		}  catch (Exception d) {
			System.out.println("An error occurred.");
			d.printStackTrace();
			return "";
		}
	}
	
	// Gets current time of device
	// Used to display proper greeting of the day on main menu
	public String getTime() {
		LocalTime morning = LocalTime.parse("00:00:00");
		LocalTime noon = LocalTime.parse("12:00:00");
		LocalTime evening = LocalTime.parse("18:00:00");
		LocalTime time = LocalTime.now();
		
		if (time.isAfter(morning) && time.isBefore(noon)) {
			return "Good morning,\n";
		}
		else if (time.isAfter(noon) && time.isBefore(evening)) {
			return "Good afternoon,\n";
		}
		else {
			return "Good evening,\n";
		}
	}
	
	// When home button is pressed
	// Used to refresh home page
	public void homeButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToMainMenuScene(e);
	}
	
	// When about us button is pressed
	// Redirect to About Us page
	public void aboutUsButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToAboutUsScene(e);
	}
	
	// When logout button is pressed
	// Return to log-in screen
	public void logoutButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToLoginScene(e);
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

	public void editButtonAction(ActionEvent event) {
		// TODO:
	}

	public void deleteButtonAction(ActionEvent event) {
		// TODO:
	}
	
}