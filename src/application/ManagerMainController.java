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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManagerMainController implements Initializable, ControlledScreen{
	ScreensController myController;

    @FXML
    private TableColumn<?, ?> oAirlineCol;

    @FXML
    private DatePicker rArrive;

    @FXML
    private Button oSearch;

    @FXML
    private Label rErrorLabel;

    @FXML
    private TableColumn<?, ?> rLeaveDateCol;

    @FXML
    private Button oneWayReserveFlightButton;

    @FXML
    private Button employee;

    @FXML
    private Button myAcc;

    @FXML
    private TableView<?> searchResults;

    @FXML
    private ChoiceBox<?> oPreferredClass;

    @FXML
    private TextField oPassenger;

    @FXML
    private DatePicker rDepart;

    @FXML
    private Label reservationSubmitLabel1;

    @FXML
    private Tab oDepartingPassFli;

    @FXML
    private TableColumn<?, ?> oPriceCol;

    @FXML
    private TableView<?> rSearchResults;

    @FXML
    private TableColumn<?, ?> oDepDateCol;

    @FXML
    private Button manegeVoucher;

    @FXML
    private TextField rPassenger;

    @FXML
    private Button rSearch;

    @FXML
    private ChoiceBox<?> rPreferredClass;

    @FXML
    private Button MakePay;

    @FXML
    private ChoiceBox<?> oFlyFrom;

    @FXML
    private DatePicker oDepart;

    @FXML
    private TableColumn<?, ?> rPriceCol;

    @FXML
    private ChoiceBox<?> oFlyTo;

    @FXML
    private Button signOut;

    @FXML
    private ChoiceBox<?> rFlyTo;

    @FXML
    private TableColumn<?, ?> rArriveSrcDateCol;

    @FXML
    private Button flightCond;

    @FXML
    private TableColumn<?, ?> rAirlineCol;

    @FXML
    private TableColumn<?, ?> rReturnDateCol;

    @FXML
    private TableColumn<?, ?> oArrDateCol;

    @FXML
    private TableColumn<?, ?> rArriveDateCol;

    @FXML
    private Label oErrorLabel;

    @FXML
    private Label reservationSubmitLabel;

    @FXML
    private Button roundTripReserveFlightButton;

    @FXML
    private ChoiceBox<?> rFlyFrom;

    @FXML
    private Button boarding;

    @FXML
    private Tab RoundTPassFli;

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
