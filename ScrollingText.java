import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScrollingText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScrollingText extends Actor
{
    /**
     * Act - do whatever the ScrollingText wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    String message;
    String currMessage = "";
    GreenfootImage txt;
    boolean canScroll = false;
    int frames = 0;
    int counter = 0;
    public ScrollingText(String msg){
        message = msg;
        txt = new GreenfootImage(1500, 300);
        Font font = new Font("Impact", 20);
        txt.setFont(font);
        txt.setColor(Color.WHITE);
        txt.drawString(currMessage, 400, 300);
        setImage(txt);
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setCanScroll(boolean b){
        canScroll = b;
    }
    
    public void act()
    {
        if(frames%10 == 0 && canScroll){
            if(counter < message.length()){
                currMessage += message.charAt(counter);
                txt.clear();
                Font font = new Font("Impact", 20);
                txt.setFont(font);
                txt.setColor(Color.WHITE);
                txt.drawString(currMessage, getImage().getWidth()/2, getImage().getHeight()/2 );
                setImage(txt);
                counter++;
            }
        }
        frames++;
    }
}
