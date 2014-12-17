package application;

import java.util.HashMap;

public interface ControlledScreen {
    
    //This method will allow the injection of the Parent ScreenPane
    public void setScreenParent(ScreensController screenPage);
    
    public void reset();
    
    
    /** call this method when a screen that is already loaded
     * needs to be displayed 
     * @param argument
     */
    public void respawn(HashMap<String,Object> arguments);
    
    
    
    
    
    
    
    
}