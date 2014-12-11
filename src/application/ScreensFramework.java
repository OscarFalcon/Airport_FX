package application;

import java.util.HashMap;

import core.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ScreensFramework extends Application {
    
    public static String screen1ID = "signInPage";
    public static String screen2ID = "AccountPage";
    public static String screen3ID = "FlightSearch";
    public static String screen4ID = "CreateAccount";
    public static String screen5ID = "MyTrip";
    
    public static HashMap<String, String> screens = new HashMap<>();
  
    
    @Override
    public void start(Stage primaryStage) {
            	
        ScreensController mainContainer = new ScreensController();

        screens.put("signInPage", "/fxml/SignInPage.fxml");
        screens.put("AccountPage", "/fxml/CustomerAccountPage.fxml");
        screens.put("FlightSearch", "/fxml/PassengerFlightSearch.fxml");
        screens.put("CreateAccount", "/fxml/createAccountPage.fxml");
        screens.put("MyTrip", "/fxml/MyTrip.fxml");
     
        mainContainer.setScreen(ScreensFramework.screen1ID);
      
        StackPane root = new StackPane();
        root.getChildren().addAll(mainContainer);
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.setTitle("Airport Management System");
      //  primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("ESC to exit full screen");
        primaryStage.show();
        
        // Setting background image
        //root.setStyle("-fx-background-image: url('application/Plane.jpg')");
       
        
        // Applying css
        root.getStylesheets().add("/application/application.css");
        
        
       // Person person = Person.retrievePerson("oscar", "oscarpass");
       // person.setFirstName("BIRDMAN");
       // person.save();
     }

    
    public static void main(String[] args) {
        launch(args);
    }
}
