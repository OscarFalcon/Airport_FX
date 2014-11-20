package core;

import java.util.ArrayList;

import database.MySQL;
import javafx.beans.property.SimpleStringProperty;

public class Person 
{

	
	private  SimpleStringProperty id;
	private  SimpleStringProperty firstName;
    private  SimpleStringProperty lastName;
    private  SimpleStringProperty username;
    private  SimpleStringProperty email;
    private  SimpleStringProperty phone;
    private  SimpleStringProperty type;
    
   
 
    
    private Person(String id,String first,String last,String username,String email,String phone,String type)
    {
    	this.id 		= new SimpleStringProperty(id);
    	this.firstName 	= new SimpleStringProperty(first);
    	this.lastName 	= new SimpleStringProperty(last);
    	this.username 	= new SimpleStringProperty(username);
    	this.email 		= new SimpleStringProperty(email);
    	this.phone 		= new SimpleStringProperty(phone);
    	this.type 		= new SimpleStringProperty(type);
    
    }
    
	public static Person retrievePerson(String username, String password)
    {
		String query = "SELECT userID, firstName, lastName, userName, email, telephone, type "
							+ "FROM userinfo WHERE userName = ? && password = ?";
		
		Object[] arguments = {username, password};
		
		int [] resultType = {MySQL.INTEGER, MySQL.STRING,MySQL.STRING, MySQL.STRING, 
								MySQL.STRING, MySQL.STRING, MySQL.STRING};
		
		ArrayList<Object[]> result = MySQL.executeQuery(query, arguments, resultType);
		if(result == null)
		{
			return null;
		}
		Object[] tmp = result.get(0);
		
		Person person = new Person(tmp[0].toString(),tmp[1].toString(),tmp[2].toString(),
										tmp[3].toString(),tmp[4].toString(),tmp[5].toString(),
										tmp[6].toString());

		return person;	
    }
	
	public boolean save()
	{
		String statement = "UPDATE userinfo set firstName = ?, lastName = ?,email = ?, telephone = ?"
							+ "WHERE userID = ?";
		
		Object[] arguments = {firstName.get(),lastName.get(),email.get(),phone.get(),id.get()};
	
		return MySQL.execute(statement, arguments);
	}
	
	
	
	
	
	/* Getters and Setters */
    
    public String getId()
    {
    	return id.get();
    } 
    
    
    public String getUserName()
    {
    	return username.get();
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
  
    
}
