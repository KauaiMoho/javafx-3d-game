import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Depth_Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Depth_Counter extends Actor
{
    /**
     * Act - do whatever the Depth_Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage bg = new GreenfootImage(800, 600);
    int depth;
    
    public void act()
    {
        // Add your action code here.
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        depth = player.getDepth() / 10;
        
        bg.clear();
        Font font = new Font("Impact", 30);
        bg.setFont(font);
        bg.setColor(Color.WHITE);
        bg.drawString("Depth: " + depth + "m", getWorld().getWidth()/50, getWorld().getHeight()*24/25);
        setImage(bg);
    }
}
