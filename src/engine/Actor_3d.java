package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.collections.ObservableFloatArray;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import lidar3d.CustomMeshView;
import physics.Collider_3d;
import physics.MeshCollider_3d;

public abstract class Actor_3d extends Group{
	protected double width;
	protected double height;
	protected ArrayList<Collider_3d> colls = new ArrayList<Collider_3d>();
	protected String path;
	protected World_3d world;
	protected double scale = 1;
	protected final static String header = "javafx-3d-game/src/lidar3d/lidar3d_resources/";
	
	protected Rotate yRot = new Rotate(0, Rotate.Y_AXIS);
	protected Rotate zRot = new Rotate(0, Rotate.Z_AXIS);
	protected Rotate xRot = new Rotate(0, Rotate.Z_AXIS);
	
    public Actor_3d(){

    }

//    public Actor_3d(String path, World_3d world, double scale, boolean addCollsToWorld){
//    	this.path = path;
//    	this.world = world;
//    	this.scale = scale;
//        importModel(world, scale, addCollsToWorld);
//    }
    
    public String getPath(){
        return path;
    }

    public void addedToWorld(){ }
    
    public void importModel(World_3d world, boolean addCollsToWorld){
    	File f = new File(path);
    	int mode = 0;
    	int count = 0;
    	try {
			Scanner s = new Scanner(f);
			TriangleMesh m = new TriangleMesh();
			while(s.hasNext()) {
				
				String str = s.next();
				if(str.equals("v")) {
					mode = 0;
				}else if(str.equals("t")) {
					mode = 1;
				}else if(str.equals("f")) {
					mode = 2;
				}else if(str.equals("=")) {
					count++;
					ObservableFloatArray meshPoints = m.getPoints();
					Point3D[] pts = new Point3D[meshPoints.size()/3];
					for (int j = 0; j < meshPoints.size() - meshPoints.size()%3; j += 3) {
						float x = meshPoints.get(j);
					    float y = meshPoints.get(j + 1);
					    float z = meshPoints.get(j + 2);		
					    pts[j/3] = new Point3D(x,y,z);
					}
					CustomMeshView v = new CustomMeshView(m);
					v.setMaterial(getMat());
					v.setDrawMode(getDrawMode());
					MeshCollider_3d mColl = new MeshCollider_3d(pts, v);
					mColl.setRayColor(getRayColor());
					colls.add(mColl);
					if(addCollsToWorld) {
						world.addCollider(mColl);
					}
					getChildren().add(v);
					m = new TriangleMesh();
				}else {
					if(mode == 0) {
						m.getPoints().addAll((float)(Float.parseFloat(str)*scale));
					}else if(mode == 1) {
						m.getTexCoords().addAll(Float.parseFloat(str));
					}else if(mode == 2) {
						m.getFaces().addAll(Integer.parseInt(str));
					}
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    
    
    public DrawMode getDrawMode() {
    	return DrawMode.FILL;
    }
    
    public void setMat(PhongMaterial m) {
    	for(int i = 0; i < colls.size(); i++) {
    		colls.get(i).getReturnShape().setMaterial(m);
    	}
    }
    
    public PhongMaterial getMat() {
    	return new PhongMaterial();
    }
    
    public Color getRayColor() {
    	return Color.WHITE;
    }

	public void setX(double value) {
		for(int i = 0; i < colls.size(); i++) {
			colls.get(i).addVector(new Point3D(value-this.getTranslateX(), 0, 0));
		}
		this.setTranslateX(value);
	}
	
	public void setY(double value) {
		for(int i = 0; i < colls.size(); i++) {
			colls.get(i).addVector(new Point3D(0, value-this.getTranslateY(), 0));
		}
		this.setTranslateY(value);
	}
	
	public void setZ(double value) {
		for(int i = 0; i < colls.size(); i++) {
			colls.get(i).addVector(new Point3D(0, 0, value-this.getTranslateZ()));
		}
		this.setTranslateZ(value);
	}
	
	public void rotateZ(double value) {
		double angle = get360Angle(value);
		zRot.setAngle(angle);
	}
	
	public void rotateY(double value) {
		double angle = get360Angle(value);
		yRot.setAngle(angle);
	}
	
	public void rotateX(double value) {
		double angle = get360Angle(value);
		xRot.setAngle(angle);
	}
	
	public double get360Angle(double angle){
		angle = angle < -180 ? 360 + angle : angle;
		angle = angle > 180 ? angle - 360 : angle;
		return angle;
	}
	
	public double getRotateZ() {
		return zRot.getAngle();
	}
	
	public double getRotateX() {
		return xRot.getAngle();
	}
	public double getRotateY() {
		return yRot.getAngle();
	}
	
	public Point3D getPosVector() {
		return new Point3D(getTranslateX(), getTranslateY(), getTranslateZ());
	}
    
    public ArrayList<Collider_3d> getColls() {
    	return colls;
    }

    public void addTransforms(Transform transform){
        getTransforms().add(transform);
    }

    public Transform getTransform(int index){
        return getTransforms().get(index);
    }

    public void act(long now) {}
    
	public void setPreferSize(double width, double height){
		this.width = width;
		this.height = height;
		maxWidth(width);
		maxHeight(height);
		minWidth(width);
		minHeight(height);
	}

	public double getGroupWidth(){
		return width;
	}
	
	public double getGroupHeight(){
		return height;
	}
}