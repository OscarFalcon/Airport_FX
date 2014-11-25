package airline;

public class Route
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
	
	
	public void setAirlineName(String airline)
	{
		this.airlineName = airline;
	}
	public String getAirlineName()
	{
		return airlineName;
	}
	public void setAirlineCode(String code)
	{
		this.airlineCode = code;
	}
	
	public String getAirlineCode()
	{
		return airlineCode;
	}
	public void setAirlineNumber(String no)
	{
		this.airlineNumber = no;
	}
	public String getAirlineNumber()
	{
		return airlineNumber;
	}
	public void setSourceAirport(String airport)
	{
		this.sourceAirport = airport;
	}
	public String getSourceAirport()
	{
		return sourceAirport;
	}
	public void setSourceAirportCode(String code)
	{
		this.sourceAirportCode = code;
	}
	public String getSourceAirportCode()
	{
		return sourceAirportCode;
	}
	public void setSourceAirportCityCode(String code)
	{
		this.sourceAirportCityCode = code;
	}
	public String getSourceAirportCityCode()
	{
		return sourceAirportCityCode;
	}
	public void setSourceAirportCity(String city)
	{
		this.sourceAirportCity = city;
	}
	public String getSourceAirportCity()
	{
		return sourceAirportCity;
	}
	public void setDestinationAirportCode(String code)
	{
		this.destinationAirportCode = code;
	}
	public String getDestinationAirportCode()
	{
		return destinationAirportCode;
	}
	public void setDestinationAirport(String airport)
	{
		this.destinationAirport = airport;
	}
	public String getDestinationAirport()
	{
		return destinationAirport;
	}
	public void setDestinationAirportCityCode(String code)
	{
		this.destinationAirportCityCode = code;
	}
	public String getDestinationAirportCityCode()
	{
		return destinationAirportCityCode;
	}
	public void setDestinationAirportCity(String city)
	{
		this.destinationAirportCity = city;
	}
	public String getDestinationAirportCity()
	{
		return destinationAirportCity;
	}
	public void setAircraftCode(String code)
	{
		this.aircraftCode = code;
	}
	public String getAircraftCode()
	{
		return aircraftCode;
	}
	public void setAircraft(String aircraft)
	{
		this.aircraft = aircraft;
	}
	public String getAircraft()
	{
		return aircraft;
	}
	public void setArrivalTime(String time)
	{
		this.arrivalTime = time;
	}
	public String getArrivalTime()
	{
		return arrivalTime;
	}
	public void setDepartureTime(String time)
	{
		this.departureTime = time;
	}
	public String getDepartureTime()
	{
		return departureTime;
	}
	

}
