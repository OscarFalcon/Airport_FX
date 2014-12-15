package application;

import java.net.URL;
import java.util.ResourceBundle;
import core.Employee;
import core.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController implements Initializable, ControlledScreen{

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

    private ScreensController myController;


    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}


	
	 @FXML void signIn(ActionEvent event)
	 {
		 String username,passwordString;
		 
		 
		 username = userName.getText();
		 passwordString = password.getText();
		 
		 if(userName.equals("") || passwordString.equals(""))
		 {
			 error.setText("Please Enter all fields");
	    	 return;
		 }
	 
		 
		 Passenger passenger = Passenger.retrievePassenger(username, passwordString);
		 if(passenger != null )
		 {
			 myController.setScreen(ScreensFramework.screen3ID);
			 myController.setPassenger(passenger);
			 resetLabels();
			 return;
		 }
		 
		 Employee employee = Employee.retrieveEmployee(username, passwordString);
		 if(employee != null)
		 {
			 switch(employee.getType())
			 {
			 	case "manager":
			 		myController.setScreen(ScreensFramework.screen6ID);
			 		resetLabels();
			 		break;
			 }
			 resetLabels();
			 return;
			 
		 }
		 error.setText("Invalid Credentials");
		 
		 
		
	 
	 }
	
 
    @FXML
    void forgotPassword(ActionEvent event) {
    	//myController.setScreen(ScreensFramework.screen2ID);
    }

    @FXML
    void createAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen4ID);
    }

	private void resetLabels()
	{
		userName.setText("");
		password.setText("");
		error.setText("");
	}
		




}
