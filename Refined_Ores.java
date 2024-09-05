import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Refined_Ores extends GUI_Elements {
    boolean upgradeMenu;
    
    int frame = 0;
    int previousPage;
    
    GreenfootImage iconCopperBars = new GreenfootImage("copper_bars_icon.png");
    GreenfootImage iconIronBars = new GreenfootImage("iron_bars_icon.png");
    GreenfootImage iconGoldBars = new GreenfootImage("gold_bars_icon.png");
    GreenfootImage iconTitaniumBars = new GreenfootImage("titanium_bars_icon.png");
    GreenfootImage iconPlatinumBars = new GreenfootImage("platinum_bars_icon.png");
    GreenfootImage iconDiamondFaceted = new GreenfootImage("diamond_faceted_icon.png");
    GreenfootImage iconUraniumEnriched = new GreenfootImage("uranium_enriched_icon.png");
    GreenfootImage iconCoke = new GreenfootImage("coke_icon.png");
    
    public Refined_Ores(int n, boolean upgradeMenuOrNot) {
        upgradeMenu = upgradeMenuOrNot;
        if (n == 0) {
            iconCoke.scale(50, 50);
            setImage(iconCoke);
        } else if (n == 1) {
            iconCopperBars.scale(50, 50);
            setImage(iconCopperBars);
        } else if (n == 2) {
            iconIronBars.scale(50,50);
            setImage(iconIronBars);
        } else if (n == 3) {
            iconGoldBars.scale(50, 50);
            setImage(iconGoldBars);
        } else if (n == 4) {
            iconUraniumEnriched.scale(50,50);
            setImage(iconUraniumEnriched);
        } else if (n == 5) {
            iconDiamondFaceted.scale(50,50);
            setImage(iconDiamondFaceted);
        } else if (n == 6) {
            iconPlatinumBars.scale(50,50);
            setImage(iconPlatinumBars);
        } else {
            iconTitaniumBars.scale(50,50);
            setImage(iconTitaniumBars);
        }
    }
    
    public void act() {
        if (upgradeMenu) {
            if (frame == 0){
                frame++;
                Scroll_Bar scrollBar = (Scroll_Bar)(getWorld().getObjects(Scroll_Bar.class).get(0));
                previousPage = scrollBar.getPageNum();
            }
            Scroll_Bar scrollBar = null;
            if (getWorld().getObjects(Scroll_Bar.class).size() > 0) {
                scrollBar = (Scroll_Bar)(getWorld().getObjects(Scroll_Bar.class).get(0));
            }
            if (scrollBar != null && scrollBar.getPageNum() != previousPage){
                previousPage = scrollBar.getPageNum();
                getWorld().removeObject(this);
            } else {
                super.act();
            }
        } else {
            super.act();
        }
           
    }
}
