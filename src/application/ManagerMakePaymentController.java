package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ManagerMakePaymentController implements Initializable, ControlledScreen{
	ScreensController myController;

    @FXML
    private TextField expirationDay;

    @FXML
    private TextField creditCardNumber;

    @FXML
    private TextField cardIdenticationNumber;

    @FXML
    private TextField cardholderName;

    @FXML
    private TextField name;

    @FXML
    private Button signout;

    @FXML
    private TextField reservationNumber;

    @FXML
    private TextField billingZipCode;

    @FXML
    private Button pay;

    @FXML
    private Button myAccount;

    @FXML
    private Button home;

    @FXML
    void Home(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen6ID);
    }

    @FXML
    void MyAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen10ID);
    }

    @FXML
    void SignOut(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen1ID);
    }

    @FXML
    void Pay(ActionEvent event) {
    	//myController.setScreen(ScreensFramework.screen1ID);
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

}
