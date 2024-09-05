package lidar3d;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
//import com.interactivemesh.jfx.importer.ImportException;
//import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.ObservableFaceArray;
import javafx.scene.shape.TriangleMesh;

public class ObJWriter_Custom extends Pane{
	
	final static String header = "src/lidar3drepo/";
	
	static String outfilename =  header + "page.txt";
	static String readpath = header + "page.obj";
	
	public static void main(String[] args) {
		
		//ONLY NEED FOR DEVELOPMENT PURPOSES, NOT PART OF OUR GAME. IF IT HAS ERRORS COMMENT THIS ENTIRE CLASS OUT.
		//THIS CODE IS NOT MEANT TO BE RUN, JUST HERE AS PART OF SHOWCASE FOR WHAT WE DID IN MAKING THIS GAME.
		//THIS USES AN EXTERNAL LIBRARY OBJ OUTPUT AND CONVERTS IT INTO READABLE TEXT FILES
		
		//ObjModelImporter objImporter = new ObjModelImporter();
		URL url;
		try {
			url = new File(readpath).toURI().toURL();
			try {
			    //objImporter.read(url);      
			    MeshView[] nodes = null;//objImporter.getImport();
					try {
						
						File f = new File(outfilename);
					    FileWriter fw = new FileWriter(f);
						
						for(int i = 0; i < nodes.length; i++) {
							MeshView m = nodes[i];
							TriangleMesh innerMesh = ((TriangleMesh)(m).getMesh());
						
							ObservableFloatArray verts = innerMesh.getPoints();
							ObservableFloatArray texs = innerMesh.getTexCoords();
							ObservableFaceArray faces = innerMesh.getFaces();
							
							writeSection("v", verts, fw);
							writeSection("t", texs, fw);
							writeSection("f", faces, fw);
							
							if(i < nodes.length) {
								fw.write("=\n");	
							}
						}
						
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}catch (Exception e) {
			    System.out.println("File Not Found.");
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}
	private static void writeSection(String header, ObservableFloatArray a, FileWriter w) {
		try {
			w.write(header);
			for(int k = 0; k < a.size(); k++) {
				w.write(" " + a.get(k));
			}
			w.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void writeSection(String header, ObservableIntegerArray a, FileWriter w) {
		try {
			w.write(header);
			for(int k = 0; k < a.size(); k++) {
				w.write(" " + a.get(k));
			}
			w.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
