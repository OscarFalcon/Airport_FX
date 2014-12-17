package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import core.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountController implements Initializable, ControlledScreen{
	
	ScreensController myController;

	@FXML
	private Label ErrorLabel;
	
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;
    
    @FXML
    private TextField username;

    @FXML
    private TextField email;
    
    @FXML
    private TextField street;
    
    @FXML
    private TextField city;
    
    @FXML
    private TextField state;
    
    @FXML
    private TextField zip;
    
    @FXML
    private TextField phone;

    @FXML
    private PasswordField password;
    
    @FXML
    private PasswordField cPassword;
    
    @FXML
    private Button createButton;

    @FXML
    private Button logInButton;
    
    @FXML
    void createAccount(ActionEvent event)
    {
    
        	Passenger passenger = new Passenger(null,firstName.getText(),lastName.getText(),username.getText(),password.getText(),email.getText(),phone.getText(),street.getText(),city.getText(),state.getText(),zip.getText());
        	passenger.insert();
         	ErrorLabel.setText("Successfully Created Account!");
         	myController.setScreen(ScreensFramework.screen1ID);
        
    }

    @FXML
    void logIn(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen1ID);
    }

	public void setScreenParent(ScreensController screenParent) {
		// TODO Auto-generated method stub
		myController = screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respawn(HashMap<String, Object> arguments) {
		// TODO Auto-generated method stub
		
	}
	
}