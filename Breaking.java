import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Breaking here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Breaking extends Actor
{
    /**
     * Act - do whatever the Breaking wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage mining = new GreenfootImage(100, 100);
    GreenfootImage mining1 = new GreenfootImage("mining_1.png");
    GreenfootImage mining2 = new GreenfootImage("mining_2.png");
    GreenfootImage mining3 = new GreenfootImage("mining_3.png");
    GreenfootImage mining4 = new GreenfootImage("mining_4.png");
    GreenfootImage mining5 = new GreenfootImage("mining_5.png");
    GreenfootImage mining6 = new GreenfootImage("mining_6.png");
    GreenfootImage mining7 = new GreenfootImage("mining_7.png");
    
    int posX;
    int posY;
    int frame = 0;
    int cooldown;
    public Breaking(int x, int y){
        posX = x;
        posY = y;
        setImage(mining);
    }
    public void act()
    {
        // Add your action code here.
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        cooldown = player.getDiggingCooldown();
        setLocation((posX - playerX) + getWorld().getWidth()/2, (posY - playerY) + getWorld().getHeight()/2);
        frame++;
        
        setAnimation();
        //if (!Greenfoot.isKeyDown(null)){
            //getWorld().removeObject(this);
        //}
    }
    
    public void setAnimation(){
        if (frame == Math.floor(cooldown/8)){
            setImage(mining1);
        } else if (frame == Math.floor(cooldown/4)){
            setImage(mining2);
        } else if (frame == Math.floor(cooldown*3/8)){
            setImage(mining3);
        } else if (frame == Math.floor(cooldown/2)){
            setImage(mining4);
        } else if (frame == Math.floor(cooldown*5/8)){
            setImage(mining5);
        } else if (frame == Math.floor(cooldown*6/8)){
            setImage(mining6);
        } else if (frame == Math.floor(cooldown*7/8)){
            setImage(mining7);
        }
    }
}
