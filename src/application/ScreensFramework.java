package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ScreensFramework extends Application {
    
    public static String screen1ID = "main";
    public static String screen1File = "/fxml/SignInPage.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "/fxml/CustomerAccountPage.fxml";
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
      
        StackPane root = new StackPane();
        root.getChildren().addAll(mainContainer);
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.setTitle("Airport Management System");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("ESC to exit full screen");
        primaryStage.show();
        
        // Setting background image
        root.setStyle("-fx-background-image: url('application/Plane.jpg')");
        // Applying css
        root.getStylesheets().add("/application/application.css");
        
           }

    
    public static void main(String[] args) {
        launch(args);
    }
}
