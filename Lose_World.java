import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lose_World here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lose_World extends World
{

    /**
     * Constructor for objects of class Lose_World.
     * 
     */
    
    Transition trans = new Transition(getWidth(), getHeight(), 255);
    Transition transTemp = new Transition(getWidth(), getHeight(), 255);
    boolean doFadeOut = true;
    boolean fadeAdded = false;
    ScrollingText scrollText = new ScrollingText("As purple vines consume your iron lung, you reflect on what was the true disease.");
    int fadeCounter = -1;
    public Lose_World()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false);
        addObject(new Prop(800, 600, "Lose_Screen.png"), getWidth()/2, getHeight()/2);
        addObject(new escapeButton(), 50, 50);
        addObject(scrollText, 70, 530);
        addObject(transTemp, getWidth()/2, getHeight()/2);
    }
    
    public void act(){
        if(doFadeOut){
            if(!fadeAdded){
                fadeAdded = true;
                removeObject(transTemp);
                fadeCounter = 255;
                addObject(trans, getWidth()/2, getHeight()/2);
            }
            if(fadeCounter > 0 && fadeAdded){
                fadeCounter--;
                trans.fade(fadeCounter);
            }else{
                removeObject(trans);
                doFadeOut = false;
            }
        }else{
            scrollText.setCanScroll(true);
        }
    }
}
