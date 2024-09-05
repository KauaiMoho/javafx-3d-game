import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Win_World here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Win_World extends World
{

    /**
     * Constructor for objects of class Win_World.
     * 
     */
    Transition trans = new Transition(getWidth(), getHeight(), 255);
    Transition transTemp = new Transition(getWidth(), getHeight(), 255);
    boolean doFadeOut = true;
    boolean fadeAdded = false;
    int fadeCounter = -1;
    Score_Text scoreText;
    ScrollingText scrollText = new ScrollingText("You have terminated the pestilence. But at what cost?");
    int s;
    boolean first = true;
    public Win_World(int score)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false);
        s = score;
        addObject(new Prop(800, 600, "Win_Screen.png"), getWidth()/2, getHeight()/2);
        addObject(new escapeButton(), 50, 50);
        scoreText = new Score_Text(score, false);
        addObject(scoreText, 330, 340);
        addObject(transTemp, getWidth()/2, getHeight()/2);
        addObject(scrollText, 190, 380);
        setPaintOrder(Transition.class, escapeButton.class);
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
        } else if (first && !doFadeOut){
            first = false;
            removeObject(scoreText);
            addObject(new Score_Text(s, true), 330, 340);
            scrollText.setCanScroll(true);
        }
    }
}
