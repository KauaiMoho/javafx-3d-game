package engine;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane{
	
	HashSet<KeyCode> keys;
	boolean stopped;
	AnimationTimer timer;
	
	int loopIdx = 0;
	double width = 0;
	double height = 0;
	boolean generated = false;
	
	public World() {
		keys = new HashSet<KeyCode>();
		stopped = true;
		
		widthProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				width = (double)newValue;
				if(width > 0 && height > 0 && !generated) {
					generated = true;
					onDimensionsInitialized();
				}
			}
		});
		
		heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				height = (double)newValue;
				if(width > 0 && height > 0 && !generated) {
					generated = true;
					onDimensionsInitialized();
				}
			}
		});
		
		setOnKeyPressed((k) -> {
			if(!keys.contains(k.getCode())) {
				//System.out.println("add");
				keys.add(k.getCode());
			}
		});
		
		setOnKeyReleased((k) -> {
			//System.out.println("remove");
			keys.remove(k.getCode());
		});
		
		sceneProperty().addListener(new ChangeListener<Scene>() {
			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if(newValue != null) {
					requestFocus();
				}
			}
		});
		
		timer = new AnimationTimer() {
			long previousTime = 0;
			@Override
			public void handle(long now) {
				if(now-previousTime >= (long)(10e2)) {
					act(now);
					for (loopIdx = 0; loopIdx < getChildren().size(); loopIdx++) {
						if (getChildren().get(loopIdx) instanceof Actor && ((Actor)(getChildren().get(loopIdx))).getWorld() != null) {
							((Actor)(getChildren().get(loopIdx))).act(now);
						}
					}
					previousTime = now;
				}
			}
		};
	}
	
	public abstract void act(long now);
	
	public abstract void onDimensionsInitialized();
	
	public <A extends Actor> List<A> getObjects(java.lang.Class<A> cls){
		List<A> r = new ArrayList<A>();
		for(int i = 0; i < getChildren().size(); i++) {
			if(cls.isInstance(getChildren().get(i))) {
				r.add(cls.cast(getChildren().get(i)));
			}
		}
		return r;
	}
	
	public <A extends Actor> List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls){
		List<A> r = new ArrayList<A>();
		for(int i = 0; i < getChildren().size(); i++) {
			Actor a = ((Actor)(getChildren().get(i)));
			if(cls.isInstance(getChildren().get(i)) && x >= (a.getX() - a.getWidth()/2) && x <= (a.getX() + a.getWidth()/2) && y >= (a.getY() - a.getHeight()/2) && y <= (a.getY() + a.getHeight()/2) ) {
				r.add(cls.cast(getChildren().get(i)));
			}
		}
		return r;
	}
	
	public boolean isKeyPressed(KeyCode code) {
//		System.out.println(keys);
		return keys.contains(code);
	}
	
	//bugged? "Note: Since the world is a Pane, you should get the list of children and add the actor to that list."
	public void add(Actor actor) {
		getChildren().add(actor);
		actor.addedToWorld();
	}
	
	public void remove(Actor actor) {
		if(!stopped) {
			loopIdx--;
		}
		getChildren().remove(actor);
		
	}
	
	public void start() {
		stopped = false;
		timer.start();
	}
	
	public void stop() {
		stopped = true;
		timer.stop();
	}
	
	public boolean isStopped() {
		return stopped;
	}
}
