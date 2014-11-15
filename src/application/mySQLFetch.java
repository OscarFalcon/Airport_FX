package application;

import java.sql.Date;
import java.util.ArrayList;

import database.MySQL;

public class mySQLFetch {

	// authorize a user logging into the system
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
	
	// Reset a forgotten password for a user
	public boolean resetPassword(String username, String password){
		boolean reset = false;
		String resetPassword = "UPDATE users SET password = ? WHERE username = ?";
		Object[] arguments = {password, username};
		reset = MySQL.execute(resetPassword, arguments);
		
		return reset;
	}
	
	// If case 0 then choose all flight information
	// If case 0 then search by src and des
	public boolean searchFlights(int selection){
		Date leaveDate = null;
		Date returnDate = null;
		
		switch(selection) {
			case 0: searchFlightDates("src", "des" , leaveDate, returnDate);
			case 1: searchFlightNoDates("src", "des");
			default: System.out.println("No available flights matched specified search criteria"); 
					 return false;
					 
		}
	}
	
	// search flights by source, destination,leave date, and return date
	public boolean searchFlightDates(String src, String des, Date leaveDate, Date returnDate){
		boolean found = false;
		
		return found;
	}
	
	// search flights by source and destination
	public boolean searchFlightNoDates(String srcLocation, String desLocation){
		boolean found = false;
		String flightDataTable = "SELECT flightID FROM flightinfo WHERE airline = ? && flightNumber = ?";
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
