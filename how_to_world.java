import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class how_to_world here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class how_to_world extends World
{

    /**
     * Constructor for objects of class how_to_world.
     * 
     */
    public how_to_world()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false); 
        addObject(new Prop(800, 600, "howToPlayScreen.png"), getWidth()/2, getHeight()/2);
        addObject(new escapeButton(), 50, 50);
        addObject(new nextHowToButton(), getWidth() - 50, getHeight() - 50);
    }
}
