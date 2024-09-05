import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GUI_Elements here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI_Elements extends Actor
{
    /**
     * Act - do whatever the GUI_Elements wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        Surface surface = (Surface)getWorld();
        if (!surface.isOpen(1) && !surface.isOpen(0) && !surface.isOpen(2)){ // let me test it
            surface.removeObject(this);
        }
    }
}
