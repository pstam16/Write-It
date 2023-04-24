package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SearchRecommendationController implements Initializable {

	@FXML
	private Button exitButton;
	@FXML
	private TextField searchField;
	@FXML
	private Button searchButton;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;
	@FXML
	private ListView<String> recommendationListView;

	String selectedItem;

	// Initializes the results that show up in the ListView
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO: Implement database
		recommendationListView.getItems().addAll(lastName);

		// Listener for selecting in the ListView
		recommendationListView.getSelectionModel().selectedItemProperty()
				.addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
					// Your action here
					selectedItem = recommendationListView.getSelectionModel().getSelectedItem();
					System.out.println("Selected item: " + newValue); // TODO: For testing, feel free to delete
				});
	}

	// Temporary testing ArrayList to hold last name info
	// TODO: Get this information from the database
	ArrayList<String> lastName = new ArrayList<>(Arrays.asList("Michael", "Henry", "Vu", "Hopkins", "Singleton",
			"Smith", "Johnson", "Miller", "Garcia", "Chen", "Li", "Ahmed", "Rodriguez", "Anderson", "Connor", "Lee",
			"Kim", "Perrson", "Lopez", "Harris", "Thomas", "Rivera", "Tom"));

	// When search button is pressed
	// Update the search results
	public void searchButtonAction(ActionEvent e) throws IOException {
		recommendationListView.getItems().clear();
		recommendationListView.getItems().addAll(searchList(searchField.getText(), lastName));

	}

	// Helper method
	// Filters and returns the search results in a list
	private List<String> searchList(String searchField, List<String> listOfStrings) {

		List<String> searchWordsArray = Arrays.asList(searchField.trim().split(" "));

		return listOfStrings.stream().filter(input -> {
			return searchWordsArray.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()));
		}).collect(Collectors.toList());
	}

	// When edit button is pressed
	// Open edit recommendation menu
	// Edit recommendation in database
	public void editButtonAction(ActionEvent e) throws IOException {
		if (selectedItem == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Selection Error");
			alert.setHeaderText(null);
			alert.setContentText("Select a recommendation before editing!");
			alert.showAndWait();
		} else
			editRecommendation(e);
	}

	public void editRecommendation(ActionEvent e) throws IOException {
		// TODO: Pass the selected recommendation's information to fill name
		// Switch to edit scene (where you can edit and store in database)
		SceneController sceneController = new SceneController();
		sceneController.switchToEditRecommendationScene(e);
	}

	// When delete button is pressed
	// Delete recommendation from database
	public void deleteButtonAction(ActionEvent e) throws IOException {
		if (selectedItem == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Selection Error");
			alert.setHeaderText(null);
			alert.setContentText("Select a recommendation before deleting!");
			alert.showAndWait();
		} else
			deleteRecommendation();
	}

	// Helper method that displays delete confirmation
	// If OK, then delete recommendation from database
	public void deleteRecommendation() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete " + selectedItem + "'s recommendation?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			// TODO: Delete recommendation from the SQLite Database
			System.out.println(selectedItem + "'s recommendation deleted"); // TODO: For testing, feel free to delete
		}
	}

	// When exit button is pressed
	// Return to main menu
	public void exitButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToMainMenuScene(e);
	}
}