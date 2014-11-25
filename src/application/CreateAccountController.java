package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountController implements Initializable, ControlledScreen{
	
	MySQLData insert = new MySQLData();
	
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
    void createAccount(ActionEvent event) {
    	
    	if (firstName.getText().equals("") || lastName.getText().equals("") || username.getText().equals("") || email.getText().equals("")
       		 || street.getText().equals("") || state.getText().equals("") || zip.getText().equals("") || password.getText().equals("") || cPassword.getText().equals("")){
       	 	ErrorLabel.setText("Please enter all fields!");
    	 }else if(insert.checkUserName(username.getText(),null) == true){
         	ErrorLabel.setText("Username Taken");
         } else if (!password.getText().equals(cPassword.getText())){
        	 ErrorLabel.setText("Passwords do not match!");
         } else {
         	insert.createPassengerAccount(firstName.getText(), lastName.getText(), username.getText(), password.getText(),
         	email.getText(), phone.getText(), street.getText(), city.getText(), state.getText(), zip.getText());
         	ErrorLabel.setText("Successfully Created Account!");
         	myController.setScreen(ScreensFramework.screen3ID);
         }
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
	
}