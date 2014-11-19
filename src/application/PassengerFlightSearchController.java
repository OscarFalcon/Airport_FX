package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PassengerFlightSearchController implements Initializable, ControlledScreen{
	ScreensController myController;

    @FXML
    private ChoiceBox<?> rPreferredClass;

    @FXML
    private ChoiceBox<?> oFlyFrom;

    @FXML
    private DatePicker oDepart;

    @FXML
    private Button oSearch;

    @FXML
    private Label rErrorLabel;

    @FXML
    private Button myTripButtonPassFli;

    @FXML
    private ChoiceBox<?> oFlyTo;

    @FXML
    private ChoiceBox<?> rFlyTo;

    @FXML
    private TableView<?> searchResults;

    @FXML
    private Label HeaderLabel;

    @FXML
    private ChoiceBox<?> oPreferredClass;

    @FXML
    private Button signOutButtonPassFli;

    @FXML
    private TextField oPassenger;

    @FXML
    private Button myAccountButtonPassFli;

    @FXML
    private DatePicker rDepart;

    @FXML
    private Label oErrorLabel;

    @FXML
    private Tab oDepartingPassFli;

    @FXML
    private ChoiceBox<?> rFlyFrom;

    @FXML
    private DatePicker oArrive;

    @FXML
    private TextField rPassenger;

    @FXML
    private Tab RoundTPassFli;

    @FXML
    private Button rSearch;

    @FXML
    void rSearchAction(ActionEvent event) {

    }

    @FXML
    void oSearchAction(ActionEvent event) {

    }

    @FXML
    void myAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen2ID);
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
