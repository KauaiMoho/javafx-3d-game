package lidar3d;

import engine.Actor_3d;
import javafx.scene.paint.Color;

public class Page extends Actor_3d{
	

	private int pageNum;
	private boolean isShowing = false;
	private UI ui;
	
	public Page(int number, double scale, UI u) {
		this.scale = scale;
		ui = u;
		path = header+"page.txt"; //could have used a box but this is simpler
		pageNum = number;
	}
	
	@Override
	public Color getRayColor() {
		return Color.SADDLEBROWN;
	}
	
	public boolean getShowing() {
		return isShowing;
	}
	
	public void setShowing(boolean b) {
		isShowing = b;
		if(b) {
			ui.showPage(pageNum);
		}else {
			ui.removeCurrPage();
		}
	}
}
