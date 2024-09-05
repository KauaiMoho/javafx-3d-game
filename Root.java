import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Root here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Root extends Actor
{
    /**
     * Act - do whatever the Root wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int rootX;
    int rootY;
    int fuelUsed = 10;
    int drillType = 3;
    int digSpeed = 500;
    
    GreenfootSound heartbeat = new GreenfootSound("heartbeat.mp3");
    GreenfootImage root = new GreenfootImage("root_corruption.png");
    public Root(int x, int y) {
        root.scale(100, 100);
        rootX = x;
        rootY = y;
    }
    
    public int getFuelUsed(){
        return fuelUsed;
    }
    
    public int getDigSpeed(){
        return digSpeed;
    }
    
    public int getDrillType(){
        return drillType;
    }
    
    public void stopSounds(){
        heartbeat.stop();
    }
    
    public void act() {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((rootX - playerX) + getWorld().getWidth()/2, (rootY - playerY) + getWorld().getHeight()/2);
        
        if(rootX >= playerX - 500 && rootX <= playerX + 500 && rootY >= playerY - 400 && rootY <= playerY + 400 && !heartbeat.isPlaying()){
            ((MyWorld)(getWorld())).setBGMVolume(50);
            heartbeat.playLoop();
        }

        if(heartbeat.isPlaying() && !(rootX >= playerX - 500 && rootX <= playerX + 500 && rootY >= playerY - 400 && rootY <= playerY + 400)){
            ((MyWorld)(getWorld())).setBGMVolume(70);
            heartbeat.stop();
        }
    }
}
