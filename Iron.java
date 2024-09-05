import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Iron extends Ore {
    int ironX;
    int ironY;

    public Iron(int x, int y) {
        ironX = x;
        ironY = y;
        unloadVal = 3;
        digSpeed = 80;
        fuelUsed = 5;
        type = "iron";
        drillType = 1;
    }
    
    public void act() {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((ironX - playerX) + getWorld().getWidth()/2, (ironY - playerY) + getWorld().getHeight()/2);
        
    }
}