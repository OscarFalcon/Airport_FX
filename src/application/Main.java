package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import database.MySQL;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		String username = "oscar";
		String password = "passoscar";
		String usersTable = "SELECT * FROM users WHERE username = ? && password = ?";
		Object[] arguments = {username, password};
		int [] resultType = {MySQL.INTEGER, MySQL.STRING, MySQL.STRING, MySQL.DATE};
		
		ArrayList<Object[]> table = MySQL.executeQuery(usersTable, arguments, resultType);
		for(int i = 0; i < table.size(); i++){
			Object[] tmp = table.get(i);
			System.out.println("UserID = " + tmp[0]);
			System.out.println("username = " + tmp[1]);
			System.out.println("password = " + tmp[2]);
			System.out.println("date = " + tmp[3]);
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
