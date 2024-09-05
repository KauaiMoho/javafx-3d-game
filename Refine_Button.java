import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Refine_Button extends Buttons
{
    int col;
    int row;
    int x;
    Vehicle v;
    GreenfootImage refine = new GreenfootImage("refine_button_02.png");
    public Refine_Button(int c, int r, Vehicle vehicle) {
        col = c;
        v = vehicle;
        row = r;
        
        
        if (c == 0 && r == 0){
            setImage(refine);
        }
        
    }
    
    public void act() {
        
        
    
        super.act();
        Surface surface = (Surface)getWorld();
        if (!surface.isOpen(1) && !surface.isOpen(0) && !surface.isOpen(2)){
            surface.removeObject(this);
        }
            
    }
    
    
    @Override
    public void pressed() {
        if (row == 0) {
            if (col == 0) {
                v.changeRefinedOreCount("coke", v.getCoalC());
                v.setCoalCTo0();
            } else if (col == 1 && v.getCokeC() >= 1 && v.getCopperC() > 0) {
                v.changeRefinedOreCount("coke",-1);
                v.changeRefinedOreCount("copper", v.getCopperC());
                v.setCopperCTo0();
            } else if ( col == 2 && v.getCokeC() >= 1 && v.getIronC() > 0) {
                v.changeRefinedOreCount("coke",-1);
                v.changeRefinedOreCount("iron", v.getIronC());
                v.setIronCTo0();
            }
        } else if (row == 1) {
            if (col == 0 && v.getCokeC() >= 1 && v.getGoldC() > 0) {
                v.changeRefinedOreCount("coke",-1);
                v.changeRefinedOreCount("gold", v.getGoldC());
                v.setGoldCTo0();
            } else if(col == 1 && v.getCokeC() >= 1 && v.getUraniumC() > 0){
                v.changeRefinedOreCount("coke",-1);
                v.changeRefinedOreCount("uranium", v.getUraniumC());
                v.setUraniumCTo0();
            }else if (col == 2 && v.getCokeC() >= 1 && v.getDiamondC() > 0){
                v.changeRefinedOreCount("coke",-1);
                v.changeRefinedOreCount("diamond", v.getDiamondC());
                v.setDiamondCTo0();
            }
        } else {
            if (col == 0 && v.getCokeC() >= 1 && v.getPlatinumC() > 0) {
                v.changeRefinedOreCount("coke",-1);
                v.changeRefinedOreCount("platinum", v.getPlatinumC());
                v.setPlatinumCTo0();
            } else if (col == 1 && v.getCokeC() >= 1 && v.getTitaniumC() > 0){
                v.changeRefinedOreCount("coke",-1);
                //System.out.println(v.getTitaniumC());
                v.changeRefinedOreCount("titanium", v.getTitaniumC());
                v.setTitaniumCTo0();
            }
        }
    }
    
}
