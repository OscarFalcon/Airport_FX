package database;
	
import application.MySQLData;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
	MySQLData fetch = new MySQLData();
	fetch.resetPassword("oscar", "blah");
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
