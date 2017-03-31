package objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GObject {

	protected boolean selected;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public GObject(int x, int y, int width, int height) {
		this.selected = false;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean pointerHit(int pointerX, int pointerY) {
		// TODO: Implement this method.
		if(pointerX >= this.x && pointerX <= this.x + width && pointerY <= this.y + height && pointerY >= this.y ){
			return true;
		}
		return false;
	}
	
	public void selected() {
		// TODO: Implement this method.
		selected = true;
	}
	
	public void deselected() {
		// TODO: Implement this method.
		selected = false;
	}
	
	public void move(int dX, int dY) { // fix smooth motion
		// TODO: Implement this method.
			this.x = dX;
			this.y = dY;
	}

	public final void paint(Graphics g) {
		/* Example of template method pattern */
		paintObject(g);
		paintRegion(g);
		paintLabel(g);
	}

	public void paintRegion(Graphics g) {
		/* Set color */
		Color color = selected ? Color.red : Color.black;
		g.setColor(color);
		/* Set size */
		int size = selected ? 3 : 1;
		/* Draw a region */
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(size));
		g2d.drawRect(x, y, width, height);
	}

	public abstract void paintObject(Graphics g);

	public abstract void paintLabel(Graphics g);
	
}
