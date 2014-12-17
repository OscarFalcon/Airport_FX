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
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class CustomerAccountPageController implements Initializable, ControlledScreen{
	ScreensController myController;
    /**
     * Initializes the controller class.
     */
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }
    
  

    @FXML
    private TextField accountCity;

    @FXML
    private TextField accountState;

 
    @FXML
    private Label passwordErrorLabel;

    @FXML
    private TextField accountFirstName;

    @FXML
    private PasswordField newPassword1;

    @FXML
    private Label AOerrorLabel;

    @FXML
    private TextField accountLastName;

    @FXML
    private PasswordField newPassword2;

    @FXML
    private TextField accountStreet;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private Label usernameErrorLabel;

    @FXML
    private TextField accountZip;

    @FXML
    private Button MyTripButtCusAcc;

    @FXML
    private Button MyAccButtCusAcc;

    @FXML
    private TextField accountEmail;

    @FXML
    private TextField accountPhone;

    @FXML
    private TextField accountUsername;

    @FXML
    private Button searchFlightButton;

    @FXML
    private Tab SignInCusAcc;

    @FXML
    private Button SignoutCusAcc;

    @FXML
    void saveAO(ActionEvent event)
    {
    	Passenger passenger;
    	String first,last,email,phone,street,city,state,zip;
    	
    	passenger = myController.getPassenger();
    	
    	first = accountFirstName.getText();
    	last = accountLastName.getText();
    	email = accountEmail.getText();
    	phone = accountPhone.getText();
    	street = accountStreet.getText();
    	city = accountCity.getText();
    	state = accountState.getText();
    	zip = accountZip.getText();
    	
    	passenger.setFirstName(first);
    	passenger.setLastName(last);
    	passenger.setEmail(email);
    	passenger.setPhone(phone);
    	passenger.setStreet(street);
    	passenger.setCity(city);
    	passenger.setState(state);
    	passenger.setZip(zip);
    	
    	if(passenger.save())
    	{
    		AOerrorLabel.setText("Successfully changed Account Information!");

    	}
    	else
    	{
    		AOerrorLabel.setText("Opps something went wrong");
    	}
    	
    	
    	
    	return;
    	
    	
    }
    
    @FXML
    void changeUsername(ActionEvent event)
    {
    	if(myController.getPassenger().changeUsername(accountUsername.getText()))
    	{
    		usernameErrorLabel.setText("Successfully changed Username!");
    	}
    	else
    	{
    		usernameErrorLabel.setText("Opps something went wrong!");
    	}
    	return;
    }

    @FXML
    void changePassword(ActionEvent event) {
    	Passenger passenger = myController.getPassenger();
    	
    	if (oldPassword.getText().equals(""))
    	{
    		passwordErrorLabel.setText("Please enter current password!");
    	} 
    	else if(newPassword1.getText().equals("") || newPassword2.getText().equals(""))
    	{
    		passwordErrorLabel.setText("Please enter new password!");
    	} 
    	else if (!newPassword1.getText().equals(newPassword2.getText()))
    	{
    		passwordErrorLabel.setText("passwords do not match");
    		
    	} 
    	else if(passenger.checkPassword(oldPassword.getText()) == false)
    	{
    		passwordErrorLabel.setText("Incorrect Password");
    	}
    	else if(passenger.changePassword(newPassword1.getText()) == false)
    	{
    		passwordErrorLabel.setText("Oops something went wrong");
    	}
    	else
    	{
    		passwordErrorLabel.setText("Successfully changed password");
    	}
    	
    }
    
    @FXML 
    void searchFlight(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen3ID);	
    }

    @FXML
    void myAccount(ActionEvent event) {
    	//DoNothing
    }

    @FXML
    void myTrip(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen5ID);
    }

    @FXML
    void signOut(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen1ID);
    }

	@Override
	public void setScreenParent(ScreensController screenParent) {
		// TODO Auto-generated method stub
		 myController = screenParent;
		
	}

	@Override
	public void reset() {
		AOerrorLabel.setText("");
    	usernameErrorLabel.setText("");
    	passwordErrorLabel.setText("");
    	oldPassword.setText("");
    	newPassword1.setText("");
    	newPassword2.setText("");

    	
    	accountFirstName.setText(myController.getPassenger().getFirstName());
    	accountLastName.setText(myController.getPassenger().getLastName());
    	accountEmail.setText(myController.getPassenger().getEmail());
    	accountPhone.setText(myController.getPassenger().getPhone());
    	accountStreet.setText(myController.getPassenger().getStreet());
    	accountCity.setText(myController.getPassenger().getCity());
    	accountState.setText(myController.getPassenger().getState());
    	accountZip.setText(myController.getPassenger().getZip());
    	
    	accountUsername.setText(myController.getPassenger().getUserName());
	}

	
	public void respawn(HashMap<String, Object> arguments) {
		// TODO Auto-generated method stub
		
	}

}
