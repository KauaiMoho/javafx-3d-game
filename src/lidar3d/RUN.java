/*
Name:       Karma Luitel
Date:       May 2, 2023
Period:     1

Is this lab fully working?  (Yes/No) - Yes
If not, explain:
*/
package lidar3d;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RUN extends Application{
    
	double scale = 50;
	boolean godmode = true;
	
	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Kenophobia");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1920, 1080, Color.BLACK);
		TitleScreen t = new TitleScreen(scene, scale, godmode);
		root.setCenter(t);
		
		stage.setScene(scene);
		stage.show();
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
