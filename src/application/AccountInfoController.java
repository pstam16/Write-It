package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class AccountInfoController {

	@FXML private Button saveButton;
	@FXML private Button exitButton;
	@FXML private TextField fullNameTextField;
	@FXML private TextField titleTextField;
	@FXML private TextField schoolAndDepartmentTextField;
	@FXML private TextField emailAddressTextField;
	@FXML private TextField phoneNumberTextField;
	@FXML private CheckBox springCheckBox;
	@FXML private CheckBox fallCheckBox;
	@FXML private CheckBox summerCheckBox;
	@FXML private TextArea coursesTextArea;
	@FXML private TextArea programsTextArea;
	@FXML private TextArea personalCharTextArea;
	@FXML private TextArea academicCharTextArea;
	
	private String path = "../Write-It/src/application/Files/";

	
	// When save button is pressed
	public void saveButtonAction(ActionEvent e) throws IOException {
			
		String fileName = createFile(fullNameTextField.getText());
		writeToFile(fileName);
			
		SceneController sceneController = new SceneController();
		sceneController.switchToMainMenuScene(e);
	}
	
	// When exit button is pressed
	public void exitButtonAction(ActionEvent e) throws IOException {
		SceneController sceneController = new SceneController();
		sceneController.switchToMainMenuScene(e);	
	}

	// Create File
	public String createFile(String name) {
		
		String fileName = name.replaceAll("\\s+", "") + ".txt";
		
		try {
			File myObj = new File(path + fileName);
			
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
		
		Scanner coursesObj = new Scanner(coursesTextArea.getText());
		Scanner programsObj = new Scanner(programsTextArea.getText());
		Scanner personalCharObj = new Scanner(personalCharTextArea.getText());
		Scanner academicCharObj = new Scanner(academicCharTextArea.getText());
		
		String name = fullNameTextField.getText();
		String title = titleTextField.getText();
		String schoolDepartment = schoolAndDepartmentTextField.getText();
		String email = emailAddressTextField.getText();
		String phoneNumber = phoneNumberTextField.getText();
		String semesters = "";
		
		if (springCheckBox.isSelected()) {
			semesters += "Spring\n";
		}
		if (fallCheckBox.isSelected()) {
			semesters += "Fall\n";
		}
		if (summerCheckBox.isSelected()) {
			semesters += "Summer";
		}
		
		try {
			FileWriter fileWriter = new FileWriter(path + fileName);
			fileWriter.write(name    + "\n" + title       + "\n" + schoolDepartment + "\n" + 
							 email   + "\n" + phoneNumber + "\n" + semesters        + "\n");
			
			while(coursesObj.hasNextLine()) {
				fileWriter.write(coursesObj.nextLine() + "\n");
			}
			
			while(programsObj.hasNextLine()) {
				fileWriter.write(programsObj.nextLine() + "\n");
			}
			
			while(personalCharObj.hasNextLine()) {
				fileWriter.write(personalCharObj.nextLine() + "\n");
			}
			
			while(academicCharObj.hasNextLine()) {
				fileWriter.write(academicCharObj.nextLine() + "\n");
			}
			
			fileWriter.close();
			
			System.out.println("Successfully wrote to " + fileName);
		} catch (Exception d) {
			 System.out.println("An error occurred.");
		      d.printStackTrace();
		}
	}
	
}
