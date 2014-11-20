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
    
   
 
    
    private Person(String id,String first,String last,String username,String email,String phone,String type)
    {
    	this.id.set(id);
    	this.firstName.set(first);
    	this.lastName.set(last);
    	this.username.set(username);
    	this.email.set(email);
    	this.phone.set(phone);
    	this.type.set(type);
    
    }
    
	public static Person retrievePerson(String username, String password)
    {
		String query = "SELECT userID, firstName, lastName, userName, email, telephone, type "
							+ "FROM userinfo WHERE userName = ? && password = ?";
		Object[] arguments = {username, password};
		int [] resultType = {MySQL.INTEGER, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, 
								MySQL.STRING, MySQL.STRING, MySQL.STRING};
		
		
		
		ArrayList<Object[]> result = MySQL.executeQuery(query, arguments, resultType);
		if(result == null)
		{
			return null;
		}
		
		Person person = new Person(result.get(0).toString(),result.get(1).toString(),result.get(2).toString(),
										result.get(3).toString(),result.get(4).toString(),result.get(5).toString(),
										result.get(6).toString());

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
