import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Titanium extends Ore {
    int titaniumX;
    int titaniumY;

    public Titanium(int x, int y) {
        titaniumX = x;
        titaniumY = y;
        unloadVal = 8;
        digSpeed = 125;
        fuelUsed = 8;
        type = "titanium";
        drillType = 2;
    }
    
    public void act() {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((titaniumX - playerX) + getWorld().getWidth()/2, (titaniumY - playerY) + getWorld().getHeight()/2);
        
    }
}
