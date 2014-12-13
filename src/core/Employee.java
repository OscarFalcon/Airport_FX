package core;

import java.util.ArrayList;

import database.MySQL;
import javafx.beans.property.SimpleStringProperty;

public class Employee extends Person  {

	private SimpleStringProperty type;
	private SimpleStringProperty status;
	private SimpleStringProperty availability;

	
	protected Employee(String id, String first, String last, String username,
			String email, String phone, String street, String city,
			String state, String zip, String type, String status, String availability) {
		
		super(id, first, last, username, email, phone, street, city, state, zip);
		setType(type);
		setStatus(status);
		setAvailability(availability);
	}
	

	
	@Override
	public boolean insert() {
		
		boolean status = false;
		
		String mysql = "INSERT INTO person(firstName,lastName,userName,password,email,telephone,street,city,state,zip)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";

		Object arguments1[] = { firstName.get(), lastName.get(), username.get(),
				password.get(), email.get(), phone.get(), street.get(),
				city.get(), state.get(), zip.get() };

		status = MySQL.execute(mysql, arguments1);
		if(status == false){
			return status;
		}
		
		mysql = "INSERT INTO employee(type) VALUES (?)";
		
		Object arguments2[] = {type.get()};
		
		status = MySQL.execute(mysql, arguments2);
		return status;
	}

	@Override
	public boolean delete() {
		String mysql = "DELETE from employee where employeeID = ?";

		Object arguments[] = { id.get() };

		return MySQL.execute(mysql, arguments);
	}

	@Override
	public boolean save() {
		boolean status = false;
		String mysql = "UPDATE person set firstName = ?, lastName = ?, userName = ?, password = ?, email = ?, telephone = ?,"
				+ "street = ?, city = ?, state = ?, zip = ?";

		Object arguments1[] = { firstName.get(), lastName.get(), username.get(),
				password.get(), email.get(), phone.get(), street.get(),
				city.get(), state.get(), zip.get() };

		status = MySQL.execute(mysql, arguments1);
		if(status == false){
			return status;
		}
		
		mysql = "UPDATE employee set type = ?, status = ?, availability = ?";
		
		Object arguments2[] = {type.get(), this.status.get(), availability.get()};
		
		status = MySQL.execute(mysql, arguments2);
		return status;
	}
	
	public static Employee retrieveEmployee(String username, String password)
    {
		String query = "SELECT person.userID, person.firstName,person.lastName,"
						+"person.userName, person.email,person.telephone,person.street,"
						+ "person.city,person.state,person.zip,employee.type,employee.status,"
						+"employee.availability"
						+ "FROM person INNER JOIN employee on person.userID = employee.employeeID"
						+ "WHERE person.userID = ? AND person.password = ?";
		
		Object[] arguments = {username, password};
		
		int [] resultType = {MySQL.INTEGER, MySQL.STRING,MySQL.STRING, MySQL.STRING, 
		MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, 
		MySQL.STRING, MySQL.STRING, MySQL.BOOLEAN};
		
		ArrayList<Object[]> result = MySQL.executeQuery(query, arguments, resultType);
		if(result.isEmpty())
		{
			return null;
		}
		Object[] tmp = result.get(0);
		
		Employee employee = new Employee(tmp[0].toString(),tmp[1].toString(),tmp[2].toString(),
		tmp[3].toString(),tmp[4].toString(),tmp[5].toString(),tmp[6].toString(),
		tmp[7].toString(),tmp[8].toString(),tmp[9].toString(),tmp[10].toString(), 
		tmp[11].toString(), tmp[12].toString());

		return employee;	
    }

	
	
	public String getType() {
		return type.get();
	}

	public void setType(String type) {
		this.type.set(type);
	}

	public String getStatus() {
		return status.get();
	}

	public void setStatus(String status) {
		this.status.set(status);
	}

	public String getAvailability() {
		return type.get();
	}

	public void setAvailability(String availability) {
		this.availability.get();
	}

}
