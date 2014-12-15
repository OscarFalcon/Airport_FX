package application;

import java.net.URL;
import java.util.ResourceBundle;

import airline.Solution;
import javafx.fxml.Initializable;

public class FlightDetailsPageController implements Initializable, ControlledScreen{
	private Solution solution;
	
	
	public void setSolution(Solution obj){
		this.solution = obj;
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenPage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
