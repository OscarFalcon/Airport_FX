package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import core.Passenger;
import core.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ResetPasswordController  implements Initializable, ControlledScreen{
	ScreensController myController;
	
	  @FXML
	    private Button cancel;

	    @FXML
	    private Button save;

	    @FXML
	    private PasswordField newPassword;

	    @FXML
	    private PasswordField confirmPassword;

	    @FXML
	    private TextField emailTextfield;

	    @FXML
	    private Label error;

	    @FXML
	    private TextField usernameTextfield;

	  

    @FXML
    void Save(ActionEvent event) {
       String username = usernameTextfield.getText();
  	   String email = emailTextfield.getText();
  	   String password = newPassword.getText();
  	   String cpassword = confirmPassword.getText();
  	   
  	   if(username.equals("") || email.equals("") || password.equals("") || cpassword.equals(""))
  	   {
  		   error.setText("Please Enter all fields");
  		   return;
  	   }
  	   

  		   Passenger passenger = Passenger.retrievePassengerByEmail(username, email);
  		   if(passenger != null)
  		   {
  			   passenger.changePassword(password);
  			   myController.setScreen(ScreensFramework.screen1ID);
  		   }
  		   if(password != cpassword)
  		   {
  			   error.setText("Passwords did not match");
  	   			resetLabel();
  		   }
  	   	
    }

    @FXML
    void Cancel(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen1ID);

    }

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respawn(HashMap<String, Object> arguments) {
		// TODO Auto-generated method stub
		
	}
	public void resetLabel()
	{
		error.setText("");
	}

}
