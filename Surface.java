import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Surface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Surface extends World
{

    /**
     * Constructor for objects of class Surface.
     * 
     */
    boolean upgradeOpen = false;
    boolean fuelOpen = false;

    boolean refineryOpen = false;

    
    World mainWorld;
    
    GreenfootImage surface_1 = new GreenfootImage("surface_1.png");
    GreenfootImage surface_2 = new GreenfootImage("surface_2.png");
    GreenfootImage surface_3 = new GreenfootImage("surface_3.png");
    
    GreenfootSound bgm = new GreenfootSound("surfaceBGM.mp3");
    boolean first = true;
    
    public Surface(World w)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false); 
        
        //USE THIS OBJECT TO CHANGE THINGS LIKE DRILLS AND FUEL
        
        Exit_button exitButton = new Exit_button(w, true, this);
        addObject(exitButton, exitButton.getImage().getWidth()*2/3, exitButton.getImage().getHeight()*2/3);
        addObject(((MyWorld)(w)).getVehicle(), -1000, -1000);
        //addObject(new Prop(800, 600, "dome.png"), getWidth()/2, getHeight()/2);
        addObject(new Fuel_station(((MyWorld)(w)).getVehicle()), getWidth()/8, getHeight()*29/40);
        addObject(new Refinery(((MyWorld)(w)).getVehicle()), getWidth()/2, getHeight()*2/3+10);
        addObject(new Upgrade_shop(((MyWorld)(w)).getVehicle()), getWidth()*7/8, getHeight()*3/4);
        
        int increment = 50;
        
        if(((MyWorld)(w)).getVehicle().getDrillType() == 1){
            setBackground(surface_1);
        }else if(((MyWorld)(w)).getVehicle().getDrillType() == 2){
            setBackground(surface_2);
        }else if(((MyWorld)(w)).getVehicle().getDrillType() == 3){
            setBackground(surface_3);
        }
        
        for(int i = 0; i < 8; i++){
            addObject(new Icon(i, true), i*increment + getWidth() - increment*8 + 10, getHeight()*2/25);
            addObject(new OreBar(i, true, ((MyWorld)(w)).getVehicle()), i*increment + getWidth() - increment*8 + 10, getHeight()*2/25 - 16);
        }
        for(int i = 0; i < 8; i++){
            addObject(new Icon(i, false), i*increment + getWidth() - increment*8 + 10, getHeight()*2/25 + 60);
            addObject(new OreBar(i, false, ((MyWorld)(w)).getVehicle()), i*increment + getWidth() - increment*8 + 10, getHeight()*2/25 + 46);
        }
        setPaintOrder(Exit_button.class, X_button.class, Buy_Button.class, Scroll_Button.class);
    }
    public String returnType() {
        return "surface";
    }
    
    public void act(){
    
        if(first){
            first = false;
            bgm.setVolume(40);
            bgm.playLoop();
        }else if(!bgm.isPlaying()){
            bgm.playLoop();
        }
    
    }
    
    public void stopBGM(){
        bgm.pause();
    }
    
    public void setOpen(int openType, boolean open){
        if (openType == 0){
            fuelOpen = open;
        } else if (openType == 1)  {
            upgradeOpen = open;
        } else {
            refineryOpen = open;
        }
    }
    public boolean isOpen(int openType){
        if (openType == 0){
            return fuelOpen;
        } else if (openType == 1) {
            return upgradeOpen;
        } else {
            return refineryOpen; 
        }
        
    }
}
