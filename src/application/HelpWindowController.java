package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;


public class HelpWindowController implements Initializable{
	
	@FXML
	private MenuBar MenuItem;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	void helpWindowClose(){
		Stage stage = (Stage) MenuItem.getScene().getWindow();
		stage.close();
	}
	
	

}
