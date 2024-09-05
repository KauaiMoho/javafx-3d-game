import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Fade extends Actor {
    
    int depth;
    int initial_width;
    int initial_height;
    int width;
    int height;
    
    
    public Fade() {
        GreenfootImage fade = new GreenfootImage("fade4.png");
        initial_width = fade.getWidth();
        initial_height = fade.getHeight();
        width = initial_width;
        height = initial_height;
        fade.scale(initial_width, initial_height);
        setImage(fade);
    }
    
    public void act() {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        depth = player.getDepth();
        width = initial_width + (6*(10-player.getBreadthOfVision())*depth/100);
        height = initial_height + (5*(10-player.getBreadthOfVision())*depth/100);
        
        if (player.getBreadthOfVision() == 0){
            if (width > 800){
                GreenfootImage fade = new GreenfootImage("fade4.png");
                fade.scale(width, height);
                setImage(fade);
            }
        } else if (player.getBreadthOfVision() == 1){
            if (width > 1150){
                GreenfootImage fade = new GreenfootImage("fade4.png");
                fade.scale(width, height);
                setImage(fade);
            }
        } else if (player.getBreadthOfVision() == 2){
            if (width > 1500){
                GreenfootImage fade = new GreenfootImage("fade4.png");
                fade.scale(width, height);
                setImage(fade);
            }
        } else if (player.getBreadthOfVision() == 3){
            if (width > 1850){
                GreenfootImage fade = new GreenfootImage("fade4.png");
                fade.scale(width, height);
                setImage(fade);
            }
        }
       
        
        
        // This is something I will explain in person.
        
        
        //if (player.getBreadthOfVision() == 1) {
            ///if (width > 800){
                //GreenfootImage fade = new GreenfootImage("fade.png");
                //fade.scale(width, height);
                //setImage(fade);
            //}
        //} else if (player.getBreadthOfVision() == 2) {
            //if (width > 1200){
                //GreenfootImage fade = new GreenfootImage("fade.png");
                //fade.scale(width, height);
                //setImage(fade);
            //}
        //}
        // Add your action code here.
    }
}
