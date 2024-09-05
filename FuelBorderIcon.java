import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FuelBorderIcon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FuelBorderIcon extends GUI_Elements
{
    /**
     * Act - do whatever the FuelBorderIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage fuelBorder = new GreenfootImage("fuel_bar3.png");
    public FuelBorderIcon(){
        fuelBorder.scale(fuelBorder.getWidth()*2, fuelBorder.getHeight()*2);
        setImage(fuelBorder);
    }
}
