package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountController implements Initializable, ControlledScreen{
	
	ScreensController myController;

    @FXML
    private TextField zipcode;

    @FXML
    private TextField lName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField fName;

    @FXML
    private TextField phone;

    @FXML
    private TextField city;

    @FXML
    private PasswordField cPassword;

    @FXML
    private TextField street;

    @FXML
    private Button createButton;

    @FXML
    private TextField state;

    @FXML
    private Button logInButton;

    @FXML
    private TextField email;

    @FXML
    void createAccount(ActionEvent event) {

    }

    @FXML
    void logIn(ActionEvent event) {

    }

	@Override
	public void setScreenParent(ScreensController screenParent) {
		// TODO Auto-generated method stub
		myController = screenParent;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}