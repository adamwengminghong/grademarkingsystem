package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.jdbc.StringUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


public class LoginController implements Initializable {
	
	@FXML
	private TextField userName;
	
	@FXML
	private TextField passWord;
	
	@FXML
	private void LOGIN(ActionEvent event) {
		if (StringUtils.isEmptyOrWhitespaceOnly(userName.getText()) || StringUtils.isEmptyOrWhitespaceOnly(passWord.getText())) {
			return;
		} else {
			if (userName.getText().equals("Judy") && passWord.getText().equals("Judy")) {
				Start.getInstance().mainPage();
			} else {
				return;
			}
			
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
