package application;

import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import airline.QPXExpressRequest;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import core.Flight;

public class PassengerFlightSearchController implements Initializable, ControlledScreen{
	ScreensController myController;
	
	@FXML
	private Button oneWayReserveFlightButton;
	
	@FXML
	private Button roundTripReserveFlightButton;
	
	@FXML
	private Label reservationSubmitLabel;
	
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
    private TableView<Solution> searchResults;
    
    @FXML
    private TableColumn<Solution,String> oAirlineCol;
   
    @FXML
    private TableColumn<Solution,String> oDepDateCol;
    
    @FXML
    private TableColumn<Solution,String> oArrDateCol;
    
    @FXML
    private TableColumn<Solution,String> oPriceCol;
    
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

    
    /**************************************** Beginning of roundtrip search ********************************************/
    
    @FXML
    void rSearchAction(ActionEvent event) {
    	//Debug info
    	
    //	if(rFlyFrom.getValue() == null || rFlyTo.getValue() == null)
    //		rErrorLabel.setText("Please specify Source and Destination of your trip!");
    //	else if(rDepart.getValue() == null || rArrive.getValue() == null)
    //		rErrorLabel.setText("Please specify departure and return date of your trip!");
    //	else{
    		rErrorLabel.setText("");
	    	System.out.println("RoundTrip Seach Action");
	    	System.out.println("SRC: " + rFlyFrom.getValue());
	    	System.out.println("DES: " + rFlyTo.getValue());
	    	System.out.println("LEAVE DATE: " + Date.valueOf(rDepart.getValue()).toString());
	    	System.out.println("AFTER LEAVE DATE");
	    	System.out.println("ARR DATE: " + Date.valueOf(rArrive.getValue()).toString());
	
	
	    	/* Retrieve Data from mysql databases for round-trip search */
	    	ObservableList<Flight> flightList = null;
	    	if(flightList.isEmpty()){
	    		rErrorLabel.setText("No Flights available");
	    	} else 
	    	{
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
    //}
    /****************************************** End of roundtrip search ********************************************/

    

    /****************************************** Beginning of Oneway search ********************************************/

    @FXML
    void oSearchAction(ActionEvent event) {
    	
    	if(oFlyFrom.getValue() == null || oFlyTo.getValue() == null)
    	{
    		oErrorLabel.setText("Please specify Source and Destination of your trip!");
    		return;
    	}
    	if(oDepart.getValue() == null )
    	{
    		oErrorLabel.setText("Please specify departure and return date of your trip!");
    		return;
    	}	
    	oErrorLabel.setText("");
    	
	    ObservableList<Solution> flightList;	
	    QPXExpressRequest request = new QPXExpressRequest();
	    
	    request.setAdultCount(1);
	    request.setDate(Date.valueOf(oDepart.getValue()));
	    request.setDestination(oFlyTo.getValue());
	    request.setOrigin(oFlyFrom.getValue());
	    request.setSolutions(500);
	    flightList = request.getResponse();	    
	    searchResults.setItems(flightList);
	    if(flightList.isEmpty())
	    {
	    	oErrorLabel.setText("No Flights available");
	    }
	    
	    
    }
    /*********************************************End of Oneway search ********************************************/

    
    
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
	public void initialize(URL location, ResourceBundle resources)
	{
		HeaderLabel.setText("WELCOME "+ myController.person.getFirstName());
		populateSrcandDesFields();
	    oneWayFlightTable();
	    roundTripFlightTable();
	    selectFlightRow();
	       
	}
	
	
	
	/********************************** Beginning Populate RoundTrip Table with Data ******************************************/
	
	/* Populates a table based on round-trip search criteria */
	public void roundTripFlightTable()
	{
		

		rAirlineCol.setMinWidth(150);
		rAirlineCol
			.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Flight, String> p) {
						return new ReadOnlyObjectWrapper<String>(p.getValue()
								.getAirline());
					}
				});

		rLeaveDateCol.setMinWidth(150);

		rLeaveDateCol
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

		rArriveDateCol.setMinWidth(150);

		rArriveDateCol
				.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Flight, String> p) {
						return new ReadOnlyObjectWrapper<String>(p.getValue()
								.getArrivalDate()
								+ "     "
								+ p.getValue().getArrivalTime());
					}
				});

		rReturnDateCol.setMinWidth(150);

		rReturnDateCol
				.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Flight, String> p) {
						return new ReadOnlyObjectWrapper<String>(p.getValue()
								.getArrivalDate()
								+ "     "
								+ p.getValue().getArrivalTime());
					}
				});

		rArriveSrcDateCol.setMinWidth(150);

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
		public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
			return new ReadOnlyObjectWrapper<String>(p.getValue().getDepatureTime());
								
		}
	});

	oArrDateCol.setMinWidth(150);
	oArrDateCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
			return new ReadOnlyObjectWrapper<String>(p.getValue().getArrivalTime());
							
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
	void populateSrcandDesFields()
	{
		oFlyFrom.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		oFlyTo.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		oPreferredClass.setItems(FXCollections.observableArrayList("First Class","Coach"));
		rFlyFrom.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		rFlyTo.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
	
		rPreferredClass.setItems(FXCollections.observableArrayList("First Class","Coach"));
	}
	
	/********************************** End of Populate src/des fields ******************************************/

	
	
	
	/********************************** Beginning of make reservation ******************************************/

	// Select row for reservation for either oneway or roundtrip flights
	public void selectFlightRow()
	{
		searchResults.setOnMouseClicked(new EventHandler<MouseEvent>() {
    	    public void handle(MouseEvent event) {
    	    	if(event.getClickCount() > 1)
    	    	{
    	    		oneWayReserveFlightButton.setOnAction(new EventHandler<ActionEvent>() {
    	    	            @Override
    	    	            public void handle(ActionEvent event) {
    	    	                //will store info from table using sql statement
    	    	            	reservationSubmitLabel.setText("Reservation Submitted");
    	    	            	System.out.println("\n\n\n " + "Reservation Submitted");
    	    	            	System.out.println("Airline Selected: " + searchResults.getSelectionModel().getSelectedItem().);
    	    	            	System.out.println("Departure Selected: " + searchResults.getSelectionModel().getSelectedItem().getDeptDate());
    	    	            	System.out.println("Arrival Selected: " + searchResults.getSelectionModel().getSelectedItem().getArrivalDate());
    	    	            	System.out.println("Price Selected: " + searchResults.getSelectionModel().getSelectedItem().getFlightPrice());

    	    	            }
    	    	     });
    	    	}
    	    }
    	});
		

		rSearchResults.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
    	    public void handle(MouseEvent event) {
    	    	if(event.getClickCount() > 1)
    	    	{
    	    		roundTripReserveFlightButton.setOnAction(new EventHandler<ActionEvent>() {
	    	            @Override
	    	            public void handle(ActionEvent event) {
	    	                //will store info from table using sql statement
	    	            	reservationSubmitLabel.setText("Reservation Submitted");
	    	            	System.out.println("Airline Selected: " + searchResults.getSelectionModel().getSelectedItem().getAirline());
	    	            	System.out.println("Departure Selected: " + searchResults.getSelectionModel().getSelectedItem().getDeptDate());
	    	            	System.out.println("Arrival Selected: " + searchResults.getSelectionModel().getSelectedItem().getArrivalDate());
	    	            	System.out.println("Price Selected: " + searchResults.getSelectionModel().getSelectedItem().getFlightPrice());
	    	            }
	    	        });	
    	    	}
    	    }
    	});
		
	}
	/********************************** End of make reservation ******************************************/

}
