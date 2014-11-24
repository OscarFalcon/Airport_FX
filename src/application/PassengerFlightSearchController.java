package application;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import core.Flight;
import core.Person;

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
    private TableView<Flight> searchResults;
    
    @FXML
    private TableColumn<Flight,String> oAirlineCol;
   
    @FXML
    private TableColumn<Flight,String> oDepDateCol;
    
    @FXML
    private TableColumn<Flight,String> oArrDateCol;
    
    @FXML
    private TableColumn<Flight,String> oPriceCol;
    
    @FXML
    private TableView<Flight> rSearchResults;
    
    @FXML
    private TableColumn<Flight,String> rAirlineCol;
    
    @FXML
    private TableColumn<Flight,String> rLeaveDateCol;
    
    @FXML
    private TableColumn<Flight,String> rArriveDateCol;
    
    @FXML
    private TableColumn<Flight,String> rReturnDateCol;
    
    @FXML
    private TableColumn<Flight,String> rArriveSrcDateCol;
    
    @FXML
    private TableColumn<Flight,String> rPriceCol;
        
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
    private Button searchFlightButton;

    
    @FXML
    void rSearchAction(ActionEvent event) {
    	//Debug info
    	if(rFlyFrom.getValue() == null || rFlyTo.getValue() == null)
    		rErrorLabel.setText("Please specify Source and Destination of your trip!");
    	else if(rDepart.getValue() == null || rArrive.getValue() == null)
    		rErrorLabel.setText("Please specify departure and return date of your trip!");
    	else{
    		rErrorLabel.setText("");
	    	System.out.println("RoundTrip Seach Action");
	    	System.out.println("SRC: " + rFlyFrom.getValue());
	    	System.out.println("DES: " + rFlyTo.getValue());
	    	System.out.println("LEAVE DATE: " + Date.valueOf(rDepart.getValue()).toString());
	    	System.out.println("AFTER LEAVE DATE");
	    	System.out.println("ARR DATE: " + Date.valueOf(rArrive.getValue()).toString());
	
	
	    	/* Retrieve Data from mysql databases for round-trip search */
	    	ObservableList<Flight> flightList;
	    	flightList = fetch.searchFlightRoundTrip(rFlyFrom.getValue(), rFlyTo.getValue(), Date.valueOf(rDepart.getValue()), Date.valueOf(rArrive.getValue()));
	    	if(flightList.isEmpty()){
	    		rErrorLabel.setText("No Flights available");
	    	} else {
		    	rSearchResults.setItems(flightList);
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
    	}
    }

   
    @FXML
    void oSearchAction(ActionEvent event) {
    	System.out.println("One Way Trip Seach Action");
    	if(rFlyFrom.getValue() == null || rFlyTo.getValue() == null)
    		oErrorLabel.setText("Please specify Source and Destination of your trip!");
    	else if(rDepart.getValue() == null || rArrive.getValue() == null)
    		oErrorLabel.setText("Please specify departure and return date of your trip!");
    	else{
    		oErrorLabel.setText("");
	    	ObservableList<Flight> flightList;
	    	flightList = fetch.searchFlightOneWay(oFlyFrom.getValue(), oFlyTo.getValue(), Date.valueOf(oDepart.getValue()));
	    	if(flightList.isEmpty()){
	    		oErrorLabel.setText("No Flights available");
	    	} else {
		    	searchResults.setItems(flightList);
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
    	}
    }
    
    @FXML
    void searchFlight(ActionEvent event){
    	//do Nothing
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
		HeaderLabel.setText("WELCOME");
		populateSrcandDesFields();
	    oneWayFlightTable();
	    roundTripFlightTable();
	       
	}
	
	/* Populates a table based on round-trip search criteria */
	
	public void roundTripFlightTable()
	{
		  rAirlineCol.setMinWidth(150);
	       
	        rAirlineCol.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>()
	        {
	        	@Override
	        	public ObservableValue<String> call(CellDataFeatures<Flight, String> p)
	        	{	        		
	        		return new ReadOnlyObjectWrapper<String>(p.getValue().getAirline());
	        	}
	       });
	        
	        rLeaveDateCol.setMinWidth(150);
	        
	        rLeaveDateCol.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>()
	    	        {
	    	        	@Override
	    	        	public ObservableValue<String> call(CellDataFeatures<Flight, String> p)
	    	        	{	        		
	    	        		return new ReadOnlyObjectWrapper<String>(p.getValue().getDeptDate() + "     " + p.getValue().getDeptTime());
	    	        	}
	    	       });
	        
	        rArriveDateCol.setMinWidth(150);
	        
	        rArriveDateCol.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>()
	    	        {
	    	        	@Override
	    	        	public ObservableValue<String> call(CellDataFeatures<Flight, String> p)
	    	        	{	        		
	    	        		return new ReadOnlyObjectWrapper<String>(p.getValue().getArrivalDate() + "     " + p.getValue().getArrivalTime());
	    	        	}
	    	       });
	        
	        rReturnDateCol.setMinWidth(150);
	        
	        rReturnDateCol.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>()
	    	        {
	    	        	@Override
	    	        	public ObservableValue<String> call(CellDataFeatures<Flight, String> p)
	    	        	{	        		
	    	        		return new ReadOnlyObjectWrapper<String>(p.getValue().getArrivalDate() + "     " + p.getValue().getArrivalTime());
	    	        	}
	    	       });
	        
	        rArriveSrcDateCol.setMinWidth(150);
	        
	        rArriveSrcDateCol.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>()
	    	        {
	    	        	@Override
	    	        	public ObservableValue<String> call(CellDataFeatures<Flight, String> p)
	    	        	{	        		
	    	        		return new ReadOnlyObjectWrapper<String>(p.getValue().getDeptDate() + "     " + p.getValue().getDeptTime());
	    	        	}
	    	       });
	        
	        
	        
	        rPriceCol.setMinWidth(150);
	        
	        rPriceCol.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>()
	    	        {
	    	        	@Override
	    	        	public ObservableValue<String> call(CellDataFeatures<Flight, String> p)
	    	        	{	        		
	    	        		return new ReadOnlyObjectWrapper<String>(p.getValue().getFlightPrice());
	    	        	}
	    	       });
	}
	
	
	/* Populates a table based on one-way trip search criteria */
	
	public void oneWayFlightTable()
	{
		  oAirlineCol.setMinWidth(150);
	       
	        oAirlineCol.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>()
	        {
	        	@Override
	        	public ObservableValue<String> call(CellDataFeatures<Flight, String> p)
	        	{	        		
	        		return new ReadOnlyObjectWrapper<String>(p.getValue().getAirline());
	        	}
	       });
	        
	        oDepDateCol.setMinWidth(150);
	        
	        oDepDateCol.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>()
	    	        {
	    	        	@Override
	    	        	public ObservableValue<String> call(CellDataFeatures<Flight, String> p)
	    	        	{	        		
	    	        		return new ReadOnlyObjectWrapper<String>(p.getValue().getDeptDate() + "     " + p.getValue().getDeptTime());
	    	        	}
	    	       });
	        
	        oArrDateCol.setMinWidth(150);
	        
	        oArrDateCol.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>()
	    	        {
	    	        	@Override
	    	        	public ObservableValue<String> call(CellDataFeatures<Flight, String> p)
	    	        	{	        		
	    	        		return new ReadOnlyObjectWrapper<String>(p.getValue().getArrivalDate() + "     " + p.getValue().getArrivalTime());
	    	        	}
	    	       });
	        
	        
	        oPriceCol.setMinWidth(150);
	        
	        oPriceCol.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>()
	    	        {
	    	        	@Override
	    	        	public ObservableValue<String> call(CellDataFeatures<Flight, String> p)
	    	        	{	        		
	    	        		return new ReadOnlyObjectWrapper<String>(p.getValue().getFlightPrice());
	    	        	}
	    	       });
	}
	
	/* Populates src and des fields for searching flights */
	
	void populateSrcandDesFields()
	{
		oFlyFrom.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		oFlyTo.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		oPreferredClass.setItems(FXCollections.observableArrayList("First Class","Coach"));
		rFlyFrom.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		rFlyTo.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
	
		rPreferredClass.setItems(FXCollections.observableArrayList("First Class","Coach"));
	}

}
