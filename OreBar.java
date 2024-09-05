import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OreBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OreBar extends Actor
{
    /**
     * Act - do whatever the OreBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    int value = 0;
    int oldHeight = 0;
    int type = 0;
    
    boolean isRefined;
    
    Color color = Color.BLACK;
    
    Color coalColor = new Color(196, 196, 196);
    Color copperColor = new Color(209, 119, 17);
    Color ironColor = new Color(212, 168, 129);
    Color goldColor = new Color(224, 183, 16);
    Color uraniumColor = new Color(119, 209, 17);
    Color diamondColor = new Color(146, 232, 223);
    Color platinumColor = new Color(227, 227, 227);
    Color titaniumColor = new Color(189, 189, 189);
    Vehicle vehicle;
    
    GreenfootImage bg = new GreenfootImage(800, 600);
    public OreBar(int n, boolean isR, Vehicle v){
        setImage(bg);
        vehicle = v;
        type = n;
        
        isRefined = isR;
        
        if(type==0){
            color = coalColor;
        }else if(type==1){
            color = copperColor;
        }else if(type==2){
            color = ironColor;
        }else if(type==3){
            color = goldColor;
        }else if(type==4){
            color = uraniumColor;
        }else if(type==5){
            color = diamondColor;
        }else if(type==6){
            color = platinumColor;
        }else{
            color = titaniumColor;
        }
    }
    
    public void act()
    {
        if (!isRefined) {
            if(type==0){
                value = vehicle.getCoalC();
            }else if(type==1){
                value = vehicle.getCopperC();
            }else if(type==2){
                value = vehicle.getIronC();
            }else if(type==3){
                value = vehicle.getGoldC();
            }else if(type==4){
                value = vehicle.getUraniumC();
            }else if(type==5){
                value = vehicle.getDiamondC();
            }else if(type==6){
                value = vehicle.getPlatinumC();
            }else{
                value = vehicle.getTitaniumC();
            }
        } else {
            if(type==0){
                value = vehicle.getCokeC();
            }else if(type==1){
                value = vehicle.getCopperBarsC();
            }else if(type==2){
                value = vehicle.getIronBarsC();
            }else if(type==3){
                value = vehicle.getGoldBarsC();
            }else if(type==4){
                value = vehicle.getEnrichedUraniumC();
            }else if(type==5){
                value = vehicle.getDiamondFacetedC();
            }else if(type==6){
                value = vehicle.getPlatinumBarsC();
            }else{
                value = vehicle.getTitaniumBarsC();
            }                
        }
        bg.clear();
        Font font = new Font("Impact", 24);
        bg.setFont(font);
        bg.setColor(color);
        bg.drawString("x" + value, getWorld().getWidth()/2 - 12, getWorld().getHeight()/2);
        setImage(bg);
        //if(value != oldHeight && value > 0){
            //img.scale(15, height*3);
            //setLocation(getX(), getWorld().getHeight()*24/25 - 16 - value);
        //}
        //oldHeight = height;
    }
}
