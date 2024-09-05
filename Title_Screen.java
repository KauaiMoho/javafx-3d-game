import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Title_Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Title_Screen extends World
{

    /**
     * Constructor for objects of class Title_Screen.
     * 
     */
    
    GreenfootSound bgm = new GreenfootSound("title.mp3");
    Prop heart = new Prop(200, 200, "root_corruption_hd.png");
    boolean first = true;
    public Title_Screen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false);
        addObject(heart, getWidth()/2, 110);
        addObject(new Prop(154*4, 32*4, "title.png"), getWidth()/2, 210);
        addObject(new Play_Button(), getWidth()/2, getHeight()*2/3);
        addObject(new howToButton(), getWidth()/2, getHeight()*4/5);
        setPaintOrder(Transition.class);
    }
    public void stopBGM(){
        bgm.stop();
    }
    public void act(){
        if(first){
            first = false;
            bgm.setVolume(80);
            bgm.playLoop();
        }
        heart.beat();
    }
}
