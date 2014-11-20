package core;

import java.util.ArrayList;

import database.MySQL;
import javafx.beans.property.SimpleStringProperty;

public class Person {

	
	private  SimpleStringProperty id;
	private  SimpleStringProperty firstName;
    private  SimpleStringProperty lastName;
    private  SimpleStringProperty username;
    private  SimpleStringProperty email;
    private  SimpleStringProperty phone;
    private  SimpleStringProperty type;
    
   
    public Person(String username,String password)
    {
    	authorized(username,password);
    }
    
     
    public boolean initialize(String username, String password){
		boolean authorized = false;
		String usersTable = "SELECT userID,username,password,email,telephone,type FROM users WHERE userID = ? && username = ? && password = ?"
				+ 																				   "email = ? && telephone = ? && type = ?";
		
		Object[] arguments = {username, password};
		int [] resultType = {MySQL.INTEGER,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING};
		
		ArrayList<Object[]> table = MySQL.executeQuery(usersTable, arguments, resultType);
		if(table == null)
		{
			return false;
		}
		if(table.size() > 0)
		{
			authorized = true;
		}
		getId();
		setFirstName(table.get(1).toString());
		setLastName(table.get(2).toString());
		setEmail(table.get(4).toString());
		setPhone(table.get(5).toString());
		setType(table.get(6).toString());
		
	return authorized;	
    } 
    
    public boolean authorized(String username, String password)
    {
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
		
		initialize(username,password);
		
	return authorized;	
    }
    
    public String getId()
    {
    	return id.get();
    } 
    public String getFirstName() 
    {
        return firstName.get();
    }
    public void setFirstName(String fName)
    {
        firstName.set(fName);
    }
    public String getLastName()
    {
        return lastName.get();
    }
    public void setLastName(String fName) 
    {
        lastName.set(fName);
    }
    public String getUserName()
    {
    	return username.get();
    }
    public String getEmail() 
    {
        return email.get();
    }
    public void setEmail(String fName)
    {
        email.set(fName);
    }
    public String getPhone()
    {
    	return phone.get();
    }
    public void setPhone(String phone)
    {
    	this.phone.set(phone);
    }
    public String getType()
    {
    	return type.get();
    }
    public void setType(String type)
    {
    	this.type.set(type);
    }
    
}
