package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class ManegerAccountPageController implements Initializable, ControlledScreen{
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

    }

    @FXML
    void SaveUserManeger(ActionEvent event) {

    }

    @FXML
    void SavePassword(ActionEvent event) {

    }

    @FXML
    void MyAccount(ActionEvent event) {

    }

    @FXML
    void SignOut(ActionEvent event) {

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
