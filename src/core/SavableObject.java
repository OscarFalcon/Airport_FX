package core;

public interface SavableObject
{


	public boolean save(); //UPDATES the database for this object

	public boolean insert(); //INSERTS this object into this database

	public boolean delete(); //DELETES this object from the database 
	
}


