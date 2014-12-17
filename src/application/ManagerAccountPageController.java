package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import core.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class ManagerAccountPageController implements Initializable, ControlledScreen{
	ScreensController myController;

    @FXML
    private Tab AccOverCusAcc;

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
    private Button save;

    @FXML
    private Button signout;

    @FXML
    private TextField accountZip;

    @FXML
    private Button saveUserManeger;

    @FXML
    private Button savePassword;

    @FXML
    private Label usernameErrorLabel;

    @FXML
    private TextField accountEmail;

    @FXML
    private TextField accountPhone;

    @FXML
    private TextField accountUsername;

    @FXML
    private Tab SignInCusAcc;

    @FXML
    private Button myAccount;

    @FXML
    void Save(ActionEvent event) {
    Employee employee = myController.getEmployee(); 
     
     employee.setFirstName(accountFirstName.getText());
     employee.setLastName(accountLastName.getText());
     employee.setPassword(oldPassword.getText());
     employee.setPassword(newPassword1.getText());
     employee.setPassword(newPassword2.getText());
     employee.setEmail(accountEmail.getText());
     employee.setPhone(accountPhone.getText());
     employee.setStreet(accountStreet.getText());
     employee.setCity(accountCity.getText());
     employee.setState(accountState.getText());
     employee.setZip(accountZip.getText());
     
                
     employee.insert();
    }

    @FXML
    void SaveUserManeger(ActionEvent event) {

    }

    @FXML
    void SavePassword(ActionEvent event) {
      
    }

    @FXML
    void MyAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen6ID);
    }

    @FXML
    void SignOut(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen1ID);
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

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respawn(HashMap<String, Object> arguments) {
		// TODO Auto-generated method stub
		
	}

}
