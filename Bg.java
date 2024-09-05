import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BG here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bg extends Actor
{
    /**
     * Act - do whatever the BG wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int bgX;
    int bgY;
    public Bg(int x, int y){
        bgX = x;
        bgY = y;
    }
    
    public void act()
    {
        // Add your action code here.
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((bgX - playerX) + getWorld().getWidth()/2, (bgY - playerY) + getWorld().getHeight()/2);
        
        if (getY() <= -getWorld().getHeight()/2){
            bgY += getWorld().getHeight()*2;
        }
        if (getY() >= getWorld().getHeight()*3/2){
            bgY -= getWorld().getHeight()*2;
        }
        if (getX() >= getWorld().getWidth()*3/2){
            bgX -= getWorld().getWidth()*2;
        }
        if (getX() <= -getWorld().getWidth()/2){
            bgX += getWorld().getWidth()*2;
        }
    }
}
