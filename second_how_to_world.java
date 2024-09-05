import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class second_how_to_world extends World {
    public second_how_to_world() {    
        super(800, 600, 1); 
        addObject(new Prop(800, 600, "howToPlayScreen1.png"), getWidth()/2, getHeight()/2);
        addObject(new escapeButton(), 50, 50); 
    }
}
