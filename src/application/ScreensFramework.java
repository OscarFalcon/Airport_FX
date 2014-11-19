package application;

import java.sql.Date;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import core.Flight;

/**
 *
 * @author Angie
 */
public class ScreensFramework extends Application {
    
    public static String screen1ID = "main";
    public static String screen1File = "/fxml/SignInPage.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "fxml/CustomerAccountPage.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "/fxml/PassengerFlightSearch.fxml";
    public static String screen4ID = "screen4";
    public static String screen4File = "/fxml/createAccountPage.fxml";
    public static String screen5ID = "screen5";
    public static String screen5File = "/fxml/MyTrip.fxml";
    
    
    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
        mainContainer.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);
        mainContainer.loadScreen(ScreensFramework.screen5ID, ScreensFramework.screen5File);
        
        mainContainer.setScreen(ScreensFramework.screen1ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
        mySQLFetch fetch = new mySQLFetch();
        ObservableList<Flight> list;
    	list = fetch.searchFlightDates("SAT", "DEN",Date.valueOf( "2014-12-19" ),Date.valueOf( "2014-12-19" ));
    	for(int i = 0; i < list.size(); i++)
    	{
    		System.out.println("Airline: " + list.get(i).getAirline());
    		System.out.println("Arrival Date: " + list.get(i).getArrivalDate());
    		System.out.println("Arrival Time: " + list.get(i).getArrivalTime());
    		System.out.println("Departure Date: " + list.get(i).getDeptDate());
    		System.out.println("Departure Time: " + list.get(i).getDeptTime());
    		System.out.println("Destination Location: " + list.get(i).getDestinationLocation());
    		System.out.println("Source Location: " + list.get(i).getSrcLocation());
    		System.out.println("Flight ID: " + list.get(i).getFlightId());
    		System.out.println("Flight Number: " + list.get(i).getFlightNumber());
    		System.out.println("Flight Price: " + list.get(i).getFlightPrice());



    	}
        
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
