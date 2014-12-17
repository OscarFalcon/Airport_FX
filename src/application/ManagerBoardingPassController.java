package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ManagerBoardingPassController implements Initializable, ControlledScreen{
	ScreensController myController;

    @FXML
    private Label fromLabel;

    @FXML
    private Label toLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label departureLebal;

    @FXML
    private Label flgihtLebal;

    @FXML
    private Label seatLebal;

    @FXML
    private Button home;

    @FXML
    private Label nameLabel;

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
    void ViewBoardingPass(ActionEvent event)
    {
    	System.out.println("actionevent");
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
