package application;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MyTripController implements Initializable, ControlledScreen{
ScreensController myController;



@FXML
private Button myTrip;

@FXML
private Button signOut;

@FXML
private Button myAccount;

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
	// TODO Auto-generated method stub
	myController = screenParent;
}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}
}
