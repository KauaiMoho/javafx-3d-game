import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Diamond here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Diamond extends Ore {
    int diamondX;
    int diamondY;

    public Diamond(int x, int y) {
        diamondX = x;
        diamondY = y;
        digSpeed = 100;
        unloadVal = 6;
        type = "diamond";
        fuelUsed = 8;
        drillType = 2;
    }
    
    public void act() {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((diamondX - playerX) + getWorld().getWidth()/2, (diamondY - playerY) + getWorld().getHeight()/2);
        
    }
}
