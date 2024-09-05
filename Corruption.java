import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Point;

/**
 * Write a description of class Corruption here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Corruption extends Actor
{
    /**
     * Act - do whatever the Corruption wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int corruptionX;
    int corruptionY;
    int frame = 0;
    int fuelUsed = 3;
    int digSpeed = 100;
    int drillType = 2;
    
    GreenfootImage corruption = new GreenfootImage("corruption.png");
    public Corruption(int x, int y) {
        setValues(x,y);
    }
    
    public void setValues(int x, int y){
        corruption.scale(100, 100);
        corruptionX = x;
        corruptionY = y;
        frame = 0;
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
    
    public void corrupt(int xOffset, int yOffset){
        if(((MyWorld)getWorld()).getAllCorruption().indexOf(new Point(corruptionX + xOffset,corruptionY + yOffset)) == -1 && getOneObjectAtOffset(xOffset, yOffset, Root.class) == null && getOneObjectAtOffset(xOffset, yOffset, Bedrock.class) == null && getOneObjectAtOffset(xOffset, yOffset, Breaking.class) == null){
            if(getOneObjectAtOffset(xOffset, yOffset, Hole.class) != null){
                if(getOneObjectAtOffset(xOffset - 49, yOffset, Vehicle.class) == null && getOneObjectAtOffset(xOffset + 49, yOffset, Vehicle.class) == null && getOneObjectAtOffset(xOffset, yOffset - 49, Vehicle.class) == null && getOneObjectAtOffset(xOffset, yOffset + 49, Vehicle.class) == null && getOneObjectAtOffset(xOffset, yOffset, Vehicle.class) == null){
                    if (getOneObjectAtOffset(xOffset, yOffset, Default_Hole.class) != null){
                        ((MyWorld)getWorld()).setGameLost(true);
                    }
                    getWorld().removeObject(getOneObjectAtOffset(xOffset, yOffset, Hole.class));
                    ((MyWorld)getWorld()).getAllCorruption().add(new Point(corruptionX + xOffset,corruptionY + yOffset));
                    getWorld().addObject(new Corruption(corruptionX + xOffset, corruptionY + yOffset), - 100, -100);
                }
            }else{
                getWorld().addObject(new Corruption(corruptionX + xOffset, corruptionY + yOffset), - 100, -100);
                ((MyWorld)getWorld()).getAllCorruption().add(new Point(corruptionX + xOffset,corruptionY + yOffset));
            }
        }
    }
    
    public void removeCorruption(){
        ((MyWorld)getWorld()).getAllCorruption().remove(new Point(corruptionX, corruptionY));
        getWorld().removeObject(this);
    }
    
    public void act() {
        
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        List<Root> root = getWorld().getObjects(Root.class);
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        
        setLocation((corruptionX - playerX) + getWorld().getWidth()/2, (corruptionY - playerY) + getWorld().getHeight()/2);
        
        frame++;
        if (frame % (Greenfoot.getRandomNumber(100) + 450) == 0 && root.size() > 0){
            int dirSpread = Greenfoot.getRandomNumber(8);
            if (dirSpread == 0){
                corrupt(-100, -100);
            }
            if (dirSpread == 1){
                corrupt(0, -100);
            }
            if (dirSpread == 2){
                corrupt(100, -100);
            }
            if (dirSpread == 3){
                corrupt(100, 0);
            }
            if (dirSpread == 4){
                corrupt(100, 100);
            }
            if (dirSpread == 5){
                corrupt(0, 100);
            }
            if (dirSpread == 6){
                corrupt(-100, 100);
            }
            if (dirSpread == 7){
                corrupt(-100, 0);
            }
        }
        if(corruptionX <= playerX - 500 || corruptionX >= playerX + 500 || corruptionY <= playerY - 400 || corruptionY >= playerY + 400){
            ((MyWorld)getWorld()).unloadCorruption(corruptionX, corruptionY, this);
        }
    }
}
