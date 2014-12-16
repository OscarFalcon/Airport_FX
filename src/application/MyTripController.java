package application;

import java.net.URL;
import java.util.ResourceBundle;

import core.Reservation;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class MyTripController implements Initializable, ControlledScreen{
	ScreensController myController;
	
	ObservableList<Reservation> list;
	
	@FXML
	private Label errorLabel;
	
    @FXML
    private TableView<Reservation> myTripTable;
	
    @FXML
    private Button cancelReservationButton;
    
    @FXML
    private TableColumn<Reservation, String> dateTimeCol;

    @FXML
    private TableColumn<Reservation, String> destCol;

    @FXML
    private TableColumn<Reservation, String> bagsCol;
    
    @FXML
    private TableColumn<Reservation, String> srcCol;

    @FXML
    private TableColumn<Reservation, String> totalSaleCol;
	
	@FXML
	private Button searchFlightButton;
	
    @FXML
    private Button signOut;

    @FXML
    private Button myAccount;

    @FXML
    private Button mytrip;
    
    @FXML 
    void searchFlight(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen3ID);
    	
    }

    @FXML
    void myAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen2ID);
    }

    @FXML
    void myTrip(ActionEvent event) {
    	
    }

    @FXML
    void signOut(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen1ID);

    }

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	public void cancelReservationAction(ActionEvent event){
		errorLabel.setText("");
		if(myController.getPassenger().cancelReservation(myTripTable.getSelectionModel().getSelectedItem().getReservationId())){
			list.remove(myTripTable.getSelectionModel().getSelectedIndex());
			errorLabel.setText("Successfully canceled Reservation!");
		}else{
			errorLabel.setText("Could not cancel Reservation, something went wrong!");
		}
	}
	
	@Override
	public void reset() {
		dateTimeCol.setCellValueFactory(new Callback<CellDataFeatures<Reservation, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Reservation, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getSrcToDest().getDepartureDate()+" "+p.getValue().getSrcToDest().getDepartureTime());
			}
		});
		
		srcCol.setCellValueFactory(new Callback<CellDataFeatures<Reservation, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Reservation, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getSrcToDest().getOriginAirportCode()+" "+p.getValue().getSrcToDest().getOriginAirport());
			}
		});
		
		destCol.setCellValueFactory(new Callback<CellDataFeatures<Reservation, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Reservation, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getSrcToDest().getDestinationAirportCode()+" "+p.getValue().getSrcToDest().getDestinationAirport());
			}
		});
		
		bagsCol.setCellValueFactory(new Callback<CellDataFeatures<Reservation, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Reservation, String> p) {
				return new ReadOnlyObjectWrapper<String>(Integer.toString(p.getValue().getNumOfBags()));
			}
		});
		
		totalSaleCol.setCellValueFactory(new Callback<CellDataFeatures<Reservation, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Reservation, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getTotalSale());
			}
		});
		
		list = FXCollections.observableArrayList(myController.getPassenger().getReservations());
		myTripTable.setItems(list);
		selectFlightRow();
		
	}
	
	
	
	
	
	public void selectFlightRow()
	{
	
		Callback<TableView<Reservation>,TableRow<Reservation>> callBack = new Callback<TableView<Reservation>,TableRow<Reservation>>()
		{
			@Override
			public TableRow<Reservation> call(TableView<Reservation> param) {
				TableRow<Reservation> row = new TableRow<>();
				row.setOnMouseClicked(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						if(event.getClickCount() == 2){ 
							@SuppressWarnings("unchecked")
							TableRow<Reservation> row = (TableRow<Reservation>) event.getSource();
							System.out.println("Table Row clicked + " + row.getItem().getReservationId());
							myController.showFlightDetailsPage(row.getItem().getSrcToDest());
							//System.out.println("Route Aricraft: " + row.getItem().getSrcToDest().getRoutes().get(0).getAircraft());
						}
					}
					
				});
				return row;
			}
			
		};
		myTripTable.setRowFactory(callBack);
	}

}
