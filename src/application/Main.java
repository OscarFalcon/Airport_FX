package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import database.MySQL;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
	mySQLFetch fetch = new mySQLFetch();
	fetch.resetPassword("oscar", "blah");
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
