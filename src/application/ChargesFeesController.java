package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ChargesFeesController implements Initializable, ControlledScreen{

	ScreensController myController;
	
    @FXML
    private Button searchcus;

    @FXML
    private TableView<?> feeresults;

    @FXML
    private Label sumcharges;

    @FXML
    private TableColumn<?, ?> ItemDesc;

    @FXML
    private TableColumn<?, ?> chargcol;

    @FXML
    private Button signOut;

    @FXML
    private TextField reservationsearch;

    @FXML
    private Button myAccount;

    @FXML
    private Button payfees;

    @FXML
    private TextField namesearch;

    @FXML
    private Button home;

    @FXML
    void Home(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen8ID);
    }

    @FXML
    void MyAccount(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen10ID);
    }

    @FXML
    void SignOut(ActionEvent event) {
    	myController.setScreen(ScreensFramework.screen1ID);
    }

    @FXML
    void SearchCustomer(ActionEvent event) {

    }

    @FXML
    void PayFees(ActionEvent event) {

    }

	@Override
	public void setScreenParent(ScreensController screenPage) {
		// TODO Auto-generated method stub
		myController = screenPage;
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