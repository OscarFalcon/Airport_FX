package core;

import java.sql.Date;
import java.sql.Time;

import javafx.beans.property.SimpleStringProperty;

public class Flight {
	
	private SimpleStringProperty flightID;
	private SimpleStringProperty airline;
	private SimpleStringProperty flightNumber;
	private SimpleStringProperty deptDate;
	private SimpleStringProperty deptTime;
	private SimpleStringProperty arrivalDate;
	private SimpleStringProperty arrivalTime;
	private SimpleStringProperty srcLocation;
	private SimpleStringProperty destinationLocation;
	private SimpleStringProperty flightPrice;
	
	
	
	public Flight(Object[] data)
	{
		this.flightID = new SimpleStringProperty(((Integer)data[0]).toString());
		this.airline = new SimpleStringProperty((String)data[1]);
		this.flightNumber = new SimpleStringProperty((String)data[2]);
		this.deptDate = new SimpleStringProperty(((Date)data[3]).toString());
		this.deptTime= new SimpleStringProperty(((Time)data[4]).toString());	
		this.arrivalDate = new SimpleStringProperty(((Date)data[5]).toString());
		this.arrivalTime= new SimpleStringProperty(((Time)data[6]).toString());
		this.srcLocation = new SimpleStringProperty((String)data[7]);
		this.destinationLocation = new SimpleStringProperty((String)data[8]);
		this.flightPrice = new SimpleStringProperty(((Float)data[9]).toString());
		
	}
	
	
	public String getFlightId(){
		return flightID.get();
	}
	
	public String getAirline(){
		return airline.get();
	}
	
	public String getFlightNumber(){
		return flightNumber.get();
	}
	
	public String getDeptDate(){
		return deptDate.get();
	}
	
	public String getDeptTime(){
		return deptTime.get();
	}
	
	public String getArrivalDate(){
		return arrivalDate.get();
	}
	
	public String getArrivalTime(){
		return arrivalTime.get();
	}
	
	public String getSrcLocation(){
		return srcLocation.get();
	}
	
	public String getDestinationLocation(){
		return destinationLocation.get();
	}
	
	public String getFlightPrice(){
		return flightPrice.get();
	}
	
	
}
