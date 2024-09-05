package physics;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape3D;
import lidar3d.CustomMeshView;

public abstract class Collider_3d {
	
	protected Point3D[] pts;
	protected Shape3D returnShape;
	protected Triangle_3d[] tris;
	protected Color rayColor = Color.WHITE;
	
	public Point3D findExtrema(Point3D dir) {
		Point3D max = pts[0];
		double mDist = max.dotProduct(dir);
		for(int i = 1; i < pts.length; i++) {
			double tDist = pts[i].dotProduct(dir);
			if(tDist > mDist) {
				mDist = tDist;
				max = pts[i];
			}
		}
		return max;
	}
	
	public void addVector(Point3D p) {
		for(int i = 0; i < pts.length; i++) {
			pts[i] = pts[i].add(p);
		}
		for(int i = 0; i < tris.length; i++) {
			tris[i].addVector(p);
		}
	}
	
	public Point3D[] getPoints() {
		return pts;
	}
	
	protected void setReturnShape(Shape3D n) {
		returnShape = n;
	}
	
	protected abstract void setTriangles();
	
	public Triangle_3d[] getTriangles() {
		return tris;
	}
	
	public Shape3D getReturnShape() {
		return returnShape;
	}
	
	public void setRayColor(Color c) {
		rayColor = c;
	}
	
	public Color getRayColor() {
		return rayColor;
	}
}
