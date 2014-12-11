package application;
import java.net.URL;
import java.util.ResourceBundle;

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
    	Person person;
    	if(userName.getText().equals("") || password.getText().equals(""))
    	{
    		error.setText("Please Enter all fields");
    	}
    	else if( ((person = Person.retrievePerson(userName.getText(), password.getText())) != null))
    	{
    		error.setText("Login Successful");
    		myController.setPerson(person);
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

    @FXML
    void forgotPassword(ActionEvent event) {
    	//myController.setScreen(ScreensFramework.screen2ID);
    }

    @FXML
    void createAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen4ID);
    }

}