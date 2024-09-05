import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.awt.Point;

public class MyWorld extends World {
    ArrayList<Point> takenTiles = new ArrayList<Point>();
    ArrayList<Point> unloadedCorruption = new ArrayList<Point>();
    ArrayList<Point> allCorruption = new ArrayList<Point>();
    
    int corruptionFrame = 0;
    int fadeCounter = 0;
    boolean fadeAdded = false;
    boolean gameLost = false;
    boolean doFadeOut = true;
    boolean first = true;
    Transition trans = new Transition(getWidth(), getHeight(), 255);
    Transition transTemp = new Transition(getWidth(), getHeight(), 255);
    Transition transOther = new Transition(getWidth(), getHeight(), 0);
    Vehicle vehicle = new Vehicle();
    GreenfootSound bgm = new GreenfootSound("mainBGM.mp3");
    
    public MyWorld() {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false); 
        addObject(transTemp, getWidth()/2, getHeight()/2);
        GreenfootImage bg = new GreenfootImage(getWidth(), getHeight());
        bg.setColor(Color.GRAY);
        bg.fill();
        setBackground(bg);
        
        Exit_button exitButton = new Exit_button(new Surface(this), false, this);
        addObject(exitButton, exitButton.getImage().getWidth()*2/3, exitButton.getImage().getHeight()*2/3);
        
        for (int i = 0; i < 2; i++){
            for (int g = 0; g < 2; g++){
                addObject(new Bg(i*getWidth(), g*getHeight()), -1000, -1000);
            }
        }
        
        addObject(vehicle, getWidth()/2, getHeight()/2);
        FuelBorder fuelBorder = new FuelBorder();
        addObject(fuelBorder, getWidth()-25, 70);
        
        FuelBar fuelBar = new FuelBar();
        addObject(fuelBar, getWidth()-25, 120);
        
        addObject(new Depth_Counter(), getWidth()/2, getHeight()/2);
        addObject(new Corruption_Counter(), getWidth()/2, getHeight()/2);
        
        
        int increment = 50;
        
        for(int i = 0; i < 8; i++){
            addObject(new Icon(i, false), i*increment + getWidth() - increment*8 + 10, getHeight()*24/25);
            addObject(new OreBar(i, false, getVehicle()), i*increment + getWidth() - increment*8 + 10, getHeight()*24/25 - 16);
        }
        
        if(!vehicle.getDevMode()){
            addObject(new Fade(), getWidth()/2, getHeight()/2);
        }        
        for (int i = 0; i < 3; i++){
            for (int g = 0; g < 3; g++){
                addObject(new Default_Hole((i-1)*100, (g-1)*100), getWidth()/2 - 100 + (i*100), getHeight()/2 - 100 + (g*100));
            }
        }
        
        for(int i = 0; i < 34; i++){
            addObject(new Bedrock(getWidth()/2 - (20*100) + (i*100), getHeight()/2 - (6*100)), -100, -100);
        }
        for(int i = 0; i < 34; i++){
            addObject(new Bedrock(getWidth()/2 - (20*100) + (i*100), getHeight()/2 + (43*100)), -100, -100);
        }
        for(int i = 0; i < 48; i++){
            addObject(new Bedrock(getWidth()/2 - (20*100) , getHeight()/2 - (5*100) + (i*100)), -100, -100);
        }
        for(int i = 0; i < 48; i++){
            addObject(new Bedrock(getWidth()/2 - (-13*100) , getHeight()/2 - (5*100) + (i*100)), -100, -100);
        }
        
        genOres();
        //System.out.println("Ores: " + getObjects(Ore.class).size());
        //System.out.println("Titanium: " + getObjects(Titanium.class).size());
        //System.out.println("Diamond: " + getObjects(Diamond.class).size());
        //System.out.println("Platinum: " + getObjects(Platinum.class).size());
        
        addObject(new Root(0, 4000), -100, -100);
        addObject(new Corruption(0,3900), -100, -100);
        allCorruption.add(new Point(0,3900));
        
        setPaintOrder(Transition.class, Exit_button.class, Depth_Counter.class, Corruption_Counter.class,FuelBorder.class, FuelBar.class, Icon.class, OreBar.class, Fade.class, Vehicle.class, Block_Outline.class, Bedrock.class, Default_Hole.class, Hole.class, Breaking.class, Root.class, Corruption.class, Ore.class);
    }
    
    public void unloadedCorrupt(int xOffset, int yOffset, Point p){
        int corruptionX = (int)p.getX();
        int corruptionY = (int)p.getY();
        if (allCorruption.indexOf(new Point(corruptionX + xOffset,corruptionY + yOffset)) == -1 && getObjectsAt(corruptionX + xOffset - vehicle.getPlayerX() + getWidth()/2, corruptionY + yOffset - vehicle.getPlayerY() + getHeight()/2, Root.class).size() == 0 && getObjectsAt(corruptionX + xOffset - vehicle.getPlayerX() + getWidth()/2, corruptionY + yOffset - vehicle.getPlayerY() + getHeight()/2, Bedrock.class).size() == 0 && getObjectsAt(corruptionX + xOffset - vehicle.getPlayerX() + getWidth()/2, corruptionY + yOffset - vehicle.getPlayerY() + getHeight()/2, Breaking.class).size() == 0){
            if (getObjectsAt(corruptionX + xOffset - vehicle.getPlayerX() + getWidth()/2, corruptionY + yOffset - vehicle.getPlayerY() + getHeight()/2, Default_Hole.class).size() != 0){
                gameLost = true;
                //System.out.println("you lose");
            }
            unloadedCorruption.add(new Point(corruptionX + xOffset,corruptionY + yOffset));
            allCorruption.add(new Point(corruptionX + xOffset,corruptionY + yOffset));
        }
    }
    
    public void act(){
        if(doFadeOut){
            if(!fadeAdded){
                fadeAdded = true;
                vehicle.setCanMove(false);
                removeObject(transTemp);
                fadeCounter = 255;
                addObject(trans, getWidth()/2, getHeight()/2);
            }
            if(fadeCounter > 0 && fadeAdded){
                fadeCounter--;
                trans.fade(fadeCounter);
            }else{
                removeObject(trans);
                vehicle.setCanMove(true);
                doFadeOut = false;
                fadeAdded = false;
                fadeCounter = 0;
            }
        }else{
            if(first){
                first = false;
                bgm.setVolume(70);
                bgm.playLoop();
            }
            corruptionFrame++;
            if(unloadedCorruption.size() > 0){
                for(int i = unloadedCorruption.size()-1; i >= 0; i--){ // 350
                    if (corruptionFrame % (Greenfoot.getRandomNumber(100) + 450) == 0 && getObjects(Root.class).size() > 0){
                        int dirSpread = Greenfoot.getRandomNumber(8);
                        if (dirSpread == 0){
                            unloadedCorrupt(-100, -100, unloadedCorruption.get(i));
                        }
                        if (dirSpread == 1){
                            unloadedCorrupt(0, -100, unloadedCorruption.get(i));
                        }
                        if (dirSpread == 2){
                            unloadedCorrupt(100, -100, unloadedCorruption.get(i));
                        }
                        if (dirSpread == 3){
                            unloadedCorrupt(100, 0, unloadedCorruption.get(i));
                        }
                        if (dirSpread == 4){
                            unloadedCorrupt(100, 100, unloadedCorruption.get(i));
                        }
                        if (dirSpread == 5){
                            unloadedCorrupt(0, 100, unloadedCorruption.get(i));
                        }
                        if (dirSpread == 6){
                            unloadedCorrupt(-100, 100, unloadedCorruption.get(i));
                        }
                        if (dirSpread == 7){
                            unloadedCorrupt(-100, 0, unloadedCorruption.get(i));
                        }
                    }
                    if(unloadedCorruption.get(i).getX() > vehicle.getPlayerX() - 500 && unloadedCorruption.get(i).getX() < vehicle.getPlayerX() + 500 && unloadedCorruption.get(i).getY() > vehicle.getPlayerY() - 500 && unloadedCorruption.get(i).getY() < vehicle.getPlayerY() + 500){
                        if(getObjectsAt((int)unloadedCorruption.get(i).getX() + getWidth()/2, (int)unloadedCorruption.get(i).getY() + getHeight()/2, Corruption.class).size() == 0){
                            if(getObjectsAt((int)unloadedCorruption.get(i).getX() + getWidth()/2, (int)unloadedCorruption.get(i).getY() + getHeight()/2, Hole.class).size() > 0){
                                removeObject(getObjectsAt((int)unloadedCorruption.get(i).getX() + getWidth()/2, (int)unloadedCorruption.get(i).getY() + getHeight()/2, Hole.class).get(0));
                            }
                            addObject(new Corruption((int)unloadedCorruption.get(i).getX(), (int)unloadedCorruption.get(i).getY()), -100, -100);
                            unloadedCorruption.remove(i);
                        }
                    }
                }
            }
            if(gameLost){
                if(!fadeAdded){
                    fadeAdded = true;
                    bgm.stop();
                    addObject(transOther, getWidth()/2, getHeight()/2);
                }
                if(fadeCounter < 255){
                    fadeCounter++;
                    transOther.fade(fadeCounter);
                }else{
                    removeObject(transOther);
                    vehicle.stopAllSounds();
                    bgm.stop();
                    Lose_World lw = new Lose_World();
                    Greenfoot.setWorld(lw);
                }
            }else if(getObjects(Root.class).size() == 0){
                if(!fadeAdded){
                    fadeAdded = true;
                    vehicle.setCanMove(false);
                    addObject(transOther, getWidth()/2, getHeight()/2);
                }
                if(fadeCounter < 255){
                    fadeCounter++;
                    transOther.fade(fadeCounter);
                }else{
                    removeObject(transOther);
                    vehicle.stopAllSounds();
                    bgm.stop();
                    Win_World ww = new Win_World(2500 - getAllCorruption().size());
                    Greenfoot.setWorld(ww);
                }
            }
        }
    }
    
    public void setBGMVolume(int i){
        bgm.setVolume(i);
    }
    
    public void stopBGM(){
        bgm.pause();
    }
    
    public void setGameLost(boolean b){
        gameLost = b;
    }
    
    public ArrayList getAllCorruption(){
        return allCorruption;
    }
    
    public void unloadCorruption(int x, int y, Corruption c){
        unloadedCorruption.add(new Point (x, y));
        removeObject(c);
    }
    
    public void genOres(){
        for (int i = 0; i < 750; i++) {
            int x = Greenfoot.getRandomNumber(33) - 16;
            int y = Greenfoot.getRandomNumber(45)+1;
            if (takenTiles.indexOf(new Point(x*100,y*100)) == -1) {
                takenTiles.add(new Point(x*100,y*100));
                String materialToPlace = pickMaterialToPlace(y);
                if (materialToPlace == "coal") {
                    addObject(new Coal(x*100,y*100), -100, -100);
                } else if (materialToPlace == "sand") {
                    addObject(new Sand(x*100,y*100), -100, -100);
                } else if (materialToPlace == "copper") {
                    addObject(new Copper(x*100,y*100), -100, -100);
                } else if (materialToPlace == "iron") {
                    addObject(new Iron(x*100,y*100), -100, -100);
                } else if (materialToPlace == "gold") {
                    addObject(new Gold(x*100,y*100), -100, -100);
                } else if (materialToPlace == "diamond") {
                    addObject(new Diamond(x*100,y*100), -100, -100);
                } else if (materialToPlace == "uranium") {
                    addObject(new Uranium(x*100,y*100), -100, -100);
                } else if (materialToPlace == "titanium") {
                    addObject(new Titanium(x*100,y*100), -100, -100);
                } else if (materialToPlace == "platinum") {
                    addObject(new Platinum(x*100,y*100), -100, -100);
                }
            }
        }
    }
    
    public void regenerateWorld(){
        takenTiles.clear();
        vehicle.setCanMove(false);
        vehicle.setLocation(getWidth()/2, getHeight()/2);
        vehicle.setXandY(0, 0);
        bgm.playLoop();
        removeObjects(getObjects(Bg.class));
        removeObjects(getObjects(Hole.class));
        removeObjects(getObjects(Ore.class));
        for (int i = 0; i < 2; i++){
            for (int g = 0; g < 2; g++){
                addObject(new Bg(i*getWidth(), g*getHeight()), -1000, -1000);
            }
        }
        for (int i = 0; i < 3; i++){
            for (int g = 0; g < 3; g++){
                addObject(new Default_Hole((i-1)*100, (g-1)*100), getWidth()/2 - 100 + (i*100), getHeight()/2 - 100 + (g*100));
            }
        }
        genOres();
        //System.out.println("Ores: " + getObjects(Ore.class).size());
        //System.out.println("Titanium: " + getObjects(Titanium.class).size());
        //System.out.println("Diamond: " + getObjects(Diamond.class).size());
        //System.out.println("Platinum: " + getObjects(Platinum.class).size());
        vehicle.setCanMove(true);
    }
    
    public Vehicle getVehicle(){
        return vehicle;
    }
    
    public String pickMaterialToPlace(int y) {
        int x = Greenfoot.getRandomNumber(101);
        if (y < 7) {
            if (x <= 49) {
                return "coal";
            } else if (x <=79) {
                return "copper";
            } else if (x <= 83) {
                return "iron";
            } else if (x <= 87) {
                return "gold";
            } else if (x <= 93) {
                return "titanium";
            } else if (x <= 96) {
                return "diamond";
            } else if (x <= 99) {
                return "platinum";
            } else {
                return "uranium";
            }
        } else if (y < 15) {
            if (x <= 29) {
                return "coal";
            } else if (x <=59) {
                return "copper";
            } else if (x <= 75) {
                return "iron";
            } else if (x <= 81) {
                return "gold";
            } else if (x <= 87) {
                return "titanium";
            } else if (x <= 93) {
                return "diamond";
            } else if (x <= 99) {
                return "platinum";
            } else {
                return "uranium";
            }
        } else if (y < 30) {
            if (x <= 19) {
                return "coal";
            } else if (x <= 39) {
                return "copper";
            } else if (x <= 49) {
                return "iron";
            } else if (x <= 64) {
                return "gold";
            } else if (x <= 74) {
                return "titanium";
            } else if (x <= 84) {
                return "diamond";
            } else if (x <= 94) {
                return "platinum";
            } else {
                return "uranium";
            }
        } else if (y < 40) {
            if (x <= 19) {
                return "coal";
            } else if (x <= 25) {
                return "copper";
            } else if (x <= 31) {
                return "iron";
            } else if (x <= 41) {
                return "gold";
            } else if (x <= 52) {
                return "titanium";
            } else if (x <= 64) {
                return "diamond";
            } else if (x <= 79) {
                return "platinum";
            } else {
                return "uranium";
            }
        } else {
            if (x <= 9) {
                return "coal";
            } else if (x <= 14) {
                return "copper";
            } else if (x <= 19) {
                return "iron";
            } else if (x <= 29) {
                return "gold";
            } else if (x <= 49) {
                return "titanium";
            } else if (x <= 69) {
                return "diamond";
            } else if (x <= 89) {
                return "platinum";
            } else {
                return "uranium";
            }
        }
        
    }
    public String returnType() {
        return "myWorld";
    }
}
