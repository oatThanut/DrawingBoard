package objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		// TODO: Implement this method.
		gObjects.add(gObject);
	}

	public void remove(GObject gObject) {
		// TODO: Implement this method.
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		// TODO: Implement this method.
        super.move(dX, dY);
		for(GObject m : this.gObjects){
            m.move(dX, dY);
		}

	}

	public void recalculateRegion() {  //Debug
		// TODO: Implement this method.
		// cal new height, width, x, y
		int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE, width = 0, height = 0;

		for(GObject a : gObjects) {
            if(maxX < a.x) {
                maxX = a.x;
                height = a.height;
            }
            if(maxY < a.y) {
                maxY = a.y;
                width = a.width;
            }
			minX = Math.min(minX, a.x);
			minY = Math.min(minY, a.y);
		}
		super.x = minX;
		super.y = minY;
		super.width = maxX - minX + width;
		super.height = maxY - minY + height;

	}

	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
		for(GObject a : gObjects) {
			a.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		g.drawString("Grouped", x, y+height+15);
	}

}
