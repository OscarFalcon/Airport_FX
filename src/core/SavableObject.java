package core;

import java.util.ArrayList;

import database.MySQL;

public abstract class SavableObject
{


	public abstract boolean save(); //UPDATES the database for this object

	public abstract boolean insert(); //INSERTS this object into this database

	public abstract boolean delete(); //DELETES this object from the database 

	
	
	
}


