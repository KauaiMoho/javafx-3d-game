import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class nextHowToButton extends Buttons {
    public nextHowToButton(){
        GreenfootImage next = new GreenfootImage("exit_icon.png");
        next.scale(getImage().getWidth()*4, getImage().getHeight()*4);
        setImage(next);
        setRotation(180);
    }
    @Override
    public void pressed(){
        Greenfoot.setWorld(new second_how_to_world());
    }
}
