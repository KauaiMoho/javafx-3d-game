import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Drill_Icon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Drill_Icon extends GUI_Elements
{
    /**
     * Act - do whatever the Drill_Icon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int COPPER = 0;
    int GOLD = 1;
    int URANIUM = 2;
    int TANK_0 = 3;
    int TANK_1 = 4;
    int TANK_2 = 5;
    int LIGHT_0 = 6;
    int LIGHT_1 = 7;
    int LIGHT_2 = 8;
    int type;
    int previousPage = 1;
    int frame = 0;
    
    GreenfootImage copperIcon = new GreenfootImage("copper_drill_icon.png");
    GreenfootImage goldIcon = new GreenfootImage("gold_drill_icon.png");
    GreenfootImage uraniumIcon = new GreenfootImage("diamond_drill_icon.png");
    GreenfootImage tank0Icon = new GreenfootImage("tank_0.png");
    GreenfootImage tank1Icon = new GreenfootImage("tank_1.png");
    GreenfootImage tank2Icon = new GreenfootImage("tank_2.png");
    GreenfootImage light0Icon = new GreenfootImage("light_0.png");
    GreenfootImage light1Icon = new GreenfootImage("light_1.png");
    GreenfootImage light2Icon = new GreenfootImage("light_2.png");
    
    public Drill_Icon(int drillType){
        type = drillType;
        if (type == COPPER){
            copperIcon.scale(90, 90);
            setImage(copperIcon);
        } else if (type == GOLD){
            goldIcon.scale(90, 90);
            setImage(goldIcon);
        } else if (type == URANIUM){
            uraniumIcon.scale(90, 90);
            setImage(uraniumIcon);
        } else if (type == TANK_0){
            tank0Icon.scale(90, 90);
            setImage(tank0Icon);
        } else if (type == TANK_1){
            tank1Icon.scale(90, 90);
            setImage(tank1Icon);
        } else if (type == TANK_2){
            tank2Icon.scale(90, 90);
            setImage(tank2Icon);
        } else if (type == LIGHT_0){
            light0Icon.scale(90, 90);
            setImage(light0Icon);
        } else if (type == LIGHT_1){
            light1Icon.scale(90, 90);
            setImage(light1Icon);
        } else if (type == LIGHT_2){
            light2Icon.scale(90, 90);
            setImage(light2Icon);
        }
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
