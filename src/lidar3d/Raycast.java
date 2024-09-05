package lidar3d;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;
import physics.Collider_3d;
import physics.CollisionTile_3d;
import physics.MeshCollider_3d;
import physics.Physics_Engine;
import physics.Triangle_3d;

public class Raycast {
	
	private Point3D angle;
	private Point3D init;
	private Point3D exit;
	private lidar3d_World world;
	private Color rayColor = Color.WHITE;
	private Shape3D currMesh;
	private Triangle_3d currTri;
 	
	public Raycast(lidar3d_World w, Point3D angle, Point3D initPos) {
		world = w;
		init = initPos;
		exit = init;
		this.angle = angle;
	}
	
	public Point3D propogate_TEMP(boolean useTex) {
		double multiplier = 1;
		Point3D dir = new Point3D( multiplier*Math.sin(angle.getY() * Math.PI / 180), multiplier*Math.sin(angle.getX() * Math.PI / 180), multiplier*Math.cos(angle.getY() * Math.PI / 180));
		double minDist = Double.MAX_VALUE;
		for(int k = 0; k < world.getColliderSize(); k++) {
			Collider_3d coll = world.getCollider(k);
			if(!world.scanBlacklistedColliders.contains(coll)) {
				Triangle_3d[] tris = coll.getTriangles();
				for(int l = 0; l < tris.length; l++) {
					double p = Physics_Engine.Moller_Trumbore_RayTriIntersection(tris[l], init, dir);
					if(p != -1) {
						if(p < minDist) {
							currMesh = coll.getReturnShape();
							rayColor = coll.getRayColor();
							currTri = tris[l];
							minDist = p;
						}else {
							//world.addShape(init.add(dir.normalize().multiply(p)), Color.BLUE);
						}
					}
				}
			}
			
		}
		
		if(minDist < Double.MAX_VALUE) {
			Point3D p = init.add(dir.normalize().multiply(minDist));
			if(useTex) {
				world.addTextureSpot(interpolateUVCoords(p, currTri), currMesh);
				return null;
			}
			return p;
		}
		return null;
	}
	
	public Point3D propogate_NEW(boolean useTex) {
		double multiplier = 1;
		Point3D dir = new Point3D( multiplier*Math.sin(angle.getY() * Math.PI / 180), multiplier*Math.sin(angle.getX() * Math.PI / 180), multiplier*Math.cos(angle.getY() * Math.PI / 180));
		double minDist = Double.MAX_VALUE;
		
		
		
		int[] initIdxs = world.getTileCoords(init);
    	int[] dirIdxs = world.getTileCoords(dir.multiply(10000));
    	int i = initIdxs[0];
    	int j = initIdxs[1];
    	int di = Math.abs(dirIdxs[0]-i);
    	int dj = Math.abs(dirIdxs[1]-j);
    	int incI = dir.getX() > 0 ? 1 : -1;
    	int incJ = dir.getZ() > 0 ? 1 : -1;
    	int e = di-dj;
    	di*=2;
    	dj*=2;
    	
    	while(world.inCollBounds(i,j)) {
    		ArrayList<Collider_3d> colls = world.getTileAt(i,j).colls;
    		Box b = world.getTileAt(i,j).getRepresentativeBox();
    		if(!world.existsInWorld(b) && world.godmode) {
    			b.setMaterial(new PhongMaterial(Color.color(Math.random(), Math.random(), Math.random(), .5)));
    			b.setDrawMode(DrawMode.LINE);
    			world.addInWorld(b);
    			
    		}
			for(int k = 0; k < colls.size(); k++) {
				Collider_3d coll = colls.get(k);
				Triangle_3d[] tris = coll.getTriangles();
				for(int l = 0; l < tris.length; l++) {
					double p = Physics_Engine.Moller_Trumbore_RayTriIntersection(tris[l], init, dir);
					if(p != -1) {
						int[] idxCheck = world.getTileCoords(init.add(dir.normalize().multiply(p)));
						if(idxCheck[0] == i && idxCheck[1] == j && p < minDist) { //ISSUE LIES HERE WITH IDX CHECK
							currMesh = coll.getReturnShape();
							rayColor = coll.getRayColor();
							currTri = tris[l];
							minDist = p;
						}else if(p < minDist){
							System.out.println("idxcheck: " + idxCheck[0] + ", " + idxCheck[1]);
							System.out.println("truecheck: " + i + ", " + j);
							
							world.addShape(init.add(dir.normalize().multiply(p)), Color.BLUE);
						}
					}
				}
			}
			
			if(minDist < Double.MAX_VALUE) {
				//System.out.println("dist " + minDist + "angle " + angle );
				Point3D p = init.add(dir.normalize().multiply(minDist));
				if(useTex) {
					world.addTextureSpot(interpolateUVCoords(p, currTri), currMesh);
					return null;
				}
				return p;
			}
    		
    		
    		if(e > 0) {  //if the change in position is positive (x oriented since dx is first), move x
    			i+=incI;
    			e-=dj;
    		}else if (e < 0) { //else do y
    			j+=incJ;
    			e+=di;
    		}else { //move both equally if line is perfectly equal change (perfectly diagonal)
    			i+=incI;
    			j+=incJ;
    			e-=di;
    			e+=dj;
    		}
    		
    	}
    	return null;
	}
	
	public Color getHitRayColor() {
		return rayColor;
	}
	
	private Point2D interpolateUVCoords(Point3D p, Triangle_3d tri) {
		
		Point3D ap = tri.getA().subtract(p);
		Point3D bp = tri.getB().subtract(p);
		Point3D cp = tri.getC().subtract(p);
		
		Point3D fullArea = tri.getA().subtract(tri.getB()).crossProduct(tri.getA().subtract(tri.getC()));
		Point3D abpSubArea = ap.crossProduct(bp);
		Point3D bcpSubArea = bp.crossProduct(cp);
		Point3D acpSubArea = cp.crossProduct(ap);
		
		double xMult = bcpSubArea.magnitude()/(fullArea.magnitude() * getSign(fullArea.dotProduct(bcpSubArea)));
		double yMult = acpSubArea.magnitude()/(fullArea.magnitude() * getSign(fullArea.dotProduct(acpSubArea)));
		double zMult = abpSubArea.magnitude()/(fullArea.magnitude() * getSign(fullArea.dotProduct(abpSubArea)));
		

		return tri.getUV1().multiply(xMult).add(tri.getUV2().multiply(yMult)).add(tri.getUV3().multiply(zMult));
	}
	
	private int getSign(double d) {
		return d >= 0 ? 1 : -1;
	}
	
	public Point3D propogate_OLD() {
		int offset = 0;
		double multiplier = 1;
		Point3D dir = new Point3D( multiplier*Math.sin(angle.getY() * Math.PI / 180), multiplier*Math.sin(angle.getX() * Math.PI / 180), multiplier*Math.cos(angle.getY() * Math.PI / 180));
		
		while (offset < 1000) {
			Point3D[] p = {exit};
			MeshCollider_3d m = new MeshCollider_3d(p, null);
			CollisionTile_3d ctile = world.getTile(exit);
			
			if(ctile != null) {
				ArrayList<Collider_3d> c = ctile.colls;
				for (int i=0; i< c.size(); i++) {
					Point3D pt = Physics_Engine.Gilbert_Johnson_Keerthi_Collision(m, c.get(i), false);
					if (pt != null) {
						rayColor = c.get(i).getRayColor();
						exit = exit.add(pt);
						return exit;
					}
				}
				exit = exit.add(dir);
				multiplier+=0.1;
				offset++;
			}else {
				break;
			}
		}
		return null; // move ray
	}
	
	
	public Point3D findIntersection(CustomMeshView intersectedMesh) {
		init.subtract(100000 * Math.sin(angle.getY() * Math.PI / 180), 0, 100000*Math.cos(angle.getY() * Math.PI / 180));
		exit.add(100000 * Math.sin(angle.getY() * Math.PI / 180), 0, 100000*Math.cos(angle.getY() * Math.PI / 180));
		int i = 0;
//		for (;i<intersectedMesh.triangles.length; i++) {
//			if (isIntersectingTriangle(intersectedMesh.triangles[i])) break;
//		}
//		if (i == intersectedMesh.triangles.length) return null;
//		Triangle_3d t = intersectedMesh.triangles[i];
//		Point3D cross = t.getB().subtract(t.getA()).crossProduct(t.getC().subtract(t.getA()));
//		double dot = -init.subtract(t.getA()).dotProduct(cross) / exit.subtract(init).dotProduct(cross);
//		return init.add(exit.subtract(init).multiply(dot));
		return null;
	}
	
	private boolean isIntersectingTriangle(Triangle_3d t) {
		if ((SignedVolume(init, t.getA(), t.getB(), t.getC()) > 0 && SignedVolume(exit, t.getA(), t.getB(), t.getC()) < 0) || (SignedVolume(init, t.getA(), t.getB(), t.getC()) > 0 && SignedVolume(exit, t.getA(), t.getB(), t.getC()) < 0)) {
			return (SignedVolume(init, exit, t.getA(), t.getB()) > 0 && SignedVolume(init, exit, t.getB(), t.getC()) > 0 && SignedVolume(init, exit, t.getA(), t.getC()) > 0) || (SignedVolume(init, exit, t.getA(), t.getB()) < 0 && SignedVolume(init, exit, t.getB(), t.getC()) < 0 && SignedVolume(init, exit, t.getA(), t.getC()) < 0);
		}
		return false;
	}
	
	private double SignedVolume(Point3D a, Point3D b, Point3D c, Point3D d) {
		Point3D cross = b.subtract(a).crossProduct(c.subtract(a));
		double dot = cross.dotProduct(d.subtract(a));
		return dot / 6.0;
	}
	
}
