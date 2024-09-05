import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GUI_Square here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI_Square extends GUI_Elements
{
    /**
     * Act - do whatever the GUI_Square wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage GUI_Square = new GreenfootImage("GUI_Square.png");
    public GUI_Square(int width, int height){
        GUI_Square.scale(width, height);
        setImage(GUI_Square);
        // what do you want me to do
    }
}
