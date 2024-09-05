import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fuel_Icon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fuel_Icon extends GUI_Elements
{
    /**
     * Act - do whatever the Fuel_Icon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage fuelIcon = new GreenfootImage("fuel_icon.png");
    public Fuel_Icon(){
        fuelIcon.scale(100, 100);
        setImage(fuelIcon);
    }
    
}
