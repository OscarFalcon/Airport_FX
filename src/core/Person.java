package core;

import java.util.ArrayList;

import database.MySQL;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

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
    
     
    /* Initialize the user if authorization was successful */
    
    public boolean initialize(String username, String password){
		boolean initialized = false;
		String userData = "SELECT userID, firstName, lastName, userName, password, email, telephone, type FROM userinfo WHERE userName = ? && password = ?";
		
		Object[] arguments = {username, password};
		int [] resultType = {MySQL.INTEGER, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING};
		ArrayList<Object[]> table = MySQL.executeQuery(userData, arguments, resultType);
		if(table == null)
		{
			return false;
		}
		else if(table.size() > 0)
		{
<<<<<<< HEAD
			authorized = true;
		}
		getId();
		setFirstName(table.get(1).toString());
		setLastName(table.get(2).toString());
		setEmail(table.get(4).toString());
		setPhone(table.get(5).toString());
		setType(table.get(6).toString());
=======
			initialized = true;
		}	
>>>>>>> c940845e5c80b87c3d1683ae88b0088bbebe0a8c
		
		System.out.println("Initialization successful");
	return initialized;	
    } 
  
    /* Authorize User */
    
	public boolean authorized(String username, String password)
    {
    	boolean authorized = false;
		String usersTable = "SELECT userID FROM userinfo WHERE userName = ? && password = ?";
		
		Object[] arguments = {username, password};
		int [] resultType = {MySQL.INTEGER};
		
		ArrayList<Object[]> table = MySQL.executeQuery(usersTable, arguments, resultType);
		if(table == null){
			System.out.println("Error: Authentication Failed!!!");
			return false;
		}
		if(table.size() > 0)
			authorized = true;
		System.out.println("Authentication Successful!!!");
		System.out.println("Attempting to initialize user");
		initialize(username,password);

	return authorized;	
    }
	
	/* Getters and Setters */
    
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
<<<<<<< HEAD
    public String getType()
    {
    	return type.get();
    }
=======
>>>>>>> c940845e5c80b87c3d1683ae88b0088bbebe0a8c
    public void setType(String type)
    {
    	this.type.set(type);
    }
    
}
