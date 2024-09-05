package lidar3d;

import engine.Actor_3d;
import engine.World_3d;
import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import javafx.util.Duration;
import physics.Collider_3d;
import physics.CollisionTile_3d;
import physics.CubeCollider_3d;
import physics.MeshCollider_3d;
import physics.Physics_Engine;
import physics.Triangle_3d;
import quicksound.Sound;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;

public class lidar3d_World extends World_3d{
    private long previousTime = 0;
    
    private double scale;
    private double moveDelta;
    private double sensitivity = 7;
    
    double collTileWIncrement = 0;
    double collTileLIncrement = 0;
    int subDivisions = 40;
    
    //SET TO TRUE TO TRY OUT ALTERNATE DOT DRAWING
    //MAKE SURE TO GO INTO MAP_ACTOR and COMMENT OUT THIS
    //
    //	@Override
	//	public DrawMode getDrawMode() {
    //		return DrawMode.LINE;
    //	}
    //
    //
    boolean useTexturesForDots = false;
    int firstDotIdx = -1;
    
    boolean ignoreMouseMove = false;
    Robot mouseMover;
    double mouseX = 0;
    double mouseY = 0;
    boolean godmode;
    UI ui;
    
    boolean shooting = false;
    int maxLidarPoints = 5000;
    
    ArrayList<Collider_3d> playerBlacklistedColliders = new ArrayList<Collider_3d>();
    ArrayList<Collider_3d> scanBlacklistedColliders = new ArrayList<Collider_3d>();
    ArrayList<Actor_3d> rayCastActs = new ArrayList<Actor_3d>();
    ArrayList<Page> pages = new ArrayList<Page>();
    
    double range = 15;
    Map_Actor map;
    
    Point3D rayPoint;
    
    double worldWidth;
    double worldHeight;
    
    //Sphere[] spheres;
    
    Color rayColor;
    
    Enemy enemy;
    boolean enemyIsChasing = false;
    boolean enemySpawnedIn = false;
    boolean enemyCanSpawnInFront = false;
    boolean isCaught = false;
    ArrayList<Point2D> enemySpawnPoints = new ArrayList<Point2D>();
    ArrayList<Integer> enemySpawnPointDirections = new ArrayList<Integer>();
    
    Door door;
    Door door2;
    
    int enemyPointCount = 0;
    
    MediaPlayer beamPlayer;
    boolean dead = false;
    
    Sound scanSound = new Sound("lidar3d/sounds/toolScan.wav");
    Sound deathSound = new Sound("lidar3d/sounds/death.wav");

    
    private TitleScreen titleScreen;
    private Scene scene;
    public lidar3d_World(Player_3d cam, Scene scene, double s, boolean gmode) {
        super(cam);
        this.scene = scene;
        worldWidth = scene.getWidth();
        worldHeight = scene.getHeight();
        scale = s;
        godmode = gmode;
        moveDelta = (godmode) ? (scale)/20 : (scale)/30;
        
        try {
            Media longMusic = new Media(getClass().getClassLoader().getResource("lidar3d/sounds/toolBeam.wav").toURI().toString());
            beamPlayer =  new MediaPlayer(longMusic);
            beamPlayer.setCycleCount(Integer.MAX_VALUE);
            beamPlayer.setStopTime(Duration.ZERO);
            beamPlayer.setStartTime(Duration.ZERO);
        } catch(Exception e) {
        	System.out.println(e);
        }
    }
    
    public ArrayList<Actor_3d> getRayActors(){
    	return rayCastActs;
    }
    
    private lidar3d_World getThis() {
    	return this;
    }
	@Override
    public void onDimensionsInitialized() {
        
        try {
            mouseMover = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        map = new Map_Actor("building2.txt", scale);
        map.importModel(this, true);
        if (godmode || useTexturesForDots) {
        	addInWorld(map);
        }
        
        enemy = new Enemy(scale, this, "man2.txt");
        enemy.importModel(this, true);
        playerBlacklistedColliders.addAll(enemy.getColls());
        addInWorld(enemy);
        if (godmode) {
        	enemy.setMat(null);
        } else {
        	enemy.setMat(new PhongMaterial(Color.TRANSPARENT));
        }
        enemySpawnPoints.add(new Point2D(-660, -135));
        enemySpawnPointDirections.add(270);
        enemySpawnPoints.add(new Point2D(140, -440));
        enemySpawnPointDirections.add(90);
        enemySpawnPoints.add(new Point2D(-140, -290));
        enemySpawnPointDirections.add(270);
        enemySpawnPoints.add(new Point2D(-700, 300));
        enemySpawnPointDirections.add(270);
        enemySpawnPoints.add(new Point2D(100, 200));
        enemySpawnPointDirections.add(90);
        enemySpawnPoints.add(new Point2D(-192, -60));
        enemySpawnPointDirections.add(90);
        int spawnNum = (int)(Math.random() * enemySpawnPoints.size());
    	enemy.setX(enemySpawnPoints.get(spawnNum).getX());
    	enemy.setZ(enemySpawnPoints.get(spawnNum).getY());
    	enemy.rotateY(enemySpawnPointDirections.get(spawnNum));
        
        door = new Door(this, new Point3D(-760, -60, -362.5));
        playerBlacklistedColliders.addAll(door.getColls());

        door2 = new Door(this, new Point3D(-760, -60, 390));
        playerBlacklistedColliders.addAll(door2.getColls());
        if (godmode || useTexturesForDots) {
             addInWorld(door);
             addInWorld(door2);
        }
        
        ui = new UI(this);
        addOutOfWorld(ui);
        ui.addedToWorld();
        ui.toFront();
        
        if(godmode) {
        	ui.addCoordsLabel(player.getPosVector());
        }

        player.setY(-scale*(5/3));
        
        int screenWidth = (int)scene.getWidth();
        int screenHeight = (int)scene.getHeight();
        mouseMover.mouseMove(screenWidth/2, screenHeight/2);
        
        this.setOnMouseMoved(e -> {
            if (!ui.isKeypadShowing()){
				mouseX = e.getSceneX();
				mouseY = e.getSceneY();
				player.rotateYBy((e.getScreenX() - scene.getWidth() / 2.0) / sensitivity);
				player.rotateXBy(-(e.getScreenY() - scene.getHeight() / 2.0) / sensitivity);
				mouseMover.mouseMove(screenWidth/2, screenHeight/2);
			}
            //ui.updateTransforms(player);
        });
        
		AnimationTimer timerBeam = new AnimationTimer() {
			long previousTime = 0;
			@Override
			public void handle(long now) {
				if(now-previousTime >= (long)(1000)) {
					previousTime = now;
                    for (int i = 0; i < range/5; i++){
                        Raycast r = new Raycast(getThis(), player.getRotVector().add(new Point3D(-2*player.getRotateX(), 0, 0)), player.getPosVector().add(new Point3D(Math.random()*range - range/2, Math.random()*range - range/2, Math.random()*range - range/2)));          
                        Point3D intersect = r.propogate_TEMP(useTexturesForDots);
                        if(intersect != null) {
                        	addShape(intersect, r.getHitRayColor());
                        	ui.drawLine(find3Dto2D(intersect));
                        	rayColor = r.getHitRayColor();
                        	performRayColorActions();
                        }
                        if(childrenLength()-firstDotIdx > maxLidarPoints) {
                        	if(((PhongMaterial)((Sphere)remove(firstDotIdx)).getMaterial()).getDiffuseColor().equals(enemy.getRayColor())){
                        		enemyPointCount--;
                        	}
                        }
                    }
				}
			}
		};
		double fullVScan = 90;
		
		AnimationTimer timerScan = new AnimationTimer() {
			long previousTime = 0;
			double scanA = -fullVScan/2;
			@Override
			public void handle(long now) {
				if(now-previousTime >= (long)(10)) {
					previousTime = now;
                    double range = 120;
                    for (int i = 0; i < 25; i++){
                    	double xAngle = Math.random()*range - range/2;
                        Raycast r = new Raycast(getThis(), player.getRotVector().add(new Point3D(-2*player.getRotateX() + scanA, xAngle, 0)), player.getPosVector().add(new Point3D(0, 0,0)));          
                        Point3D intersect = r.propogate_TEMP(useTexturesForDots);
                        if(intersect != null) {
                        	addShape(intersect, r.getHitRayColor());
                        	ui.drawLine(new Point2D((xAngle+range/2)*(worldWidth/range),(scanA + fullVScan/2)*(worldHeight/fullVScan)));
                        	rayColor = r.getHitRayColor();
                        	performRayColorActions();
                        }
                        if(childrenLength()-firstDotIdx > maxLidarPoints) {
                        	if(((PhongMaterial)((Sphere)remove(firstDotIdx)).getMaterial()).getDiffuseColor().equals(enemy.getRayColor())){
                        		enemyPointCount--;
                        	}
                        }
                    }
                    scanA++;
				}
				if(scanA >= fullVScan/2) {
					scanA = -fullVScan/2;
					shooting = false;
					if(ui.gunMode != 0) {
            			ui.changeGunTexture(0);
            		}
					scanSound.stop();
					this.stop();
				}
			}
		};
		
		this.setOnScroll(new EventHandler<ScrollEvent>() {

			@Override
			public void handle(ScrollEvent event) {
				if(range+event.getDeltaY()/2 > 4 && range+event.getDeltaY()/2 < 35) {
					range+=event.getDeltaY()/2;
				}
			}
	    });
		
        
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) {
            	if(!dead) {
            		if(!shooting) {
                		shooting = true;
                		if(ui.gunMode != 1) {
                			ui.changeGunTexture(1);
                		}
                		beamPlayer.seek(Duration.ZERO);
                		beamPlayer.play();
                		timerBeam.start();
                	}else {
                		shooting = false;
                		if(ui.gunMode != 0) {
                			ui.changeGunTexture(0);
                		}
                		beamPlayer.stop();
                		timerBeam.stop();
                	}
            	}
            	
            	
            } 
         });
        
        
        this.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				if(e.getCode().equals(KeyCode.R) && !shooting && !dead) {
					shooting = true;
					if(ui.gunMode != 1) {
            			ui.changeGunTexture(1);
            		}
					scanSound.play();
					timerScan.start();
				}else if(e.getCode().equals(KeyCode.F) && godmode && !dead) {
					Raycast r = new Raycast(getThis(), player.getRotVector().add(new Point3D(-2*player.getRotateX(), 0, 0)), player.getPosVector().add(new Point3D(Math.random()*range - range/2, Math.random()*range - range/2, Math.random()*range - range/2)));          
			        Point3D intersect = r.propogate_TEMP(useTexturesForDots);
			        if(intersect != null) {
			        	addShape(intersect, r.getHitRayColor());
			        	ui.drawLine(find3Dto2D(intersect));
			        	rayColor = r.getHitRayColor();
			        }
				}
			}
        	
        });

        setupCollisionGrid();
        
        if(!godmode) {
			addInWorld(new AmbientLight());
        }
		
        setupPages();
        
		start();
    }
	
	private void setupPages() {
		
		double[] coords = new double[] {
				148, -347,
				-421, 193,
				-157, -67,
				148, 400,
				-681, -206
				
		};
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(1);nums.add(2);nums.add(3);nums.add(4);nums.add(5);
		
		for(int i = 0; i < 5; i++) {
			
			int idx = (int) (Math.random()*nums.size());
			Page p = new Page(nums.remove(idx), scale/2, ui);
			p.importModel(this, true);
			p.setX(coords[i*2]); //hardcoded
			p.setY(-60);
			p.setZ(coords[i*2+1]);
			
			playerBlacklistedColliders.addAll(p.getColls());
			if (godmode || useTexturesForDots) {
	        	addInWorld(p);
	        }
			pages.add(p);
		}
	}

	private void performRayColorActions() {
		//changeGunTexture
		if(rayColor.equals(enemy.getRayColor())) {
    		enemyPointCount++;
    		if(ui.gunMode != 2) {
    			ui.changeGunTexture(2);
    		}
		}
	}
    
	public void addShape(Point3D pt, Color c) {
   	     Sphere s = new Sphere(0.5);
         s.setTranslateX(pt.getX());
         s.setTranslateY(pt.getY());
         s.setTranslateZ(pt.getZ());
         s.setMaterial(new PhongMaterial(c));
         addInWorld(s);
//    	Box b = new Box(0.5,0.5,0.5) ;
//    	b.setTranslateX(pt.getX());
//    	b.setTranslateY(pt.getY());
//        b.setTranslateZ(pt.getZ());
//        b.setMaterial(new PhongMaterial(c));
//        addInWorld(b);
        if(firstDotIdx == -1) {
        	firstDotIdx = childrenLength()-1;
        }
    }
	
	public void addTextureSpot(Point2D uv, Shape3D mesh) { 
		PhongMaterial mat = ((PhongMaterial)(mesh.getMaterial())); 
		Image image = mat.getDiffuseMap(); PixelReader pixelReader = image.getPixelReader(); 
		int width = (int) image.getWidth(); int height = (int) image.getHeight();
		WritableImage writableImage = new WritableImage(pixelReader, width, height); 
		PixelWriter pixelWriter = writableImage.getPixelWriter(); 
		pixelWriter.setColor((int)(uv.getX()*image.getWidth()), (int)(uv.getY()*image.getHeight()), Color.CRIMSON); 
		mat.setDiffuseMap(writableImage); 
	}
    
	private void setupCollisionGrid() {
		Point2D[] outerPoints = {
				new Point2D(Double.MAX_VALUE, Double.MAX_VALUE), //min x,z
				new Point2D(Double.MIN_VALUE, Double.MIN_VALUE) //max x,z
		};
		for(int i = 0; i < worldColliders.size(); i++) {
			Point3D[] pts = worldColliders.get(i).getPoints();
			for(int j = 0; j< pts.length; j++) {
				outerPoints[0] = new Point2D(Math.min(outerPoints[0].getX(), pts[j].getX()), Math.min(outerPoints[0].getY(), pts[j].getZ()));
				outerPoints[1] = new Point2D(Math.max(outerPoints[1].getX(), pts[j].getX()), Math.max(outerPoints[1].getY(), pts[j].getZ()));
			}
		}
		collTileWIncrement = (outerPoints[1].getX()-outerPoints[0].getX())/subDivisions;
		collTileLIncrement = (outerPoints[1].getY()-outerPoints[0].getY())/subDivisions;
		collTiles = new CollisionTile_3d[subDivisions][subDivisions];
		
		double x = outerPoints[0].getX();
		double y = outerPoints[0].getY();
		for(int i = 0; i < subDivisions; i++) {
			x+=collTileWIncrement;
			for(int j = 0; j < subDivisions; j++) {
				y+=collTileLIncrement;
				Point2D[] pts = {
						new Point2D(x-collTileWIncrement, y-collTileLIncrement),
						new Point2D(x, y)
					};
				Box b = new Box(collTileWIncrement, 10000, collTileLIncrement);
				b.setTranslateX(x-collTileWIncrement/2);
				b.setTranslateY(0);
				b.setTranslateZ(y-collTileLIncrement/2);
				CollisionTile_3d c = new CollisionTile_3d(pts, b);
				CubeCollider_3d cC = new CubeCollider_3d(b);
				
				for(int k = 0; k < worldColliders.size(); k++) {
					Collider_3d wColl = worldColliders.get(k);
					Point3D pC = Physics_Engine.Gilbert_Johnson_Keerthi_Collision(cC, wColl, false);
					if(pC != null && !c.colls.contains(wColl)) {
						c.colls.add(wColl);
					}
				}
				
				collTiles[i][j] = c;
			}
			y = outerPoints[0].getY();
		}
	}
	
	public int[] getTileCoords(Point3D p) {
		double minX = collTiles[0][0].getMinPoint().getX();
		double minY = collTiles[0][0].getMinPoint().getY();
		
		int x = (int)((p.getX()-minX)/collTileWIncrement);
    	int y = (int)((p.getZ()-minY)/collTileLIncrement);
    	return new int[] {x, y};
    }
    
    public CollisionTile_3d getTile(Point3D p) {
    	int[] c = getTileCoords(p);
     	return collTiles[c[0]][c[1]];
    }
    
    public CollisionTile_3d getTileAt(int i, int j) {
     	return collTiles[i][j];
    }
    
    public boolean inCollBounds(int i, int j) {
    	return i >= 0 && j >= 0 && i < collTiles.length && j < collTiles[0].length;
    }

    public void setTitleScreen(TitleScreen title){
        titleScreen = title;
    }
    
    @Override
    public void act(long now){
        Player_3d player = getCamera();
        if(now-previousTime >= (long)(10)) {
            for(int i = 0; i < worldColliders.size(); i++) {
				if(!godmode && !playerBlacklistedColliders.contains(worldColliders.get(i))) {
            		Point3D p = Physics_Engine.Gilbert_Johnson_Keerthi_Collision(player.coll, worldColliders.get(i), true);
                    if(p != null) {
                        player.moveAll(p);
                    }
            	}
            }
            for(int i = 0; i < pages.size(); i++) {
            	Page p = pages.get(i);
            	Point3D intersect = Physics_Engine.Gilbert_Johnson_Keerthi_Collision(p.getColls().get(0), player.coll, false);
            	if(intersect != null && !p.getShowing()) {
            		p.setShowing(true);
            	}else if(intersect == null && p.getShowing()){
            		p.setShowing(false);
            	}
            }
            Point3D doorC = Physics_Engine.Gilbert_Johnson_Keerthi_Collision(door.getColls().get(0), player.coll, false);
            Point3D door2C = Physics_Engine.Gilbert_Johnson_Keerthi_Collision(door2.getColls().get(0), player.coll, false);
            if((doorC != null || door2C != null) && !ui.isKeypadShowing()) {
                scene.setCursor(Cursor.DEFAULT);
                ui.showKeypad();
            }
			//  else if (ui.isKeypadShowing()){
			// 	scene.setCursor((Cursor.NONE));
			// 	ui.removeKeypad();
			// }
        }
        Rotate yRot = (Rotate)(player.getTransforms().get(1));
        double xDelta = Math.sin(-Math.toRadians(yRot.getAngle()))*moveDelta;
        double zDelta = Math.cos(-Math.toRadians(yRot.getAngle()))*moveDelta;
        
        double xhDelta = Math.cos(Math.toRadians(yRot.getAngle()))*moveDelta;
        double zhDelta = Math.sin(Math.toRadians(yRot.getAngle()))*moveDelta;
        
        
        if(isKeyPressed(KeyCode.A)) {
			player.setX(player.getTranslateX() - xhDelta);
			player.setZ(player.getTranslateZ() + zhDelta);
			if(godmode) {
	        	ui.updateCoords(player.getPosVector());
	        }
        }
        if(isKeyPressed(KeyCode.D)) {
			player.setX(player.getTranslateX() + xhDelta);
			player.setZ(player.getTranslateZ() - zhDelta);
			if(godmode) {
				ui.updateCoords(player.getPosVector());
	        }
        }
        if(isKeyPressed(KeyCode.S)) {
			player.setX(player.getTranslateX() + xDelta);
			player.setZ(player.getTranslateZ() - zDelta);
			if(godmode) {
				ui.updateCoords(player.getPosVector());
	        }
        }
        if(isKeyPressed(KeyCode.W)) {
			player.setX(player.getTranslateX() - xDelta);
			player.setZ(player.getTranslateZ() + zDelta);
			if(godmode) {
				ui.updateCoords(player.getPosVector());
	        }
        }
        if(godmode) {
	        if(isKeyPressed(KeyCode.SHIFT)) {
	        	player.setY(player.getTranslateY() + moveDelta);	
	        }
	        if(isKeyPressed(KeyCode.SPACE)) {
	        	player.setY(player.getTranslateY() - moveDelta);
	        }
	        ui.updateCoords(player.getPosVector());
    	}
        
        if(isKeyPressed(KeyCode.ESCAPE)){
            System.exit(0);
        }
        
        // for (int i = 0; i < spheres.length; i++){
        //     if (!spheres[i].isVisible()) {
        //         spheres[i].setVisible(true);
        //     }
        //     if (!player.getBoundsInParent().intersects(spheres[i].getBoundsInParent())) {
        //         spheres[i].setVisible(false);
        //     }
        // }
        if (firstDotIdx > 0){ 
        	for (int i = firstDotIdx;  i < childrenLength(); i++){
        		Node node = getChild(i);
        		Point3D point = new Point3D(node.getTranslateX(), node.getTranslateY(), node.getTranslateZ());
                // Point3D point2d = find3Dto2D(point);
                Point3D vector = point.subtract(new Point3D(player.getTranslateX(), player.getTranslateY(), player.getTranslateZ()));
                boolean visible = false;
                
                double angleXZ = Math.toDegrees(Math.atan2(vector.getZ(), vector.getX()));
                double angleY = Math.toDegrees(Math.atan2( vector.getY(),(new Point3D(vector.getX(), 0, vector.getZ())).magnitude()));
                // if ((point2d.getX() > getThis().getScreenWidth() || point2d.getX() < 0) && (point2d.getY() > 1500 /*getThis().getScreenHeight()*/ || point2d.getY() < 500))
                //     visible = false;
                // else if (player.getVector().dotProduct(vector) >= 0)
                //     visible = true;
                // else 
                //     visible = false;
                if (angleXZ > 0){
                    angleXZ = 90-angleXZ;
                } else {
                    angleXZ = -angleXZ + 90 > 180 ? -270 - angleXZ : -angleXZ + 90;
                }
                angleY *= -1;
                double diffXZ = Math.abs(angleXZ - player.getRotateY());
                if (diffXZ > 180)
                    diffXZ = 180 - Math.abs(angleXZ) + 180 - Math.abs(player.getRotateY());
                
                if (Math.abs(diffXZ) > 50 || Math.abs(angleY - player.getRotateX()) > 35) //50, 35
                    visible = false;
                else
                    visible = true;
                
                if (!node.isVisible()){
                	if(((PhongMaterial)((Sphere)node).getMaterial()).getDiffuseColor().equals(enemy.getRayColor())){
                		enemyPointCount++;
                	}
                    node.setVisible(true);
                }
                if (!visible){
                	if(((PhongMaterial)((Sphere)node).getMaterial()).getDiffuseColor().equals(enemy.getRayColor())){
                		enemyPointCount--;
                	}
                    node.setVisible(false);
                }
        	}

        }
        
        // enemy scan and starts chase
        if (enemyPointCount > 150 && !enemyIsChasing) {
        	enemyIsChasing = true;
        	scanBlacklistedColliders.addAll(enemy.getColls());
        	enemy.showPic();
        	playEnemySound();
        	enemy.setMat(new PhongMaterial(Color.DARKRED));
        	previousTime = now;
        	enemyPointCount = 0;
        }
        
        // enemy flashes color and moves to player
        if (enemyIsChasing) {
        	if (now-previousTime >= 0.5e9) {
        		if (godmode) enemy.setMat(null);
        		else enemy.setMat(new PhongMaterial(Color.TRANSPARENT));
        	}
        	
        	// checks if enemy has caught player
        	for (int i=0; i<enemy.getColls().size(); i++) {
        		if (Physics_Engine.Gilbert_Johnson_Keerthi_Collision(player.coll, enemy.getColls().get(i), false) != null) {
        			enemyIsChasing = false;
        			isCaught = true;
        		}
        	}
        	
        	enemy.moveToPlayer();
        }
        
        // stops chase
        if(enemyIsChasing && now-previousTime >= 15e9) {
        	scanBlacklistedColliders.removeAll(enemy.getColls());
        	enemy.remPic();
        	enemyIsChasing = false;
        	previousTime = now;
        	int spawnNum = (int)(Math.random() * enemySpawnPoints.size());
        	enemy.setX(enemySpawnPoints.get(spawnNum).getX());
        	enemy.setZ(enemySpawnPoints.get(spawnNum).getY());
        	enemy.rotateY(enemySpawnPointDirections.get(spawnNum));
        	
        	
        }
        
        
        // allows enemy to spawn in front of u
        if (!enemyIsChasing && now-previousTime >= 30e9) { 
        	previousTime = now;
        	if (enemySpawnedIn) {
        		enemyCanSpawnInFront = true;
        	} else {
        		enemySpawnedIn = true;
        	}
        }
        
        // randomizes when enemy spawns in front of player
        if (!enemyIsChasing && enemyCanSpawnInFront && now-previousTime >= 1e9) {
        	if (Math.random() * 100 < 10) {
        		enemy.setX(player.getTranslateX() + 180 * Math.sin(Math.toRadians(player.getRotateY())));
        		enemy.setZ(player.getTranslateZ() + 180 * Math.cos(Math.toRadians(player.getRotateY())));
        		enemy.rotateY(enemy.getAngleToPlayer());
        		enemyCanSpawnInFront = false;
        	}
        	previousTime = now;
        }
        
        // adds death screen after being caught
        if (isCaught && !ui.isDeathScreenShowing()) {
        	ui.addDeathScreen();
        	deathSound.play();
			dead = true;
        	previousTime = now;
        }  
        
        // removes death screen and returns back to title screen
        if (ui.isDeathScreenShowing()) {
        	if (now-previousTime >= 3e9) {
    			ui.removeDeathScreen();
    			deathSound.stop();
    			deathSound.close();
    			isCaught = false;
    			scene.setCursor(Cursor.DEFAULT);
    			((BorderPane)scene.getRoot()).setCenter(titleScreen);
    		}
        }
        
        rayColor = null;
        
        if (now-previousTime >= (17e9 + 5e9*Math.random())) {
        	playRandomAmbiance();
        	previousTime = now;
        }
    }

	/**
	 * 
	 */
	private void playRandomAmbiance() {
		int i = (int)(Math.random()*9) + 1;
		Sound s = new Sound("lidar3d/sounds/ambiance" + i + ".wav");
		s.play();
	}
	
	private void playEnemySound() {
		int i = (int)(Math.random()*3) + 1;
		Sound s = new Sound("lidar3d/sounds/enemy" + i + ".wav");
		s.play();
	}

	public void enteredDoor(){
		((BorderPane)scene.getRoot()).setCenter(titleScreen);
	}

	public void exitedKeypad(){
		this.requestFocus();
		scene.setCursor(Cursor.NONE);
		scene.setFill(Color.BLACK);
	}
}