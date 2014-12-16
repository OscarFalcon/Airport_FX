package application;

import java.net.URL;
import java.util.ResourceBundle;

import airline.Solution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManagerMainController implements Initializable, ControlledScreen{
	ScreensController myController;

	 @FXML
	    private TableColumn<Solution, String> oAirlineCol;

	    @FXML
	    private DatePicker rArrive;

	    @FXML
	    private Button oSearch;

	    @FXML
	    private Label rErrorLabel;

	    @FXML
	    private TableColumn<Solution, String> rLeaveDateCol;

	    @FXML
	    private Button oneWayReserveFlightButton;

	    @FXML
	    private Button employee;

	    @FXML
	    private Button myAcc;
	    
	    @FXML
	    private Button manegeVoucher;

	    @FXML
	    private Button rSearch;
	    
	    @FXML
	    private Button MakePay;
	    
	    @FXML
	    private Button boarding;
	    
	    @FXML
	    private Button signOut;
	    
	    @FXML
	    private Button flightCond;

	    @FXML
	    private Button roundTripReserveFlightButton;
	    
	    @FXML
	    private TableView<Solution> searchResults;

	    @FXML
	    private ChoiceBox<String> oPreferredClass;

	    @FXML
	    private TextField oPassenger;

	    @FXML
	    private DatePicker rDepart;

	    @FXML
	    private Label reservationSubmitLabel1;

	    @FXML
	    private Tab oDepartingPassFli;

	    @FXML
	    private TableColumn<Solution, String> oPriceCol;

	    @FXML
	    private TableView<Solution> rSearchResults;

	    @FXML
	    private TableColumn<Solution, String> oDepDateCol;

	    @FXML
	    private TextField rPassenger;

	    @FXML
	    private ChoiceBox<String> rPreferredClass;

	    @FXML
	    private ChoiceBox<String> oFlyFrom;

	    @FXML
	    private DatePicker oDepart;

	    @FXML
	    private TableColumn<Solution, String> rPriceCol;

	    @FXML
	    private ChoiceBox<String> oFlyTo;

	    @FXML
	    private ChoiceBox<String> rFlyTo;

	    @FXML
	    private TableColumn<Solution, String> rArriveSrcDateCol;


	    @FXML
	    private TableColumn<Solution, String> rAirlineCol;

	    @FXML
	    private TableColumn<Solution, String> rReturnDateCol;

	    @FXML
	    private TableColumn<Solution, String> oArrDateCol;

	    @FXML
	    private TableColumn<Solution, String> rArriveDateCol;

	    @FXML
	    private Label oErrorLabel;

	    @FXML
	    private Label reservationSubmitLabel;

	    @FXML
	    private ChoiceBox<String> rFlyFrom;

	    @FXML
	    private Tab RoundTPassFli;

	    @FXML
	    private Label HeaderLabel;
	    
	    
	    
	
	

    @FXML
    void FlightCondition(ActionEvent event) {

    }

    @FXML
    void MakePayment(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen7ID);
    }

    @FXML
    void BoardingPass(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen11ID);
    }

    @FXML
    void ManegeVoucher(ActionEvent event) {
    	//myController.setScreen(ScreensFramework.screen1ID);
    }

    @FXML
    void Employee(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen12ID);
    }

    @FXML
    void SignOut(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen1ID);
    }

    @FXML
    void MyAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen10ID);
    }

    @FXML
    void rSearchAction(ActionEvent event) {
    	//myController.setScreen(ScreensFramework.screen1ID);
    }

    @FXML
    void oSearchAction(ActionEvent event) {

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
