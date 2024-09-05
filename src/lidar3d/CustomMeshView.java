package lidar3d;

import javafx.collections.ObservableFloatArray;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.ObservableFaceArray;
import javafx.scene.shape.TriangleMesh;
import physics.Triangle_3d;

public class CustomMeshView extends MeshView{
	
	private Triangle_3d[] triangles;
	
	public CustomMeshView(Mesh m) {
		super(m);
//		setScaleX(s);
//		setScaleY(s);
//		setScaleZ(s);
		setTriangles();
	}
	
	public Triangle_3d[] getTriangles() {
		return triangles;
	}
	
	private void setTriangles() {
		TriangleMesh meshes = ((TriangleMesh)getMesh());
		ObservableFaceArray faces = meshes.getFaces();
		ObservableFloatArray tex = meshes.getTexCoords();
		ObservableFloatArray pos = meshes.getPoints();
		
		Point3D avg = Point3D.ZERO;
		for(int i = 0; i < pos.size()-pos.size()%3; i+=3) {
			avg.add(new Point3D(pos.get(i), pos.get(i+1), pos.get(i+2)));
		}
		avg = avg.multiply(1/(pos.size()-pos.size()%3));
		
		Triangle_3d[] tris = new Triangle_3d[faces.size()/6];
		for(int i = 0; i < faces.size(); i+=6) {
			Triangle_3d tri = new Triangle_3d(
					getPointCoords(faces.get(i), pos), getPointCoords(faces.get(i+2), pos), getPointCoords(faces.get(i+4), pos),
					getTexCoords(faces.get(i+1), tex), getTexCoords(faces.get(i+3), tex), getTexCoords(faces.get(i+5), tex), avg
			);
			tris[i/6] = tri;
		}
		triangles = tris;
	}
	
	private Point2D getTexCoords(int idx, ObservableFloatArray t) {
		int e = idx*2;
		return new Point2D(t.get(e), t.get(e+1));
	}
	
	private Point3D getPointCoords(int idx, ObservableFloatArray p) {
		Point3D ans;
		int t = idx*3;
		double x = p.get(t);
		double y = p.get(t+1);
		double z = p.get(t+2);
		ans = new Point3D(x,y,z);
		return ans;
	}
}
