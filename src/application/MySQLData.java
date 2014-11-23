package application;

import java.sql.Date;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import core.AirportLookups;
import core.Flight;
import database.MySQL;

public class MySQLData {
	
	
	//Get authorized user info
	public boolean authorizeUser(String username, String password){
		boolean authorized = false;
		String usersTable = "SELECT userID FROM userinfo WHERE username = ? && password = ?";
		
		Object[] arguments = {username, password};
		int [] resultType = {MySQL.INTEGER};
		
		ArrayList<Object[]> table = MySQL.executeQuery(usersTable, arguments, resultType);
		if(table == null){
			return false;
		}
		if(table.size() > 0)
			authorized = true;
		
	return authorized;	
	}
	
	//Reset password
	public boolean resetPassword(String username, String password){
		boolean reset = false;
		String resetPassword = "UPDATE userinfo SET password = ? WHERE username = ?";
		Object[] arguments = {password, username};
		reset = MySQL.execute(resetPassword, arguments);
		
		return reset;
	}
	
	/*Query database by src, des, srcDate, desDate (Get flight info for round trip */
	public ObservableList<Flight> searchFlightRoundTrip(String srcLocation, String desLocation, Date srcDate, Date depDate){
		String flightDataTable = "SELECT flightID,airline,flightNumber,depDate,timeDep,arrDate,timeArr,srcLocation,desLocation,price FROM flightinfo WHERE srcLocation = ? && desLocation = ? && depDate = ? ||"
				+ "desLocation = ? && srcLocation = ? && depDate = ?";
		
		
		Object[] arguments = {srcLocation, desLocation, srcDate, desLocation, srcLocation, depDate};
		int [] resultType = {MySQL.INTEGER,MySQL.STRING,MySQL.STRING,MySQL.DATE,MySQL.TIME,MySQL.DATE,MySQL.TIME,MySQL.STRING,MySQL.STRING,MySQL.FLOAT};
		ArrayList<Object[]> table = MySQL.executeQuery(flightDataTable, arguments, resultType);
		
		if(table == null)
		{
			return null;
		}
		
		ObservableList<Flight> data = FXCollections.observableArrayList();
		for(int i = 0; i< table.size();i++)
		{
			Object[] tmp;
			
			tmp = table.get(i);
			Flight flight = new Flight(tmp);
			data.add(flight);
			
		}
		return data;
		
		
	}

	/* Get flight info for one way trips */
	
	public ObservableList<Flight> searchFlightOneWay(String srcLocation, String desLocation, Date srcDate){
		String flightDataTable = "SELECT flightID,airline,flightNumber,depDate,timeDep,arrDate,timeArr,srcLocation,desLocation,price FROM flightinfo WHERE srcLocation = ? && desLocation = ?"
								+ "&& depDate = ?";
		Object[] arguments = {srcLocation, desLocation, srcDate};
		int [] resultType = {MySQL.INTEGER,MySQL.STRING,MySQL.STRING,MySQL.DATE,MySQL.TIME,MySQL.DATE,MySQL.TIME,MySQL.STRING,MySQL.STRING,MySQL.FLOAT};
		ArrayList<Object[]> table = MySQL.executeQuery(flightDataTable, arguments, resultType);
		
		if(table == null)
		{
			return null;
		}
		
		ObservableList<Flight> data = FXCollections.observableArrayList();
		for(int i = 0; i< table.size();i++)
		{
			Object[] tmp;
			
			tmp = table.get(i);
			Flight flight = new Flight(tmp);
			data.add(flight);
			
			
		}
		return data;
		
		
	}
	
	//get Airport Codes
	public ObservableList<AirportLookups> getAirportCodes(String code)
	{
		
		String airportDataTable = "SELECT ID, city, airport, state, iata FROM airport_lookups WHERE ID = ?";
		Object [] arguments = {code};
		int [] resultType ={MySQL.INTEGER,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING}; 
		ArrayList<Object[]> table = MySQL.executeQuery(airportDataTable, arguments, resultType);
		
		if(table == null)
		{
			return null;
		}
		
		ObservableList<AirportLookups> data = FXCollections.observableArrayList();
		for(int i = 0; i< table.size();i++)
		{
			Object[] tmp;
			
			tmp = table.get(i);
			AirportLookups lookups = new AirportLookups(tmp);
			data.add(lookups);
			
		}
		
		return data;
	}
	
	//Reset password
		public boolean createPassengerAccount(String first,String last,String username, String password, String email,String phone,
				String street, String city, String state, String zip){
			boolean created = false;
			String createAccount = " insert into userinfo (firstName, lastName, userName, password, email, telephone, street, city, state, zip)"
	        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Object[] arguments = {first, last, username, password, email, phone, street, city, state, zip};
			created = MySQL.execute(createAccount, arguments);
			
			if(created == false)
			{
				return created;
			}
			return created;
		}
}
