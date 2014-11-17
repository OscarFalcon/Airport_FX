package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class CustomerAccountPageController implements Initializable, ControlledScreen{
	ScreensController myController;
    /**
     * Initializes the controller class.
     */
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private Tab AccOverCusAcc;

    @FXML
    private TextField accountOverPh;

    @FXML
    private TextField SignInNewCusAcc;

    @FXML
    private TextField SignInEmailCusAcc;

    @FXML
    private TextField accountOverNa;

    @FXML
    private TextField accountOverStr;

    @FXML
    private Button MyTripButtCusAcc;

    @FXML
    private TextField accountOverState;

    @FXML
    private Button MyAccButtCusAcc;

    @FXML
    private TextField accountOverEm;

    @FXML
    private TextField SignInOldCusAcc;

    @FXML
    private Button SignInSaveEmailCusAcc;

    @FXML
    private Button SignInSavePassCusAcc;

    @FXML
    private TextField SignInReCusAcc;

    @FXML
    private Tab SignInCusAcc;

    @FXML
    private Button SignoutCusAcc;

    @FXML
    private TextField accountOverCi;

    @FXML
    private TextField accountOverZip;

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void myAccount(ActionEvent event) {
    	//myController.setScreen(ScreensFramework.screen1ID);

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

}
