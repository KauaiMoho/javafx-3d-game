import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Score_Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score_Text extends Actor
{
    /**
     * Act - do whatever the Score_Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage bg = new GreenfootImage(800, 600);
    int score;
    boolean done;
    int frame = 0;
    int increment;
    public Score_Text(int s, boolean fadeDone){
        score = s;
        done = fadeDone;
        increment = (int)(s/100);
        String text = "0";
        Font font = new Font("Impact", 50);
        bg.setFont(font);
        bg.setColor(Color.WHITE);
        bg.drawString(text, 400, 300);
        setImage(bg);
    }
    public void act()
    {
        // Add your action code here.
        if (done){
            if (score - frame > increment){
                frame += increment;
            } else {
                frame += score - frame; 
            }
            String text = "" + frame;
            bg.clear();
            Font font = new Font("Impact", 50);
            bg.setFont(font);
            bg.setColor(Color.WHITE);
            bg.drawString(text, getImage().getWidth()/2, getImage().getHeight()/2);
            setImage(bg);
        }
    }
}
