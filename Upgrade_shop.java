import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Upgrade_shop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Upgrade_shop extends Buttons
{
    /**
     * Act - do whatever the Upgrade_shop wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    Vehicle vehicle;
    
    public Upgrade_shop(Vehicle v){
        vehicle = v;
    }
    
    @Override
    public void pressed(){
        Surface surface = (Surface)getWorld();
        if (!surface.isOpen(1) && !surface.isOpen(0) && !surface.isOpen(2)){
            surface.setOpen(1, true);
            getWorld().addObject(new GUI_Square(600, 450), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new Scroll_Bar(), getWorld().getWidth()/2, getWorld().getHeight()*3/4);
            getWorld().addObject(new Scroll_Button(0), getWorld().getWidth()/2 - getWorld().getWidth()/6, getWorld().getHeight()*3/4);
            getWorld().addObject(new Scroll_Button(1), getWorld().getWidth()/2 + getWorld().getWidth()/6, getWorld().getHeight()*3/4);
            showPage(1);
            getWorld().addObject(new X_button(), 650, 115);
            
            
        }
        
    }
    
    public void showPage(int page){
        if (page == 1){
            for (int i = 0; i < 3; i++){
                getWorld().addObject(new Square_Icon(), getWorld().getWidth()/2 + 150*(i-1), getWorld().getHeight()/3);
                getWorld().addObject(new Drill_Icon(i), getWorld().getWidth()/2 + 150*(i-1), getWorld().getHeight()/3);
            }
            getWorld().addObject(new Refined_Ores(0, true), getWorld().getWidth()/2 - 175, getWorld().getHeight()/2 - 10);
            getWorld().addObject(new Refined_Ores(1, true), getWorld().getWidth()/2 - 175, getWorld().getHeight()*14/25);
            getWorld().addObject(new Refined_Ores(2, true), getWorld().getWidth()/2 - 25, getWorld().getHeight()/2 - 10);
            getWorld().addObject(new Refined_Ores(3, true), getWorld().getWidth()/2 - 25, getWorld().getHeight()*14/25);
            getWorld().addObject(new Refined_Ores(7, true), getWorld().getWidth()/2 + 125, getWorld().getHeight()/2 - 10);
            getWorld().addObject(new Refined_Ores(5, true), getWorld().getWidth()/2 + 125, getWorld().getHeight()*14/25);
            getWorld().addObject(new GUI_Text("x3", getWorld().getWidth()/2 - 150, getWorld().getHeight()/2), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new GUI_Text("x6", getWorld().getWidth()/2 - 150, getWorld().getHeight()*29/50), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new GUI_Text("x3", getWorld().getWidth()/2, getWorld().getHeight()/2), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new GUI_Text("x5", getWorld().getWidth()/2, getWorld().getHeight()*29/50), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new GUI_Text("x3", getWorld().getWidth()/2 + 150, getWorld().getHeight()/2), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new GUI_Text("x3", getWorld().getWidth()/2 + 150, getWorld().getHeight()*29/50), getWorld().getWidth()/2, getWorld().getHeight()/2);
            for (int i = 0; i < 3; i++){
                getWorld().addObject(new Buy_Button(i, page, vehicle), getWorld().getWidth()/2 + (i-1)*150, 390);
            }
        } else if (page == 2){
            //System.out.println("test");
            for (int i = 0; i < 3; i++){
                getWorld().addObject(new Square_Icon(), getWorld().getWidth()/2 + 150*(i-1), getWorld().getHeight()/3);
                getWorld().addObject(new Drill_Icon(i+3), getWorld().getWidth()/2 + 150*(i-1), getWorld().getHeight()/3);
            }
            getWorld().addObject(new Refined_Ores(0, true), getWorld().getWidth()/2 - 175, getWorld().getHeight()/2-10);
            //getWorld().addObject(new Ore_Icon(1), getWorld().getWidth()/2 - 175, getWorld().getHeight()*14/25);
            getWorld().addObject(new Refined_Ores(0, true), getWorld().getWidth()/2 - 25, getWorld().getHeight()/2-10);
            //getWorld().addObject(new Ore_Icon(3), getWorld().getWidth()/2 - 25, getWorld().getHeight()*14/25);
            getWorld().addObject(new Refined_Ores(4, true), getWorld().getWidth()/2 + 125, getWorld().getHeight()/2-10);
            //getWorld().addObject(new Ore_Icon(4), getWorld().getWidth()/2 + 125, getWorld().getHeight()*14/25);
            getWorld().addObject(new GUI_Text("x5", getWorld().getWidth()/2 - 150, getWorld().getHeight()/2), getWorld().getWidth()/2, getWorld().getHeight()/2);
            //getWorld().addObject(new GUI_Text("x10", getWorld().getWidth()/2 - 150, getWorld().getHeight()*29/50), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new GUI_Text("x10", getWorld().getWidth()/2, getWorld().getHeight()/2), getWorld().getWidth()/2, getWorld().getHeight()/2);
            //getWorld().addObject(new GUI_Text("x10", getWorld().getWidth()/2, getWorld().getHeight()*29/50), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new GUI_Text("x3", getWorld().getWidth()/2 + 150, getWorld().getHeight()/2), getWorld().getWidth()/2, getWorld().getHeight()/2);
            // getWorld().addObject(new GUI_Text("x10", getWorld().getWidth()/2 + 150, getWorld().getHeight()*29/50), getWorld().getWidth()/2, getWorld().getHeight()/2);
            for (int i = 0; i < 3; i++){
                getWorld().addObject(new Buy_Button(i, page, vehicle), getWorld().getWidth()/2 + (i-1)*150, 350);
            }
        } else if (page == 3){
            for (int i = 0; i < 3; i++){
                getWorld().addObject(new Square_Icon(), getWorld().getWidth()/2 + 150*(i-1), getWorld().getHeight()/3);
                getWorld().addObject(new Drill_Icon(i+6), getWorld().getWidth()/2 + 150*(i-1), getWorld().getHeight()/3);
            }
            getWorld().addObject(new Refined_Ores(1, true), getWorld().getWidth()/2 - 175, getWorld().getHeight()/2 - 10);
            //getWorld().addObject(new Ore_Icon(1), getWorld().getWidth()/2 - 175, getWorld().getHeight()*14/25);
            getWorld().addObject(new Refined_Ores(2, true), getWorld().getWidth()/2 - 25, getWorld().getHeight()/2 - 10);
            //getWorld().addObject(new Ore_Icon(3), getWorld().getWidth()/2 - 25, getWorld().getHeight()*14/25);
            getWorld().addObject(new Refined_Ores(3, true), getWorld().getWidth()/2 + 125, getWorld().getHeight()/2 - 10);
            //getWorld().addObject(new Ore_Icon(4), getWorld().getWidth()/2 + 125, getWorld().getHeight()*14/25);
            getWorld().addObject(new GUI_Text("x3", getWorld().getWidth()/2 - 150, getWorld().getHeight()/2), getWorld().getWidth()/2, getWorld().getHeight()/2);
            //getWorld().addObject(new GUI_Text("x10", getWorld().getWidth()/2 - 150, getWorld().getHeight()*29/50), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new GUI_Text("x3", getWorld().getWidth()/2, getWorld().getHeight()/2), getWorld().getWidth()/2, getWorld().getHeight()/2);
            //getWorld().addObject(new GUI_Text("x10", getWorld().getWidth()/2, getWorld().getHeight()*29/50), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new GUI_Text("x3", getWorld().getWidth()/2 + 150, getWorld().getHeight()/2), getWorld().getWidth()/2, getWorld().getHeight()/2);
            // getWorld().addObject(new GUI_Text("x10", getWorld().getWidth()/2 + 150, getWorld().getHeight()*29/50), getWorld().getWidth()/2, getWorld().getHeight()/2);
            for (int i = 0; i < 3; i++){
                getWorld().addObject(new Buy_Button(i, page, vehicle), getWorld().getWidth()/2 + (i-1)*150, 350);
            }
        }
    }
}
