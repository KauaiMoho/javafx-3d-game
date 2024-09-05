import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class howToButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class howToButton extends Buttons
{
    /**
     * Act - do whatever the howToButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public howToButton(){
        GreenfootImage howTo = new GreenfootImage("how_to_play.png");
        howTo.scale(getImage().getWidth()*4, getImage().getHeight()*4);
        setImage(howTo);
    }
    
    @Override
    public void pressed(){
        ((Title_Screen)getWorld()).stopBGM();
        Greenfoot.setWorld(new how_to_world());
    }
}
