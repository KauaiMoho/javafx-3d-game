import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Coal extends Ore {
    int coalX;
    int coalY;
    
    public Coal(int x, int y) {
        coalX = x;
        coalY = y;
        unloadVal = 1;
        digSpeed = 30;
        fuelUsed = 5;
        type = "coal";
        drillType = 0;
    }
    
    public void act() {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((coalX - playerX) + getWorld().getWidth()/2, (coalY - playerY) + getWorld().getHeight()/2);
        
    }
}
