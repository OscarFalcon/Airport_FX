package application;

import java.sql.Date;
import java.util.ArrayList;

import core.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import database.MySQL;

public class mySQLFetch {

	public boolean authorizeUser(String username, String password){
		boolean authorized = false;
		String usersTable = "SELECT ID FROM users WHERE username = ? && password = ?";
		
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
	
	public boolean resetPassword(String username, String password){
		boolean reset = false;
		String resetPassword = "UPDATE users SET password = ? WHERE username = ?";
		Object[] arguments = {password, username};
		reset = MySQL.execute(resetPassword, arguments);
		
		return reset;
	}
	
	public ObservableList<Flight> searchFlightDates(String srcLocation, String desLocation, Date srcDate, Date depDate){
		String flightDataTable = "SELECT flightID,airline,flightNumber,depDate,timeDep,arrDate,timeArr,srcLocation,desLocation,price FROM flightinfo WHERE srcLocation = ? && desLocation = ?"
								+ "&& arrDate = ? && depDate = ?";
		
		
		Object[] arguments = {srcLocation, desLocation, srcDate, depDate};
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
	
	// search flights by source and destination
	
	public boolean searchFlightNoDates(String srcLocation, String desLocation){
		boolean found = false;
		String flightDataTable = "SELECT flightID FROM flightinfo WHERE srcLocation = ? && desLocation = ?";
		Object[] arguments = {srcLocation, desLocation};
		int [] resultType = {MySQL.INTEGER};
		ArrayList<Object[]> table = MySQL.executeQuery(flightDataTable, arguments, resultType);
		if(table == null){
			return false;
		}
		if(table.size() > 0)
			found = true;
		return found;
	}
	
	
	
	
	
}
