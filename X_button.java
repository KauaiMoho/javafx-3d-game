import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class X_button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class X_button extends Buttons
{
    /**
     * Act - do whatever the X_button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage xButton = new GreenfootImage("X_button.png");
    public X_button(){
        xButton.scale(xButton.getWidth()*4, xButton.getHeight()*4);
        setImage(xButton);
    }   
    
    @Override
    public void pressed(){
        Surface surface = (Surface)getWorld();
        if (surface.isOpen(0)){
            surface.setOpen(0, false);
        }
        if (surface.isOpen(1)){
            surface.setOpen(1, false);
        }
        if (surface.isOpen(2)){
            surface.setOpen(2, false);
        }
        if (!surface.isOpen(1) && !surface.isOpen(0) && !surface.isOpen(2)){
            surface.removeObject(this); // yeah, let me show you
        }
    }
}
