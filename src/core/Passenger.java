package core;

import java.util.ArrayList;

import airline.Solution;
import javafx.beans.property.SimpleStringProperty;
import database.MySQL;


public class Passenger extends Person
{
	
	protected  SimpleStringProperty username;
	protected  SimpleStringProperty password;
	private ArrayList<Reservation> reservations;
	
	
	
	public Passenger(String id,String first,String last,String username,String password,String email,String phone,
					String street, String city, String state, String zip)
	{
		super(id,first,last,username,email,phone,street,city, state,zip);
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
	}
	
	
	public boolean cancelReservation(Integer reservationID){
		String mysql_string = "DELETE FROM reservation WHERE reservationID = ?";
		Object[] arguments = {reservationID.toString()};
		System.out.println("Deleting reservationID: "+reservationID);
		return MySQL.execute(mysql_string, arguments);
	}
	
	
	public ArrayList<Reservation> getReservations()
	{
		reservations = new ArrayList<Reservation>();
		//First need to get Reservation data 
		String query = "SELECT * FROM reservation WHERE personID = ?";
		
		Object arguments[] = {getId().toString()};
		System.out.println(getId().toString());
		
		int [] resultType = {MySQL.INTEGER, MySQL.INTEGER,MySQL.INTEGER, MySQL.INTEGER, MySQL.STRING, MySQL.INTEGER};
				
		ArrayList<Object[]> results = MySQL.executeQuery(query, arguments, resultType);
		if(results.isEmpty())
		{
			System.out.println("Reservations is empty");
			return reservations;
		}
		
		for(int i=0; i < results.size(); i++){
			System.out.println("LOOP: "+ i);
			Object[] reservationObj = results.get(i);
			Reservation reservation = new Reservation();
			reservation.setNumOfBags(Integer.parseInt(reservationObj[0].toString()));
			String srcToDestID = reservationObj[1].toString();
			String destToSrcID = reservationObj[2].toString();
			
			//Get Solution objects from reservation!!!!
			Solution srcToDest = new Solution();
			Solution destToSrc = new Solution();
			query = "SELECT saleTotal,arrivalTime,departureTime,originAirportCode,originAirport,destinationAirportCode,destinationAirport "
					+ "FROM solution WHERE SolutionID = ?";
			
			Object arguments1[] = {srcToDestID}; 
			int [] resultType1 = {MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING};
			ArrayList<Object[]> solutions = MySQL.executeQuery(query, arguments1, resultType1);
			if(solutions.isEmpty()){
				System.out.println("Reservation has no solutions");
			}

			Object[] solutionObj = solutions.get(0);
			srcToDest.setSaleTotal(solutionObj[0].toString());
			srcToDest.setArrivalTime(solutionObj[1].toString());
			srcToDest.setdepartureTime(solutionObj[2].toString());
			srcToDest.setOriginAirportCode(solutionObj[3].toString());
			srcToDest.setOriginAirport(solutionObj[4].toString());
			srcToDest.setDestinationAirportCode(solutionObj[5].toString());
			srcToDest.setDestinationAirport(solutionObj[6].toString());
			
			
			
			reservation.setSrcToDest(srcToDest);
			//reservation.setDestToSrc(destToSrc);
			reservation.setNumOfBags(Integer.parseInt(reservationObj[3].toString()));
			reservation.setTotalSale(reservationObj[4].toString());
			
			reservations.add(reservation);
		}
		
		return reservations;
	}

	
	@Override
	public boolean insert()
	{
		boolean created = false;
	
							
		System.out.println("LAST INDEX USED IS: " + MySQL.getLastInsertID());
	
		String createAccount = "INSERT INTO person(firstName,lastName,email,telephone,street,city,state,zip)"
						     + "VALUES (?,?,?,?,?,?,?,?)";
		
		Object personArguments[] = {firstName.get(),lastName.get(),email.get(),phone.get(),
				                    street.get(),city.get(),state.get(),zip.get()};
		
		created = MySQL.execute(createAccount, personArguments);
		
		if(created == false)
			return false;
		
		createAccount = "INSERT INTO passenger(userID,username,password)"
					  + "VALUES (?,?,?)";
		
		Object passengerArguments[] = {MySQL.getLastInsertID(),username.get(),password.get()};
		
		created = MySQL.execute(createAccount, passengerArguments);
		
		if(created == false)
			return false;

		
		return created;
		
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
		
		String mysql = "UPDATE person set firstName = ?, lastName = ?,email = ?, telephone = ?,"
							+ "street = ?, city = ?, state = ?, zip = ? WHERE userID = ?";
		
		Object arguments[] = {firstName.get(),lastName.get(),email.get(),
				  			  phone.get(),street.get(),city.get(),state.get(),zip.get(),id.get()};
		
		return MySQL.execute(mysql, arguments);
	
		
	}
	
	public static Passenger retrievePassenger(String username, String password)
    {
		String query = "SELECT person.userID,person.firstName,person.lastName,"
						+"passenger.username,passenger.password,person.email,person.telephone,person.street,"
						+ "person.city,person.state,person.zip "
						+ "FROM person JOIN passenger ON person.userID = passenger.userID "
						+ "WHERE passenger.userName = ? && passenger.password = ?"; 
		
		
		Object[] arguments = {username, password};
		
		int [] resultType = {MySQL.INTEGER, MySQL.STRING,MySQL.STRING, MySQL.STRING, 
		MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING, MySQL.STRING,MySQL.STRING};
		
		ArrayList<Object[]> result = MySQL.executeQuery(query, arguments, resultType);
		if(result.isEmpty())
		{
			return null;
		}
		
		Object[] tmp = result.get(0);
		
		Passenger passenger = new Passenger(tmp[0].toString(),tmp[1].toString(),tmp[2].toString(),
		tmp[3].toString(),tmp[4].toString(),tmp[5].toString(),tmp[6].toString(),
		tmp[7].toString(),tmp[8].toString(),tmp[9].toString(),tmp[10].toString());

		return passenger;
		
		
    }
	
	public boolean changeUsername(String username)
	{
		String mysql = "UPDATE passenger SET username = ? WHERE userID = ?";
		return MySQL.execute(mysql, new Object[]{username,id.get()});
		
		
	}
	
	public boolean changePassword(String newPassword)
	{
		String mysql = "UPDATE passenger SET password = ? WHERE userID = ?";
		Object[] arguments = {newPassword,id.get()};
		return MySQL.execute(mysql, arguments);
	}
	
	public boolean checkPassword(String password)
	{
		ArrayList<Object[]> results;
		String mysql = "SELECT username FROM passenger WHERE username = ? AND password = ?";
		results = MySQL.executeQuery(mysql, new Object[]{username.get(),password},new int[]{MySQL.STRING});
		if(results != null && results.size() == 0)
			return false;
		return true;	
	}
	
	
	
	
	
	public String getUserName()
	{
		return username.get();
	}
	public void setUserName(String username)
	{
		this.username.set(username);
	}
	
	public String getPassword()
	{
		return password.get();
	}
	public void setPassword(String password)
	{
		this.password.set(password);
	}

	

}
