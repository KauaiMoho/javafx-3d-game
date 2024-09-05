import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Props here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Prop extends Actor
{
    /**
     * Act - do whatever the Props wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    int frames = 0; 
    int w = 0;
    int h = 0;
    public Prop(int width, int height, String filename){
        img = new GreenfootImage(filename);
        img.scale(width, height);
        w = width;
        h = height;
        setImage(img);
    }
    
    public void beat(){
        if(frames == 1){
            img.scale((int)(w*1.1),(int)(h*1.1));
            setImage(img);
        }else if(frames == 50){
            img.scale(w, h);
            setImage(img);
        }else if(frames == 100){
            frames = 0;
        }
        frames++;
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
