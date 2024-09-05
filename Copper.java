import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Copper extends Ore {
    int copperX;
    int copperY;
    
    public Copper(int x, int y) {
        copperX = x;
        copperY = y;
        unloadVal = 2;
        digSpeed = 40;
        fuelUsed = 5;
        type = "copper";
        drillType = 0;
    }
    
    public void act() {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((copperX - playerX) + getWorld().getWidth()/2, (copperY - playerY) + getWorld().getHeight()/2);
        
    }
}