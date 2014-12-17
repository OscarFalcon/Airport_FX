package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManagerFlightConditionController implements Initializable, ControlledScreen{
	ScreensController myController;

    @FXML
    private TableColumn<?, ?> rPriceCol;

    @FXML
    private TableColumn<?, ?> rLeaveDateCol;

    @FXML
    private Button signOut;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<?, ?> rArriveSrcDateCol;

    @FXML
    private Button rescheduleReservation;

    @FXML
    private TableColumn<?, ?> rAirlineCol;

    @FXML
    private TableColumn<?, ?> rReturnDateCol;

    @FXML
    private TextField resevationField;

    @FXML
    private Button home;

    @FXML
    private Button search;

    @FXML
    private TableColumn<?, ?> rArriveDateCol;

    @FXML
    private Button cancelReservation;

    @FXML
    private TableView<?> rSearchResults;

    @FXML
    private Button myAccount;

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
    void Search(ActionEvent event) {

    }

    @FXML
    void CancelReservation(ActionEvent event) {

    }

    @FXML
    void RescheduleReservation(ActionEvent event) {

    }

	@Override
	public void setScreenParent(ScreensController screenParent) {
		// TODO Auto-generated method stub
		myController = screenParent;	
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respawn(HashMap<String, Object> arguments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
