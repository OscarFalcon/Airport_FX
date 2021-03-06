package application;

import java.io.IOException;
import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import airline.Solution;
import core.Employee;
import core.Passenger;


//TODO
//NEED TO MAKE IT SO THE SCREENS LOAD UPON SETTING IT! 


public class ScreensController  extends StackPane {
    //Holds the screens to be displayed
	
	private Passenger passenger = null;
	private Employee employee = null;
	
    private HashMap<String, Node> screens = new HashMap<>();
    private HashMap<String, ControlledScreen> screenControllers = new HashMap<>();
    
    public ScreensController() {
        super();
    }
    
    public void setPassenger(Passenger obj){
    	passenger = obj;
    }
    
    public void setEmployee(Employee obj){
    	employee = obj;
    }
    
    public Passenger getPassenger(){
    	return this.passenger;
    }
    
    public Employee getEmployee(){
    	return this.employee;
    }
    
    //Add the screen to the collection
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    //Returns the Node with the appropriate name
    public Node getScreen(String name) {
        return screens.get(name);
    }
    
    public ControlledScreen getScreenController(String name){
    	return this.screenControllers.get(name);
    }

    //Loads the fxml file, add the screen to the screens collection and
    //finally injects the screenPane to the controller.
    public boolean loadScreen(String name, String resource) {
        try {
        	System.out.println("Attempting to load..." + name);
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
            screenControllers.put(name, myScreenController);
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }

    //This method tries to displayed the screen with a predefined name.
    //First it makes sure the screen has been already loaded.  Then if there is more than
    //one screen the new screen is been added second, and then the current screen is removed.
    // If there isn't any screen being displayed, the new screen is just added to the root.
    public boolean setScreen(final String name) {
    	ControlledScreen screenController;
    	//We keep loading screens, may need to unload the screen currently being displayed.
    	//loadScreen(name, screens.get(name));
    	
    	System.out.println("this is "+name +" " + screens.get(name));
        
    	if (screens.get(name) != null) {   //screen loaded
            final DoubleProperty opacity = opacityProperty();
            
            //call the reset method (this is similar to init screen)
            if((screenController = screenControllers.get(name)) != null){
            	screenController.reset();
            }
            
            if (!getChildren().isEmpty()) {    //if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        getChildren().remove(0);                    //remove the displayed screen
                        getChildren().add(0, screens.get(name));     //add the screen
                        Timeline fadeIn = new Timeline(
                                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                        fadeIn.play();
                    }
                }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));       //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }

    }

    //This method will remove the screen with the given name from the collection of screens
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }
    
    
    
    
    
    public ControlledScreen loadPopUp(String name)
    {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(name));
        AnchorPane page;
		try
		{
			page = (AnchorPane) loader.load();
		} catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
    	
    	Stage stage = new Stage();
        //stage.setTitle("Flight Details");
        stage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(page);
        stage.setScene(scene);
        
        ControlledScreen controller = loader.getController();
        controller.setScreenParent(this);
        controller.reset();
        stage.show();
        return controller;
    }	   
}