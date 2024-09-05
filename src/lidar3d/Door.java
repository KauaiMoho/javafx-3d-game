package lidar3d;

import engine.Actor_3d;
import engine.World_3d;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import physics.CubeCollider_3d;

public class Door extends Actor_3d{
	
	public Door(lidar3d_World world, Point3D pos) {
		Box box = new Box(10, 120, 90);
		box.setTranslateX(pos.getX());
		box.setTranslateY(pos.getY());
		box.setTranslateZ(pos.getZ());
        CubeCollider_3d coll = new CubeCollider_3d(box);
		box.setMaterial(new PhongMaterial(Color.BLUE));
        world.addCollider(coll);
		coll.setRayColor(getRayColor());
		getChildren().add(box);
		colls.add(coll);
		this.getTransforms().add(zRot);
		this.getTransforms().add(yRot);
		this.world = world;
	}
	
	@Override
	public Color getRayColor() {
		return Color.BLUE;
	}
}
