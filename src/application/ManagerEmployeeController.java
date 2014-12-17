package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import core.Employee;

public class ManagerEmployeeController implements Initializable, ControlledScreen {

	ScreensController myController;


    @FXML
    private TableColumn<Employee, String> availabilityCol;

    @FXML
    private TableColumn<Employee, String> statusCol;

    @FXML
    private TableColumn<Employee, String> jobTitleCol;

    @FXML
    private TableColumn<Employee, String> nameCol;

    @FXML
    private Button signout;

    @FXML
    private Button changeStatus;

    @FXML
    private Button myAccount;

    @FXML
    private Button home;

    public void getEmployees()
    {

    	Employee employee = Employee.retrieveEmployee("birdman", "password");
    	

		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new Callback<CellDataFeatures<Employee, String>,ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Employee, String> p) {
			Employee e = p.getValue();
			return new ReadOnlyObjectWrapper<String>(e.getFirstName() + e.getLastName());
			}
		});
		
		jobTitleCol.setMinWidth(200);
		jobTitleCol.setCellValueFactory(new Callback<CellDataFeatures<Employee, String>,ObservableValue<String>>(){
		@Override
		public ObservableValue<String> call(CellDataFeatures<Employee, String> p) {
			return new ReadOnlyObjectWrapper<String>(p.getValue().getType());
		}	
		});
		
		statusCol.setMinWidth(200);
		statusCol.setCellValueFactory(new Callback<CellDataFeatures<Employee, String>,ObservableValue<String>>(){
		@Override
		public ObservableValue<String> call(CellDataFeatures<Employee, String> p) {
			return new ReadOnlyObjectWrapper<String>(p.getValue().getStatus());
		}	
		});
		
		
		availabilityCol.setMinWidth(200);
		availabilityCol.setCellValueFactory(new Callback<CellDataFeatures<Employee, String>,ObservableValue<String>>(){
		@Override
		public ObservableValue<String> call(CellDataFeatures<Employee, String> p) {
			return new ReadOnlyObjectWrapper<String>(p.getValue().getAvailability());
		}	
		});

    }
    
   
    @FXML
    void Home(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen6ID);
    }

    @FXML
    void MyAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen10ID);
    }

    @FXML
    void Signout(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen1ID);
    }

	
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
		
	}

	@FXML
	public void ChangeStatus(ActionEvent event){
		
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		
		getEmployees();
	}

	
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	
	public void respawn(HashMap<String, Object> arguments) {
		// TODO Auto-generated method stub
		
	}

}

