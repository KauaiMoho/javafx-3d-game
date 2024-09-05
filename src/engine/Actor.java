package engine;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView{
	
	public abstract void act(long now);
	
	public void addedToWorld() {
		
	}
	
	public double getHeight() {
		return getBoundsInParent().getHeight();
	}
	
	public double getWidth() {
		return getBoundsInParent().getWidth();
	}
	
	public <A extends Actor> List<A> getIntersectingObjects(java.lang.Class<A> cls){
		List<A> r = new ArrayList<A>();
		List<A> worldObjs =  ((World)(getParent())).getObjects(cls);
		for(int i = 0; i < worldObjs.size(); i++) {
			if(this.intersects(this.parentToLocal(worldObjs.get(i).getBoundsInParent())) && !worldObjs.get(i).equals(this)) {
				r.add(worldObjs.get(i));
			}
		}
		return r;
	}
	
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		List<A> objs = getIntersectingObjects(cls);
		return objs.size() > 0 ? objs.get(0) : null;
		
	}
	
	public World getWorld() {
		return (World)(getParent());
	}
	
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
}
