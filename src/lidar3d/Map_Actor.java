package lidar3d;

import engine.Actor_3d;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Scale;

public class Map_Actor extends Actor_3d{
    public Map_Actor(String p, double scale){
        path = header + p;
        this.scale = scale;
    }

	@Override
	public PhongMaterial getMat() {
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseMap(new Image(getClass().getResourceAsStream("/lidar3d/textures/blankhires.png")));
		return mat;
	}
	
	
	@Override
	public DrawMode getDrawMode() {
    	return DrawMode.LINE;
    }
	
	@Override
	public Color getRayColor() {
    	return Color.CORAL;
    }

}
