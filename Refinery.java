import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Refinery extends Buttons {
    Vehicle vehicle;
    public Refinery(Vehicle v) {
        vehicle = v;
        getImage().scale(450, 300);
    }
    @Override
    public void pressed(){
        Surface surface = (Surface)getWorld();
        if (!surface.isOpen(0) && !surface.isOpen(1) && !surface.isOpen(2)){
            surface.setOpen(2, true);// what is wrong with this
            getWorld().addObject(new GUI_Square(600, 450), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new X_button(), 650, 115);
            
            
            
            for (int i = 0; i < 3; i++) {
                getWorld().addObject(new Refine_Button(i, 0, vehicle), 200 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()),200);
                if (i == 0) {
                    getWorld().addObject(new Refined_Ores(0, false), 200 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()) ,130);
                } else if (i == 1) {
                    getWorld().addObject(new Refined_Ores(1, false), 200 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()) ,130);
                } else {
                    getWorld().addObject(new Refined_Ores(2, false), 200 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()) ,130);
                }
            }
            for (int i = 0; i < 3; i++) {
                getWorld().addObject(new Refine_Button(i, 1, vehicle), 200 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()), 325);
                if (i == 0) {
                    getWorld().addObject(new Refined_Ores(3, false), 200 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()) ,260);
                } else if (i == 1) {
                    getWorld().addObject(new Refined_Ores(4, false), 200 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()) ,260);
                } else {
                    getWorld().addObject(new Refined_Ores(5, false), 200 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()) ,260);
                }
            }
            for (int i = 0; i < 2; i++) {
                //getWorld().addObject(new Refine_Button(i, 2, vehicle), 300 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()), 450);
                if (i == 0) {
                    getWorld().addObject(new Refine_Button(i, 2, vehicle), 300 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()), 450);
                    getWorld().addObject(new Refined_Ores(6, false), 300 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()) ,380);
                } else {
                    getWorld().addObject(new Refine_Button(i, 2, vehicle), 300 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()), 450);
                    getWorld().addObject(new Refined_Ores(7, false), 300 + i*(new Refine_Button(7, 7, vehicle).getImage().getWidth()) ,380);
                }
            }
        }
        
    }
}
