package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.jdbc.StringUtils;

import controller.AssignmentController;
import controller.QuestionController;
import controller.RubricController;
import core.Assignment;
import core.Rubric;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddQuestionController implements Initializable {
	
	@FXML
	private ChoiceBox<Assignment> assignmentChoiceBox;
	
	@FXML
	private ChoiceBox<Rubric> rubricChoiceBox;
	
	@FXML
	private TextField questionNameField;
	
	@FXML
	private TextField questionWeightField;

	
	private ObservableList<Assignment> assignmentChoiceBoxData = FXCollections.observableArrayList();
	
	private ObservableList<Rubric> rubricChoiceBoxData = FXCollections.observableArrayList();
	
	@FXML
	private void addQuestion() {
		if (assignmentChoiceBox.getSelectionModel().getSelectedItem() != null && rubricChoiceBox.getSelectionModel().getSelectedItem() != null && !StringUtils.isEmptyOrWhitespaceOnly(questionNameField.getText()) && !StringUtils.isEmptyOrWhitespaceOnly(questionWeightField.getText())) {
			QuestionController.addQuestion(questionNameField.getText(), new Double(questionWeightField.getText()).toString(), rubricChoiceBox.getSelectionModel().getSelectedItem(), assignmentChoiceBox.getSelectionModel().getSelectedItem());
			Stage stage = (Stage) assignmentChoiceBox.getScene().getWindow();
			stage.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assignmentChoiceBoxData = AssignmentController.initAssignmentDB();
		assignmentChoiceBox.setItems(assignmentChoiceBoxData);
		rubricChoiceBoxData = RubricController.initRubricDB();
		rubricChoiceBox.setItems(rubricChoiceBoxData);
	}

}
