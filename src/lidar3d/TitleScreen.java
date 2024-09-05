package lidar3d;

import java.io.File;


import engine.World_3d;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TitleScreen extends VBox{
	
	World_3d mainWorld;
	double scale;
	Scene scene;
	MediaPlayer bgPlayer;
	
	public TitleScreen(Scene sc, double s, boolean godmode) {
		super(100);
		scale = s;
		scene = sc;
		
		String path = getClass().getClassLoader().getResource("lidar3d/textures/blurred.png").toString();
		Image image = new Image(path);
		ImageView start = new ImageView(image);
		start.setOnMouseClicked(e -> {
			bgPlayer.stop();
			lidar3d_World mWorld = new lidar3d_World(new Player_3d(scale), scene, scale, godmode);
			mWorld.setTitleScreen(getThis());
			((BorderPane)scene.getRoot()).setCenter(mWorld);
			scene.setCursor(Cursor.NONE);
		});

		// start.addListener(start.hoverProperty());
		path = getClass().getClassLoader().getResource("lidar3d/textures/lidarMain.png").toString();
		Image title = new Image(path);
		setBackground(new Background(new BackgroundImage(title, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null)));

		path = getClass().getClassLoader().getResource("lidar3d/textures/titleText.png").toString();
		Image titleText = new Image(path);
		ImageView text = new ImageView(titleText);
		

		setAlignment(Pos.CENTER);
		HBox titleBox = new HBox(0);
		titleBox.setAlignment(Pos.CENTER);
		HBox menu = new HBox(scale * 5);
		menu.setAlignment(Pos.CENTER);
		
		titleBox.getChildren().add(text);
		menu.getChildren().addAll(start);
		
		
		getChildren().addAll(titleBox, menu);
		
		try {
            Media longMusic = new Media(getClass().getClassLoader().getResource("lidar3d/sounds/titleBg.mp3").toURI().toString());
            bgPlayer =  new MediaPlayer(longMusic);
            bgPlayer.setCycleCount(Integer.MAX_VALUE);
            bgPlayer.setStopTime(Duration.ZERO);
            bgPlayer.setStartTime(Duration.ZERO);
            bgPlayer.play();
        } catch(Exception e) {
        	System.out.println(e);
        }
	}

	public TitleScreen getThis(){
		return this;
	}
}
