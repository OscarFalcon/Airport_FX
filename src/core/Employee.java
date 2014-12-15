package core;

import java.util.ArrayList;

import database.MySQL;

public class Employee extends Person  {

	private String type;
	private String status;
	private String availability;
	private String employeeID;
	private String userID;
	private String username;
	private String password;
	
	
	
	protected Employee(String id, String first, String last, String username,
			String password, String email, String phone, String street, String city,
			String state, String zip, String type, String status, String availability) {
		
		super(id, first, last,username,email, phone, street, city, state, zip);
		this.type = type;
		this.status  = status;
		this.availability =  availability;
		this.employeeID = id;
		this.password = password;
		this.username = username;
	
	}
	

	
	@Override
	public boolean insert() {
				
		String mysql = "insert into person(firstName,lastName,email,telephone,street,city,state,zip) VALUES "
				+ "(?,?,?,?,?,?,?,?);";

		Object arguments1[] = {firstName.get(), lastName.get(), username,
				password, email.get(), phone.get(), street.get(),
				city.get(), state.get(), zip.get() };

		
		 if(MySQL.execute(mysql, arguments1) == false)
		 {
			 return false;
		 }
		
		
		mysql = "INSERT INTO employee(type,userID,username,password) VALUES (?,?,?,?);";
		
		Object arguments2[] = {type,userID,username,password};
		
		 return MySQL.execute(mysql, arguments2);
	
	}

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public boolean save() {
		boolean status = false;
		String mysql = "UPDATE person set firstName = ?, lastName = ?, userName = ?, password = ?, email = ?, telephone = ?,"
				+ "street = ?, city = ?, state = ?, zip = ?";

		Object arguments1[] = { firstName.get(), lastName.get(), username,
				password, email.get(), phone.get(), street.get(),
				city.get(), state.get(), zip.get() };

		status = MySQL.execute(mysql, arguments1);
		if(status == false){
			return status;
		}
		
		mysql = "UPDATE employee set type = ?, status = ?, availability = ?";
		
		Object arguments2[] = {type, status, availability};
		
		status = MySQL.execute(mysql, arguments2);
		return status;
	}
	
	public static Employee retrieveEmployee(String username, String password)
    {
		String query = "SELECT person.userID,person.firstName,person.lastName,"
						+"employee.username,employee.password,person.email,person.telephone,person.street,"
						+ "person.city,person.state,person.zip,employee.type,employee.status,"
						+"employee.availability "
						+ "FROM person INNER JOIN employee on person.userID = employee.userID "
						+ "WHERE employee.username = ? && employee.password = ?";
		
		Object[] arguments = {username, password};
		int [] resultType = {MySQL.INTEGER, MySQL.STRING,MySQL.STRING, MySQL.STRING, 
		MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, 
		MySQL.STRING, MySQL.STRING, MySQL.STRING,MySQL.STRING};
		
		ArrayList<Object[]> result = MySQL.executeQuery(query, arguments, resultType);
		
		if(result.isEmpty())
		{
			return null;
		}
		
		Object[] tmp = result.get(0);
		
		String availability = (String) tmp[12], status = (String) tmp[11];
		
		if(availability == null) availability = "";
		if(status == null ) status = "";
		
		
		Employee employee = new Employee(tmp[0].toString(),tmp[1].toString(),tmp[2].toString(),
		tmp[3].toString(),tmp[4].toString(),tmp[5].toString(),tmp[6].toString(),
		tmp[7].toString(),tmp[8].toString(),tmp[9].toString(),tmp[10].toString(),tmp[11].toString(), 
		status,availability);
		
		
		return employee;	
    }

	public String getEmployeeID(){
		return employeeID;
	}
	
	public void setEmployeeID(String employeeID){
		this.employeeID = (employeeID);
	}
	public String getType() {
		return type;
	}

	public void setType(String employeeType) {
		type = (employeeType);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String employeeStatus) {
		status = (employeeStatus);
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String employeeAvailability) {
		availability = (employeeAvailability);
	}
	
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
	
	

}
