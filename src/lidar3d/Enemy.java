package lidar3d;

import engine.Actor_3d;
import engine.World_3d;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import physics.CubeCollider_3d;

public class Enemy extends Actor_3d{
	
	
	
	lidar3d_World world;
	double enemySpeed = 1;
	ImageView picture;
	
	
	public Enemy(double scale, lidar3d_World world, String p) {
		path = header+p;
		this.scale = scale;
		this.getTransforms().add(zRot);
		this.getTransforms().add(yRot);
		this.world = world;
		
		String path = getClass().getClassLoader().getResource("lidar3d/textures/enemy.png").toString();
		Image pic = new Image(path);
		picture = new ImageView(pic);
		picture.getTransforms().addAll(new Scale(0.25,0.25,0.25));
		picture.setTranslateX(picture.getTranslateX()-pic.getWidth()/8);
		picture.setTranslateY(picture.getTranslateY()-pic.getHeight()/4);
		
	}
	
	public void remPic() {
		getChildren().remove(picture);
		for(int i = 0; i < colls.size(); i++) {
			getChildren().add(colls.get(i).getReturnShape());
		}
	}
	
	public void showPic() {
		getChildren().add(picture);
		for(int i = 0; i < colls.size(); i++) {
			getChildren().remove(colls.get(i).getReturnShape());
		}
		
	}
	
	
	
	public Enemy getThis() {
		return this;
	}
	

	
	public void runMeshTimer() {
		AnimationTimer meshTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if(now >= (long)(10e20)) {
					if(world.existsInWorld(getThis())) {
						world.removeInWorld(getThis());
					}
					this.stop();
				}
			}
		};
		if(!world.existsInWorld(getThis())) {
			world.addInWorld(getThis());
		}
		meshTimer.start();
	}
	
	@Override
	public Color getRayColor() {
		return Color.DARKRED;
	}
	
	public Point2D getDirectionVector() {
		Point2D playerPos = new Point2D(world.getPlayer().getTranslateX(), world.getPlayer().getTranslateZ());
        Point2D enemyPos = new Point2D(getTranslateX(), getTranslateZ());
        Point2D direction = enemyPos.subtract(playerPos).normalize();
        return direction;
	}
	
	public double getAngleToPlayer() {
		Point2D direction = getDirectionVector();
        double angle = Math.toDegrees(Math.atan2(direction.getY(),direction.getX()));
        return -angle + 90;
	}
	
	public void moveToPlayer() {
        rotateY(getAngleToPlayer());
        setX(getTranslateX() - getDirectionVector().multiply(enemySpeed).getX());
    	setZ(getTranslateZ() - getDirectionVector().multiply(enemySpeed).getY());
	}
	
}
