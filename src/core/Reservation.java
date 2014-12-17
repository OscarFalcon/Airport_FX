package core;


import java.util.ArrayList;

import database.MySQL;
import airline.Solution;

public class Reservation extends SavableObject {

	
	private String srcToDestID;
	private String destToSrcID;
	private Solution srcToDest = null;
	private Solution destToSrc = null;
	private Passenger primaryPassenger = null;
	private String totalSale;
	private int numberOfBags;
	private int reservationID;
	
	
	
	public boolean retrieveSolutions(){
		
		String query = "SELECT solutionID,saleTotal,arrivalTime,departureTime,originAirportCode,originAirport,destinationAirportCode,destinationAirport "
				+ "FROM solution WHERE SolutionID = ?";
		
		Object arguments[] = {srcToDestID};
		System.out.println("SolutionID: "+srcToDestID);
		
		int [] resultTypes = {MySQL.INTEGER,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING,MySQL.STRING};
		ArrayList<Object[]> solution = MySQL.executeQuery(query, arguments, resultTypes);
		
		if(!solution.isEmpty()){
			srcToDest = new Solution();
			Object[] solutionObj = solution.get(0);
			srcToDest.setSolutionID(Integer.parseInt(solutionObj[0].toString()));
			srcToDest.setSaleTotal(solutionObj[1].toString());
			srcToDest.setArrivalTime(solutionObj[2].toString());
			srcToDest.setdepartureTime(solutionObj[3].toString());
			srcToDest.setOriginAirportCode(solutionObj[4].toString());
			srcToDest.setOriginAirport(solutionObj[5].toString());
			srcToDest.setDestinationAirportCode(solutionObj[6].toString());
			srcToDest.setDestinationAirport(solutionObj[7].toString());
			
			srcToDest.retrieveRoutes();
		} else {
			System.out.println("no solution for srcToDest");
		}
		

		//**************Second Solution ***********************
		
		Object arguments2[] = {destToSrcID};
		
		ArrayList<Object[]> solution2 = MySQL.executeQuery(query, arguments2, resultTypes);
		
		if(!solution2.isEmpty()){
			destToSrc = new Solution();
			Object[] solutionObj2 = solution2.get(0);
			destToSrc.setSolutionID(Integer.parseInt(solutionObj2[0].toString()));
			destToSrc.setSaleTotal(solutionObj2[1].toString());
			destToSrc.setArrivalTime(solutionObj2[2].toString());
			destToSrc.setdepartureTime(solutionObj2[3].toString());
			destToSrc.setOriginAirportCode(solutionObj2[4].toString());
			destToSrc.setOriginAirport(solutionObj2[5].toString());
			destToSrc.setDestinationAirportCode(solutionObj2[6].toString());
			destToSrc.setDestinationAirport(solutionObj2[7].toString());
			
			destToSrc.retrieveRoutes();
		} else {
			System.out.println("Reservation has no solutions");
		}
	
		return true;	
	}
	
	
	
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
		
		if(srcToDest.insert() == false)
		{
			return false;
		}
		
		if(destToSrc != null &&  destToSrc.insert() == false)
		{
			return false;
		}
		
		if(insert() == false)
		{
			return false;
		}
		reservationID = MySQL.getLastInsertID();
		return true;
		
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
	
	
	public String getSrcToDestID(){
		return srcToDestID;
	}
	
	public void setSrcToDestID(String ID){
		this.srcToDestID = ID;
	}
	
	public String getDestToSrcID(){
		return destToSrcID;
	}
	
	public void setDestToSrcID(String ID){
		this.destToSrcID = ID;
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
	
	public void setReservationId(int id)
	{
		this.reservationID = id;
	}
	
	
}

