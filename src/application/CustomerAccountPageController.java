package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

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
    private Tab AccOverCusAcc;

    @FXML
    private TextField accountCity;

    @FXML
    private TextField accountState;

    @FXML
    private Button saveAO;

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
    private Button saveUsername;

    @FXML
    private Button SignInSavePassCusAcc;

    @FXML
    private TextField accountUsername;

    @FXML
    private Button searchFlightButton;

    @FXML
    private Tab SignInCusAcc;

    @FXML
    private Button SignoutCusAcc;

    @FXML
    void saveAO(ActionEvent event) {
    	AOerrorLabel.setText("Successfully changed Account Information!");
    }
    
    @FXML
    void changeUsername(ActionEvent event) {
    	usernameErrorLabel.setText("Successfully changed Username!");
    }

    @FXML
    void changePassword(ActionEvent event) {
    	if (oldPassword.getText().equals("")){
    		passwordErrorLabel.setText("Please enter Old Password!");
    	} else if(newPassword1.getText().equals("") || newPassword2.getText().equals("")){
    		passwordErrorLabel.setText("Please enter new password!");
    	} else if (!newPassword1.getText().equals(newPassword2.getText())){
    		passwordErrorLabel.setText("Passwords do not match!");
    	/*} else if(!update.authorizeUser(myController.getPassenger().getUserName(), oldPassword.getText())){ 
    		passwordErrorLabel.setText("Wrong Password");
    	}else if (oldPassword.getText().equals(newPassword1.getText())) { 
    		passwordErrorLabel.setText("");
    		*/
    	} else {
    		/*
    		if(!myController.getPassenger().resetPassword(newPassword1.getText())){
    			passwordErrorLabel.setText("Could not change Password");
    		}
    		else passwordErrorLabel.setText("Successfully changed Password!");
    		*/
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

	@Override
	public void respawn(HashMap<String, Object> arguments) {
		// TODO Auto-generated method stub
		
	}

}
