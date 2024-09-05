import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scroll_Bar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scroll_Bar extends GUI_Elements
{
    /**
     * Act - do whatever the Scroll_Bar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage bar_1 = new GreenfootImage("bar_1.png");
    GreenfootImage bar_2 = new GreenfootImage("bar_2.png");
    GreenfootImage bar_3 = new GreenfootImage("bar_3.png");
    
    int pageNum = 1;
    public Scroll_Bar(){
        bar_1.scale(bar_1.getWidth()*4, bar_1.getHeight()*4);
        bar_2.scale(bar_2.getWidth()*4, bar_2.getHeight()*4);
        bar_3.scale(bar_3.getWidth()*4, bar_3.getHeight()*4);
        setImage(bar_1);
    }
    public void act()
    {
        // Add your action code here.
        super.act();
        if (pageNum == 1){
            setImage(bar_1);
        }
        if (pageNum == 2){
            setImage(bar_2);
        }
        if (pageNum == 3){
            setImage(bar_3);
        }
    }
    public void increasePageNum(int increase){
        pageNum += increase;
        if (pageNum > 3){
            pageNum = 1;
        }
        if (pageNum < 1){
            pageNum = 3;
        }
        //System.out.println(pageNum);
        Upgrade_shop upgradeShop = (Upgrade_shop)(getWorld().getObjects(Upgrade_shop.class).get(0));
        upgradeShop.showPage(pageNum);
    }
    public int getPageNum(){
        return pageNum;
    }
}
