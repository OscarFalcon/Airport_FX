package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class PassengerFlightSearchController implements Initializable, ControlledScreen{
	ScreensController myController;

    @FXML
    private TextField owPassNumPassFli;

    @FXML
    private DatePicker rReturningPassFli;

    @FXML
    private Button rSearchButtPassFli;

    @FXML
    private TextField owClassPassFli;

    @FXML
    private DatePicker oDeparting;

    @FXML
    private Button myTripButtonPassFli;

    @FXML
    private CheckBox oNonStopPassFli;

    @FXML
    private Label rReturning;

    @FXML
    private TextField rFlyingToPassFli;

    @FXML
    private Button signOutButtonPassFli;

    @FXML
    private TextField classPassFli;

    @FXML
    private TextField oFlyingFrom;

    @FXML
    private Button myAccountButtonPassFli;

    @FXML
    private TextField rFlyingFromPassFli;

    @FXML
    private DatePicker rDepartingPassFli;

    @FXML
    private Tab oDepartingPassFli;

    @FXML
    private TextField orFlyingTo;

    @FXML
    private CheckBox rNonStopPassFli;

    @FXML
    private TextField pasnumPassFli;

    @FXML
    private Button oSearchButtPassFli;

    @FXML
    private Tab RoundTPassFli;

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
