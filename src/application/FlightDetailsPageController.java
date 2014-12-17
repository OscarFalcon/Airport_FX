package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import airline.Route;
import airline.Solution;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class FlightDetailsPageController implements Initializable, ControlledScreen{
	
	@FXML
    private TableView<Route> flightDetailsTable;
	
    @FXML
    private TableColumn<Route, String> departCol;

    @FXML
    private TableColumn<Route, String> airlineCol;

    @FXML
    private TableColumn<Route, String> destCol;

    @FXML
    private TableColumn<Route, String> arriveCol;

    @FXML
    private TableColumn<Route, String> srcCol;

    @FXML
    private TableColumn<Route, String> flightNumCol;
	
	@Override
	public void setScreenParent(ScreensController screenPage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {

		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		airlineCol.setCellValueFactory(new Callback<CellDataFeatures<Route, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Route, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getAirlineName());
			}
		});
		
		flightNumCol.setCellValueFactory(new Callback<CellDataFeatures<Route, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Route, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getAirlineNumber());
			}
		});
		
		srcCol.setCellValueFactory(new Callback<CellDataFeatures<Route, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Route, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getSourceAirport() +" " + p.getValue().getSourceAirportCity());
			}
		});
		
		destCol.setCellValueFactory(new Callback<CellDataFeatures<Route, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Route, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getDestinationAirport()+" "+p.getValue().getDestinationAirportCity());
			}
		});
		
		departCol.setCellValueFactory(new Callback<CellDataFeatures<Route, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Route, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getDepartureTime());
			}
		});
		
		arriveCol.setCellValueFactory(new Callback<CellDataFeatures<Route, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Route, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue().getArrivalTime());
			}
		});
		
		
	}


	@Override
	public void respawn(HashMap<String, Object> arguments)
	{
		Solution solution = (Solution) arguments.get("solution");
		ObservableList<Route> list = FXCollections.observableArrayList(solution.getRoutes());
		flightDetailsTable.setItems(list);
	}

}
