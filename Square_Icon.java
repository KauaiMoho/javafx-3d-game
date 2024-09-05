import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Square_Icon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Square_Icon extends GUI_Elements
{
    /**
     * Act - do whatever the Square_Icon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int previousPage = 1;
    int frame = 0;
    
    GreenfootImage squareIcon = new GreenfootImage("icon_bg.png");
    public Square_Icon(){
        squareIcon.scale(120, 120);
        setImage(squareIcon);
    }
    
    public void act(){
        if (frame == 0){
            frame++;
            Scroll_Bar scrollBar = (Scroll_Bar)(getWorld().getObjects(Scroll_Bar.class).get(0));
            previousPage = scrollBar.getPageNum();
        }
        Scroll_Bar scrollBar = null;
        if (getWorld().getObjects(Scroll_Bar.class).size() > 0){
            scrollBar = (Scroll_Bar)(getWorld().getObjects(Scroll_Bar.class).get(0));
        }
        if (scrollBar != null && scrollBar.getPageNum() != previousPage){
            previousPage = scrollBar.getPageNum();
            getWorld().removeObject(this);
        } else {
            super.act();
        }
    }
}
