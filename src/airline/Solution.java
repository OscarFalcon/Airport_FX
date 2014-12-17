package airline;

import java.sql.Date;
import java.util.ArrayList;

import core.SavableObject;
import database.MySQL;

public class Solution extends SavableObject
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
	
	private Integer solutionID = null;				//id of this solution in the database;
	
	
	private ArrayList<Route> routes;
	
	
	
	public Solution()
	{
		routes = new ArrayList<Route>();
	}
	
	
	public void setSolutionID(int ID){
		this.solutionID = ID;
	}
	
	public Integer getSolutionID()
	{
		return solutionID;
		
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
	public Date getDepartureDate()
	{
		return Date.valueOf(departureTime.split("T")[0]);
	}
	public String getDepartureTime()
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
	public void setOriginAirportCode(String code)//ORIGIN AIRPORT CODE
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


	@Override
	public boolean save()
	{
		
		return false;
	}


	@Override
	public boolean insert()
	{
		String insertSolutionString = null;
		String insertSolutionRouteString = null;
		
		
		
		insertSolutionString = "INSERT INTO solution(saleTotal,arrivalTime,departureTime,originCityCode,originCity,"
				+"originAirportCode,originAirport,destinationCityCode,destinationCity,destinationAirportCode,"
				+ "destinationAirport) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

		Object arguments[] = {saleTotal,arrivalTime,departureTime,originCityCode,originCity,
								originAirportCode,originAirport,destinationCityCode,destinationCity,
								destinationAirportCode,destinationAirport};

		if(MySQL.execute(insertSolutionString, arguments) == false)
		{
			return false;
		}
		
		solutionID = MySQL.getLastInsertID();
		insertSolutionRouteString = "INSERT INTO solution_routes(solutionID,routeID) VALUES (?,?)";
		
		Integer routeID = null;
		for(Route route : routes)
		{
			
			route.insert();
			routeID = route.getRouteID();
			
			if( MySQL.execute(insertSolutionRouteString, new Object[]{solutionID,routeID}) == false )
			{
				return false;	
			}
	
		}
		
		return true;		
	}


	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean retrieveRoutes() {
		String mysql ="SELECT routeID FROM solution_routes WHERE solutionID = ?";
		
		int[] result_types = {MySQL.INTEGER};
		
		Object[] arguments = {getSolutionID().toString()};
		
		ArrayList<Object[]> routeIDs = MySQL.executeQuery(mysql, arguments, result_types);
		
		for(int i = 0; i<routeIDs.size(); i++){
			
			Object[] routeID = routeIDs.get(i);
			
			mysql = "SELECT airlineName,sourceAirportCode,sourceAirportCity,destinationAirportCode,destinationAirportCity,arrivalTime,departureTime"
					+ " FROM route WHERE routeID = ?";
			
			int[] result_types1 = {MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING};
			
			Object[] argumentsTMP = {routeID[0].toString()};
			
			ArrayList<Object[]> result = MySQL.executeQuery(mysql, argumentsTMP, result_types1);
			
			Object[] routeObj = result.get(0);
			Route route = new Route();
			route.setAirlineName(routeObj[0].toString());
			route.setSourceAirportCode(routeObj[1].toString());
			route.setSourceAirportCity(routeObj[2].toString());
			route.setDestinationAirportCode(routeObj[3].toString());
			route.setDestinationAirportCity(routeObj[4].toString());
			route.setArrivalTime(routeObj[5].toString());
			route.setDepartureTime(routeObj[6].toString());
			
			routes.add(route);
		}
		
		return true;
	}
	
	
	
}
