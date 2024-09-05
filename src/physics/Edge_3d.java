package physics;

import javafx.geometry.Point3D;

public class Edge_3d {
	
	private Point3D a;
	private Point3D b;
	
	public Edge_3d(Point3D a, Point3D b) {
		this.a = a;
		this.b = b;
	}
	
	
	public Point3D getA(){
		return a;
	}
	public Point3D getB(){
		return b;
	}
	
	@Override
	public boolean equals(Object o) {
		Edge_3d e = (Edge_3d)o;
		return a == e.a && b == e.b;
	}
}
