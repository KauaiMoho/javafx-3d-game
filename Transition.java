import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Transition here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Transition extends Actor
{
    /**
     * Act - do whatever the Transition wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage fadeImg;
    
    public Transition(int w, int h, int i){
        fadeImg = new GreenfootImage(w, h);
        fadeImg.setColor(Color.BLACK);
        fadeImg.fill();
        fadeImg.setTransparency(i);
        setImage(fadeImg);
    }
    
    public void fade(int i){
        fadeImg.setTransparency(i);
        setImage(fadeImg);
    }
}
