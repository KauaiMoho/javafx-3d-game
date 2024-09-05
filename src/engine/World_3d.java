package engine;
import javafx.geometry.Point2D;
import java.util.ArrayList;

import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Rotate;
import lidar3d.Player_3d;
import physics.Collider_3d;
import physics.CollisionTile_3d;
import physics.MeshCollider_3d;

public abstract class World_3d extends World{
    protected Player_3d player;
    private Group root;
    protected ArrayList<Collider_3d> worldColliders = new ArrayList<Collider_3d>();
    protected CollisionTile_3d[][] collTiles;
    double moveDelta = 20;//900;
	double fov = 60;
	double nClip = 0.1;
	double fClip = 100000;
    double sceneWidth = 1920;
	double sceneHeight = 1080;

    public World_3d(Player_3d cam){
        player = cam;
        setPrefSize(sceneWidth, sceneHeight);
		sceneProperty().addListener((ChangeListener) -> {
			this.requestFocus();
		});
        addCamera();
    }
    
    public Point2D find3Dto2D(Point3D p) {
    	Point3D changed = root.localToScene(p, true);
    	return new Point2D(changed.getX(), changed.getY());
    	
    }
    
    public boolean existsInWorld(Node n) {
    	return root.getChildren().contains(n);
    }
    
    public boolean existsInWorld(Actor_3d actor) {
    	return root.getChildren().contains(actor);
    }
    
    public int getColliderSize(){
       return worldColliders.size();
    }

    public void addCollider(Collider_3d col){
        worldColliders.add(col);
    }
    
    public Node remove(int i){
        return root.getChildren().remove(i);
    }
    
    public int childrenLength(){
        return root.getChildren().size();
    }

    public void addInWorld(Node n){
        root.getChildren().add(n);
    }
    
    public void addInWorld(Actor_3d actor){
        root.getChildren().add(actor);
        actor.addedToWorld();
    }
    public void removeInWorld(Node n){
        root.getChildren().remove(n);
    }
    
    public void removeInWorld(Actor_3d actor){
        root.getChildren().remove(actor);
    }

    public Node getChild(int index){
        return root.getChildren().get(index);
    }
    
    public void addOutOfWorld(Node n){
        getChildren().add(n);
    }
    
    public void removeOutOfWorld(Node n){
        getChildren().remove(n);
    }
    
    public Collider_3d getCollider(int index){
        return worldColliders.get(index);
    }
    
    public Player_3d getPlayer() {
    	return player;
    }

    private void addCamera(){
        root = new Group();
        Rotate xRot;
        Rotate yRot;
        Rotate zRot;
        SubScene subScene = new SubScene(root, sceneWidth, sceneHeight, true, SceneAntialiasing.BALANCED);
        xRot = new Rotate(0, Rotate.X_AXIS);
        yRot = new Rotate(0, Rotate.Y_AXIS);
        zRot = new Rotate(0, Rotate.Z_AXIS);
        player.getTransforms().addAll(xRot, yRot, zRot); //0, -200, -1000
        player.setNearClip(nClip);
        player.setFarClip(fClip);
        player.setFieldOfView(fov);
        player.setSubScene(subScene);
        subScene.setCamera(player);
        getChildren().add(subScene);
    }

    public Player_3d getCamera(){
        return player;
    }

    @Override
    public void act(long now){}

    public double getScreenWidth(){ return sceneWidth;}

    public double getScreenHeight(){ return sceneHeight;}
}