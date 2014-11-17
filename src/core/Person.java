package core;

import java.util.ArrayList;

import database.MySQL;
import javafx.beans.property.SimpleStringProperty;

public class Person {

	
	private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;
    
    public Person(String fName, String lName, String email,String phone) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }
    
	
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String fName) {
        firstName.set(fName);
    }
        
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String fName) {
        lastName.set(fName);
    }
    
    public String getEmail() {
        return email.get();
    }
    public void setEmail(String fName) {
        email.set(fName);
    }
        
    
    
    
    
    
    
	
    public boolean authorize(String username, String password){
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
    
    
    
    
	
}
