import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Icon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Icon extends Actor
{
    /**
     * Act - do whatever the Icon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage iconCoal = new GreenfootImage("coal_icon.png");
    GreenfootImage iconCopper = new GreenfootImage("copper_icon.png");
    GreenfootImage iconIron = new GreenfootImage("iron_icon.png");
    GreenfootImage iconGold = new GreenfootImage("gold_icon.png");
    GreenfootImage iconUranium = new GreenfootImage("uranium_icon.png");
    GreenfootImage iconDiamond = new GreenfootImage("diamond_icon.png");
    GreenfootImage iconPlatinum = new GreenfootImage("platinum_icon.png");
    GreenfootImage iconTitanium = new GreenfootImage("titanium_icon.png");
    
    GreenfootImage iconCopperBars = new GreenfootImage("copper_bars_icon.png");
    GreenfootImage iconIronBars = new GreenfootImage("iron_bars_icon.png");
    GreenfootImage iconGoldBars = new GreenfootImage("gold_bars_icon.png");
    GreenfootImage iconTitaniumBars = new GreenfootImage("titanium_bars_icon.png");
    GreenfootImage iconPlatinumBars = new GreenfootImage("platinum_bars_icon.png");
    GreenfootImage iconDiamondFaceted = new GreenfootImage("diamond_faceted_icon.png");
    GreenfootImage iconUraniumEnriched = new GreenfootImage("uranium_enriched_icon.png");
    GreenfootImage iconCoke = new GreenfootImage("coke_icon.png");
    
    public Icon(int n, boolean isRefined){
        if (!isRefined) {
            if(n==0){
                setImage(iconCoal);
            }else if(n==1){
                setImage(iconCopper);
            }else if(n==2){
                setImage(iconIron);
            }else if(n==3){
                setImage(iconGold);
            }else if(n==4){
                setImage(iconUranium);
            }else if(n==5){
                setImage(iconDiamond);
            }else if(n==6){
                setImage(iconPlatinum);
            }else {
                setImage(iconTitanium);
            }
        } else {
            if (n==0) {
                setImage(iconCoke);
            }else if(n==1){
                setImage(iconCopperBars);
            }else if(n==2){
                setImage(iconIronBars);
            }else if(n==3){
                setImage(iconGoldBars);
            }else if(n==4){
                setImage(iconUraniumEnriched);
            }else if(n==5){
                setImage(iconDiamondFaceted);
            }else if (n==6)  {
                setImage(iconPlatinumBars);
            } else {
                setImage(iconTitaniumBars);
            }
        }
            
    }
}
