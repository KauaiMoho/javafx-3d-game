import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Vehicle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vehicle extends Actor
{
    /**
     * Act - do whatever the Vehicle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    

    boolean dev_mode = false;

    boolean full_gear_mode = false;
    
    boolean canMove = true;
    
    int playerX = 0;
    int playerY = 0;
    int SPEED = 5;
    int GRAVITY = 1;
    int hitboxSize = 25;
 
    int breadthOfVision = 0;
    int drillType = 0;
    int fuelType = 0;
    
    int UP = 3;
    int RIGHT = 2;
    int DOWN = 1; 
    int LEFT = 0;
    int NONE = -1;
    
    int frame = 0;
    int frame2 = 0;
    int default_breaking_speed = 20;
    int breaking_speed = default_breaking_speed;
    int digging_dir = NONE;
    
    int initial_fuel = 100;
    int fuel = initial_fuel;
    
    int defaultFuelUsed = 3;
    
    //inital depth will change once we add an "above ground" area or somting like that, currently the spawnpoint is depth 0.
    int depth = 0;
    
    int coalC = 0;
    int copperC = 0;
    int goldC = 0;
    int ironC = 0;
    int sandC = 0;
    int uraniumC = 0;
    int diamondC = 0;
    int platinumC = 0;
    int titaniumC = 0;
    
    int cokeC = 0;
    int copperBarsC = 0;
    int goldBarsC = 0;
    int ironBarsC = 0;
    int diamondFacetedC = 0;
    int platinumBarsC = 0;
    int titaniumBarsC = 0;
    int uraniumEnrichedC = 0;
    
    
    int direction = RIGHT;
    
    boolean first = true;
    
    GreenfootSound breakStone = new GreenfootSound("break_stone.mp3");
    GreenfootSound breakCorruption0 = new GreenfootSound("corruption_break_1.mp3");
    GreenfootSound breakCorruption1 = new GreenfootSound("corruption_break_2.mp3");
    GreenfootSound breakCorruption2 = new GreenfootSound("corruption_break_3.mp3");
    GreenfootSound breakCorruption3 = new GreenfootSound("corruption_break_4.mp3");
    
    public Vehicle(){
        if(dev_mode){
            SPEED = 10;
        }
        if(full_gear_mode){
            drillType = 3;
            coalC = 99;
            copperC = 99;
            goldC = 99;
            ironC = 99;
            sandC = 99;
            uraniumC = 99;
            diamondC = 99;
            platinumC = 99;
            titaniumC = 99;
        }
        breakStone.setVolume(40);
        breakCorruption0.setVolume(60);
        breakCorruption1.setVolume(60);
        breakCorruption2.setVolume(60);
        breakCorruption3.setVolume(60);
        //initial_fuel = getInitialFuel(fuelType);
        setImage(drillType + "_3.png");
    }
    
    public void setCanMove(boolean b){
        canMove = b;
    }
    
    public void setXandY(int x, int y){
        playerX = x;
        playerY = y;
    }
    
    public void act()
    {
        if (canMove){
            if (Greenfoot.isKeyDown("D")){
                direction = RIGHT;
                if (digging_dir != RIGHT){
                    digging_dir = RIGHT;
                    frame2 = 0;
                    removeBreaking();
                }
                playerX += SPEED;
                int range = 40;
                boolean nearCenter = playerY - round2(playerY) <= range && playerY - round2(playerY) >= -range;
                if (!dev_mode && (getOneObjectAtOffset(52, -hitboxSize, Hole.class) == null || getOneObjectAtOffset(52, hitboxSize, Hole.class) == null)){
                    playerX -= SPEED;
                    if (nearCenter){
                        if (frame2 == 0 && canMine(100, 0)){
                            removeBreaking();
                            breaking_speed = setDiggingSpeed(100, 0);
                            getWorld().addObject(new Breaking(round2(playerX) + 100, round2(playerY)), -100, -100);
                        }
                        frame2++;
                        if (frame2 >= breaking_speed && canMine(100, 0)){
                            removeTile(100, 0);
                            if(!full_gear_mode){
                                fuel-=getFuelUsed(100,0);
                            }
                            breaking_speed = default_breaking_speed;
                            frame2 = 0;
                        }    
                    }
                    
                }
            } else if (Greenfoot.isKeyDown("S")){
                direction = DOWN;
                if (digging_dir != DOWN){
                    digging_dir = DOWN;
                    frame2 = 0;
                    removeBreaking();
                }
                playerY += SPEED;
                int range = 40;
                boolean nearCenter = playerX - round2(playerX) <= range && playerX - round2(playerX) >= -range;
                if (!dev_mode && (getOneObjectAtOffset(-hitboxSize, 52, Hole.class) == null || getOneObjectAtOffset(hitboxSize, 52, Hole.class) == null)){
                    playerY -= SPEED;
                    if (nearCenter){
                        if (frame2 == 0 && canMine(0, 100)){
                            removeBreaking();
                            if (getOneObjectAtOffset(0, 100, Hole.class) == null){
                                breaking_speed = setDiggingSpeed(0, 100);
                                getWorld().addObject(new Breaking(round2(playerX), round2(playerY) + 100), -100, -100);
                            }
                        }
                        frame2++;
                        if (frame2 >= breaking_speed && canMine(0, 100)){
                            removeTile(0, 100);
                            if(!full_gear_mode){
                                fuel-=getFuelUsed(0,100);
                            }
                            breaking_speed = default_breaking_speed;
                            frame2 = 0;
                        }
                    }
                }
            } else if (Greenfoot.isKeyDown("A")){
                direction = LEFT;
                if (digging_dir != LEFT){
                    digging_dir = LEFT;
                    frame2 = 0;
                    removeBreaking();
                }
                playerX -= SPEED;
                int range = 40;
                boolean nearCenter = playerY - round2(playerY) <= range && playerY - round2(playerY) >= -range;
                if (!dev_mode && (getOneObjectAtOffset(-52, -hitboxSize, Hole.class) == null || getOneObjectAtOffset(-52, hitboxSize, Hole.class) == null)){
                    playerX += SPEED;
                    if (nearCenter){
                        if (frame2 == 0 && canMine(-100, 0)){
                            removeBreaking();
                            breaking_speed = setDiggingSpeed(-100, 0);
                            getWorld().addObject(new Breaking(round2(playerX) - 100, round2(playerY)), -100, -100);
                        }
                        frame2++;
                        if (frame2 >= breaking_speed && canMine(-100, 0)){
                            removeTile(-100, 0);
                            if(!full_gear_mode){
                                fuel-=getFuelUsed(-100,0);
                            }
                            breaking_speed = default_breaking_speed;
                            frame2 = 0;
                        }    
                    }
                    
                }
            } else if (Greenfoot.isKeyDown("W")){
                direction = UP;
                if (digging_dir != UP){
                    digging_dir = UP;
                    frame2 = 0;
                    removeBreaking();
                }
                playerY -= SPEED;
                int range = 40;
                boolean nearCenter = playerX - round2(playerX) <= range && playerX - round2(playerX) >= -range;
                if (!dev_mode && (getOneObjectAtOffset(-hitboxSize, -52, Hole.class) == null || getOneObjectAtOffset(hitboxSize, -52, Hole.class) == null)){
                    playerY += SPEED;
                    if (nearCenter){
                        if (frame2 == 0 && canMine(0, -100)){
                            removeBreaking();
                            if (getOneObjectAtOffset(0, -100, Hole.class) == null){
                                breaking_speed = setDiggingSpeed(0, -100);
                                getWorld().addObject(new Breaking(round2(playerX), round2(playerY) - 100), -100, -100);
                            }
                        }
                        frame2++;
                        if (frame2 >= breaking_speed && canMine(0, -100)){
                            removeTile(0, -100);
                            if(!full_gear_mode){
                                fuel-=getFuelUsed(0,-100);
                            }
                            breaking_speed = default_breaking_speed;
                            frame2 = 0;
                        }
                    }
                }
            } else {
                frame2 = 0;
                removeBreaking();
                digging_dir = NONE;
            }
        }
        
        depth = -playerY;
        setAnimation();
        setDirection();
        setOutline();
        frame+=5;
    }
    
    public int getFuelUsed(int x, int y){
        if(getOneObjectAtOffset(x, y, Root.class) != null){
            return ((Root)(getOneObjectAtOffset(x, y, Root.class))).getFuelUsed();
        }else if(getOneObjectAtOffset(x, y, Corruption.class) != null){
            return ((Corruption)(getOneObjectAtOffset(x, y, Corruption.class))).getFuelUsed();
        }else if(getOneObjectAtOffset(x, y, Ore.class) != null){
            return ((Ore)(getOneObjectAtOffset(x, y, Ore.class))).getFuelUsed();
        }else{
            return defaultFuelUsed;
        }
    }
    
    public boolean canMine(int x, int y){
        if(fuel > 0 && getOneObjectAtOffset(x, y, Bedrock.class) == null){
            if(getOneObjectAtOffset(x, y, Root.class) != null){
                return ((Root)(getOneObjectAtOffset(x, y, Root.class))).getDrillType() <= drillType;
            }else if(getOneObjectAtOffset(x, y, Corruption.class) != null){
                return ((Corruption)(getOneObjectAtOffset(x, y, Corruption.class))).getDrillType() <= drillType;
            }else if(getOneObjectAtOffset(x, y, Ore.class) != null){
                return ((Ore)(getOneObjectAtOffset(x, y, Ore.class))).getDrillType() <= drillType;
            }
            return true;
        }
        return false;
    }
    
    public int setDiggingSpeed(int x, int y){
        if(getOneObjectAtOffset(x, y, Root.class) != null){
            return ((Root)(getOneObjectAtOffset(x, y, Root.class))).getDigSpeed();
        }else if(getOneObjectAtOffset(x, y, Corruption.class) != null){
            return ((Corruption)(getOneObjectAtOffset(x, y, Corruption.class))).getDigSpeed();
        }else if(getOneObjectAtOffset(x, y, Ore.class) != null){
            return ((Ore)(getOneObjectAtOffset(x, y, Ore.class))).getDigSpeed() - (drillType*20/3);
        }else{
            return default_breaking_speed - (drillType*10/3);
        }
    }
    
    public void stopAllSounds(){
        breakStone.stop();
        breakCorruption0.stop();
        breakCorruption1.stop();
        breakCorruption2.stop();
        breakCorruption3.stop();
    }
    
    public void playRandomCorruptBreak(){
        int sfxVal = Greenfoot.getRandomNumber(4);
        if(sfxVal == 0){
            breakCorruption0.play();
        }else if(sfxVal == 1){
            breakCorruption1.play();
        }else if(sfxVal == 2){
            breakCorruption2.play();
        }else{
            breakCorruption3.play();
        }
    }
    
    public void removeTile(int x, int y){
        stopAllSounds();
        if(getOneObjectAtOffset(x, y, Root.class) != null){
            ((Root)(getOneObjectAtOffset(x, y,Root.class))).stopSounds();
            getWorld().removeObject(getOneObjectAtOffset(x, y,Root.class));
            playRandomCorruptBreak();
        }else if(getOneObjectAtOffset(x, y, Corruption.class) != null){
            ((Corruption)getOneObjectAtOffset(x, y, Corruption.class)).removeCorruption();
            playRandomCorruptBreak();
        }else if(getOneObjectAtOffset(x, y, Ore.class) != null){
            changeOreCount(((Ore)getOneObjectAtOffset(x, y,Ore.class)).getType(), getFortuneValue(((Ore)getOneObjectAtOffset(x, y,Ore.class)).getType()));
            getWorld().removeObject(getOneObjectAtOffset(x, y,Ore.class));
            getWorld().addObject(new Hole(round2(playerX) + x, round2(playerY) + y) ,-100, -100);
            breakStone.play();
        }else{
            getWorld().addObject(new Hole(round2(playerX) + x, round2(playerY) + y) ,-100, -100);
            breakStone.play();
        }
    }
    
    public int getFortuneValue(String t) {
        if(t == "coal"){
            if(drillType == 0){
                return 1;
            }else if(drillType == 1){
                if(Greenfoot.getRandomNumber(100) <  20){
                    return 2;
                }else{
                    return 1;
                }
            }else if(drillType == 2){
                return 2;
            }else{
                if(Greenfoot.getRandomNumber(100) <  50){
                    return 3;
                }else{
                    return 2;
                }
            }
        }
        return 1;
    }
    
    public void changeOreCount(String type, int n){
        if(type == "coal"){
            coalC+=n;
        }else if(type == "copper"){
            copperC+=n;
        }else if(type == "iron"){
            ironC+=n;
        }else if(type == "gold"){
            goldC+=n;
        }else if(type == "uranium"){
            uraniumC+=n;
        }else if(type == "diamond"){
            diamondC+=n;
        }else if(type == "platinum"){
            platinumC+=n;
        }else if(type == "titanium"){
            titaniumC+=n;
        }else {
            sandC+=n;
        }
    }
    
    public void changeRefinedOreCount (String type, int n) {
        if (type == "coke") {
            cokeC += n;
        } else if(type == "copper"){
            copperBarsC+=n;
        } else if(type == "iron"){
            ironBarsC+=n;
        }else if(type == "platinum"){
            platinumBarsC+=n;
        }else if(type == "gold"){
            goldBarsC+=n;
        }else if(type == "uranium"){
            uraniumEnrichedC+=n;
        }else if(type == "diamond"){
            diamondFacetedC+=n;
        }else if(type == "platinum"){
            platinumBarsC+=n;
        }else if(type == "titanium"){
            titaniumBarsC+=n;
        }
    }
    
    
    
    public int round(int n){
        return (int)(Math.floor(n/100)*100);
    }
    
    public int round2(int n){
        if (n < 0){
            if (n % 100 < -50){
                return n - (100 + (n % 100));
            } else {
                return n - (n % 100);
            }
        } else {
            if (n % 100 > 50){
                return n + (100 - (n % 100));
            } else {
                return n - (n % 100);
            }    
        }
        
    }
    
    public void setAnimation(){
        if (frame == 120){
            setImage(drillType + "_7.png");
            frame = -30;
        } else if (frame == 105){
            setImage(drillType + "_6.png");
        } else if (frame == 90){
            setImage(drillType + "_5.png");
        } else if (frame == 75){
            setImage(drillType + "_4.png");
        } else if (frame == 60){
            setImage(drillType + "_3.png");
        } else if (frame == 45){
            setImage(drillType + "_2.png");
        } else if (frame == 30){
            setImage(drillType + "_1.png");
        } else if (frame == 15){
            setImage(drillType + "_0.png");
        }
    }
    
    
    //ADD SETTERS FOR SHOP HERE
    
    public void setDrillType(int d){
        drillType = d;
    }
    
    public void setFuelType(int f){
        fuelType = f;
        if (fuelType == 0){
            initial_fuel = 100;
            fuel = 100;
        } else if (fuelType == 1){
            initial_fuel = 250;
            fuel = 250;
        } else if (fuelType == 2){
            initial_fuel = 500;
            fuel = 500;
        } else if (fuelType == 3){
            initial_fuel = 1000;
            fuel = 1000;
        } else {
            initial_fuel = 0;
            fuel = 0;
        }
    }
    
    public int getCoalC(){
        return coalC;
    }
    public void setCoalCTo0(){
        coalC = 0;
    }
    
    public int getCopperC(){
        return copperC;
    }
    public void setCopperCTo0(){
        copperC = 0;
    }
    
    public int getIronC(){
        return ironC;
    }
    public void setIronCTo0(){
        ironC = 0;
    }
    
    
    public int getGoldC(){
        return goldC;
    }
    public void setGoldCTo0(){
        goldC = 0;
    }
    
    public int getUraniumC(){
        return uraniumC;
    }
    public void setUraniumCTo0(){
        uraniumC = 0;
    }
    
    
    public int getDiamondC(){
        return diamondC;
    }
    public void setDiamondCTo0(){
        diamondC = 0;
    }
    
    public int getPlatinumC(){
        return platinumC;
    }
    public void setPlatinumCTo0(){
        platinumC = 0;
    }
    
    public int getTitaniumC(){
        return titaniumC;
    }
    public void setTitaniumCTo0(){
        titaniumC = 0;
    }
    
    
    public int getTitaniumBarsC(){
        return titaniumBarsC;
    }
    public int getCopperBarsC(){
        return copperBarsC;
    }
    public int getIronBarsC(){
        return ironBarsC;
    }
    public int getGoldBarsC(){
        return goldBarsC;
    }
    public int getPlatinumBarsC(){
        return platinumBarsC;
    }
    public int getDiamondFacetedC(){
        return diamondFacetedC;
    }
    public int getCokeC(){
        return cokeC;
    }
    public int getEnrichedUraniumC(){
        return uraniumEnrichedC;
    }
    
    public int getFuel(){
        return fuel;
    }
    
    public void increaseFuel(int fAmount){
        fuel += fAmount;
    }
    
    public int getFuelSize(){
        return initial_fuel;
    }
    
    public void setOutline(){
        if (getWorld().getObjects(Block_Outline.class).size() > 0){
            getWorld().removeObject(getWorld().getObjects(Block_Outline.class).get(0));
        }
        if (direction == LEFT){
            int offsetX = (round2(playerX) - 100) - playerX;
            int offsetY = (round2(playerY)) - playerY;
            if (getOneObjectAtOffset(offsetX, offsetY, Hole.class) == null && canMine(offsetX, offsetY)){
                getWorld().addObject(new Block_Outline(true), offsetX + getWorld().getWidth()/2, offsetY + getWorld().getHeight()/2);
            } else {
                getWorld().addObject(new Block_Outline(false), offsetX + getWorld().getWidth()/2, offsetY + getWorld().getHeight()/2);
            }
        } else if (direction == DOWN){
            int offsetX = (round2(playerX)) - playerX;
            int offsetY = (round2(playerY) + 100) - playerY;
            if (getOneObjectAtOffset(offsetX, offsetY, Hole.class) == null && canMine(offsetX, offsetY)){
                getWorld().addObject(new Block_Outline(true), offsetX + getWorld().getWidth()/2, offsetY + getWorld().getHeight()/2);
            } else {
                getWorld().addObject(new Block_Outline(false), offsetX + getWorld().getWidth()/2, offsetY + getWorld().getHeight()/2);
            }
        } else if (direction == RIGHT){
            int offsetX = (round2(playerX) + 100) - playerX;
            int offsetY = (round2(playerY)) - playerY;
            if (getOneObjectAtOffset(offsetX, offsetY, Hole.class) == null && canMine(offsetX, offsetY)){
                getWorld().addObject(new Block_Outline(true), offsetX + getWorld().getWidth()/2, offsetY + getWorld().getHeight()/2);
            } else {
                getWorld().addObject(new Block_Outline(false), offsetX + getWorld().getWidth()/2, offsetY + getWorld().getHeight()/2);
            }
        } else if (direction == UP){
            int offsetX = (round2(playerX)) - playerX;
            int offsetY = (round2(playerY) - 100) - playerY;
            if (getOneObjectAtOffset(offsetX, offsetY, Hole.class) == null && canMine(offsetX, offsetY)){
                getWorld().addObject(new Block_Outline(true), offsetX + getWorld().getWidth()/2, offsetY + getWorld().getHeight()/2);
            } else {
                getWorld().addObject(new Block_Outline(false), offsetX + getWorld().getWidth()/2, offsetY + getWorld().getHeight()/2);
            }
        }
    }
    
    public void setDirection(){
        if (direction == LEFT){
            setRotation(0);
            GreenfootImage img;
            if (frame >= 15){
                img = new GreenfootImage(drillType + "_" + (int)(frame / 15) + ".png");
            } else {
                img = new GreenfootImage(drillType + "_7.png");
            }
            img.mirrorHorizontally();
            setImage(img);
        }
        if (direction == RIGHT){
            setRotation(0);
            GreenfootImage img;
            if (frame >= 15){
                img = new GreenfootImage(drillType + "_" + (int)(frame / 15) + ".png");
            } else {
                img = new GreenfootImage(drillType + "_7.png");
            }
            setImage(img);
        }
        if (direction == DOWN){
            setRotation(90);
            GreenfootImage img;
            if (frame >= 15){
                img = new GreenfootImage(drillType + "_" + (int)(frame / 15) + ".png");
            } else {
                img = new GreenfootImage(drillType + "_7.png");
            }
            setImage(img);
        }
        if (direction == UP){
            setRotation(-90);
            GreenfootImage img;
            if (frame >= 15){
                img = new GreenfootImage(drillType + "_" + (int)(frame / 15) + ".png");
            } else {
                img = new GreenfootImage(drillType + "_7.png");
            }
            img.mirrorVertically();
            setImage(img);
        }
    }
    
    public void removeBreaking(){
        if (getWorld().getObjects(Breaking.class).size() > 0){
            getWorld().removeObject(getWorld().getObjects(Breaking.class).get(0));
        }
    }
    
    
    public int getPlayerX(){
        return playerX;
    }
    
    public int getPlayerY(){
        return playerY;
    }
    
    public int getDrillType(){
        return drillType;
    }
    
    public int getFuelType(){
        return fuelType;
    }
    
    public int getDiggingCooldown(){
        return breaking_speed;
    }
    
    public int getDepth(){
        return depth;
    }
    
    public boolean getDevMode(){
        return dev_mode;
    }
    
    public int getBreadthOfVision(){
        return breadthOfVision;
    }
    
    public void setBreadthOfVision(int v){
        breadthOfVision = v;
    }
}
