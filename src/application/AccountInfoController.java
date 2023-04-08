package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

public class AccountInfoController {

	@FXML private Button saveButton;
	@FXML private Button exitButton;
	@FXML private TextField fullNameTextField;
	@FXML private TextField titleTextField;
	@FXML private TextField schoolAndDepartmentTextField;
	@FXML private TextField emailAddressTextField;
	@FXML private TextField phoneNumberTextField;
	@FXML private TextField coursesTextField;
	@FXML private TextField programsTextField;
	@FXML private TextField personalCharTextField;
	@FXML private TextField academicCharTextField;
	@FXML private CheckBox springCheckBox;
	@FXML private CheckBox fallCheckBox;
	@FXML private CheckBox summerCheckBox;

	// When save button is pressed
	public void saveButtonAction(ActionEvent e) throws IOException {
			
		String fileName = createFile(fullNameTextField.getText());
		writeToFile("../Write-It/src/application/Files/" + fileName);
			
		SceneController sceneController = new SceneController();
		sceneController.switchToMainMenuScene(e);
		
	}
	
	// When exit button is pressed
	public void exitButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToMainMenuScene(e);	
	}
	
	// Validate input
	
	
	
	// Create File
	public String createFile(String name) {
		
		String fileName = name.replaceAll("\\s+", "") + ".txt";
		
		try {
			File myObj = new File("../Write-It/src/application/Files/" + fileName);
			
			if (myObj.createNewFile()) {
				System.out.println ("File created: " + myObj.getName());
				return myObj.getName();
			} else {
				System.out.println("File already exists");
				return fileName;
			}
		} catch (Exception d) {
			System.out.println("An error occurred.");
			d.printStackTrace();
		}
		return null;
	}
	
	// Save to file
	public void writeToFile(String fileName) {
		String name = fullNameTextField.getText();
		String title = titleTextField.getText();
		String schoolDepartment = schoolAndDepartmentTextField.getText();
		String email = emailAddressTextField.getText();
		String phoneNumber = phoneNumberTextField.getText();
		String courses = coursesTextField.getText();
		String programs = programsTextField.getText();
		String personalChar = personalCharTextField.getText();
		String academicChar = academicCharTextField.getText();
		
		String semesters = "";
		if (springCheckBox.isSelected()) {
			semesters += "Spring ";
		}
		if (fallCheckBox.isSelected()) {
			semesters += "Fall ";
		}
		if (summerCheckBox.isSelected()) {
			semesters += "Summer";
		}
		
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			fileWriter.write(name    + "\n" + title       + "\n" + schoolDepartment + "\n" + 
							 email   + "\n" + phoneNumber + "\n" + semesters        + "\n" + 
							 courses + "\n" + programs    + "\n" + personalChar     + "\n" + 
							 academicChar);
			fileWriter.close();
			
			System.out.println("Successfully wrote to " + fileName);
		} catch (Exception d) {
			 System.out.println("An error occurred.");
		      d.printStackTrace();
		}
	}
	
}
