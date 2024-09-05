import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Hole extends Actor {
    int posX;
    int posY;
    boolean first = false;
    public Hole(){
        first = true;
    }
    public Hole(int x, int y){
        posX = x;
        posY = y;
    }
    
    public void act()
    {
        // Add your action code here.
        Vehicle player = (Vehicle)(getWorld().getObjects(Vehicle.class).get(0));
        
        int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
        if (first){
            first = false;
            posX = getX() - getWorld().getWidth()/2 + playerX;
            posY = getY() - getWorld().getHeight()/2 + playerY;
        }
        
        setLocation((posX - playerX) + getWorld().getWidth()/2, (posY - playerY) + getWorld().getHeight()/2);
        
    }
}
