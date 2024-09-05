import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class escapeButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class escapeButton extends Buttons
{
    
    public escapeButton(){
        GreenfootImage escape = new GreenfootImage("exit_icon.png");
        escape.scale(getImage().getWidth()/2, getImage().getHeight()/2);
        setImage(escape);
    }
    @Override
    public void pressed(){
        Greenfoot.setWorld(new Title_Screen());
    }
}
