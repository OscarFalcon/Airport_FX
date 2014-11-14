package application;

import java.util.ArrayList;

import database.MySQL;

public class mySQLFetch {

	public boolean authorizeUser(String username, String password){
		boolean authorized = false;
		String usersTable = "SELECT ID FROM users WHERE username = ? && password = ?";
		
		Object[] arguments = {username, password};
		int [] resultType = {MySQL.INTEGER};
		
		ArrayList<Object[]> table = MySQL.executeQuery(usersTable, arguments, resultType);
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
	
	
	
}
