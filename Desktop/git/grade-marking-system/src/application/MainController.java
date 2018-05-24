package application;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import controller.AssignmentController;
import controller.CourseController;
import controller.QuestionController;
import controller.RubricController;
import controller.StudentController;
import core.Assignment;
import core.Course;
import core.Question;
import core.Rubric;
import core.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import other.ReadExcel;


public class MainController implements Initializable {
	
	//Variable for 'Student Data' page
	private ObservableList<Student> studentData = FXCollections.observableArrayList();
	
	@FXML
	private TextField studentFilterField;
	
	@FXML
	private TableView<Student> studentTable;
	
	@FXML
	private TableColumn<Student, String> studentIDCol;
	
	@FXML
	private TableColumn<Student, String> studentSurnameCol;
	
	@FXML
	private TableColumn<Student, String> studentFirstnameCol;
	
	@FXML
	private TableColumn<Student, String> studentGroupCol;
	
	@FXML
	private Button studentDataFileChooser;
	
	@FXML
	private Button studentDataSave;
	
	@FXML
	private Button studentDataClear;
	
	
	private ObservableList<Rubric> rubricData = FXCollections.observableArrayList();
	
	private ObservableList<Course> courses = FXCollections.observableArrayList();
	
	private ObservableList<Assignment> assignments = FXCollections.observableArrayList();
	
	private ObservableList<Question> questions = FXCollections.observableArrayList();
	
	
	@FXML
	private Button addRubric;
	
	
	@FXML
	private ListView<Rubric> rubricList;
	
	@FXML 
	private ListView<Course> courseList;
	
	@FXML 
	private ListView<Assignment> assignmentList;
	
	@FXML 
	private ListView<Question> questionList;

	
	
	//Function for 'Student Data'
	public void initStudentCol() {
		studentIDCol.setCellValueFactory(new PropertyValueFactory<Student, String>("ID"));
		studentSurnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname"));
		studentFirstnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("Firstname"));
		studentGroupCol.setCellValueFactory(new PropertyValueFactory<Student, String>("Group"));
	}
	
	@FXML
	private void studentSaveButtonClick(ActionEvent event) {
		
		StudentController.clearStudentDB();
		studentData.forEach((student) -> {
			StudentController.addStudentDB(student);
		});
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText("Save successfully");
		alert.initStyle(StageStyle.UTILITY);
		alert.showAndWait();
	}
	
	@FXML void studentClearButtonClick(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.initStyle(StageStyle.UTILITY);
		alert.setHeaderText(null);
		alert.setContentText("Are you sure with this?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			studentData.clear();
			studentTable.setItems(studentData);
		} else {
		    return;
		}
	}
	
	@FXML
	private void openFileChooser(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xls, *.xlsx)", "*.xls", "*.xlsx");
		fileChooser.setTitle("Select Excel File");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(studentDataFileChooser.getScene().getWindow());
		if (file != null) {
			try {
				studentData = ReadExcel.readStudentExcelFile(file);
				studentTable.setItems(studentData);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			return;
		}
	}
	
	@FXML
	private void addRubric(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.initStyle(StageStyle.UTILITY);
		dialog.setTitle("Please Input Name");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter Rubric name:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && !result.get().isEmpty()){
			Rubric rubric = new Rubric(result.get());
			RubricController.addRubricDB(rubric);
			initRubricPage();
		}
		else {
			return;
		}
	}

	
	private void initStudentDataPage() {
		studentData = StudentController.initStudentDB();
		initStudentCol();
		studentTable.setItems(studentData);
		
	}
	
	private void initRubricPage() {
		rubricData = RubricController.initRubricDB();
		rubricList.setItems(rubricData);
		
		
		rubricList.setCellFactory(lv -> {
			ListCell<Rubric> cell = new ListCell<Rubric>() {
				protected void updateItem(Rubric item, boolean empty) {
		            super.updateItem(item, empty);
		            setText(item == null ? "" : item.toString());
		        }
			};
			cell.setOnMouseClicked(e -> {
		        if (!cell.isEmpty()) {
		        	if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {
		        		Dialog<ButtonType> dialog = new Dialog<>();
		        		dialog.initOwner(studentDataFileChooser.getScene().getWindow());
		        		FXMLLoader fxmlLoader = new FXMLLoader();
		        		fxmlLoader.setLocation(getClass().getResource("RUBRICTABLE.fxml"));
		        		try {
		        	        Parent dialogContent = fxmlLoader.load();
		        	        application.RubricController RubricController = fxmlLoader.<application.RubricController>getController();
		        	        RubricController.setItem(rubricList.getSelectionModel().getSelectedItem());
		        	        dialog.getDialogPane().setContent(dialogContent);
		        	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

		        	    } catch (IOException e1) {
		        	        System.out.println("Couldn't load the dialog");
		        	        e1.printStackTrace();
		        	        return;
		        	    }
		        	    dialog.showAndWait();
					}
		        	else {
		        	}
		        }
		    });
		    return cell;
		});
	}
	
	
	public void initCourseAssignmentPage() {
		initListView();
	}
	
	@FXML
	public void openAddCourse() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(studentDataFileChooser.getScene().getWindow());
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("ADD_COURSE.fxml"));
		try {
	        Parent dialogContent = fxmlLoader.load();
	        dialog.getDialogPane().setContent(dialogContent);
	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
	    } catch (IOException e) {
	        System.out.println("Couldn't load the dialog");
	        e.printStackTrace();
	        return;
	    }
	    dialog.showAndWait();
	    initListView();
	}
	
	@FXML
	public void openAddAssignment() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(studentDataFileChooser.getScene().getWindow());
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("ADD_ASSIGNMENT.fxml"));
		try {
	        Parent dialogContent = fxmlLoader.load();
	        dialog.getDialogPane().setContent(dialogContent);
	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
	    } catch (IOException e) {
	        System.out.println("Couldn't load the dialog");
	        e.printStackTrace();
	        return;
	    }
	    dialog.showAndWait();
	    initListView();
	}
	
	@FXML
	public void openAddQuestion() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(studentDataFileChooser.getScene().getWindow());
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("ADD_QUESTION.fxml"));
		try {
	        Parent dialogContent = fxmlLoader.load();
	        dialog.getDialogPane().setContent(dialogContent);
	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
	    } catch (IOException e) {
	        System.out.println("Couldn't load the dialog");
	        e.printStackTrace();
	        return;
	    }
	    dialog.showAndWait();
	    initListView();
	}
	
	
	public void initListView() {
		courses = CourseController.initCourseDB();
		assignments = AssignmentController.initAssignmentDB();
		questions = QuestionController.initQuestionDB();
		courseList.setItems(courses);
		assignmentList.setItems(assignments);
		questionList.setItems(questions);
	}
	
	/*
	 * Initialize function
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Initialize 'Student Data'
		initStudentDataPage();
		
		//Initialize 'Rubric'
		initRubricPage();
		
		initCourseAssignmentPage();
		
	}

}
