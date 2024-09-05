import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */ 
public class Ore extends Actor
{
    int digSpeed = 0;
    int drillType = 0;
    int unloadVal = 0;
    int fuelUsed = 0;
    int hardness;
    String type = "";
    public int getFuelUsed(){
        return fuelUsed;
    }
    public int getDigSpeed(){
        return digSpeed;
    }
    public int getUnloadVal(){
        return unloadVal;
    }
    public int getDrillType(){
        return drillType;
    }
    public String getType(){
        return type;
    }
}
