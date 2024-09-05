import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scroll_Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scroll_Button extends Buttons
{
    /**
     * Act - do whatever the Scroll_Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int arrowDir;
    GreenfootImage Scroll_Button = new GreenfootImage("Scroll_Button.png");
    public Scroll_Button(int direction){
        arrowDir = direction;
        if (direction == 0){
            Scroll_Button.scale(Scroll_Button.getWidth()*4, Scroll_Button.getHeight()*4);
            setImage(Scroll_Button);
        } else {
            GreenfootImage Scroll_Button2 = new GreenfootImage("Scroll_Button.png");
            Scroll_Button2.scale(Scroll_Button2.getWidth()*4, Scroll_Button2.getHeight()*4);
            Scroll_Button2.mirrorHorizontally();
            setImage(Scroll_Button2);
        }
    }
    public void act(){
        super.act();
        Surface surface = (Surface)getWorld();
        if (!surface.isOpen(1) && !surface.isOpen(0)){
            surface.removeObject(this);
        }
    }
    @Override
    public void pressed(){
        Scroll_Bar scrollBar = getWorld().getObjects(Scroll_Bar.class).get(0);
        if (arrowDir == 0){
            scrollBar.increasePageNum(-1);
        }
        if (arrowDir == 1){
            scrollBar.increasePageNum(1);
        }
        
    }
}
