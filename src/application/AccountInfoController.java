package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
	private ScrollPane facultyPane;
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
	private ComboBox<String> semestersComboBox;
	@FXML 
	private ListView<String> semestersListView;
	@FXML 
	private Text invalidInputSemesterText;
	@FXML 
	private ComboBox<String> coursesComboBox;
	@FXML 
	private ListView<String> coursesListView;
	@FXML 
	private Text invalidInputCourseText;
	@FXML 
	private ComboBox<String> programsComboBox;
	@FXML 
	private ListView<String> programsListView;
	@FXML 
	private Text invalidInputProgramText;
	@FXML 
	private ComboBox<String> personalCharacComboBox;
	@FXML 
	private ListView<String> personalCharacListView;
	@FXML 
	private Text invalidInputPersonalCharacText;
	@FXML 
	private ComboBox<String> academicCharacComboBox;
	@FXML 
	private ListView<String> academicCharacListView;
	@FXML
	private Text invalidInputAcademicCharacText;
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DatabaseManager db = new DatabaseManager();
		// HELP, only one value is being stored
		// Get all items from database and display in list view
		semestersListView.getItems().addAll(db.getSingleStringVar("semesters", "semesterName").toString()); 
		coursesListView.getItems().addAll(db.getSingleStringVar("courses", "courseName"));
		programsListView.getItems().addAll(db.getSingleStringVar("programs", "programName"));
		personalCharacListView.getItems().addAll(db.getSingleStringVar("characteristics", "description", 0));
		academicCharacListView.getItems().addAll(db.getSingleStringVar("characteristics", "description", 1));
		
		// Set items into ComboBox
		semestersComboBox.setItems(FXCollections.observableArrayList("Fall", "Spring", "Summer", "Winter"));
		coursesComboBox.setItems(FXCollections.observableArrayList("CS 146 - Data Structures and Algorithms", "CS 147 - Computer Architecture",
						"CS 149 - Operating Systems", "CS 151 - Object-Oriented Design", "CS 152 - Programming Paradigms", "CS 154 - Formal Languages and Computability",
						"CS 160 - Software Engineering", "CS 157A - Introduction to Database Management Systems", "CS 166 - Information Security"));
		programsComboBox.setItems(FXCollections.observableArrayList("Advertising, BS", "Chemistry, BA", "Computer Science, BS", "Electrical Engineering, BS",
						"Accountancy, MS", "Aerospace Engineering, MS", "Computer Science, MS", "Mathematics, MS", "Audiology, AUD", "Educational Leadership, EdD"));
		personalCharacComboBox.setItems(FXCollections.observableArrayList("very passionate", "very enthusiastic", "punctual", "attentive", "polite"));
		academicCharacComboBox.setItems(FXCollections.observableArrayList("submitted well-written assignments", "participated in all of my class activities",
						"worked hard", "was very well prepared for every exam and assignment", "picked up new skills quickly", "was able to excel academically at the top of my class"));
		
		dataSavedText.setText("");
	}
	
	// Add items selected items from combo box to list view
	public void addSelectedSemester(ActionEvent event) {
		if (semestersComboBox.getValue() != null) {
			semestersListView.getItems().add(semestersComboBox.getValue());
			invalidInputSemesterText.setText("");
		} else {
			invalidInputSemesterText.setText("Invalid input");
		}
	}
	
	public void addSelectedCourse(ActionEvent event) {
		if (coursesComboBox.getValue() != null) {
			coursesListView.getItems().add(coursesComboBox.getValue());
			invalidInputCourseText.setText("");
		} else {
			invalidInputCourseText.setText("Invalid input");
		}
	}
	
	public void addSelectedProgram(ActionEvent event) {
		if (programsComboBox.getValue() != null) {
			programsListView.getItems().add(programsComboBox.getValue());
			invalidInputProgramText.setText("");
		} else {
			invalidInputProgramText.setText("Invalid input");
		}
	}

	public void addSelectedPersonalCharac(ActionEvent event) {
		if (personalCharacComboBox.getValue() != null) {
			personalCharacListView.getItems().add(personalCharacComboBox.getValue());
			invalidInputPersonalCharacText.setText("");
		} else {
			invalidInputPersonalCharacText.setText("Invalid input");
		}
	}

	public void addSelectedAcademicCharac(ActionEvent event) {
		if (academicCharacComboBox.getValue() != null) {
			academicCharacListView.getItems().add(academicCharacComboBox.getValue());
			invalidInputAcademicCharacText.setText("");
		} else {
			invalidInputAcademicCharacText.setText("Invalid input");
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

	// Displays error message if: Password fields are blank, Current password is wrong, or New and confirm passwords do not match
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
		String name = firstNameTextField.getText() + " " + lastNameTextField.getText();
		String title = titleTextField.getText();
		String schoolDepartment = schoolNameTextField.getText() + " " + departmentNameTextField.getText();
		String email = emailTextField.getText();
		String phoneNumber = phoneNumberTextField.getText();
		
		// Add all items from faculty info page
		semestersList.addAll(semestersListView.getItems());
		coursesList.addAll(coursesListView.getItems());
		programsList.addAll(programsListView.getItems());
		personalCharList.addAll(personalCharacListView.getItems());
		academicCharList.addAll(academicCharacListView.getItems());
		
		// Upload user data to database
		if (!name.isEmpty() && !title.isEmpty() && !schoolDepartment.isEmpty() && !email.isEmpty()
				&& !phoneNumber.isEmpty()) {
			db.setUserData(name, title, schoolDepartment, email, phoneNumber);
		}
		// upload semesters, courses, programs, personal characteristics,
		// academic characteristics to database
		Iterator<String> iter = semestersList.listIterator();
		while (iter.hasNext()) {
			// System.out.println(iter.next());
			db.setSingleStringVar("semesters", "semesterName", iter.next());
		}

		iter = coursesList.iterator();
		while (iter.hasNext()) {
			db.setSingleStringVar("courses", "courseName", iter.next());
		}

		iter = programsList.iterator();
		while (iter.hasNext()) {
			db.setSingleStringVar("programs", "programName", iter.next());
		}

		// add to characteristics table with type 0 (personal)
		iter = personalCharList.iterator();
		while (iter.hasNext()) {
			db.setSingleStringVarWithType("characteristics", "description", iter.next(), 0);
		}

		// add to characteristics table with type 1 (academic)
		iter = academicCharList.iterator();
		while (iter.hasNext()) {
			db.setSingleStringVarWithType("characteristics", "description", iter.next(), 1);
		}

	}

	// When home button is pressed
		// Return to main menu screen
		public void homeButtonAction(ActionEvent e) throws IOException {
			SceneController sceneController = new SceneController();
			sceneController.switchToMainMenuScene(e);
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