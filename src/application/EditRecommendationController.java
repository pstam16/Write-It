package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EditRecommendationController {
	private DatabaseManager db = new DatabaseManager();
	@FXML
	private Button exitButton;
	@FXML
	private Button editRecommendationButton;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private ChoiceBox<String> genderChoiceBox;
	@FXML
	private TextField schoolNameField;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ChoiceBox<String> programChoiceBox;
	@FXML
	private ChoiceBox<String> semesterChoiceBox;
	@FXML
	private TextField yearField;
	@FXML
	private ListView<String> courseListView;
	@FXML
	private VBox gradeVBox;
	@FXML
	private ListView<String> personalListView;
	@FXML
	private ListView<String> academicListView;
	@FXML
	private Map<String, TextArea> gradeTextAreas;

	public void initialize() {

		// Set choices for genderChoice
		genderChoiceBox.getItems().addAll("Male", "Female", "Non-Binary", "Other");

		// Set choices for programChoice
		programChoiceBox.getItems().addAll(db.getAllSingleStringVars("programs", "programName"));

		// Set choices for First Semester semesterChoice
		semesterChoiceBox.getItems().addAll(db.getAllSingleStringVars("semesters", "semesterName"));

		// Set other Courses by Professor #MULTI-SELECT BY LEFT CLICK + CONTROL
		courseListView.getItems().addAll(db.getAllSingleStringVars("courses", "courseName"));
		courseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		gradeTextAreas = new HashMap<>();

		// Update choices if user selects / deselects choices
		courseListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// Clear existing text areas from gradeVBox
			gradeVBox.getChildren().clear();

			// Add new text areas for the currently selected courses
			List<String> courses = courseListView.getSelectionModel().getSelectedItems();
			for (String course : courses) {
				TextArea gradeTextArea = new TextArea();
				gradeTextArea.setPromptText("Enter grade for " + course);
				gradeVBox.getChildren().add(gradeTextArea);
				gradeTextAreas.put(course, gradeTextArea);
			}
		});

		// Set List of Student's Personal Characteristics #MULTI-SELECT BY LEFT CLICK +
		// CONTROL
		personalListView.getItems().addAll(db.getAllSingleStringVars("characteristics", "description", "type", 0));
		personalListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Set List of Student's Academic Characteristics #MULTI-SELECT BY LEFT CLICK +
		// CONTROL
		academicListView.getItems().addAll(db.getAllSingleStringVars("characteristics", "description", "type", 1));
		academicListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	public void editRecommendation(ActionEvent e) throws IOException {

		// Get values from input fields
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String gender = genderChoiceBox.getValue();
		String schoolName = schoolNameField.getText();
		LocalDate selectedDate = datePicker.getValue();
		String program = programChoiceBox.getValue();
		String semester = semesterChoiceBox.getValue();
		String year = yearField.getText();

		// Loop through the gradeTextAreas map and get the grade for each course
		List<String> courses = courseListView.getSelectionModel().getSelectedItems();
		Map<String, String> grades = new HashMap<>();
		for (String course : courses) {
			TextArea gradeTextArea = gradeTextAreas.get(course);
			if (gradeTextArea != null) {
				String grade = gradeTextArea.getText();
				grades.put(course, grade);
			}
		}
		List<String> personalCharacteristics = personalListView.getSelectionModel().getSelectedItems();
		List<String> academicCharacteristics = academicListView.getSelectionModel().getSelectedItems();

		// TODO: Edit values in database
		db.addRecommendation(firstName, lastName, gender, schoolName, selectedDate, program, semester, year, courses, grades, personalCharacteristics, academicCharacteristics);

		SceneController sceneController = new SceneController();
		sceneController.switchToDraftRecommendationScene(e);
	}

	// Clear all selected Courses
	@FXML
	private void handleClearSelection() {
		courseListView.getSelectionModel().clearSelection();
		gradeVBox.getChildren().clear();
		gradeTextAreas.clear();
	}

	// When exit button is pressed
	// Return to main menu
	public void exitButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToSearchRecommendationScene(e);
	}

}
