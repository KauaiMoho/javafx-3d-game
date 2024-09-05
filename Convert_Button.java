import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Convert_Button extends Buttons {
    
    GreenfootImage convertButton = new GreenfootImage("convert_fuel.png");
    GreenfootImage convertButtonCoke = new GreenfootImage("convert_fuel_1.png");
    GreenfootImage convertButtonUranium = new GreenfootImage("convert_fuel_2.png");
    Vehicle vehicle;
    int typeOfFuel;
    
    public Convert_Button(Vehicle v, int type){
        typeOfFuel = type;
        vehicle = v;
        int width = convertButton.getWidth()*9/10;
        int height = convertButton.getHeight()*9/10;
        convertButton.scale(width, height);
        convertButtonCoke.scale(width, height);
        convertButtonUranium.scale(width, height);
        if (typeOfFuel == 0) {
            setImage(convertButton);    
        } else if (typeOfFuel == 1) {
            setImage(convertButtonCoke);
        } else {
            setImage(convertButtonUranium);
        }
    }
    
    public void act()
    {
        // Add your action code here.
        super.act();
        Surface surface = (Surface)getWorld();
        if (!surface.isOpen(1) && !surface.isOpen(0)){
            surface.removeObject(this);
        }
    }
    
    @Override
    public void pressed(){
        if (typeOfFuel == 0) {
            if (vehicle.getFuel() < vehicle.getFuelSize() && vehicle.getCoalC() >= 1){
                if (vehicle.getFuelSize() - vehicle.getFuel() < 25){
                    vehicle.increaseFuel(vehicle.getFuelSize() - vehicle.getFuel());
                    vehicle.changeOreCount("coal", -1);
                } else {
                    vehicle.increaseFuel(25);
                    vehicle.changeOreCount("coal", -1);
                }
            }
        } else if (typeOfFuel == 1) {
            if (vehicle.getFuel() < vehicle.getFuelSize() && vehicle.getCokeC() >= 1){
                if (vehicle.getFuelSize() - vehicle.getFuel() < 50){
                    vehicle.increaseFuel(vehicle.getFuelSize() - vehicle.getFuel());
                    vehicle.changeRefinedOreCount("coke", -1);
                } else {
                    vehicle.increaseFuel(50);
                    vehicle.changeRefinedOreCount("coke", -1);
                }
            }
        } else {
            if (vehicle.getFuel() < vehicle.getFuelSize() && vehicle.getEnrichedUraniumC() >= 1){
                if (vehicle.getFuelSize() - vehicle.getFuel() < 100){
                    vehicle.increaseFuel(vehicle.getFuelSize() - vehicle.getFuel());
                    vehicle.changeRefinedOreCount("uranium", -1);
                } else {
                    vehicle.increaseFuel(100);
                    vehicle.changeRefinedOreCount("uranium", -1);
                }
            }
        }
    }
}
