import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Ore_Icon extends GUI_Elements{
    
    GreenfootImage iconCoal = new GreenfootImage("coal_icon.png");
    GreenfootImage iconCopper = new GreenfootImage("copper_icon.png");
    GreenfootImage iconIron = new GreenfootImage("iron_icon.png");
    GreenfootImage iconGold = new GreenfootImage("gold_icon.png");
    GreenfootImage iconUranium = new GreenfootImage("uranium_icon.png");
    GreenfootImage iconDiamond = new GreenfootImage("diamond_icon.png");
    GreenfootImage iconTitanium = new GreenfootImage("titanium_icon.png");
    
    int frame = 0;
    int previousPage;
    
    public Ore_Icon(int n){
            
        if(n==0){
            setImage(iconCoal);
        }else if(n==1){
            setImage(iconCopper);
        }else if(n==2){
            setImage(iconIron);
        }else if(n==3){
            setImage(iconGold);
        }else if (n==4){
            setImage(iconUranium);
        } else if (n==5){
            setImage(iconDiamond);
        } else if (n==6){
            setImage(iconTitanium);
        }
        
    }
    
    public void act(){
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
    }
}
