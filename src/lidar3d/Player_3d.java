package lidar3d;
import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import physics.CubeCollider_3d;

public class Player_3d extends PerspectiveCamera{
	CubeCollider_3d coll;
	
	SubScene subscene;
	
	double width = 1920;
	double height = 1080;
	
	public Rotate xRot = new Rotate(0, new Point3D(1, 0, 0));//Rotate.X_AXIS);
	Rotate yRot = new Rotate(0, new Point3D(0, 1, 0));
	Rotate zRot = new Rotate(0, new Point3D(0, 0, 1));
	
	double boxW = 2*Math.tan(Math.toRadians(this.getFieldOfView()/2))*(this.getFarClip()-this.getNearClip());
	double boxH = boxW*(height/width);
	
	Point3D rotVector = new Point3D(0, 0, 1);
	Point3D rotVectorXZ = new Point3D(0, 0, 1);
	
	public Player_3d(double scale) {
		super(true);
		coll = new CubeCollider_3d(new Box((scale*2)/3,scale*1.5,(scale*2)/3));
		
		this.getTransforms().addAll(xRot, yRot);
	}
	
	public Player_3d(boolean b, double width, double height, double depth) {
		super(b);
		coll = new CubeCollider_3d(new Box(width,height,depth));
		this.getTransforms().addAll(xRot, yRot, zRot);
	}
	
	public void setSubScene(SubScene s) {
		subscene = s;
	}
	
	public void moveAll(Point3D p) {
		setX(getTranslateX() + p.getX());
		setY(getTranslateY() + p.getY());
		setZ(getTranslateZ() + p.getZ());
	}
	
	public double getFocalLength() {
		return this.getFarClip()-this.getNearClip();
	}
	
	public void setX(double value) {
		coll.addVector(new Point3D(value-this.getTranslateX(), 0, 0));
		this.setTranslateX(value);
	}
	
	public void setY(double value) {
		coll.addVector(new Point3D(0, value-this.getTranslateY(), 0));
		this.setTranslateY(value);
	}
	
	public void setZ(double value) {
		coll.addVector(new Point3D(0, 0, value-this.getTranslateZ()));
		this.setTranslateZ(value);
	}
	
	
	
	public void rotateXBy(double value) {
		double angle = get360Angle(getLimitAngle(xRot.getAngle() + value));
		xRot.setAxis(new Point3D(Math.cos(Math.toRadians(yRot.getAngle())), 0, -Math.sin(Math.toRadians(yRot.getAngle()))));
		xRot.setAngle(angle);
		updateRotVector();
	}
	
	public void rotateYBy(double value) {
		double angle = get360Angle(yRot.getAngle() + value);
		yRot.setAngle(angle);
		updateRotVector();
	}
	
	public void rotateZBy(double value) {
		double angle = get360Angle(zRot.getAngle() + value);
		zRot.setAngle(angle);
		updateRotVector();
	}

	public void updateRotVector(){
		Point3D temp = new Point3D(Math.sin(Math.toRadians(yRot.getAngle())), 0, Math.cos(Math.toRadians(yRot.getAngle())));
		//System.out.println("Before: " + temp.normalize());
		//double mag = temp.magnitude();
		temp = temp.subtract(temp.normalize().multiply(Math.sin(Math.toRadians(xRot.getAngle()))*Math.cos(Math.toRadians(yRot.getAngle()))*temp.magnitude()));
		// System.out.println("diff: " + (mag - temp.magnitude()));
		// System.out.println(Math.sin(Math.toRadians(xRot.getAngle()))*Math.cos(Math.toRadians(yRot.getAngle()))*temp.magnitude());
		rotVector = new Point3D(temp.getX(), Math.tan(xRot.getAngle())*temp.magnitude(), temp.getZ());
		
	}

	public double get360Angle(double angle){
		angle = angle < -180 ? 360 + angle : angle;
		angle = angle > 180 ? angle - 360 : angle;
		return angle;
	}

	public double getLimitAngle(double angle){
		angle = Math.max(-90, Math.min(90, angle));
		return angle;
	}

	public Point3D getVector(){
		return rotVector;
	}
	
	public double getRotateX() {
		return xRot.getAngle();
	}
	
	public double getRotateY() {
		return yRot.getAngle();
	}
	
	public double getRotateZ() {
		return zRot.getAngle();
	}
	
	
	public Point3D getPosVector() {
		return new Point3D(getTranslateX(), getTranslateY(), getTranslateZ());
	}
	
	public Point3D getRotVector() {
		return new Point3D(getRotateX(), getRotateY(), getRotateZ());
	}
}
