package lidar3d;

import engine.Actor_3d;
import engine.World_3d;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UI extends Actor_3d{
	private Label coords;
	private World_3d world;
	private ImageView laser;
	public int gunMode = 0;
    private ImageView currPage;
    private ImageView deathScreen;
    private ImageView keypad;
    private Text passText;
    private Button[] keypadButtons;
    private boolean isDeathScreenShowing = false;
    private boolean isKeypadShowing = false;
    private int curr = 0;
    public UI(World_3d world) {
        this.world = world;
    }

    @Override
    public void addedToWorld(){
        setPreferSize(1920, 1080);
        changeGunTexture(0);
        // showKeypad();
        
    }
    
    public void changeGunTexture(int mode) {
    	if(getChildren().contains(laser)) {
    		getChildren().remove(laser);
    	}
    	gunMode = mode;
    	String path;
    	if(mode == 0) {
    		path = getClass().getClassLoader().getResource("lidar3d/textures/tool_blank.png").toString();
    	}else if (mode == 1) {
    		path = getClass().getClassLoader().getResource("lidar3d/textures/tool_scanning.png").toString();
    	}else {
    		path = getClass().getClassLoader().getResource("lidar3d/textures/tool_error.png").toString();
    	}
    	Image laserImg = new Image(path);
        laser = new ImageView(laserImg);
        laser.setTranslateX(getGroupWidth()-laserImg.getWidth());
        laser.setTranslateY(getGroupHeight()-laserImg.getHeight());
        getChildren().add(laser);
        laser.toFront();
    }
    
    public void showPage(int i) {
    	String path = getClass().getClassLoader().getResource("lidar3d/textures/page" + i + ".png").toString();
    	Image img = new Image(path);
    	currPage = new ImageView(img);
    	currPage.setTranslateX(getGroupWidth()/2-img.getWidth()/2);
    	currPage.setTranslateY(getGroupHeight()/2-img.getHeight()/2);
    	getChildren().add(currPage);
    }
    
    public void removeCurrPage() {
    	getChildren().remove(currPage);
    }

    public void showKeypad(){
        isKeypadShowing = true;
    	String path = getClass().getClassLoader().getResource("lidar3d/textures/keypad_blank.png").toString();
    	Image img = new Image(path);
    	keypad = new ImageView(img);
    	keypad.setTranslateX(getGroupWidth()/2-img.getWidth()/2);
    	keypad.setTranslateY(getGroupHeight()/2-img.getHeight()/2);
        path = getClass().getClassLoader().getResource("lidar3d/textures/digital-7.ttf").toString();
        Font digitalFont = Font.loadFont(path, 100);
        passText = new Text(0, 0, ""); //6823453912
        passText.setFont(digitalFont);
        passText.setStroke(new Color(0,128.0/255,29.0/255, 1));
        passText.setFill(new Color(2.0/255,189.0/255,38.0/255,1));
        passText.setTranslateX(getGroupWidth()/2 - img.getWidth()*0.35);
        passText.setTranslateY(getGroupHeight()/2 - img.getHeight()*0.3);

        keypadButtons = new Button[12];
        for (int i = 0; i < keypadButtons.length; i++){
            int row = i / 3;
            int col = i % 3;
            path = getClass().getClassLoader().getResource("lidar3d/textures/button_" + (i + 1) + ".png").toString();
    	    Image buttonImg = new Image(path);
            ImageView image = new ImageView(buttonImg);
            keypadButtons[i] = new Button("", image);
            keypadButtons[i].setPadding(new Insets(0, 0, 0, 0));
            keypadButtons[i].setTranslateX(getGroupWidth()/2 - img.getWidth()*0.775/2 + col*img.getWidth()/6.2);
            keypadButtons[i].setTranslateY(getGroupHeight()/2 - img.getHeight()*0.417/2 + row*img.getHeight()/8.5);
            curr = i+1;
            keypadButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                int temp = curr;
                @Override
                public void handle(ActionEvent event) {
                    //System.out.println(temp); //6823453912
                    if (temp == 10){
                        getThis().removeKeypad();
                        ((lidar3d_World)(world)).exitedKeypad();
                    } else if (temp == 12){
                        if (passText.getText().equals("6823453912")){
                            getThis().removeKeypad();
                            ((lidar3d_World)(world)).enteredDoor();
                        }
                    } else if (passText.getText().length() < 10){
                        if (temp == 11)
                            passText.setText(passText.getText() + "0");
                        else 
                            passText.setText(passText.getText() + temp);
                    }
                }
            });
        }
    	getChildren().addAll(keypad, passText);
        for (int i = 0; i < keypadButtons.length; i++){
            getChildren().add(keypadButtons[i]);
        }
    }

    public void removeKeypad(){
        isKeypadShowing = false;
        getChildren().removeAll(keypad, passText);
        for (int i = 0; i < keypadButtons.length; i++){
            getChildren().remove(keypadButtons[i]);
        }
    }

    public UI getThis(){
        return this;
    }

    public boolean isKeypadShowing(){
        return isKeypadShowing;
    }
    
    public void addDeathScreen() {
    	isDeathScreenShowing = true;
    	String path = getClass().getClassLoader().getResource("lidar3d/textures/enemy.png").toString();
    	Image img = new Image(path);
    	deathScreen = new ImageView(img);
    	deathScreen.setFitWidth(getGroupWidth());
    	deathScreen.setFitHeight(getGroupHeight());
    	getChildren().add(deathScreen);
    }
    
    public void removeDeathScreen() {
    	isDeathScreenShowing = false;
    	getChildren().remove(deathScreen);
    }
    
    public boolean isDeathScreenShowing() {
    	return isDeathScreenShowing;
    }
    
    public void addCoordsLabel(Point3D p) {
    	coords = new Label("X: " + p.getX() + "Y: " + p.getY() + "Z: " + p.getZ());
    	coords.setTextFill(Color.MAGENTA);
    	coords.setTranslateX(coords.getBoundsInParent().getWidth()/2);
    	coords.setTranslateY(coords.getBoundsInParent().getHeight()/2);
    	world.addOutOfWorld(coords);
    }
    public void updateCoords(Point3D p) {
    	coords.setText("X: " + p.getX() + "Y: " + p.getY() + "Z: " + p.getZ());
    	coords.setTranslateX(coords.getBoundsInParent().getWidth()/2);
    	coords.setTranslateY(coords.getBoundsInParent().getHeight()/2);
    }

    public void drawLine(Point2D pt){
        Line line = new Line(1300, 700, pt.getX(), pt.getY());
        line.setStroke(Color.RED);
        line.setStrokeWidth(1);
        line.setSmooth(false);
        line.toBack();
        getChildren().add(line);
        AnimationTimer timer = new AnimationTimer() {
			long previousTime = 0;
            boolean first = false;
			@Override
			public void handle(long now) {
                if(now-previousTime >= (long)(1000)) {
                	previousTime = now;
                    if (first){
                    	getChildren().remove(line);
                        this.stop();
                    }
                    first = true;

                }
            }
        };
        timer.start();
    }
}
