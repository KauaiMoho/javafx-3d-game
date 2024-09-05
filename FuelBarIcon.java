import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FuelBarIcon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FuelBarIcon extends GUI_Elements
{
    /**
     * Act - do whatever the FuelBarIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    Vehicle vehicle;
    public FuelBarIcon(Vehicle v){
        vehicle = v;
        img = new GreenfootImage(40, 400);
        img.setColor(new Color(105, 80, 13));
        img.fillRect(0,0,200,200);
        setImage(img); 
    }
    
    public void act()
    {
        super.act();
        if(vehicle.getFuel()*2 > 0){
            //img.clear();
            img = new GreenfootImage(40, 400);
            img.setColor(new Color(105, 80, 13));
            img.fillRect(0,0,200,200);
            img.scale(40,400*curScale()/100);
            setImage(img); 
        } else {
            img.clear();
        }
        //System.out.println(img.getHeight());
    }
    
    public int curScale(){
        for (int i = 0; i < 101; i++){
            if (vehicle.getFuelSize() * i/100 >= vehicle.getFuel()){
                return i;
            }
        }
        return 0;
    }
}
