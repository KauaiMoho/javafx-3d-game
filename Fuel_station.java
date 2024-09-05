import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fuel_station here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fuel_station extends Buttons
{
    /**
     * Act - do whatever the Fuel_station wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Vehicle vehicle;
    public Fuel_station(Vehicle v){
        vehicle = v;
        getImage().scale(getImage().getWidth()*2/3, getImage().getHeight()*2/3);
    }
    
    @Override
    public void pressed(){
        Surface surface = (Surface)getWorld();
        if (!surface.isOpen(0) && !surface.isOpen(1) && !surface.isOpen(2)){
            surface.setOpen(0, true);
            getWorld().addObject(new GUI_Square(600, 450), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new X_button(), 650, 115);
            getWorld().addObject(new Convert_Button(vehicle, 0), 470, 300);
            getWorld().addObject(new Convert_Button(vehicle, 1), 470, 370);
            getWorld().addObject(new Convert_Button(vehicle, 2), 470, 440);
            getWorld().addObject(new Fuel_Icon(), 230, 180);
            getWorld().addObject(new FuelBarIcon(vehicle), 225, 450);
            getWorld().addObject(new FuelBorderIcon(), 225, 350);
        }
        
    }
    
}
