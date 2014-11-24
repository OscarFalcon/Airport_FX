package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MyTripController implements Initializable, ControlledScreen{
	ScreensController myController;
	@FXML
	private Button searchFlightButton;
	
    @FXML
    private Button signOut;

    @FXML
    private Button myAccount;

    @FXML
    private Button mytrip;
    
    @FXML 
    void searchFlight(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen3ID);
    	
    }

    @FXML
    void myAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen2ID);
    }

    @FXML
    void myTrip(ActionEvent event) {
    	

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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
