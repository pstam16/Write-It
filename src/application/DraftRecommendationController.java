package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class DraftRecommendationController implements Initializable {
	private DatabaseManager db = new DatabaseManager();
	@FXML
	private Button saveButton;
	@FXML
	private TextArea draft;

	// Initializes the recommendation in the TextArea
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		generateRecommendation();
	}

	public void generateRecommendation() {
		// find latest inserted student
		int last = db.getLastInsertID("recommendations");
		
		// get data of last inserted student
		String firstName = db.getSingleStringVarFromRow("recommendations", "firstName", last);
		String lastName = db.getSingleStringVarFromRow("recommendations", "lastName", last);
		String gender = db.getSingleStringVarFromRow("recommendations", "gender", last);
		String schoolName = db.getSingleStringVarFromRow("recommendations", "schoolName", last);
		String program = db.getSingleStringVarFromRow("recommendations", "program", last);
		String semester = db.getSingleStringVarFromRow("recommendations", "semester", last);
		String year = db.getSingleStringVarFromRow("recommendations", "year", last);
		List<String> courses = db.getDataFromStudent(last, "courseID", "grades", "courseName", "courses");
		List<String> gradeList = db.getAllSingleStringVars("grades", "grade", "studentID", db.getNameHash(firstName, lastName));
		System.out.println(gradeList);
		Map<String, String> grades = new HashMap<>();
		for (int i = 0; i < Math.min(courses.size(), gradeList.size()); i++) {
			System.out.println("inserting");
			grades.put(courses.get(i), gradeList.get(i));
		}
		System.out.println(grades);
		List<String> academicCharacteristics = db.getDataFromStudent(last, "characteristicID", "studentChars", "description", "characteristics", 1);
		List<String> personalCharacteristics = db.getDataFromStudent(last, "characteristicID", "studentChars", "description", "characteristics", 0);
		
		String professor = db.getSingleStringVarFromRow("user", "firstName", 1) + " " + db.getSingleStringVarFromRow("user", "lastName", 1);
		String professorTitle = db.getSingleStringVarFromRow("user", "firstName", 1);
		String school = db.getSingleStringVarFromRow("user", "school", 1);
		String department = db.getSingleStringVarFromRow("user", "department", 1);
		String email = db.getSingleStringVarFromRow("user", "email", 1);
		String phone = db.getSingleStringVarFromRow("user", "phoneNumber", 1);
		// Testing ^^^^^^

		// Create pronouns from gender
		String pronoun;
		String uppercasePronoun;
		if (gender.equals("Male")) {
			pronoun = "he";
			uppercasePronoun = "He";
		} else if (gender.equals("Female")) {
			pronoun = "she";
			uppercasePronoun = "She";
		} else {
			pronoun = "they";
			uppercasePronoun = "They";
		}

		// Compile message using StringBuilder
		StringBuilder sb = new StringBuilder();

		// Letter of Recommendation
		// For: <Student's Full Name>
		// Date: <Today's Date>
		// To: Graduate Admissions Committee
		sb.append("Letter of Recommendation\n\n");
		sb.append("For: ").append(firstName).append(" " + lastName).append("\n");
		sb.append("Date: ").append(LocalDate.now().toString()).append("\n");
		sb.append("To: Graduate Admissions Committee\n\n");

		// I am writing this letter to recommend my former student <Student's Full Name>
		// who is applying for the <program name> in your school.
		sb.append("I am writing this letter to recommend my former student ").append(firstName).append(" ")
				.append(lastName).append(" who is applying for the ").append(program).append(" in your school.\n\n");
		// I met <Student's First Name> in <First Semester> when he enrolled in my
		// <First Course Taken> course.
		sb.append("I met ").append(firstName).append(" in ").append(semester).append(" ").append(year).append(" when ")
				.append(pronoun).append(" enrolled in my ").append(courses.get(0)).append(" course.\n\n");
		// <Student's First Name> earned <letter grade> from this tough course, and this
		// shows how knowledgeable and hard worker <he/she> is.
		sb.append(firstName).append(" earned ").append(grades.get(courses.get(0)))
				.append(" from this tough course, and this shows how knowledgeable and hard working ").append(pronoun)
				.append(" is.\n\n");
		// If the student took more courses with this professor, the comma-separated
		// list of them plus the grades earned are listed in the following
		// paragraph
		// <He/She> also earned a/an "<letter grade>" from my "<course name>", a/an
		// "<letter grade>" from my "<course name>", ... <course/courses>.
		if (courses.size() > 1) {
			sb.append(uppercasePronoun).append(" also earned");
			int courseCount = courses.size();
			for (int i = 1; i < courseCount; i++) {
				sb.append(" ").append(indefiniteArticle(grades.get(courses.get(i)))).append(grades.get(courses.get(i)))
						.append(" from my ").append(courses.get(i));
				if (i < courseCount - 1) {
					sb.append(",");
				}
				if (i == courseCount - 2) {
					sb.append(" and");
				}
			}
			if (courseCount > 2) {
				sb.append(" courses.\n\n");
			} else
				sb.append(" course.\n\n");
		}
		// <Student's First Name> <Comma separated Academic Characteristics>.
		sb.append(firstName);
		int academicCount = academicCharacteristics.size();
		for (int i = 0; i < academicCount; i++) {
			sb.append(" ").append(academicCharacteristics.get(i));
			if (i < academicCount - 1) {
				sb.append(",");
			}
			if (i == academicCount - 2) {
				sb.append(" and");
			}
		}
		sb.append(".\n\n");

		// <He/She> was always <Comma separated Personal Characteristics>
		sb.append(uppercasePronoun).append(" was always");
		int personalCount = personalCharacteristics.size();
		for (int i = 0; i < personalCount; i++) {
			sb.append(" ").append(personalCharacteristics.get(i));
			if (i < personalCount - 1) {
				sb.append(",");
			}
			if (i == personalCount - 2) {
				sb.append(" and");
			}
		}
		sb.append(".\n\n");

		// Furthermore, I noticed from the term project result, <he/she> developed
		// leadership, time management, and problem-solving skills. <He/She> worked
		// effectively with the team members and delegated tasks appropriately. They
		// were able to deliver a successful project in a timely fashion.
		sb.append("Furthermore, I noticed from the term project result, ").append(pronoun)
				.append(" developed leadership, time management, and problem-solving skills. ").append(uppercasePronoun)
				.append(" worked effectively with the team members and delegated tasks appropriately. They were able to deliver a successful project in a timely fashion.\n\n");

		// I believe that <Student's First Name> has the capacity to excel at a higher
		// education program and that it is my pleasure to highly recommend him.
		sb.append("I believe that ").append(firstName).append(
				" has the capacity to excel at a higher education program and that it is my pleasure to highly recommend him.\n\n");

		sb.append("Please do not hesitate to contact me with further questions.\n\n");
		sb.append("Very Respectfully,\n");
		sb.append(professor).append("\n\n");
		sb.append(professorTitle).append("\n");
		sb.append(school).append(", ").append(department).append("\n");
		sb.append(email).append("\n");
		sb.append(phone);

		draft.setText(sb.toString());
	}

	// Indefinite article before grade.
	// an A
	// a B/C/D/F
	public String indefiniteArticle(String grade) {
		if (grade == "A")
			return "an ";
		return "a ";
	}

	// Save letter in database and exit to main menu
	public void saveButtonAction(ActionEvent e) throws IOException {
		// TODO: Save TextArea in database
		SceneController sceneController = new SceneController();
		sceneController.switchToMainMenuScene(e);
	}
}
