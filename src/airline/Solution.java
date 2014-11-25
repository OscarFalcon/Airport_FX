package airline;

import java.util.ArrayList;

public class Solution
{
	
	private String saleTotal; 				//ok
	
	private String arrivalTime; 			//ok?
	private String departureTime;			//ok
	
	private String originCityCode;			//ok
	private String originCity;				//ok
	private String originAirportCode;		//ok
	private String originAirport;			//ok
	
	private String destinationCityCode;		//ok?
	private String destinationCity;			//ok?
	private String destinationAirport;		//ok?
	private String destinationAirportCode;	//ok?
	
	
	private ArrayList<Route> routes;
	
	
	
	public Solution()
	{
		routes = new ArrayList<Route>();
		
		
		
		
	}
	
	
	public ArrayList<Route> getRoutes()
	{
		return routes;
	}
	public void setSaleTotal(String total)
	{
		this.saleTotal = total;
		
	}
	public String getSaleTotal()
	{
		return saleTotal;
	}
	public void setArrivalTime(String time)
	{
		this.arrivalTime = time;
	}
	public String getArrivalTime()
	{
		return arrivalTime;
	}
	public void setdepartureTime(String time)
	{
		this.departureTime = time;
	}
	public String getDepatureTime()
	{
		return departureTime;
	}
	public void setOriginCityCode(String code)
	{
		this.originCityCode = code;
	}
	public String getOriginCityCode()
	{
		return originCityCode;
	}
	public void setOriginCity(String city)
	{
		this.originCity = city;
	}
	public String getOriginCity()
	{
		return originCity;
	}
	public void setDestinationCityCode(String code)
	{
		this.destinationCityCode = code;
	}
	public String getDestinationCityCode()
	{
		return destinationCityCode;
	}
	public void setDestinationCity(String city)
	{
		this.destinationCity = city;
	}
	public String getDestinationCity()
	{
		return destinationCity;
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
	public void setOriginAriportCode(String code)
	{
		this.originAirportCode = code;
	}
	public String getOriginAirportCode()
	{
		return originAirportCode;
	}
	public void setOriginAirport(String airport)
	{
		this.originAirport = airport;
	}
	public String getOriginAirport()
	{
		return originAirport;
	}
	
	public String toString()
	{
		return saleTotal + ": " + originCityCode;
	}
	
	
	
}
