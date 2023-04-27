package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AccountInfoController extends ChangePasswordController implements Initializable {
	@FXML
	private AnchorPane personalPane;
	@FXML
	private AnchorPane facultyPane;
	@FXML
	private AnchorPane studentPane;
	@FXML
	private AnchorPane changePasswordPane;
	@FXML
	private VBox personalInfoVBox;
	@FXML
	private VBox facultyInfoVBox;
	@FXML
	private VBox studentVBox;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private TextField titleTextField;
	@FXML
	private TextField schoolNameTextField;
	@FXML
	private TextField departmentNameTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField phoneNumberTextField;
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
	@FXML
	private Text dataSavedText;
	@FXML
	private TextField semestersTextField;
	@FXML
	private ListView<String> semestersListView;
	@FXML
	private Text invalidSemesterText;
	@FXML
	private TextField coursesTextField;
	@FXML
	private ListView<String> coursesListView;
	@FXML
	private Text invalidCourseText;
	@FXML
	private TextField programsTextField;
	@FXML
	private ListView<String> programsListView;
	@FXML
	private Text invalidProgramText;
	@FXML
	private TextField personalCharacTextField;
	@FXML
	private ListView<String> personalCharacListView;
	@FXML
	private Text invalidPersonalCharacText;
	@FXML
	private TextField academicCharacTextField;
	@FXML
	private ListView<String> academicCharacListView;
	@FXML
	private Text invalidAcademicCharacText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DatabaseManager db = new DatabaseManager();
		
		// Set prompt text for personal info page using information from database
		firstNameTextField.setPromptText(db.getSingleStringVarFromID("user", "firstName", "id", 1));
		lastNameTextField.setPromptText(db.getSingleStringVarFromID("user", "lastName", "id", 1));
		titleTextField.setPromptText(db.getSingleStringVarFromID("user", "title", "id", 1));
		schoolNameTextField.setPromptText(db.getSingleStringVarFromID("user", "school", "id", 1));
		departmentNameTextField.setPromptText((db.getSingleStringVarFromID("user", "department", "id", 1)));
		emailTextField.setPromptText((db.getSingleStringVarFromID("user", "email", "id", 1)));
		phoneNumberTextField.setPromptText(db.getSingleStringVarFromID("user", "phoneNumber", "id", 1));
		
		// Get all items from database and display in list view
		semestersListView.getItems().addAll(db.getAllSingleStringVars("semesters", "semesterName"));
		coursesListView.getItems().addAll(db.getAllSingleStringVars("courses", "courseName"));
		programsListView.getItems().addAll(db.getAllSingleStringVars("programs", "programName"));
		personalCharacListView.getItems().addAll(db.getAllSingleStringVars("characteristics", "description", "type", 0));
		academicCharacListView.getItems().addAll(db.getAllSingleStringVars("characteristics", "description", "type", 1));
	}

	// Add items selected items from combo box to list view
	public void addSelectedSemester(ActionEvent event) {
		String semester = semestersTextField.getText();
		invalidSemesterText.setText("");

		if (!semester.isEmpty()) {
			semestersListView.getItems().add(semester);
		}
		else {
			invalidSemesterText.setText("Invalid input");
		}
	}

	public void addSelectedCourse(ActionEvent event) {
		String course = coursesTextField.getText();
		invalidCourseText.setText("");

		if (!course.isEmpty()) {
			coursesListView.getItems().add(course);
		} else {
			invalidCourseText.setText("Invalid input");
		}
	}

	public void addSelectedProgram(ActionEvent event) {
		String program = programsTextField.getText();

		if (!program.isEmpty()) {
			programsListView.getItems().add(program);
			invalidProgramText.setText("");
		} else {
			invalidProgramText.setText("Invalid input");
		}
	}

	public void addSelectedPersonalCharac(ActionEvent event) {
		String personalCharac = personalCharacTextField.getText();

		if (!personalCharac.isEmpty()) {
			personalCharacListView.getItems().add(personalCharac);
			invalidPersonalCharacText.setText("");
		} else {
			invalidPersonalCharacText.setText("Invalid input");
		}
	}

	public void addSelectedAcademicCharac(ActionEvent event) {
		String academicCharac = academicCharacTextField.getText();

		if (!academicCharac.isEmpty()) {
			academicCharacListView.getItems().add(academicCharac);
			invalidAcademicCharacText.setText("");
		} else {
			invalidAcademicCharacText.setText("Invalid input");
		}
	}

	// Remove selected items from view
	public void removeSelectedSemester(ActionEvent event) {
		int selectedID = semestersListView.getSelectionModel().getSelectedIndex();
		semestersListView.getItems().remove(selectedID);
	}

	public void removeSelectedCourse(ActionEvent event) {
		int selectedID = coursesListView.getSelectionModel().getSelectedIndex();
		coursesListView.getItems().remove(selectedID);
	}

	public void removeSelectedProgram(ActionEvent event) {
		int selectedID = programsListView.getSelectionModel().getSelectedIndex();
		programsListView.getItems().remove(selectedID);
	}

	public void removeSelectedPersonalCharac(ActionEvent event) {
		int selectedID = personalCharacListView.getSelectionModel().getSelectedIndex();
		personalCharacListView.getItems().remove(selectedID);
	}

	public void removeSelectedAcademicCharac(ActionEvent event) {
		int selectedID = academicCharacListView.getSelectionModel().getSelectedIndex();
		academicCharacListView.getItems().remove(selectedID);
	}

	// When new pane is selected from secondary side-menu's buttons
	public void personalPaneSwitch(MouseEvent e) {
		personalPane.setVisible(true);
		facultyPane.setVisible(false);
		studentPane.setVisible(false);
		changePasswordPane.setVisible(false);
	}

	public void facultyPaneSwitch(MouseEvent e) {
		personalPane.setVisible(false);
		facultyPane.setVisible(true);
		studentPane.setVisible(false);
		changePasswordPane.setVisible(false);
	}

	public void studentPaneSwitch(MouseEvent e) {
		personalPane.setVisible(false);
		facultyPane.setVisible(false);
		studentPane.setVisible(true);
		changePasswordPane.setVisible(false);
	}

	public void changePasswordPaneSwitch(MouseEvent event) {
		personalPane.setVisible(false);
		facultyPane.setVisible(false);
		studentPane.setVisible(false);
		changePasswordPane.setVisible(true);
	}

	// When "Edit" button is pressed
	public void enablePersonalInfoVBox(MouseEvent event) {
		personalInfoVBox.setDisable(false);
	}

	public void enableFacultyVBox(MouseEvent event) {
		facultyInfoVBox.setDisable(false);
	}

	public void enableStudentVBox(MouseEvent event) {
		studentVBox.setDisable(false);
	}

	// When the change password button is pressed
	public void changePassButtonAction(ActionEvent e) throws IOException {
		super.changePassButtonAction(e);
	}

	// Displays error message if: Password fields are blank, Current password is
	// wrong, or New and confirm passwords do not match
	public void passButtonError(String currentPassword, String newPassword, String confirmPassword) {
		super.passButtonError(currentPassword, newPassword, confirmPassword);
	}

	// Checks to see if confirm password matches with new password
	public boolean passwordConfirmation(String newPassword, String confirmPassword) {
		return super.passwordConfirmation(newPassword, confirmPassword);
	}

	public void writeToFile() {
		// object to control database operations
		DatabaseManager db = new DatabaseManager();

		// List objects to hold data from text areas
		List<String> semestersList = new ArrayList<>();
		List<String> coursesList = new ArrayList<>();
		List<String> programsList = new ArrayList<>();
		List<String> personalCharList = new ArrayList<>();
		List<String> academicCharList = new ArrayList<>();

		// Add all inputs from personal info page
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String title = titleTextField.getText();
		String school = schoolNameTextField.getText();
		String department = departmentNameTextField.getText();
		String email = emailTextField.getText();
		String phoneNumber = phoneNumberTextField.getText();

		// Add all items from faculty info page
		semestersList.addAll(semestersListView.getItems());
		coursesList.addAll(coursesListView.getItems());
		programsList.addAll(programsListView.getItems());
		personalCharList.addAll(personalCharacListView.getItems());
		academicCharList.addAll(academicCharacListView.getItems());

		// Upload user data to database
		if (!(firstName.isEmpty())) {
			db.setSingleStringVar("user", "firstName", firstName);
		}

		if (!(lastName.isEmpty())) {
			db.setSingleStringVar("user", "lastName", lastName);
		}

		if (!title.isEmpty()) {
			db.setSingleStringVar("user", "title", title);
		}

		if (!school.isEmpty()) {
			db.setSingleStringVar("user", "school", school);
		}

		if (!department.isEmpty()) {
			db.setSingleStringVar("user", "department", department);
		}
		if (!email.isEmpty()) {
			db.setSingleStringVar("user", "email", email);
		}

		if (!phoneNumber.isEmpty()) {
			db.setSingleStringVar("user", "phoneNumber", phoneNumber);
		}

		// delete old data
		db.deleteAll("semesters");
		db.deleteAll("courses");
		db.deleteAll("programs");
		db.deleteAll("characteristics");
		
		// upload semesters, courses, programs, personal characteristics,
		// academic characteristics to database
		if (!semestersList.isEmpty()) {
			for (String semester : semestersList) {
				db.addSingleVar("semesters", "semesterName", semester);
			}
		}
		if (!coursesList.isEmpty()) {
			for (String course : coursesList) {
				db.addSingleVar("courses", "courseName", course);
			}
		}
		if (!programsList.isEmpty()) {
			for (String program : programsList) {
				db.addSingleVar("programs", "programName", program);
			}
		}
		// add to characteristics table with type 0 (personal)
		if (!personalCharList.isEmpty()) {
			for (String personalChar : personalCharList) {
				db.addSingleStringVarWithId("characteristics", "description", personalChar, "type", 0);
			}
		}

		// add to characteristics table with type 1 (academic)
		if (!academicCharList.isEmpty()) {
			for (String academicChar : academicCharList) {
				db.addSingleStringVarWithId("characteristics", "description", academicChar, "type", 1);
			}
		}
	}

	// When home button is pressed
	// Return to main menu screen
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

	// When account button is pressed
	// Refresh current page
	public void accountButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToAccountInfoScene(e);
	}

	// When logout button is pressed
	// Return to log-in screen
	public void logoutButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToLoginScene(e);
	}

	// When save button is pressed
	// Save data to database and return to home screen
	public void saveButtonAction(ActionEvent e) throws IOException {
		// upload data to database
		writeToFile();
		dataSavedText.setText("Information has been updated");
	}

	// When exit button is pressed
	// Return to main menu
	public void exitButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToMainMenuScene(e);
	}
}
