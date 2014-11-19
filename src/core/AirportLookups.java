package core;
import javafx.beans.property.SimpleStringProperty;

public class AirportLookups {
	
	// Look up Airport Information
	
	private SimpleStringProperty airportID;
	private SimpleStringProperty airportCity;
	private SimpleStringProperty airportName;
	private SimpleStringProperty airportState;
	private SimpleStringProperty airportCode;
	

	public AirportLookups(Object[] data) {
		this.airportID = new SimpleStringProperty(((Integer)data[0]).toString());
		this.airportCity = new SimpleStringProperty((String)data[1]);
		this.airportName = new SimpleStringProperty((String)data[2]);
		this.airportState = new SimpleStringProperty((String)data[3]);
		this.airportCode = new SimpleStringProperty((String)data[4]);
		}
	
	public String getAirportID(){
		return airportID.get();
	}
	
	public String getAirportCity(){
		return airportCity.get();
	}
	
	public String getAirportName(){
		return airportName.get();
	}
	public String getAirportState(){
		return airportState.get();
	}
	
	public String getAirportCode(){
		return airportCode.get();
	}
	
	
}
