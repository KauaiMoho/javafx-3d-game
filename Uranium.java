import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Uranium extends Ore {
    int uraniumX;
    int uraniumY;

    public Uranium(int x, int y) {
        uraniumX = x;
        uraniumY = y;
        unloadVal = 5;
        digSpeed = 150;
        fuelUsed = 10;
        type = "uranium";
        drillType = 2;
    }
    
    public void act() {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((uraniumX - playerX) + getWorld().getWidth()/2, (uraniumY - playerY) + getWorld().getHeight()/2);
        
    }
}