package core;


import database.MySQL;
import airline.Solution;

public class Reservation extends SavableObject {

	private Solution srcToDest;
	private Solution destToSrc;
	private int numOfBags;
	private String totalSale;
	private Person primaryPerson;
	private int reservationID;
	
	
	@Override
	public boolean save()
	{
		String mysql = "UPDATE reservation SET srcToDest = ?, destToSrc = ?, numberOfBags = ?, totalSale = ?"
					+ " WHERE reservationID = ? ";
		
		Object arguments[] = {MySQL.INTEGER,MySQL.INTEGER,MySQL.INTEGER,MySQL.STRING,MySQL.INTEGER};
		
		return(MySQL.execute(mysql, arguments));
		
	}

	@Override
	public boolean insert() {
		boolean status;
		String mysql = "INSERT INTO reservation (srcToDest,destToSrc,numberOfBags,totalSale) "
					+ "	VALUES(?,?,?,?)";
		
		Object arguments[] = {MySQL.INTEGER,MySQL.INTEGER,MySQL.INTEGER,MySQL.INTEGER};
		status = MySQL.execute(mysql, arguments);
		if(status == false)
			return status;
		return status;
	}

	@Override
	public boolean delete() {
		
		String mysql = "DELETE FROM reservation WHERE reservationID = ?";
		Object argument[] = {reservationID};
		return MySQL.execute(mysql, argument);

	}
	
	
	
	public Solution getSrcToDest()
	{
		return srcToDest;
	}
	
	public void setSrcToDest(Solution srcToDest)
	{
		this.srcToDest = srcToDest;
	}

	public Solution getDestToSrc()
	{
		return destToSrc;
	}
	
	public void setDestToSrc(Solution destToSrc)
	{
		this.destToSrc = destToSrc;
	}
	
	public int getNumOfBags()
	{
		return numOfBags;
	}
	
	public void setNumOfBags(int numOfBags)
	{
		this.numOfBags = numOfBags;
	}
	
	public String getTotalSale()
	{
		return totalSale;
	}
	
	public void setTotalSale(String totalSale)
	{
		this.totalSale = totalSale;
	}
	
	public Person getPrimaryPerson()
	{
		return primaryPerson;
	}
	
	public void setPrimaryPerson(Person primaryPerson)
	{
		this.primaryPerson = primaryPerson;
	}
	
	public int getReservationId()
	{
		return reservationID;
	}
	
	public void setReservationId(int reservationID)
	{
		this.reservationID = reservationID;
	}
}

