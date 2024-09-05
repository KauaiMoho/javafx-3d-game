import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bedrock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bedrock extends Actor
{
    /**
     * Act - do whatever the Bedrock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int bedrockX;
    int bedrockY;
    //-15,3
    //15,3
    //-15,45
    //15,45
    public Bedrock(int x, int y){
        bedrockX = x;
        bedrockY = y;
        GreenfootImage img = getImage();
        img.scale(100,100);
        setImage(img);
    }
    
    public void act()
    {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((bedrockX - playerX) + getWorld().getWidth()/2, (bedrockY - playerY) + getWorld().getHeight()/2);
        
    }
}
