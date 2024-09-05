import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gold extends Ore {
    int goldX;
    int goldY;

    public Gold(int x, int y) {
        goldX = x;
        goldY = y;
        unloadVal = 4;
        digSpeed = 100;
        fuelUsed = 6;
        type = "gold";
        drillType = 1;
    }
    
    public void act() {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((goldX - playerX) + getWorld().getWidth()/2, (goldY - playerY) + getWorld().getHeight()/2);
        
    }
}