package application;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import core.Passenger;
import airline.QPXExpressRequest;
import airline.Route;
import airline.Solution;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class PassengerFlightSearchController implements Initializable, ControlledScreen{
	ScreensController myController;
	
	
	
	@FXML
	private Button oneWayReserveFlightButton;
	
	@FXML
	private Button roundTripReserveFlightButton;
	
	@FXML
	private Label oReservationSubmitLabel;
	
	@FXML
	private Label rReservationSubmitLabel;
	
	@FXML
    private ChoiceBox<String> rPreferredClass;

	/** one way trip **/
    @FXML
    private ChoiceBox<String> oFlyFromChoiceBox;
    
    @FXML
    private ChoiceBox<String> oFlyToChoiceBox;

    @FXML
    private DatePicker oDepartDatePicker;

    @FXML
    private Button oSearch;

    @FXML
    private Label rErrorLabel;

    @FXML
    private Button myTripButtonPassFli;

  

    @FXML
    private ChoiceBox<String> rFlyTo;

    @FXML
    private TableView<Solution> onewaySearchResultsTableView;
    
    @FXML
    private TableColumn<Solution,String> oAirlineCol;
   
    @FXML
    private TableColumn<Solution,String> oDepDateCol;
    
    @FXML
    private TableColumn<Solution,String> oArrDateCol;
    
    @FXML
    private TableColumn<Solution,String> oPriceCol;
    
    @FXML
    private TableView<Solution> roundTripSearchResultsTable;
    
    @FXML
    private TableColumn<Solution,String> rAirlineCol;
    
    @FXML
    private TableColumn<Solution,String> rLeaveDateCol;
    
    @FXML
    private TableColumn<Solution,String> rArriveDateCol;
    
    @FXML
    private TableColumn<Solution,String> rReturnDateCol;
    
    @FXML
    private TableColumn<Solution,String> rArriveSrcDateCol;
    
    @FXML
    private TableColumn<Solution,String> rPriceCol;
        
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

    
    /**************************************** Beginning of roundtrip search ********************************************/
    

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		HeaderLabel.setText("WELCOME "+ myController.person.getFirstName());
		populateSrcandDesFields();
	    oneWayFlightTable();
	    roundTripFlightTable();
	    selectFlightRow();
	       
	}
    
    
	
	@FXML
	void oReserveFlightAction(ActionEvent event){
		Solution selected = onewaySearchResultsTableView.getSelectionModel().getSelectedItem();
		String srcToDest = Integer.toString(selected.getSolutionID());
		String destToSrc = "null";
		String numberOfBags = "0";
		String totalSale = selected.getSaleTotal();
		String personID = myController.person.getId();
		System.out.println("Reserve Flight: "+srcToDest+destToSrc+numberOfBags+totalSale+personID);
		if(myController.passenger.addReservation(srcToDest, destToSrc, numberOfBags, totalSale, personID) == false){
			System.out.println("Something went wrong here!");
			oReservationSubmitLabel.setText("Could not reserve Flight!");
		} else {
			oReservationSubmitLabel.setText("Successfully reserved your flight!");
		}
	}
    
	@FXML
	void rReserveFlightAction(ActionEvent event){
		
	}
    
    
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
	    
	    		ObservableList<Solution> flightList;
		    	
	    	    QPXExpressRequest request = new QPXExpressRequest();
	    	    request.setAdultCount(1);
	    	    request.setDate(Date.valueOf(rDepart.getValue()));
	    	    request.setDestination(rFlyTo.getValue());
	    	    request.setOrigin(rFlyFrom.getValue());
	    	    request.setSolutions(20);
	    	    flightList = request.getResponse();
	    	    System.out.println("****************************");
	    	    System.out.println(request.toJson());
	    	    onewaySearchResultsTableView.setItems(flightList);
	    	    
	    		if(flightList.isEmpty()){
		    		rErrorLabel.setText("No Flights available");
		    	} 
    	}
    }
    /****************************************** End of roundtrip search ********************************************/

    

    /****************************************** Beginning of Oneway search ********************************************/

    @FXML
    void oSearchAction(ActionEvent event) {
    	System.out.println("One Way Trip Seach Action");
    	
    	oErrorLabel.setText("");
    	
    	if(oFlyFromChoiceBox.getValue() == null || oFlyToChoiceBox.getValue() == null)
    	{
    		oErrorLabel.setText("Please specify Source and Destination of your trip!");
    		return;
    	}
    	
    	if(oDepartDatePicker.getValue() == null )
    	{
    		oErrorLabel.setText("Please specify departure and return date of your trip!");
    		return;
    	}
    	
    	
	    ObservableList<Solution> flightList;
	    QPXExpressRequest request = new QPXExpressRequest();
	    
	    request.setAdultCount(1);
	    request.setDate(Date.valueOf(oDepartDatePicker.getValue()));
	    request.setDestination(oFlyToChoiceBox.getValue());
	    request.setOrigin(oFlyFromChoiceBox.getValue());
	    request.setSolutions(500);
	    flightList = request.getResponse();	    
	 
	    onewaySearchResultsTableView.setItems(flightList);
	   
	    if(flightList.isEmpty())
	    {
	    	oErrorLabel.setText("No Flights available");
	    }
	    
	    
	    
	    
    }
    /*********************************************End of Oneway search ********************************************/

    

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

	
	
	
	/********************************** Beginning Populate RoundTrip Table with Data ******************************************/
	
	/* Populates a table based on round-trip search criteria */
	public void roundTripFlightTable()
	{
		
		rAirlineCol.setMinWidth(150);
		rAirlineCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getRoutes().get(0).getAirlineName());
			}
		});

		rLeaveDateCol.setMinWidth(150);

		rLeaveDateCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getDepartureDate().toString());
			}
		});

		rArriveDateCol.setMinWidth(150);

		rArriveDateCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				String value;				
				ArrayList<Route> routes = p.getValue().getRoutes();
				int size = routes.size();
				
				value = routes.get(size-1).getArrivalDate().toString()
						+ " at "
						+ routes.get(size-1).getArrivalTime().toString();
				
				return new ReadOnlyObjectWrapper<String>(value);
								
			}
				});

		rReturnDateCol.setMinWidth(150);

		rReturnDateCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue()
								.getArrivalDate()
								+ "     "
								+ p.getValue().getArrivalTime());
					}
		});

		rArriveSrcDateCol.setMinWidth(150);
/**
		rArriveSrcDateCol
				.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Flight, String> p) {
						return new ReadOnlyObjectWrapper<String>(p.getValue()
								.getDeptDate()
								+ "     "
								+ p.getValue().getDeptTime());
					}
				});

		rPriceCol.setMinWidth(150);

		rPriceCol
				.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Flight, String> p) {
						return new ReadOnlyObjectWrapper<String>(p.getValue()
								.getFlightPrice());
					}
				});
				**/
	}
	/********************************** End Populate RoundTrip Table with Data ******************************************/

	
	
	
	/********************************** Beginning Populate Oneway Table with Data ******************************************/

	/* Populates a table based on one-way trip search criteria */

	public void oneWayFlightTable()
	{
		oAirlineCol.setMinWidth(150);
		oAirlineCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p)
			{
				return new ReadOnlyObjectWrapper<String>(p.getValue().getRoutes().get(0).getAirlineName());
			}
	});

		
	oDepDateCol.setMinWidth(150);
	oDepDateCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<Solution, String> p)
		{
			String value = p.getValue().getDepartureDate().toString() +
					 " at " +
					 p.getValue().getDepartureTime();
			
			return new ReadOnlyObjectWrapper<String>(value);
								
		}
	});

	oArrDateCol.setMinWidth(150);
	oArrDateCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<Solution, String> p)
		{
			String value;
			ArrayList<Route> routes;
			Route route;
			int size;
			
			routes = p.getValue().getRoutes();
			route = routes.get(routes.size()-1);
			
			value = route.getArrivalDate() +
					" at " +
					route.getArrivalTime();
			return new ReadOnlyObjectWrapper<String>(value);
							
		}
	});

	oPriceCol.setMinWidth(150);
	oPriceCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
			return new ReadOnlyObjectWrapper<String>(p.getValue().getSaleTotal());
		}
	});
	}
	
	/********************************** End Populates Oneway Table with Data ******************************************/

	
	
	
	/********************************** Beginning of Populate src/des fields ******************************************/

	/* Populates src and des fields for searching flights */
	/* Node we need to populate the list from the QPX API */
	void populateSrcandDesFields()
	{
		Route route = null;
		
		oFlyFromChoiceBox.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		oFlyToChoiceBox.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		oPreferredClass.setItems(FXCollections.observableArrayList("First Class","Coach"));
		rFlyFrom.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		rFlyTo.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
	
		rPreferredClass.setItems(FXCollections.observableArrayList("First Class","Coach"));
	}
	
	/********************************** End of Populate src/des fields ******************************************/

	
	
	
	/********************************** Beginning of make reservation ***************************************** **/

	
	
	ObservableList<Solution> flightList;
	// Select row for reservation for either oneway or roundtrip flights
	public void selectFlightRow()
	{
		
		
		Callback<TableView<Solution>,TableRow<Solution>> callBack = new Callback<TableView<Solution>,TableRow<Solution>>()
		{
			@Override
			public TableRow<Solution> call(TableView<Solution> param) {
				System.out.println("init tableRow");
				TableRow<Solution> row = new TableRow<>();
				row.setOnMouseClicked(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						System.out.println("Table Row clicked");
						@SuppressWarnings("unchecked")
						TableRow<Solution> row = (TableRow<Solution>) event.getSource();
						System.out.println("Route Aricraft: " + row.getItem().getRoutes().get(0).getAircraft());
						
					}
					
				});
				return row;
			}
			
		};
	
		onewaySearchResultsTableView.setRowFactory(callBack);
		    
		
		
		
		
		
		
		/**
		onewaySearchResultsTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Table View Clicked");
    	    	if(event.getClickCount() >= 1)
    	    	{
    	    		oneWayReserveFlightButton.setOnAction(new EventHandler<ActionEvent>() {
    	    	            @Override
    	    	            public void handle(ActionEvent event) {
    	    	                //will store info from table using sql statement
    	    	            	reservationSubmitLabel.setText("Reservation Submitted");
    	    	            	System.out.println("\n\n\n " + "Reservation Submitted");
    	    	            	System.out.println("Arrival Time Selected: " + onewaySearchResultsTableView.getSelectionModel().getSelectedItem().getArrivalTime());
    	    	            //	System.out.println("Departure Time Selected: " + onewaySearchResultsTableView.getSelectionModel().getSelectedItem().getDepatureTime());
    	    	            //	System.out.println("Arrival Selected: " + searchResults.getSelectionModel().getSelectedItem().getArrivalDate());
    	    	            //	System.out.println("Price Selected: " + searchResults.getSelectionModel().getSelectedItem().getFlightPrice());

    	    	            }
    	    	     });
    	    	}
    	    }
    	});**/
		
		/**
		roundTripSearchResultsTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
    	    public void handle(MouseEvent event) {
    	    	if(event.getClickCount() > 1)
    	    	{
    	    		roundTripReserveFlightButton.setOnAction(new EventHandler<ActionEvent>() {
	    	            @Override
	    	            public void handle(ActionEvent event) {
	    	                //will store info from table using sql statement
	    	            	reservationSubmitLabel.setText("Reservation Submitted");
	    	            	//System.out.println(searchResults.getSelectionModel().getSelectedItem().getArrivalTime());
	    	            	System.out.println("Airline Selected: " + onewaySearchResultsTableView.getSelectionModel().getSelectedItem().getArrivalTime());
	    	            	//System.out.println("Departure Selected: " + onewaySearchResultsTableView.getSelectionModel().getSelectedItem().getDepatureTime());
	    	            	//System.out.println("Arrival Selected: " + searchResults.getSelectionModel().getSelectedItem().getArrivalDate());
	    	            	//System.out.println("Price Selected: " + searchResults.getSelectionModel().getSelectedItem().getFlightPrice());
	    	            }
	    	        });	
    	    	}
    	    }
    	});**/
		
	} 
	/********************************** End of make reservation ******************************************/

}
