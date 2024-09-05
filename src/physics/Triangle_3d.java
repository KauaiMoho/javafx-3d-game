package physics;

import java.util.Arrays;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

public class Triangle_3d {
	
	private Point3D a;
	private Point3D b;
	private Point3D c;
	private Point3D origin;
	private Point3D normal;
	private double dist;
	
	
	private Point2D uv1;
	private Point2D uv2;
	private Point2D uv3;
	
	public Triangle_3d(Point3D a, Point3D b, Point3D c) {
		this.a = a;
		this.b = b;
		this.c = c;
		origin = Point3D.ZERO;
		setOriginNormalAndDist(Point3D.ZERO);
	}
	
	public Triangle_3d(Point3D a, Point3D b, Point3D c, Point2D u1, Point2D u2, Point2D u3, Point3D origin) {
		this.a = a;
		this.b = b;
		this.c = c;
		uv1 = u1;
		uv2 = u2;
		uv3 = u3;
		this.origin = origin;
		setOriginNormalAndDist(origin);
	}
	
	public void addVector(Point3D v) {
		a = a.add(v);
		b = b.add(v);
		c = c.add(v);
		setOriginNormalAndDist(origin);
	}

	public void setOriginNormalAndDist(Point3D o){
		Point3D ab = b.subtract(a);
		Point3D ac = c.subtract(a);
		Point3D ao = o.subtract(a);
		Point3D n = ab.crossProduct(ac).normalize();
		
		if(ao.dotProduct(n) > 0) {
			n = n.multiply(-1);
		}
		double d = a.dotProduct(n);
		normal = n;
		dist = d;
		
	}

	public Point3D getMidpoint(){
		Point3D ab = a.midpoint(b);
		return ab.midpoint(c);
	}
	
	public double getDistFromOrigin() {
		return dist;
	}
	
	public Point3D getNormal() {
		return normal;
	}
	
	public Point2D getUV1() {
		return uv1;
	}
	
	public Point2D getUV2() {
		return uv2;
	}
	
	public Point2D getUV3() {
		return uv3;
	}

	public Point3D getA(){
		return a;
	}
	public Point3D getB(){
		return b;
	}
	
	public Point3D getC(){
		return c;
	}

	public Point3D[] getPoints(){
		return new Point3D[]{a, b, c, getMidpoint()};
	}
	
	public String toString() {
		return Arrays.toString(getPoints()) + "";
	}
}
