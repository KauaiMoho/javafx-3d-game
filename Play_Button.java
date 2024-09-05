import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Play_Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Play_Button extends Buttons
{
    /**
     * Act - do whatever the Play_Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean pressed = false;
    boolean fadeAdded = false;
    int fadeCounter = 0;
    Transition trans = new Transition(800, 600, 0);    
    public Play_Button(){
        GreenfootImage playButton = new GreenfootImage("playButton.png");
        playButton.scale(getImage().getWidth()*4, getImage().getHeight()*4);
        setImage(playButton);
    }
    
    @Override
    public void pressed(){
        pressed = true;
    }
    
    public void act(){
        super.act();
        if(pressed){
            if(!fadeAdded){
                fadeAdded = true;
                getWorld().addObject(trans, getWorld().getWidth()/2, getWorld().getHeight()/2);
            }
            if(fadeCounter < 255){
                fadeCounter++;
                trans.fade(fadeCounter);
            }else{
                ((Title_Screen)(getWorld())).stopBGM();
                Greenfoot.setWorld(new MyWorld());
            }
        }
    }
}
