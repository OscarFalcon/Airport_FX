package airline;

import java.sql.Date;
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
	
	
	public ArrayList<Route> getRoutes() //ROUTES
	{
		return routes;
	}
	
	public void setSaleTotal(String total)//SALE TOTAL
	{
		this.saleTotal = total;
		
	}
	public String getSaleTotal()
	{
		return saleTotal;
	}
	
	
	
	public Date getArrivalDate()
	{
		return Date.valueOf(arrivalTime.split("T")[0]);
	}
	public String getArrivalTime()
	{
		return arrivalTime.split("T")[1];
	}
	public Date getDepatureDate()
	{
		return Date.valueOf(departureTime.split("T")[0]);
	}
	public String getDepatureTime()
	{
		return departureTime.split("T")[1];
	}
	
	
	
	
	public void setArrivalTime(String time)//ARRIVAL TIME
	{
		this.arrivalTime = time;
	}
	public String getUnformattedArrivalTime()
	{
		return arrivalTime;
	}
	public void setdepartureTime(String time)//DEPARTURE TIME
	{
		this.departureTime = time;
	}
	public String getUnformattedDepatureTime()
	{
		return departureTime;
	}
	public void setOriginCityCode(String code)//ORIGIN CITY CODE
	{
		this.originCityCode = code;
	}
	public String getOriginCityCode()
	{
		return originCityCode;
	}
	public void setOriginCity(String city)//ORIGIN CITY
	{
		this.originCity = city;
	}
	public String getOriginCity()
	{
		return originCity;
	}
	public void setDestinationCityCode(String code)//DESTINATION CITY CODE
	{
		this.destinationCityCode = code;
	}
	public String getDestinationCityCode()
	{
		return destinationCityCode;
	}
	public void setDestinationCity(String city)//DESTINATION CITY
	{
		this.destinationCity = city;
	}
	public String getDestinationCity()
	{
		return destinationCity;
	}
	public void setDestinationAirportCode(String code)//DESTINATION AIRPORT CODE
	{
		this.destinationAirportCode = code;
	}
	public String getDestinationAirportCode()
	{
		return destinationAirportCode;
	}
	public void setDestinationAirport(String airport)//DESTINATION AIRPORT
	{
		this.destinationAirport = airport;
	}
	public String getDestinationAirport()
	{
		return destinationAirport;
	}
	public void setOriginAriportCode(String code)//ORIGIN AIRPORT CODE
	{
		this.originAirportCode = code;
	}
	public String getOriginAirportCode()
	{
		return originAirportCode;
	}
	public void setOriginAirport(String airport)//ORIGIN AIRPORT
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
