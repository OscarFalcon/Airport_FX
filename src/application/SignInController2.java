package application;
import java.net.URL;
import java.util.ResourceBundle;

import core.Employee;
import core.Passenger;
import core.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController2 implements Initializable, ControlledScreen{
	ScreensController myController;
	
    /**
     * Initializes the controller class.
     */
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	error.setText("");
        // TODO
    }
    
    @FXML
    private Label error;
    
    @FXML
    private PasswordField password;

    @FXML
    private Button forgotBotton;

    @FXML
    private TextField userName;

    @FXML
    private Button createAcc;

    @FXML
    private Button signInButton;
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
        
    @FXML
    void signIn(ActionEvent event) {
    	Passenger passenger;
    	Employee employee;
    	Person person;

    	    	
    	if( ((person = Person.retrievePerson(userName.getText(), password.getText())) != null));
    	
    	if(userName.getText().equals("") || password.getText().equals(""))
    	{
    		error.setText("Please Enter all fields");
    	}
    	

    	// Employee login
    	else if( ((employee = Employee.retrieveEmployee(userName.getText(), password.getText())) != null))
    	{
        	
    		
       		// need to setStatus and setAvailability
    		String employeeType = employee.getType();
    		switch(employeeType)
    		{
    		case "manager": 		employeeType = "manager";
    		loginSuccessful(person,employee);
    		myController.setScreen(ScreensFramework.screen6ID);
    		break;
    		case "receptionist":	employeeType = "receptionist";
    		loginSuccessful(person,employee);
    		myController.setScreen(ScreensFramework.screen8ID);
    		break;
    		case "bagger": 			employeeType = "bagger";
    		break;
    		case "sysadmin": 		employeeType = "sysAdmin";			
    		}
    		
    	}
    	
    	// Passenger login

    	else if( ((passenger = Passenger.retrievePassenger(userName.getText(), password.getText())) != null))
    	{
    		
    		error.setText("Login Successful -- Passenger");
    		myController.setPerson(passenger);
    		myController.setScreen(ScreensFramework.screen3ID);
    		userName.setText("");
    		password.setText("");
    	}
    	
    	else 
    	{
    		error.setText("Login Failed");
    		userName.setText("");
    		password.setText("");
    	}
    	
    	
    }
    /* helper method for employee login */
    public void loginSuccessful(Person person, Employee employee)
    {
    	error.setText("Login Successful -- Employee");
		myController.setPerson(employee);
		//employee.setAvailability('1'));
		userName.setText("");
		password.setText("");
    }
    
    @FXML
    void forgotPassword(ActionEvent event) {
    	//myController.setScreen(ScreensFramework.screen2ID);
    }

    @FXML
    void createAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen4ID);
    }

}