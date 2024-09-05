package physics;

import java.util.ArrayList;
import javafx.geometry.Point3D;
import physics.*;

public class Physics_Engine {
	
	
	private static final double EPSILON = 10e-6;
	
	static private Point3D findSuppPoint(Collider_3d a, Collider_3d b, Point3D dir) {
		return a.findExtrema(dir).subtract(b.findExtrema(dir.multiply(-1)));
	}
	
	public static Point3D Gilbert_Johnson_Keerthi_Collision(Collider_3d a, Collider_3d b, boolean doEPA) {
		Point3D dir = new Point3D(0,0,1);
		Point3D currPoint = findSuppPoint(a,b,dir); // function gets the furthest point of a list of given points given a direction vector
		ArrayList<Point3D> simplex = new ArrayList<Point3D>(); //a simplex is the simplest shape that can encompass a point, in 3d it is a tetrahedron
		simplex.add(currPoint);
		
		dir = currPoint.multiply(-1); // find vector to origin
		
		
		//this code gets the first point in a random direction vector to construct the simplex, chosen based off an arbitary direction vector
		int count = 0;
		while(true) { //loop constructs simplex by adding points based on calculated direction vectors until simplex is finished
			count++;
			currPoint = findSuppPoint(a,b,dir); // get the next point opposite to the first, higest chance of encompassing the origin
			
			//check if orgin could exist between two points, if not end (since there cannot be collision
			if(currPoint.dotProduct(dir) <= EPSILON || count > 20) { //count > 20 to stop concave collisions
				return null;
			}
			simplex.add(currPoint); //add new point to simplex
			dir = checkSimplex(simplex, dir); //check simplex for origin
			if(dir == null){ //no direction means the origin is inside simplex
				if(doEPA) {
					return expandingPolytopeAlg(a,b,simplex); //epa algorithm to find the opposite penetration vector 
				}
				return Point3D.ZERO;
			}
		}
	}

	static private Point3D checkSimplex(ArrayList<Point3D> s, Point3D dir) { //returns null if origin is in simplex, else returns the direction of where the origin has to be
		if(s.size() == 2) {
			return nextDir(s);
		}else if(s.size() == 3) {
			return triSimplex(s, dir);
		}else if(s.size() == 4){
			return tetraSimplex(s, dir);
		}
		return dir;
	}

	static private Point3D tetraSimplex(ArrayList<Point3D> s, Point3D dir) {
		Point3D AB = s.get(2).subtract(s.get(3));
		Point3D AC = s.get(1).subtract(s.get(3));
		Point3D AD = s.get(0).subtract(s.get(3));
		Point3D AO = s.get(3).multiply(-1);
		//same edges as triangle case - read comments there
		
		
		Point3D ABCDir = AB.crossProduct(AC);
		Point3D ADBDir = AD.crossProduct(AB);
		Point3D ACDDir = AC.crossProduct(AD);
		//get perpendicular vectors for all edges 

		if(ABCDir.dotProduct(AO) > 0) {
			s.remove(0);
			return triSimplex(s, dir);
		}else if(ADBDir.dotProduct(AO) > 0) {
			s.remove(1);
			return triSimplex(s, dir);
		}else if(ACDDir.dotProduct(AO) > 0) {
			s.remove(2);
			return triSimplex(s, dir);
		}
		//checks all 3 vornoi reigons (only needed for the top "faces") of the tetrahedron due to the requirmeents for adding a point, top and bottom voronoi reigon dont need to be checked)
		//if one of them contain the origin remove the point in the tetrahedron not making up that face, since it must be away from the origin) and find a new tetrahedron in the direction of that face.

		
		return null; //YAY origin is inside, since none of the 3d vornoi reigons contain the origin
	}
	
	
	
	static private Point3D triSimplex(ArrayList<Point3D> s, Point3D dir) { //see if origin lies within a triangle case of the simplex
		Point3D AB = s.get(1).subtract(s.get(2));
		Point3D AC = s.get(0).subtract(s.get(2));
		//get edge vectos for sides of triangle
		Point3D AO = s.get(2).multiply(-1);
		//origin vector to triangle
		Point3D ABCDir = AB.crossProduct(AC); //vector perpendicular to triangle edges
		
		//for the triangle case there are a total of 6 vornoi reigons. however, due to the line case ruling out the bottom regions, we only need to check the top 3
		
		if(ABCDir.crossProduct(AC).dotProduct(AO) > 0) { //check if vector perpendicular to triangle side AC towards vornoi reigon AC is in same direction as origin
			if(AC.dotProduct(AO) > 0) { // check if the edge AC is towards the origin (topmost voronoi reighon)
				s.remove(1); //remove b since it is not towards origin
				return AC.crossProduct(AO).crossProduct(AC);
			}else {
				s.remove(0); //remove a since origin must be towards the top of the triangle, get next point
				return nextDir(s);
			}
		}
		if(AB.crossProduct(ABCDir).dotProduct(AO) > 0) { //check AB voronoi reigon contains origin
			s.remove(0); //remove c as it is not towards for origin
			return nextDir(s);
		}
		if(ABCDir.dotProduct(AO) > 0) { // check if origin is above/below triangle
			return ABCDir; //return dir if it is 
		}
		s.add(1,s.remove(0)); // if it passes all of these test (none of the vornoi reigons contain origin), c must be a good point and the origin must be above or below the simplex triangle
		return ABCDir.multiply(-1); //move on to the tetrahedron case
		
	}

	static private Point3D nextDir(ArrayList<Point3D> s) { //line case - 2 points
		Point3D AB = s.get(0).subtract(s.get(1)); //vector between points
		Point3D AO = s.get(1).multiply(-1); //vector to origin
		return (AB.crossProduct(AO)).crossProduct(AB);
		
		//find the vector perpendicular to the line in direction of the origin - using triple product of vectors to get perpendicular line in certain direction
		
	}
	
	static Point3D expandingPolytopeAlg(Collider_3d a, Collider_3d b, ArrayList<Point3D> simplex) { //algorithm, given a simplex, find the shortest vector to remove the origin from the simplex
		
		ArrayList<Triangle_3d> faces = new ArrayList<Triangle_3d>();
		faces.add(new Triangle_3d(simplex.get(0), simplex.get(1), simplex.get(2)));
		faces.add(new Triangle_3d(simplex.get(0), simplex.get(2), simplex.get(3)));
		faces.add(new Triangle_3d(simplex.get(1), simplex.get(3), simplex.get(2)));
		faces.add(new Triangle_3d(simplex.get(0), simplex.get(3), simplex.get(1)));
		//get all of the faces of the tetrahedron simplex in a specific order (based of the resource i found for this)
		
		Point3D ans = null;

		while(true) {
			Triangle_3d face = findNextFace(faces); // get face closest to origin
			Point3D nextPnt = findSuppPoint(a,b, face.getNormal()); //get support point away from the face
			double check = nextPnt.dotProduct(face.getNormal()); // see if origin is outside of face
			if(Math.abs(check-face.getDistFromOrigin()) < 0.0001) { // check if  face vector is opposite to origin - means origin is outside 
				ans = face.getNormal().multiply(face.getDistFromOrigin()); //return outside vector
				break;
			}
			rebuildPolytope(faces, nextPnt); //add point to polytope - removing overlapping edges
		}
		return ans.multiply(-1);
	}

	private static void rebuildPolytope(ArrayList<Triangle_3d> faces, Point3D nextPnt) {
		ArrayList<Triangle_3d> facesToRemove = new ArrayList<Triangle_3d>(faces);
		
		for(int i = 0; i < facesToRemove.size(); i++) {
			Triangle_3d face = facesToRemove.get(i);
			if(face.getNormal().dotProduct(nextPnt.subtract(face.getA())) <= 0) {
				facesToRemove.remove(i);
				i--;
			}
		}
		//gets array of all overlapping faces if the new point was added
		
		ArrayList<Edge_3d> edgs = new ArrayList<Edge_3d>();
		
		for(int i = 0; i < facesToRemove.size(); i++) {
			Edge_3d ab = new Edge_3d(facesToRemove.get(i).getA(), facesToRemove.get(i).getB());
			Edge_3d bc = new Edge_3d(facesToRemove.get(i).getB(), facesToRemove.get(i).getC());
			Edge_3d ac = new Edge_3d(facesToRemove.get(i).getA(), facesToRemove.get(i).getC());
			
			removeObsoleteEdgeAndAddNew(edgs, ab);
			removeObsoleteEdgeAndAddNew(edgs, bc);
			removeObsoleteEdgeAndAddNew(edgs, ac);
			//gets all edges of every face to be edited, removing overlapping ones
		}
		
		for(int i = 0; i < facesToRemove.size(); i++) {
			faces.remove(facesToRemove.get(i));
		}//remove faces
		
		for(int i = 0; i < edgs.size(); i++) {
			faces.add(new Triangle_3d(edgs.get(i).getA(), edgs.get(i).getB(), nextPnt));
		} //create new faces based on nonoverlapping edges
		
	}
	
	static private void removeObsoleteEdgeAndAddNew(ArrayList<Edge_3d> edgs, Edge_3d e) {
		boolean add = true;
		for(int i = 0; i < edgs.size(); i++) {
			if(edgs.get(i).equals(e)) { 
				edgs.remove(i);
				add = false;
				break;
			}
		}
		//removes overlapping edges if new edge overlaps, else adds new edge
		if(add) {
			edgs.add(e);
		}
	}

	static private Triangle_3d findNextFace(ArrayList<Triangle_3d> faces) {
		Triangle_3d ret = faces.get(0);
		for(int i = 1; i < faces.size(); i++) {
			Triangle_3d f= faces.get(i);
			if(f.getDistFromOrigin() < ret.getDistFromOrigin()) {
				ret = faces.get(i);
			}
		}
		//get the face closest to the origin
		return ret;
	}
	
	static public double Moller_Trumbore_RayTriIntersection(Triangle_3d t, Point3D init, Point3D dir) {
		
		Point3D ndir = dir.normalize(); //just in case 
		Point3D AB = t.getB().subtract(t.getA()); //V1-V0
		Point3D AC = t.getC().subtract(t.getA()); //V2-v0
		Point3D AO = init.subtract(t.getA());
		//these three are pretty much standard industry practice in physics engine at this point lol
		
		//find determinant (triple product)
		Point3D dEdge2 = ndir.crossProduct(AC);
		double determinant = dEdge2.dotProduct(AB);
		if(determinant > -EPSILON && determinant < EPSILON) { //parallel ray, might change later to account for only one side of triangle (basing this off a paper rn though)
			return -1;
			
		}
		
		double u = dEdge2.dotProduct(AO)/determinant; //cramer's rule for u
		if(u < 0 || u > 1) { //check barycentric coords (have to be between 1 and 0)
			return -1;
		}
		
		Point3D AOB = AO.crossProduct(AB);
		double v = AOB.dotProduct(ndir)/determinant; //cramer's rule for v
		if(v < 0 || u + v > 1) { //check barycentric coords (have to be between 1 and 0)
			return -1;
		}
		
		double intersectDistance = AOB.dotProduct(AC)/determinant;
		
		if(intersectDistance > EPSILON) {
			return intersectDistance;
		}
			
		return -1;
		
	}
	
	
}
