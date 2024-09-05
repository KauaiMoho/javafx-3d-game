 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Buy_Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Buy_Button extends Buttons
{
    /**
     * Act - do whatever the Buy_Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int previousPage = 1;
    int frame = 0;
    int pos;
    int page;
    boolean pressedAlready = false;
    Vehicle vehicle;
    Scroll_Bar scrollBar;
    GreenfootSound build = new GreenfootSound("build.mp3");
    GreenfootImage notPressed = new GreenfootImage("buy_button_0.png");
    GreenfootImage isPressed = new GreenfootImage("buy_button_1.png");
    public Buy_Button(int p, int pa, Vehicle v){
        pos = p;
        page = pa;
        vehicle = v;
    }
    public void act(){
        if (frame == 0){
            frame++;
            //Cant below just go in constructor
            
            scrollBar = (Scroll_Bar)(getWorld().getObjects(Scroll_Bar.class).get(0));
            previousPage = scrollBar.getPageNum();
        }
        makePressed();
        Scroll_Bar scrollBar = null;
        if (getWorld().getObjects(Scroll_Bar.class).size() > 0){
            scrollBar = (Scroll_Bar)(getWorld().getObjects(Scroll_Bar.class).get(0));
        }
        if (scrollBar != null && scrollBar.getPageNum() != previousPage){
            previousPage = scrollBar.getPageNum();
            getWorld().removeObject(this);
        } else {
            super.act();
            Surface surface = (Surface)getWorld();
            if (!surface.isOpen(1) && !surface.isOpen(0)){
                surface.removeObject(this);
            }
        }
        
    }
    public void makePressed(){
        if(page == 1){
            if (pos == 0){
                if(vehicle.getDrillType() >= 1){
                    setImage(isPressed);
                    pressable = false;
                }
            }else if(pos == 1){
                if(vehicle.getDrillType() >= 2){
                    setImage(isPressed);
                    pressable = false;
                }
            }else{
                if(vehicle.getDrillType() >= 3){
                    setImage(isPressed);
                    pressable = false;
                }
            }
        }else if(page == 2){
            if (pos == 0){
                if(vehicle.getFuelType() >= 1){
                    setImage(isPressed);
                    pressable = false;
                }
            }else if(pos == 1){
                if(vehicle.getFuelType() >= 2){
                    setImage(isPressed);
                    pressable = false;
                }
            }else{
                if(vehicle.getFuelType() >= 3){
                    setImage(isPressed);
                    pressable = false;
                }
            }
        }else{
            if (pos == 0){
                if(vehicle.getBreadthOfVision() >= 1){
                    setImage(isPressed);
                    pressable = false;
                }
            }else if(pos == 1){
                if(vehicle.getBreadthOfVision() >= 2){
                    setImage(isPressed);
                    pressable = false;
                }
            }else{
                if(vehicle.getBreadthOfVision() >= 3){
                    setImage(isPressed);
                    pressable = false;
                }
            }
        }
    }
    @Override
    public void pressed(){
        if(page == 1){
            if (pos == 0){
                if(vehicle.getDrillType() < 1 && vehicle.getCokeC() >= 3 && vehicle.getCopperBarsC() >= 6){
                    vehicle.changeRefinedOreCount("coke", -3);
                    vehicle.changeRefinedOreCount("copper", -6);
                    vehicle.setDrillType(1);
                    setImage(isPressed);
                    pressable = false;
                    build.play();
                }
            }else if(pos == 1){
                if(vehicle.getDrillType() < 2 && vehicle.getIronBarsC() >= 3 && vehicle.getGoldBarsC() >= 5){
                    vehicle.changeRefinedOreCount("iron", -3);
                    vehicle.changeRefinedOreCount("gold", -5);
                    vehicle.setDrillType(2);
                    setImage(isPressed);
                    pressable = false;
                    build.play();
                }
            }else{
                if(vehicle.getDrillType() < 3 && vehicle.getTitaniumBarsC() >= 3 && vehicle.getDiamondFacetedC() >= 3){
                    vehicle.changeRefinedOreCount("titanium", -3);
                    vehicle.changeRefinedOreCount("diamond", -3);
                    vehicle.setDrillType(3);
                    setImage(isPressed);
                    pressable = false;
                    build.play();
                }
            }
        }else if(page == 2){
            if (pos == 0){
                if(vehicle.getFuelType() < 1 && vehicle.getCokeC() >= 5){
                    vehicle.changeRefinedOreCount("coke", -5);
                    vehicle.setFuelType(1);
                    setImage(isPressed);
                    pressable = false;
                    build.play();
                }
            }else if(pos == 1){
                if(vehicle.getFuelType() < 2 && vehicle.getCokeC() >= 10){
                    vehicle.changeRefinedOreCount("coke", -10);
                    vehicle.setFuelType(2);
                    setImage(isPressed);
                    pressable = false;
                    build.play();
                }
            }else{
                if(vehicle.getFuelType() < 3 && vehicle.getEnrichedUraniumC() >= 3){
                    vehicle.changeRefinedOreCount("uranium", -3);
                    vehicle.setFuelType(3);
                    setImage(isPressed);
                    pressable = false;
                    build.play();
                }
            }
        }else{
            if (pos == 0){
                if(vehicle.getBreadthOfVision() < 1 && vehicle.getCopperBarsC() >= 3){
                    vehicle.changeRefinedOreCount("copper", -3);
                    vehicle.setBreadthOfVision(1);
                    setImage(isPressed);
                    pressable = false;
                    build.play();
                }
            }else if(pos == 1){
                if(vehicle.getBreadthOfVision() < 2 && vehicle.getIronBarsC() >= 3){
                    vehicle.changeRefinedOreCount("iron", -3);
                    vehicle.setBreadthOfVision(2);
                    setImage(isPressed);
                    pressable = false;
                    build.play();
                }
            }else{
                if(vehicle.getBreadthOfVision() < 3 && vehicle.getGoldBarsC() >= 3){
                    vehicle.changeRefinedOreCount("gold", -3);
                    vehicle.setBreadthOfVision(3);
                    setImage(isPressed);
                    pressable = false;
                    build.play();
                }
            }
        }
    }
}
