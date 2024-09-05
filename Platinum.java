import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Platinum extends Ore {
    int platinumX;
    int platinumY;

    public Platinum(int x, int y) {
        platinumX = x;
        platinumY = y;
        unloadVal = 7;
        digSpeed = 125;
        fuelUsed = 10;
        type = "platinum";
        drillType = 1;
    }
    
    public void act() {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((platinumX - playerX) + getWorld().getWidth()/2, (platinumY - playerY) + getWorld().getHeight()/2);
        
    }
}