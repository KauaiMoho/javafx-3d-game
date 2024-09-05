import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Exit_button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Exit_button extends Buttons
{
    /**
     * Act - do whatever the Exit_button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    World otherWorld;
    World oldWorld;
    boolean doRegenerate;
    int pressCooldown = 1000;
    int frame = pressCooldown;
    GreenfootImage home = new GreenfootImage("homeButton2.png");
    GreenfootImage home1 = new GreenfootImage("homeButton2_2.png");
    
    int world = 0;
    
    GreenfootSound button = new GreenfootSound("button.mp3");
    
    public Exit_button(World w, boolean r, World o){
        home.scale(home.getWidth()*6, home.getHeight()*6);
        home1.scale(home1.getWidth()*6, home1.getHeight()*6);
        otherWorld = w;
        oldWorld = o;
        doRegenerate = r;
        setImage(home);
    }
    
    public void act(){
        super.act();
        if (frame >= pressCooldown || world == 0){
            pressable = true;
            setImage(home);
        }
        frame++;
    }
    
    @Override
    public void pressed(){
        if (frame >= pressCooldown || world == 0){
            pressable = false;
            frame = 0;
            setImage(home1);
            if(!doRegenerate){
                ((MyWorld)oldWorld).stopBGM();
            }else{
                ((Surface)oldWorld).stopBGM();
            }
            Greenfoot.setWorld(otherWorld);
            if(doRegenerate){
                ((MyWorld)(otherWorld)).regenerateWorld();
                world = 0;
            } else {
                world = 1;
            }
        }
        
    }
}
