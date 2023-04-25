package application;

import java.util.*;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class AccountInfoController {

	@FXML
	private Button saveButton;
	@FXML
	private Button exitButton;
	@FXML
	private TextField fullNameTextField;
	@FXML
	private TextField titleTextField;
	@FXML
	private TextField schoolAndDepartmentTextField;
	@FXML
	private TextField emailAddressTextField;
	@FXML
	private TextField phoneNumberTextField;
	@FXML
	private CheckBox springCheckBox;
	@FXML
	private CheckBox fallCheckBox;
	@FXML
	private CheckBox summerCheckBox;
	@FXML
	private TextArea coursesTextArea;
	@FXML
	private TextArea programsTextArea;
	@FXML
	private TextArea personalCharTextArea;
	@FXML
	private TextArea academicCharTextArea;

	// When save button is pressed
	public void saveButtonAction(ActionEvent e) throws IOException {
		// upload data to database
		writeToFile();
		// return to main menu
		SceneController sceneController = new SceneController();
		sceneController.switchToMainMenuScene(e);
	}

	// When exit button is pressed
	// Return to main menu
	public void exitButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToMainMenuScene(e);
	}

	// Save to file
	public void writeToFile() {
		// object to control database operations
		DatabaseManager db = new DatabaseManager();

		// Scanner objects to read data from text areas
		Scanner coursesObj = new Scanner(coursesTextArea.getText());
		Scanner programsObj = new Scanner(programsTextArea.getText());
		Scanner personalCharObj = new Scanner(personalCharTextArea.getText());
		Scanner academicCharObj = new Scanner(academicCharTextArea.getText());

		// List objects to hold data from text areas
		List<String> coursesList = new ArrayList<>();
		List<String> programsList = new ArrayList<>();
		List<String> personalCharList = new ArrayList<>();
		List<String> academicCharList = new ArrayList<>();
		List<String> semestersList = new ArrayList<>();

		String name = fullNameTextField.getText();
		String title = titleTextField.getText();
		String schoolDepartment = schoolAndDepartmentTextField.getText();
		String email = emailAddressTextField.getText();
		String phoneNumber = phoneNumberTextField.getText();

		if (springCheckBox.isSelected()) {
			semestersList.add("Spring\n");
		}
		if (fallCheckBox.isSelected()) {
			semestersList.add("Fall\n");
		}
		if (summerCheckBox.isSelected()) {
			semestersList.add("Summer\n");
		}

		try {
			// add user inputs to ArrrayLists for database upload
			while (coursesObj.hasNextLine()) {
				coursesList.add(coursesObj.nextLine() + "\n");
			}

			while (programsObj.hasNextLine()) {
				programsList.add(programsObj.nextLine() + "\n");
			}

			while (personalCharObj.hasNextLine()) {
				personalCharList.add(personalCharObj.nextLine() + "\n");
			}

			while (academicCharObj.hasNextLine()) {
				academicCharList.add(academicCharObj.nextLine() + "\n");
			}

		} catch (Exception d) {
			System.out.println("An error occurred.");
			d.printStackTrace();
		}

		// Upload user data to database
		if (!name.isEmpty()) {
			db.setSingleStringVar("user", "name", name);
		}

		if (!title.isEmpty()) {
			db.setSingleStringVar("user", "title", title);
		}
		
		if (!schoolDepartment.isEmpty()) {
			db.setSingleStringVar("user", "schoolDepartment", schoolDepartment);
		}
		
		if (!email.isEmpty()) {
			db.setSingleStringVar("user", "email", email);
		}

		if (!phoneNumber.isEmpty()) {
			db.setSingleStringVar("user", "phoneNumber", phoneNumber);
		}

		// upload semesters, courses, programs, personal characteristics,
		// academic characteristics to database
		Iterator<String> iter = semestersList.iterator();
		while (iter.hasNext()) {
			// System.out.println(iter.next());
			db.addSingleStringVar("semesters", "semesterName", iter.next());
		}

		iter = coursesList.iterator();
		while (iter.hasNext()) {
			db.addSingleStringVar("courses", "courseName", iter.next());
		}

		iter = programsList.iterator();
		while (iter.hasNext()) {
			db.addSingleStringVar("programs", "programName", iter.next());
		}

		// add to characteristics table with type 0 (personal)
		iter = personalCharList.iterator();
		while (iter.hasNext()) {
			db.addSingleStringVarWithType("characteristics", "description", iter.next(), 0);
		}

		// add to characteristics table with type 1 (academic)
		iter = academicCharList.iterator();
		while (iter.hasNext()) {
			db.addSingleStringVarWithType("characteristics", "description", iter.next(), 1);
		}
	}

}
