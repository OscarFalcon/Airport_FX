package application;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import core.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	MySQLData fetch = new MySQLData();

	@FXML
    private ChoiceBox<String> rPreferredClass;

    @FXML
    private ChoiceBox<String> oFlyFrom;

    @FXML
    private DatePicker oDepart;

    @FXML
    private Button oSearch;

    @FXML
    private Label rErrorLabel;

    @FXML
    private Button myTripButtonPassFli;

    @FXML
    private ChoiceBox<String> oFlyTo;

    @FXML
    private ChoiceBox<String> rFlyTo;

    @FXML
    private TableView<String> searchResults;

    @FXML
    private Label HeaderLabel;

    @FXML
    private ChoiceBox<String> oPreferredClass;

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
    private ChoiceBox<String> rFlyFrom;

    @FXML
    private DatePicker rArrive;

    @FXML
    private TextField rPassenger;

    @FXML
    private Tab RoundTPassFli;

    @FXML
    private Button rSearch;

    @FXML
    void rSearchAction(ActionEvent event) {
    	//Debug info
    	System.out.println("RoundTrip Seach Action");
    	System.out.println("SRC: " + rFlyFrom.getValue());
    	System.out.println("DES: " + rFlyTo.getValue());
    	System.out.println("LEAVE DATE: " + Date.valueOf(rDepart.getValue()).toString());
    	System.out.println("AFTER LEAVE DATE");
    	System.out.println("ARR DATE: " + Date.valueOf(rArrive.getValue()).toString());

    	
    	ObservableList<Flight> flightList;
    	flightList = fetch.searchFlightRoundTrip(rFlyFrom.getValue(), rFlyTo.getValue(), Date.valueOf(rDepart.getValue()), Date.valueOf(rArrive.getValue()));
    	System.out.println("searchFlightRoundTrip");
    	for(int i = 0; i < flightList.size(); i++)
    	{
	    	System.out.println("Airline: " + flightList.get(i).getAirline());
			System.out.println("Arrival Date: " + flightList.get(i).getArrivalDate());
			System.out.println("Arrival Time: " + flightList.get(i).getArrivalTime());
			System.out.println("Departure Date: " + flightList.get(i).getDeptDate());
			System.out.println("Departure Time: " + flightList.get(i).getDeptTime());
			System.out.println("Destination Location: " + flightList.get(i).getDestinationLocation());
			System.out.println("Source Location: " + flightList.get(i).getSrcLocation());
			System.out.println("Flight ID: " + flightList.get(i).getFlightId());
			System.out.println("Flight Number: " + flightList.get(i).getFlightNumber());
			System.out.println("Flight Price: " + flightList.get(i).getFlightPrice());
    	}
    }

    @FXML
    void oSearchAction(ActionEvent event) {
    	System.out.println("One Way Trip Seach Action");

    	//To do fix dates.
    	ObservableList<Flight> flightList;
    	flightList = fetch.searchFlightOneWay(oFlyFrom.getValue(), oFlyTo.getValue(), Date.valueOf(oDepart.getValue()));
    	for(int i = 0; i < flightList.size(); i++)
    	{
	    	System.out.println("Airline: " + flightList.get(i).getAirline());
			System.out.println("Arrival Date: " + flightList.get(i).getArrivalDate());
			System.out.println("Arrival Time: " + flightList.get(i).getArrivalTime());
			System.out.println("Departure Date: " + flightList.get(i).getDeptDate());
			System.out.println("Departure Time: " + flightList.get(i).getDeptTime());
			System.out.println("Destination Location: " + flightList.get(i).getDestinationLocation());
			System.out.println("Source Location: " + flightList.get(i).getSrcLocation());
			System.out.println("Flight ID: " + flightList.get(i).getFlightId());
			System.out.println("Flight Number: " + flightList.get(i).getFlightNumber());
			System.out.println("Flight Price: " + flightList.get(i).getFlightPrice());
    	}
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
		oFlyFrom.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX"));
		oFlyTo.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX"));
		rFlyFrom.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX"));
		rFlyTo.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX"));
		rPreferredClass.setItems(FXCollections.observableArrayList("First Class","Coach"));
		oPreferredClass.setItems(FXCollections.observableArrayList("First Class","Coach"));
	}

}
