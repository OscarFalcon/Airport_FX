package core;

import java.util.ArrayList;

import database.MySQL;


public class Passenger extends Person
{

	public Passenger(String id,String first,String last,String username,String email,String phone,
					String street, String city, String state, String zip)
	{
		super(id,first,last,username,email,phone,street,city, state,zip);

	}
	
	
	public ArrayList<Reservation> getPastReservations()
	{
		
		return null;
	}
	
	
	public ArrayList<Reservation> getFutureReservations()
	{
		return null;
	}
	
	
	@Override
	public boolean insert()
	{
		String mysql = "INSERT INTO person(firstName,lastName,userName,password,email,telephone,street,city,state,zip)"
						+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		Object arguments[] = {firstName.get(),lastName.get(),username.get(),password.get(),email.get(),
							  phone.get(),street.get(),city.get(),state.get(),zip.get()};
		
		return MySQL.execute(mysql, arguments);
		
	}

	@Override
	public boolean delete() 
	{
		String mysql = "DELETE from person where userID = ?";
		
		Object arguments[] = {id.get()};
		
		return MySQL.execute(mysql, arguments);
	}
	
	@Override
	public boolean save()
	{
		String mysql = "UPDATE person set firstName = ?, lastName = ?, userName = ?, password = ?, email = ?, telephone = ?,"
							+ "street = ?, city = ?, state = ?, zip = ?";
		
		Object arguments[] = {firstName.get(),lastName.get(),username.get(),password.get(),email.get(),
				  			  phone.get(),street.get(),city.get(),state.get(),zip.get()};
		
		return MySQL.execute(mysql, arguments);
	
	}
	
	public static Passenger retrievePassenger(String username, String password)
    {
		String query = "SELECT person.userID,person.firstName,person.lastName,"
						+"passenger.username,person.email,person.telephone,person.street,"
						+ "person.city,person.state,person.zip "
						+ "FROM person JOIN passenger ON person.userID = passenger.userID "
						+ "WHERE passenger.userName = ? && passenger.password = ?"; 
		
		
		Object[] arguments = {username, password};
		
		int [] resultType = {MySQL.INTEGER, MySQL.STRING,MySQL.STRING, MySQL.STRING, 
		MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING};
		
		ArrayList<Object[]> result = MySQL.executeQuery(query, arguments, resultType);
		if(result.isEmpty())
		{
			return null;
		}
		
		Object[] tmp = result.get(0);
		
		Passenger passenger = new Passenger(tmp[0].toString(),tmp[1].toString(),tmp[2].toString(),
		tmp[3].toString(),tmp[4].toString(),tmp[5].toString(),tmp[6].toString(),
		tmp[7].toString(),tmp[8].toString(),tmp[9].toString());

		return passenger;	
    }

	

}
