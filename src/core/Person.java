package core;

import java.util.ArrayList;

import database.MySQL;
import javafx.beans.property.SimpleStringProperty;

public class Person extends SavableObject
{

	
	protected  SimpleStringProperty id;
	protected  SimpleStringProperty firstName;
	protected  SimpleStringProperty lastName;
	protected  SimpleStringProperty username;
	protected  SimpleStringProperty password;
	protected  SimpleStringProperty email;
	protected  SimpleStringProperty phone;
	protected  SimpleStringProperty street;
	protected  SimpleStringProperty city;
	protected  SimpleStringProperty state;
    protected  SimpleStringProperty zip;
    
    
   
 
    
    protected Person(String id,String first,String last,String username,String email,String phone,
    				String street, String city, String state, String zip)
    {
    	this.id 		= new SimpleStringProperty(id);
    	this.firstName 	= new SimpleStringProperty(first);
    	this.lastName 	= new SimpleStringProperty(last);
    	this.username 	= new SimpleStringProperty(username);
    	this.email 		= new SimpleStringProperty(email);
    	this.phone 		= new SimpleStringProperty(phone);
    	this.street     = new SimpleStringProperty(street);
    	this.city       = new SimpleStringProperty(city);
    	this.state      = new SimpleStringProperty(state);
    	this.zip        = new SimpleStringProperty(zip);
    
    }
    
	public static Person retrievePerson(String username, String password)
    {
		String query = "SELECT userID, firstName, lastName, userName, email, telephone, street, city, state, zip "
							+ "FROM person WHERE userName = ? && password = ?";
		
		Object[] arguments = {username, password};
		
		int [] resultType = {MySQL.INTEGER, MySQL.STRING,MySQL.STRING, MySQL.STRING, 
		MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING};
		
		ArrayList<Object[]> result = MySQL.executeQuery(query, arguments, resultType);
		if(result.isEmpty())
		{
			return null;
		}
		Object[] tmp = result.get(0);
		
		Person person = new Person(tmp[0].toString(),tmp[1].toString(),tmp[2].toString(),
		tmp[3].toString(),tmp[4].toString(),tmp[5].toString(),tmp[6].toString(),
		tmp[7].toString(),tmp[8].toString(),tmp[9].toString());

		return person;	
    }
	
	public boolean resetPassword(String newPassword)
	{
		boolean reset = false;
		String resetPassword = "UPDATE userinfo SET password = ? WHERE userName = ?";
		Object[] arguments = {newPassword, username.get()};
		reset = MySQL.execute(resetPassword, arguments);
		
		return reset;
	}
	
	
	
	/* Person interface common methods */

	@Override
	public boolean insert()
	{
		return false;
	}

	@Override
	public boolean delete() {
		return false;
	}
	
	@Override
	public boolean save()
	{
		return false;
	}
	
	
	
	/* Getters and Setters */
    
    public String getId()
    {
    	return id.get();
    } 
    
    public void setId(String id)
    {
    	this.id.set(id);
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
    
    public String getStreet()
    {
    	return street.get();
    }
    public void setStreet(String street)
    {
    	this.street.set(street);
    }
    
    public String getCity()
    {
    	return city.get();
    }
    public void setCity(String city)
    {
    	this.city.set(city);
    }
    
    public String getState()
    {
    	return state.get();
    }
    public void setState(String state)
    {
    	this.state.set(state);
    }
    
    public String getZip()
    {
    	return zip.get();
    }
    public void setZip(String zip)
    {
    	this.zip.set(zip);
    }
   
	
    
}