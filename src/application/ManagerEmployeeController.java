package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import core.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class ManagerEmployeeController {

	ScreensController myController;


    @FXML
    private TableColumn<Employee, String> availabilityCol;

    @FXML
    private TableColumn<Employee, String> statusCol;

    @FXML
    private TableColumn<Employee, String> jobTitleCol;

    @FXML
    private TableColumn<Employee, String> nameCol;

    @FXML
    private Button signout;

    @FXML
    private Button changeStatus;

    @FXML
    private Button myAccount;

    @FXML
    private Button home;

    public void getEmployees()
    {
    	Employee employee = Employee.retrieveEmployee("birdman", "password");
    	
    }
    
    
    
    
    
    @FXML
    void Home(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen6ID);
    }

    @FXML
    void MyAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen10ID);
    }

    @FXML
    void Signout(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen1ID);
    }

	
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
		
	}

	
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	
	public void respawn(HashMap<String, Object> arguments) {
		// TODO Auto-generated method stub
		
	}

}

