import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Sand extends Ore {
    int sandX;
    int sandY;
    public Sand(int x, int y) {
        sandX = x;
        sandY = y;
        GreenfootImage image = getImage();
        image.scale(100, 100);
        setImage(image);
        unloadVal = -1;
        digSpeed = 10;
        type = "sand";
        drillType = 0;
    }
    public void act() {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((sandX - playerX) + getWorld().getWidth()/2, (sandY - playerY) + getWorld().getHeight()/2);
        
    }
}