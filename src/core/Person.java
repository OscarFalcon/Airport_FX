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
    private  SimpleStringProperty street;
    private  SimpleStringProperty city;
    private  SimpleStringProperty state;
    private  SimpleStringProperty zip;
    private  SimpleStringProperty type;
    
   
 
    
    private Person(String id,String first,String last,String username,String email,String phone,
    				String street, String city, String state, String zip, String type)
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
    	this.type 		= new SimpleStringProperty(type);
    
    }
    
	public static Person retrievePerson(String username, String password)
    {
		String query = "SELECT userID, firstName, lastName, userName, email, telephone, street, city, state, zip, type "
							+ "FROM userinfo WHERE userName = ? && password = ?";
		
		Object[] arguments = {username, password};
		
		int [] resultType = {MySQL.INTEGER, MySQL.STRING,MySQL.STRING, MySQL.STRING, 
		MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING};
		
		ArrayList<Object[]> result = MySQL.executeQuery(query, arguments, resultType);
		if(result == null)
		{
			return null;
		}
		Object[] tmp = result.get(0);
		
		Person person = new Person(tmp[0].toString(),tmp[1].toString(),tmp[2].toString(),
		tmp[3].toString(),tmp[4].toString(),tmp[5].toString(),tmp[6].toString(),
		tmp[7].toString(),tmp[8].toString(),tmp[9].toString(),tmp[10].toString());

		return person;	
    }
	
	public boolean save()
	{
		String statement = "UPDATE userinfo set firstName = ?, lastName = ?,email = ?, telephone = ?,"
							+ "street = ?, city = ?, state = ?, zip = ? WHERE userID = ?";
		
		Object[] arguments = {firstName.get(),lastName.get(),email.get(),phone.get(), street.get(),
				             city.get(), state.get(), zip.get(),id.get()};
	
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
    
    public String getType()
    {
    	return type.get();
    }
    public void setType(String type)
    {
    	this.type.set(type);
    }
    
}
