package airline;

import java.sql.Date;
import java.util.ArrayList;

import core.SavableObject;
import database.MySQL;

public class Route extends SavableObject
{

	private String airlineName;				// ex. "United Airlines, Inc."  							ok
	private String airlineCode;				// ex. "UA" for UnitedAirlines								ok
	private String airlineNumber;			// ex. "3518"  												ok
	
	private String sourceAirport;			// ex. "Denver International"								ok
	private String sourceAirportCode;		// ex. "ORD" for Chicago O'Hare International Airport		ok
	private String sourceAirportCityCode;	// ex. "CHI" for Chicago									ok?
	private String sourceAirportCity;		// ex. "Chicago" for well.. Chicago							ok?
	
	private String destinationAirportCode;	//															ok
	private String destinationAirport;		//															ok					
	private String destinationAirportCityCode;//														ok?														
	private String destinationAirportCity;	//															ok?
	
	private String aircraftCode;			// ex. "738" for Boeing 737									ok
	private String aircraft;				// ex. Boeing 737											ok
	
	private String arrivalTime;				// ex. 2014-12-12T10:25-07:00 								ok
	private String departureTime;			// ex. 2014-12-12T09:05-06:00								ok
	
	
	private Integer routeID = null; 	    //the ID of the route in the database;
	
	
	
	public Integer getRouteID()
	{
		return routeID;
	}
	
	public void setAirlineName(String airline)	//AIRLINE NAME
	{
		this.airlineName = airline;
	}
	public String getAirlineName()
	{
		return airlineName;
	}
	public void setAirlineCode(String code)	//AIRLINE CODE
	{
		this.airlineCode = code;
	}
	
	public String getAirlineCode()
	{
		return airlineCode;
	}
	public void setAirlineNumber(String no)	//AIRLINE NUMBER
	{
		this.airlineNumber = no;
	}
	public String getAirlineNumber()
	{
		return airlineNumber;
	}
	public void setSourceAirport(String airport) //SOURCE AIRPORT
	{
		this.sourceAirport = airport;
	}
	public String getSourceAirport()
	{
		return sourceAirport;
	}
	public void setSourceAirportCode(String code) //SOURCE AIRPORT CODE
	{
		this.sourceAirportCode = code;
	}
	public String getSourceAirportCode()
	{
		return sourceAirportCode;
	}
	public void setSourceAirportCityCode(String code) //SOURCE AIRPORT CITY CODE
	{
		this.sourceAirportCityCode = code;
	}
	public String getSourceAirportCityCode()
	{
		return sourceAirportCityCode;
	}
	public void setSourceAirportCity(String city) //SOURCE AITPORT CITY
	{
		this.sourceAirportCity = city;
	}
	public String getSourceAirportCity()
	{
		return sourceAirportCity;
	}
	public void setDestinationAirportCode(String code) // DESTINATION AIRPORT CODE
	{
		this.destinationAirportCode = code;
	}
	public String getDestinationAirportCode()
	{
		return destinationAirportCode;
	}
	public void setDestinationAirport(String airport) //DESTINATION AIRPORT 
	{
		this.destinationAirport = airport;
	}
	public String getDestinationAirport()
	{
		return destinationAirport;
	}
	public void setDestinationAirportCityCode(String code) //DESTINATION AIRPORT CITY CODE
	{
		this.destinationAirportCityCode = code;
	}
	public String getDestinationAirportCityCode()
	{
		return destinationAirportCityCode;
	}
	public void setDestinationAirportCity(String city) //DESTINATION AIRPORT CITY
	{
		this.destinationAirportCity = city;
	}
	public String getDestinationAirportCity()
	{
		return destinationAirportCity;
	}
	public void setAircraftCode(String code) // AIRCRAFT CODE
	{
		this.aircraftCode = code;
	}
	public String getAircraftCode()			
	{
		return aircraftCode;
	}
	public void setAircraft(String aircraft) // AIRCRAFT NAME 
	{
		this.aircraft = aircraft;
	}
	public String getAircraft()
	{
		return aircraft;
	}
	public void setArrivalTime(String time)	//UNFORMATTED ARRIVAL TIME
	{
		this.arrivalTime = time;
	}
	
	public void setDepartureTime(String time){
		this.departureTime = time;
	}
	
	public String getUnformattedArrivalTime()
	{
		return this.arrivalTime;
	}
	
	
	public void setUnformattedDepartureTime(String time) //UNFORMATTED DEPARTURE TIME
	{
		this.departureTime = time;
	}
	public String getUnformattedDepartureTime()	
	{
		return departureTime;
	}
	
	public Date getArrivalDate()			//ARRIVAL DATE	
	{
		return Date.valueOf(arrivalTime.split("T")[0]);
	}
	public String getArrivalTime()			// ARRIVAL TIME
	{
		return arrivalTime.split("T")[1];
	}
	
	
	public Date getDepatureDate()			// DEPARTURE DATE	
	{
		return Date.valueOf(departureTime.split("T")[0]);
	}
	public String getDepartureTime()		// DEPARTURE TIME
	{
		return departureTime.split("T")[1];
	}
	
	
	
	
	
	@Override
	public boolean save()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean insert()
	{
		String mysql = "INSERT INTO route(airlineName,airlineCode,airlineNumber,sourceAirport,"
						+ "sourceAirportCode,sourceAirportCityCode,sourceAirportCity,destinationAirportCode,"
						+ "destinationAirport,destinationAirportCityCode,destinationAirportCity,aircraftName,"
						+ "aircraft,arrivalTime,departureTime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Object arguments[] = {airlineName,airlineCode,airlineNumber,sourceAirport,
				sourceAirportCode,sourceAirportCityCode,sourceAirportCity,destinationAirportCode,
				destinationAirport,destinationAirportCityCode,destinationAirportCity,aircraft,
				aircraftCode,arrivalTime,departureTime};
		
		if( MySQL.execute(mysql, arguments) == false)
		{
			return false;
		}
		
		routeID = MySQL.getLastInsertID();
		return true;
	}
	
	
	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
