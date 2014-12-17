package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ScreensFramework extends Application {
    
	/*Customer*/
    public static String screen1ID = "signInPage";
    public static String screen2ID = "AccountPage";
    public static String screen3ID = "FlightSearch";
    public static String screen4ID = "CreateAccount";
    public static String screen5ID = "MyTrip";
    public static String screen15ID = "resetPassword";
    
    /*mangaer*/
    public static String screen6ID = "ManagerMainPage";
    public static String screen7ID = "ManagerMakePayment";
    public static String screen10ID = "ManagerAccountPage";
    public static String screen11ID = "ManagerBoardingPass";
    public static String screen12ID = "ManagerEmployee";
    public static String screen16ID = "ManagerFlightCondition";
    
    
    /*Receptionist*/
    public static String screen8ID = "ReceptionistMain";
    public static String screen9ID = "ReceptionistFlightCondition";
    public static String screen13ID = "ChargesFees";
    
  
    /** pop ups **/
    public static String flightDetailsPage = "/fxml/FlightDetailsPage.fxml";
    public static String customerFlightDetailsPage = "/fxml/customerflightDetailsPage.fxml";
    

    @Override
    public void start(Stage primaryStage) {
            	
        ScreensController mainContainer = new ScreensController();

        /*Customer*/
        mainContainer.loadScreen("signInPage", "/fxml/SignInPage.fxml");
        mainContainer.loadScreen("AccountPage", "/fxml/CustomerAccountPage.fxml");
        mainContainer.loadScreen("FlightSearch", "/fxml/PassengerFlightSearch.fxml");
        mainContainer.loadScreen("CreateAccount", "/fxml/createAccountPage.fxml");
        mainContainer.loadScreen("MyTrip", "/fxml/MyTrip.fxml");
        mainContainer.loadScreen("resetPassword", "/fxml/resetPassword.fxml");
        /*mangaer*/
        mainContainer.loadScreen("ManagerMainPage", "/fxml/ManagerMainPage.fxml");
        mainContainer.loadScreen("ManagerAccountPage", "/fxml/ManagerAccountPage.fxml");
        mainContainer.loadScreen("ManagerMakePayment", "/fxml/ManagerMakePayment.fxml");
        mainContainer.loadScreen("ManagerBoardingPass", "/fxml/ManagerBoardingPass.fxml");
        mainContainer.loadScreen("ManagerEmployee", "/fxml/ManagerEmployee.fxml");
        mainContainer.loadScreen("ManagerFlightCondition", "/fxml/ManagerFlightCondition.fxml");
        /*Receptionist*/

        mainContainer.loadScreen(screen8ID, "/fxml/ReceptionistMain.fxml");
        mainContainer.loadScreen("ReceptionistFlightCondition", "/fxml/ReceptionistFlightCondition.fxml");
        mainContainer.loadScreen("ChargesFees", "/fxml/ChargesFees.fxml");
        mainContainer.loadScreen("ReceptionistMain", "/fxml/ReceptionistMain.fxml");
        
  
        
     
        mainContainer.setScreen(ScreensFramework.screen1ID);
      
        StackPane root = new StackPane();
        root.getChildren().addAll(mainContainer);
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.setTitle("Airport Management System");
        primaryStage.setFullScreenExitHint("ESC to exit full screen");
        primaryStage.show();
       
        // Applying css
        root.getStylesheets().add("/application/application.css");
        
     }

    
    public static void main(String[] args) {
        launch(args);
    }
}
