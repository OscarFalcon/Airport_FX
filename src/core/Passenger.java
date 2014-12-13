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
	
	

}
