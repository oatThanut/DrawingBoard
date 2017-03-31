package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import objects.*;

public class DrawingBoard extends JPanel {

	private MouseAdapter mouseAdapter; 
	private List<GObject> gObjects;
	private GObject target;
	
	private int gridSize = 10;
	
	public DrawingBoard() {
		gObjects = new ArrayList<GObject>();
		mouseAdapter = new MAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setPreferredSize(new Dimension(800, 600));
	}
	
	public void addGObject(GObject gObject) {
		// TODO: Implement this method.
		gObjects.add(gObject);
		repaint();
	}
	
	public void groupAll() {
		// TODO: Implement this method.
        CompositeGObject group = new CompositeGObject();
        for(GObject a : gObjects) {
        	if(a instanceof CompositeGObject){

			}
            group.add(a);
        }
        group.recalculateRegion();
        repaint();
        clear();
        gObjects.add(group);
	}

	public void deleteSelected() {
		// TODO: Implement this method.

        gObjects.remove(target);
        target = null;
        repaint();
	}
	
	public void clear() {
		// TODO: Implement this method.
        gObjects.clear();
        repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g);
		paintGrids(g);
		paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void paintGrids(Graphics g) {
		g.setColor(Color.lightGray);
		int gridCountX = getWidth() / gridSize;
		int gridCountY = getHeight() / gridSize;
		for (int i = 0; i < gridCountX; i++) {
			g.drawLine(gridSize * i, 0, gridSize * i, getHeight());
		}
		for (int i = 0; i < gridCountY; i++) {
			g.drawLine(0, gridSize * i, getWidth(), gridSize * i);
		}
	}

	private void paintObjects(Graphics g) {
		for (GObject go : gObjects) {
			go.paint(g);
		}
	}

	class MAdapter extends MouseAdapter {

		// TODO: You need some variables here
        int x = 0;
        int y = 0;
		
		private void deselectAll() {
			// TODO: Implement this method.
            for(GObject a : gObjects) {
                a.deselected();
            }
            repaint();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO: Implement this method.
            x = e.getX();
            y = e.getY();
            deselectAll();
            for(int i = gObjects.size() -1 ; i >= 0 ; i--) {
                if (gObjects.get(i).pointerHit(x, y)) {
                    target = gObjects.get(i);
                    target.selected();
                    break;

                }
            }
            repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO: Implement this method.
            if(target != null) {
                target.move(e.getX(), e.getY());
            }
                repaint();

		}
	}
	
}