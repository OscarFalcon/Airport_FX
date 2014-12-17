package application;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import airline.QPXExpressRequest;
import airline.Route;
import airline.Solution;
import core.Reservation;

public class ReceptionistMainController implements Initializable, ControlledScreen{
    ScreensController myController;
	
	
	@FXML
	private Label HeaderLabel;
	
	@FXML
	private Label oReservationSubmitLabel;
	
	@FXML
	private Label rReservationSubmitLabel;
	
	  
	/** one way trip **/
	@FXML
	private ChoiceBox<String> oPreferredClass;

	@FXML
	private Label oErrorLabel;

	@FXML
	private TextField oneWayPassengerCountTextField;
	
    @FXML
    private ChoiceBox<String> oFlyFromChoiceBox;
    
    @FXML
    private ChoiceBox<String> oFlyToChoiceBox;

    @FXML
    private DatePicker oneWayDepartDatePicker;

    @FXML
    private Label rErrorLabel;

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
    
    
    /** ROUND TRIP **/
    
    @FXML
    private ChoiceBox<String> rFlyFrom;

    @FXML
    private DatePicker roundTripArrivalDatePicker;
    
    @FXML
    private DatePicker roundTripLeavingDatePicker;
    
    @FXML
    private TextField roundTripPassengerCountTextField;
    
    @FXML
    private ChoiceBox<String> rPreferredClass;
    
    
    // round trip src to dest table view
    @FXML
    private TableView<Solution> roundTripSrcToDestSearchResultsTable;
    
    @FXML
    private TableColumn<Solution,String> rAirlineCol;
    
    @FXML
    private TableColumn<Solution,String> roundTripDepartureDateTimeCol;
    
    @FXML
    private TableColumn<Solution,String> roundTripArrivalDateTimeCol;
    
    @FXML
    private TableColumn<Solution,String> rReturnDateCol;
    
    @FXML
    private TableColumn<Solution,String> rArriveSrcDateCol;
    
    @FXML
    private TableColumn<Solution,String> rPriceCol;
    
    // round trip dest tp src table view 
    @FXML
    private TableView<Solution> roundTripDestToSrcSearchResultsTable;
    
    @FXML
    private TableColumn<Solution,String> rAirlineCol2;
    
    @FXML
    private TableColumn<Solution,String> roundTripDepartureDateTimeCol2;
    
    @FXML
    private TableColumn<Solution,String> roundTripArrivalDateTimeCol2;
    
    @FXML
    private TableColumn<Solution,String> rPriceCol2;
    
    
    
    @Override
	public void setScreenParent(ScreensController screenParent) {
		// TODO Auto-generated method stub
		myController = screenParent;
		
	}
  

    /**************************************** Beginning of roundtrip search ********************************************/
    

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		populateSrcandDesFields();
	    oneWayFlightTable();
	    initRoundTripFlightTables();
	    selectFlightRow();
	}
    
    
	
	@FXML
	void oReserveFlightAction(ActionEvent event)
	{
		Solution selected = onewaySearchResultsTableView.getSelectionModel().getSelectedItem();
		Reservation reservation = new Reservation();
		reservation.setSrcToDest(selected);
		reservation.setNumOfBags(0);
		reservation.setTotalSale(selected.getSaleTotal());
		reservation.setPrimaryPassenger(myController.getPassenger());
		
		if(reservation.book() == false)
		{
			oReservationSubmitLabel.setText("Error adding Reservation!");
		} else
		{
			oReservationSubmitLabel.setText("Successfully Reserved Flight!");

		}
	}
    
	@FXML
	void rReserveFlightAction(ActionEvent event)
	{
		Reservation reservation = new Reservation();
		
		if(roundTripSrcToDestSearchResultsTable.getSelectionModel().getSelectedIndex() < 0
				|| roundTripDestToSrcSearchResultsTable.getSelectionModel().getSelectedIndex() < 0)
		{
			rReservationSubmitLabel.setText("Please specify two reservation solutions");
			return;
		}
		
		Solution srcToDest = roundTripSrcToDestSearchResultsTable.getSelectionModel().getSelectedItem();
		Solution destToSrc = roundTripDestToSrcSearchResultsTable.getSelectionModel().getSelectedItem();
		
		String srcToDestPrice = srcToDest.getSaleTotal().substring(3);
		String destToSrcPrice = destToSrc.getSaleTotal().substring(3);
		
		Double price1 = Double.parseDouble(srcToDestPrice);
		Double price2 = Double.parseDouble(destToSrcPrice);
		Double totalSale = price1 + price2;
		
		reservation.setSrcToDest(srcToDest);
		reservation.setDestToSrc(destToSrc);
		reservation.setTotalSale("USD" + totalSale.toString());
		reservation.setPrimaryPassenger(myController.getPassenger());
		reservation.setNumOfBags(0);
		
		if(reservation.book() == false)
		{
			rReservationSubmitLabel.setText("Opps something went wrong");
			return;
		}
		
		rReservationSubmitLabel.setText("Successfully Reserved Flight!");
		return;
		
	
	}
    
    @FXML
    void roundTripSearchAction(ActionEvent event)
    {
    	
    	String sourceCityCode = rFlyFrom.getValue();
    	String destinationCityCode = rFlyTo.getValue();
    	
    	
    	if(sourceCityCode == null || destinationCityCode == null)
    	{
    		rErrorLabel.setText("Please specify Source and Destination of your trip!");
    		return;
    	}
    	
    	if(roundTripLeavingDatePicker.getValue() == null || roundTripArrivalDatePicker.getValue() == null)
    	{
    		rErrorLabel.setText("Please specify departure and return date of your trip!");
    		return;
    	}
    	
    	Date departingDate = Date.valueOf(roundTripLeavingDatePicker.getValue());
    	Date returningDate = Date.valueOf(roundTripArrivalDatePicker.getValue());
    	
    	
    	Integer count;
    	try
    	{
    		count = Integer.parseInt(roundTripPassengerCountTextField.getText());
    	}
    	catch(Exception e)
    	{
    		rErrorLabel.setText("Passenger Count must be a valid number");
    		return;
    	}
    	
    	if(departingDate.after(returningDate))
    	{
    		rErrorLabel.setText("Departing date must be before returning date (: ");
    		return;
    		
    	}
    	rErrorLabel.setText("");
    	ObservableList<Solution> srcToDestFlightList;
    	ObservableList<Solution> destToSrcFlightList;
    	
    	
    	QPXExpressRequest request = new QPXExpressRequest();
    	request.setAdultCount(count);
	    request.setDate(departingDate);
	    request.setDestination(destinationCityCode);
	    request.setOrigin(sourceCityCode);
	    request.setSolutions(50);
	    srcToDestFlightList = request.getResponse();
	    roundTripSrcToDestSearchResultsTable.setItems(srcToDestFlightList);
	     
	    
	    request.setOrigin(destinationCityCode); //the new origin is the original destination (:
	    request.setDestination(sourceCityCode); //same for the new destination  
	    request.setDate(returningDate);
	    destToSrcFlightList = request.getResponse();
	    roundTripDestToSrcSearchResultsTable.setItems(destToSrcFlightList);
	      		
    	
    }
    /****************************************** End of roundtrip search ********************************************/

    

    /****************************************** Beginning of Oneway search ********************************************/

    @FXML
    void oSearchAction(ActionEvent event)
    {
    	
    	oErrorLabel.setText("");
    	
    	if(oFlyFromChoiceBox.getValue() == null || oFlyToChoiceBox.getValue() == null)
    	{
    		oErrorLabel.setText("Please specify Source and Destination of your trip!");
    		return;
    	}
    	
    	if(oneWayDepartDatePicker.getValue() == null )
    	{
    		oErrorLabel.setText("Please specify departure and return date of your trip!");
    		return;
    	}
    	
    	Integer count;
    	try
    	{
    		count = Integer.parseInt(oneWayPassengerCountTextField.getText());
    	}
    	catch(Exception e)
    	{
    		oErrorLabel.setText("Passenger Count must be a valid number");
    		return;
    	}
    	
    	
    	
    	
	    ObservableList<Solution> flightList;
	    QPXExpressRequest request = new QPXExpressRequest();
	    
	    request.setAdultCount(count);
	    request.setDate(Date.valueOf(oneWayDepartDatePicker.getValue()));
	    request.setDestination(oFlyToChoiceBox.getValue());
	    request.setOrigin(oFlyFromChoiceBox.getValue());
	    request.setSolutions(50);
	    flightList = request.getResponse();	    
	 
	    onewaySearchResultsTableView.setItems(flightList);
	   
	    if(flightList.isEmpty())
	    {
	    	oErrorLabel.setText("No Flights available");
	    }
	    
	    
	    
	    
    }
    /*********************************************End of Oneway search ********************************************/
    @FXML
    void ChargesFees(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen13ID);
    }

    @FXML
    void BoardingPass(ActionEvent event) {

    }

    
    @FXML
    void ReservationStatus(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen9ID);
    }

    @FXML
    void myAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen10ID); //need to change
    }


    @FXML
    void signOut(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen1ID);
    }
    

	public void initRoundTripFlightTables()
	{
		
		/** round trip departure search results table **/
		
		rAirlineCol.setMinWidth(200);
		rAirlineCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getRoutes().get(0).getAirlineName());
			}
		});

		roundTripDepartureDateTimeCol.setMinWidth(300);
		roundTripDepartureDateTimeCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				Solution s = p.getValue();
				return new ReadOnlyObjectWrapper<String>(s.getDepartureDate().toString()
							+ " at " + s.getDepartureTime());
			}
		});

		roundTripArrivalDateTimeCol.setMinWidth(300);
		roundTripArrivalDateTimeCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				String value;				
				ArrayList<Route> routes = p.getValue().getRoutes();
				int size = routes.size();
				
				value = routes.get(size-1).getArrivalDate()
						+ " at "
						+ routes.get(size-1).getArrivalTime().toString();
				
				return new ReadOnlyObjectWrapper<String>(value);
								
			}
		});
		rPriceCol.setMinWidth(150);
		rPriceCol.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>()
		{
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getSaleTotal());
						
			}
		});
		
		/** round trip arriving search results table **/
		rAirlineCol2.setMinWidth(200);
		rAirlineCol2.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getRoutes().get(0).getAirlineName());
			}
		});

		roundTripDepartureDateTimeCol2.setMinWidth(300);
		roundTripDepartureDateTimeCol2.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				Solution s = p.getValue();
				return new ReadOnlyObjectWrapper<String>(s.getDepartureDate().toString()
							+ " at " + s.getDepartureTime());
			}
		});

		roundTripArrivalDateTimeCol2.setMinWidth(300);
		roundTripArrivalDateTimeCol2.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				String value;				
				ArrayList<Route> routes = p.getValue().getRoutes();
				int size = routes.size();
				
				value = routes.get(size-1).getArrivalDate()
						+ " at "
						+ routes.get(size-1).getArrivalTime().toString();
				
				return new ReadOnlyObjectWrapper<String>(value);
								
			}
		});
		rPriceCol2.setMinWidth(150);
		rPriceCol2.setCellValueFactory(new Callback<CellDataFeatures<Solution, String>, ObservableValue<String>>()
		{
			@Override
			public ObservableValue<String> call(CellDataFeatures<Solution, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getSaleTotal());
						
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
		oFlyFromChoiceBox.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		oFlyToChoiceBox.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		oPreferredClass.setItems(FXCollections.observableArrayList("First Class","Coach"));
		rFlyFrom.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
		rFlyTo.setItems(FXCollections.observableArrayList("SAT", "ATL", "DEN", "BWI", "LAX", "PHX"));
	
		rPreferredClass.setItems(FXCollections.observableArrayList("First Class","Coach"));
	}
	
	/********************************** End of Populate src/des fields ******************************************/

	
	
	
	/********************************** Beginning of make reservation ***************************************** **/

	
	
	//ObservableList<Solution> flightList;
	// Select row for reservation for either oneway or roundtrip flights
	public void selectFlightRow()
	{
	
		Callback<TableView<Solution>,TableRow<Solution>> callBack = new Callback<TableView<Solution>,TableRow<Solution>>()
		{
			@Override
			public TableRow<Solution> call(TableView<Solution> param) {
				TableRow<Solution> row = new TableRow<>();
				row.setOnMouseClicked(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						if(event.getClickCount() == 2){ 
							@SuppressWarnings("unchecked")
							TableRow<Solution> row = (TableRow<Solution>) event.getSource();							
							ControlledScreen controller;
							HashMap<String, Object> arguments = new HashMap<String, Object>();
							arguments.put("solution",row.getItem());
							controller = myController.loadPopUp(ScreensFramework.flightDetailsPage);
							controller.respawn(arguments);
						}
					}
					
				});
				return row;
			}
			
		};
		onewaySearchResultsTableView.setRowFactory(callBack);
		//roundTripDestToSrcSearchResultsTable.setRowFactory(callBack);
		//roundTripSrcToDestSearchResultsTable.setRowFactory(callBack);
		
		
	
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



	@Override
	public void reset() {
		//HeaderLabel.setText("WELCOME "+ myController.getPassenger().getFirstName()+ " " +myController.getPassenger().getLastName());
	}



	@Override
	public void respawn(HashMap<String, Object> arguments) {
		// TODO Auto-generated method stub
		
	}
	

}

