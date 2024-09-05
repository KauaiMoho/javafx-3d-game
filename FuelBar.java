import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FuelBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FuelBar extends Actor
{
    /**
     * Act - do whatever the FuelBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    public FuelBar(){
        img = new GreenfootImage(20, 200);
        img.setColor(new Color(105, 80, 13));
        img.fillRect(0,0,100,100);
        setImage(img);    
    }
    
    public void act()
    {
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        //System.out.println(player.getFuel()*(200/player.getFuelSize()));
        if(player.getFuel()*2 > 0){
            img = new GreenfootImage(20, 200);
            img.setColor(new Color(105, 80, 13));
            img.fillRect(0,0,100,100);
            img.scale(20,200*curScale()/100);
            setImage(img); 
        } else {
            img.clear();
        }
        //System.out.println(img.getHeight());
    }
    
    public int curScale(){
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        for (int i = 0; i < 101; i++){
            if (player.getFuelSize() * i/100 >= player.getFuel()){
                return i;
            }
        }
        return 0;
    }
}
