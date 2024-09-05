import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GUI_Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI_Text extends GUI_Elements
{
    /**
     * Act - do whatever the GUI_Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    String text = "";
    int xPos;
    int yPos;
    int previousPage = 1;
    int frame = 0;
    
    GreenfootImage bg = new GreenfootImage(800, 600);
    public GUI_Text(String displayText, int x, int y){
        text = displayText;
        xPos = x;
        yPos = y;
        Font font = new Font("Impact", 24);
        bg.setFont(font);
        bg.setColor(Color.WHITE);
        bg.drawString(text, xPos, yPos);
        setImage(bg);
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
