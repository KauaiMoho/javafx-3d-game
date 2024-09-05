import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block_Outline here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block_Outline extends Actor
{
    /**
     * Act - do whatever the Block_Outline wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage canOutline = new GreenfootImage("mineOutline_1.png");
    GreenfootImage cantOutline = new GreenfootImage("mineOutline_0.png");
    public Block_Outline(boolean canMine){
        if (canMine){
            setImage(canOutline);
        } else {
            setImage(cantOutline);
        }
    }
    public void act()
    {
        // Add your action code here.
    }
}
