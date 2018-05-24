package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.jdbc.StringUtils;

import controller.AssignmentController;
import controller.CourseController;
import core.Assignment;
import core.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAssignmentController implements Initializable {
	
	private ObservableList<Course> courseChoiceBoxData = FXCollections.observableArrayList();
	
	@FXML
	private ChoiceBox<Course> courseChoiceBox;
	
	@FXML
	private TextField assignmentNameField;
	
	@FXML
	private TextField assignmentWeightField;
	
	@FXML
	private void addAssignment() {
		if (courseChoiceBox.getSelectionModel().getSelectedItem() != null && !StringUtils.isEmptyOrWhitespaceOnly(assignmentNameField.getText()) && !StringUtils.isEmptyOrWhitespaceOnly(assignmentWeightField.getText())) {
			AssignmentController.addAssignment(courseChoiceBox.getSelectionModel().getSelectedItem(), new Assignment(courseChoiceBox.getSelectionModel().getSelectedItem().getID(), assignmentNameField.getText(), new Double(assignmentWeightField.getText()).toString()));
			Stage stage = (Stage) courseChoiceBox.getScene().getWindow();
			stage.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		courseChoiceBoxData = CourseController.initCourseDB();
		courseChoiceBox.setItems(courseChoiceBoxData);
	}

}
