package database;
	
import application.mySQLFetch;
import javafx.application.Application;
import javafx.stage.Stage;


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
