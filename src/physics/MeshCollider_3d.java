package physics;

import javafx.geometry.Point3D;
import lidar3d.CustomMeshView;

public class MeshCollider_3d extends Collider_3d{
	
	public MeshCollider_3d(Point3D[] p, CustomMeshView m) {
		pts = p;
		setReturnShape(m);
		setTriangles();
	}

	@Override
	protected void setTriangles() {
		tris = ((CustomMeshView)returnShape).getTriangles();
	}
	
}
