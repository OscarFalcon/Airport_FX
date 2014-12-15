package core;


import database.MySQL;
import airline.Solution;

public class Reservation extends SavableObject {

	private Solution srcToDest = null;
	private Solution destToSrc = null;
	private Passenger primaryPassenger = null;
	private String totalSale;
	private int numberOfBags;
	private int reservationID;
	
	
	
	@Override
	public boolean save()
	{
		String mysql = "UPDATE reservation SET srcToDest = ?, destToSrc = ?, numberOfBags = ?, totalSale = ?"
							+ " WHERE reservationID = ? ";
		
		Object arguments[] = {MySQL.INTEGER,MySQL.INTEGER,MySQL.INTEGER,MySQL.STRING,MySQL.INTEGER};
		
		return(MySQL.execute(mysql, arguments));
		
	}
	
	
	public boolean book()
	{
		
		return srcToDest.insert() && destToSrc.insert() && insert();
		
	}

	@Override
	public boolean insert()
	{
		
		String mysql = "INSERT INTO reservation (srcToDest,destToSrc,numberOfBags,totalSale,personID) "
					+ "	VALUES(?,?,?,?,?)";
		
		Integer destToSrcId = null;
		if(destToSrc == null)
			destToSrcId = null;
		else
			destToSrcId = destToSrc.getSolutionID();
			
		
		
		
		Object arguments[] = {srcToDest.getSolutionID(),destToSrcId,numberOfBags,
								totalSale,primaryPassenger.getId()};
				
		return MySQL.execute(mysql, arguments);
		
	}

	@Override
	public boolean delete()
	{
		return MySQL.execute("DELETE FROM reservation WHERE reservationID = ?", new Object[]{reservationID});
	}
	
	
	
	public Solution getSrcToDest()				//SrcToDest
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
	
	public void setDestToSrc(Solution destToSrc) //DestinationToSrc
	{
		this.destToSrc = destToSrc;
	}
	
	public int getNumOfBags()				//Number of Bags
	{
		return numberOfBags;
	}
	
	public void setNumOfBags(int numOfBags)
	{
		this.numberOfBags = numOfBags;
	}
	
	public String getTotalSale()			//Sale total
	{
		return totalSale;
	}
	
	public void setTotalSale(String totalSale)	
	{
		this.totalSale = totalSale;
	}
	
	public Passenger getPrimaryPassenger()		//PRIMARY PERSON
	{
		return primaryPassenger;
	}
	
	public void setPrimaryPassenger(Passenger primaryPassenger)
	{
		this.primaryPassenger = primaryPassenger;
	}
	
	public int getReservationId()		//RESERVATION ID
	{
		return reservationID;
	}
	
	
}

