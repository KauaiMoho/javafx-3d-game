/*
Name:       Karma Luitel
Date:       May 18, 2023
Period:     1

Is this lab fully working?  (Yes/No) - Yes
If not, explain:
*/
package physics;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.shape.Box;

public class CollisionTile_3d {
	
	private Point2D[] points;
	private Box representativeBox;
	
	public ArrayList<Collider_3d> colls = new ArrayList<Collider_3d>();
	
	public CollisionTile_3d(Point2D[] pos, Box b) {
		points = pos;
		representativeBox = b;
	}
	
	public void addColl(MeshCollider_3d c) {
		colls.add(c);
	}
	
	public Point2D getMinPoint() {
		return points[0];
	}
	
	public Point2D getMaxPoint() {
		return points[1];
	}
	
	public Box getRepresentativeBox() {
		return representativeBox;
	}
}
