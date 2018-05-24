package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.jdbc.StringUtils;

import controller.RubricItemController;
import core.Rubric;
import core.RubricItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class RubricController implements Initializable {
	
	private Rubric rubric;
	
	@FXML
	private TableView<RubricItem> rubricItemTable;
	
	@FXML
	private TableColumn<RubricItem, String> itemIDCol;
	
	@FXML
	private TableColumn<RubricItem, String> itemNameCol;
	
	@FXML
	private TableColumn<RubricItem, String> itemWeight;
	
	@FXML
	private TextField itemName;
	
	@FXML
	private TextField sliderValue;
	
	private ObservableList<RubricItem> rubricItemData = FXCollections.observableArrayList();
	
	
	public void initRubricItemCol() {
		itemIDCol.setCellValueFactory(new PropertyValueFactory<RubricItem, String>("rubricID"));
		itemNameCol.setCellValueFactory(new PropertyValueFactory<RubricItem, String>("Name"));
		itemWeight.setCellValueFactory(new PropertyValueFactory<RubricItem, String>("Weight"));
	}
	
	@FXML
	private void addRubricItem(ActionEvent event) {
		if (!StringUtils.isEmptyOrWhitespaceOnly(itemName.getText()) && !sliderValue.getText().equals("")) {
			rubricItemData.add(new RubricItem(rubric.getID(), itemName.getText(), sliderValue.getText()));
			RubricItemController.addRubricDB(rubric, itemName.getText(), sliderValue.getText());
            rubricItemTable.setItems(rubricItemData);
		}
	}
	
	
	
	public void setItem(Rubric rubric) {
		initRubricItemCol();
		this.rubric = rubric;
	    System.out.println(rubric);
	    rubricItemData = RubricItemController.initRubricDB(rubric);
        rubricItemTable.setItems(rubricItemData);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
