package physics;


import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.shape.Box;

public class CubeCollider_3d extends Collider_3d{
	
	
	public CubeCollider_3d(Box b) {
		setReturnShape(b);
		updatePoints(b);
		setTriangles();
	}
	
	private void updatePoints(Box b) {
		pts = new Point3D[] {
			new Point3D(b.getTranslateX()-b.getWidth()/2, b.getTranslateY()-b.getHeight()/2, b.getTranslateZ()-b.getDepth()/2), 
			new Point3D(b.getTranslateX()+b.getWidth()/2, b.getTranslateY()-b.getHeight()/2, b.getTranslateZ()-b.getDepth()/2),
			new Point3D(b.getTranslateX()-b.getWidth()/2, b.getTranslateY()+b.getHeight()/2, b.getTranslateZ()-b.getDepth()/2),
			new Point3D(b.getTranslateX()+b.getWidth()/2, b.getTranslateY()+b.getHeight()/2, b.getTranslateZ()-b.getDepth()/2),
			
			new Point3D(b.getTranslateX()-b.getWidth()/2, b.getTranslateY()-b.getHeight()/2, b.getTranslateZ()+b.getDepth()/2),
			new Point3D(b.getTranslateX()+b.getWidth()/2, b.getTranslateY()-b.getHeight()/2, b.getTranslateZ()+b.getDepth()/2),
			new Point3D(b.getTranslateX()-b.getWidth()/2, b.getTranslateY()+b.getHeight()/2, b.getTranslateZ()+b.getDepth()/2),
			new Point3D(b.getTranslateX()+b.getWidth()/2, b.getTranslateY()+b.getHeight()/2, b.getTranslateZ()+b.getDepth()/2)
		};
	}
	
	private Point3D getCenter() {
		return new Point3D(returnShape.getTranslateX(), returnShape.getTranslateY(), returnShape.getTranslateZ());
	}

	@Override
	protected void setTriangles() {
		tris = new Triangle_3d[] {
			// uv based on this http://ilkinulas.github.io/assets/uv_mapping/dice_unwrap.png - this took a while
			new Triangle_3d(pts[0], pts[1], pts[2], new Point2D(0.75,(2.0/3)), new Point2D(1,(2.0/3)), new Point2D(1,(1.0/3)), getCenter()),
			new Triangle_3d(pts[0], pts[2], pts[4], new Point2D(0.75,(2.0/3)), new Point2D(1,(1.0/3)), new Point2D(0.75,(1.0/3)), getCenter()),
			new Triangle_3d(pts[1], pts[3], pts[5], new Point2D(0.5,(2.0/3)), new Point2D(0.75,(2.0/3)), new Point2D(0.75,(1.0/3)), getCenter()),
			new Triangle_3d(pts[5], pts[3], pts[7], new Point2D(0.5,(2.0/3)), new Point2D(0.75,(1.0/3)), new Point2D(0.5,(1.0/3)), getCenter()),
			new Triangle_3d(pts[2], pts[3], pts[7], new Point2D(0.25,(2.0/3)), new Point2D(0.5,(2.0/3)), new Point2D(0.5,(1.0/3)), getCenter()),
			new Triangle_3d(pts[2], pts[7], pts[6], new Point2D(0.25,(1.0/3)), new Point2D(0.5,(1.0/3)), new Point2D(0.25,(1.0/3)), getCenter()),
			new Triangle_3d(pts[0], pts[2], pts[6], new Point2D(0,(2.0/3)), new Point2D(0.25,(2.0/3)), new Point2D(0.25,(1.0/3)), getCenter()),
			new Triangle_3d(pts[0], pts[6], pts[4], new Point2D(0,(2.0/3)), new Point2D(0.25,(1.0/3)), new Point2D(0,(1.0/3)), getCenter()),
			new Triangle_3d(pts[0], pts[1], pts[3], new Point2D(0.25,1), new Point2D(0.5,1), new Point2D(0.5,(2.0/3)), getCenter()),
			new Triangle_3d(pts[0], pts[3], pts[2], new Point2D(0.25,1), new Point2D(0.5,(2.0/3)), new Point2D(0.25,(2.0/3)), getCenter()),
			new Triangle_3d(pts[4], pts[5], pts[7], new Point2D(0.25,(1.0/3)), new Point2D(0.5,(1.0/3)), new Point2D(0.5,0), getCenter()),
			new Triangle_3d(pts[4], pts[7], pts[6], new Point2D(0.25,(1.0/3)), new Point2D(0.5,0), new Point2D(0.25,0), getCenter())
		};
	}

}
