import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Corruption_Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Corruption_Counter extends Actor {
    GreenfootImage image = new GreenfootImage(800, 600);
    Color corruptionColor = new Color(207, 43, 136);
    public void act() {
        int numCorruption = ((MyWorld)(getWorld())).getAllCorruption().size();
        
        image.clear();
        Font font = new Font("Impact", 30);
        image.setFont(font);
        image.setColor(corruptionColor);
        image.drawString("Corrupted blocks: " + numCorruption, getWorld().getWidth()/50, getWorld().getHeight()*22/25);
        setImage(image);
        
    }
}
