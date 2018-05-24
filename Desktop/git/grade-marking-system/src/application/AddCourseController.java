package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.jdbc.StringUtils;

import controller.CourseController;
import core.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddCourseController implements Initializable  {
	
	@FXML
	private TextField courseNameField;
	
	@FXML
	private void addCourse() {
		if (!StringUtils.isEmptyOrWhitespaceOnly(courseNameField.getText())) 
		{
			CourseController.addCourse(new Course(courseNameField.getText()));
			Stage stage = (Stage) courseNameField.getScene().getWindow();
			stage.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub

	}

}
