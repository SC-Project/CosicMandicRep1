package equinoobstim1.view.listeners;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.zest.core.widgets.Graph;

import equinoobstim1.view.EquiZoomManager;

@SuppressWarnings("restriction")
public class ZoomWheelListener implements MouseWheelListener{

	private EquiZoomManager zoomManager = null;
	Graph graph = null;
	Point point = null;
	
	public ZoomWheelListener(EquiZoomManager zoomManager, Graph graph) {
		this.zoomManager = zoomManager;
		this.graph = graph;
	}

	@Override
	public void mouseScrolled(MouseEvent e) {
		if ((e.stateMask & SWT.CTRL) == 0)
			return;

		if (e.count > 0) {
			if(!zoomManager.canZoomIn())
				return; 
			double x = zoomManager.getViewport().getSize().width;
			double y = zoomManager.getViewport().getSize().height;
			double zoomlvl = zoomManager.getZoom();
			Rectangle rect = new Rectangle((int)(e.x - x/2), (int)(e.y-y/2),(int)(x/10), (int)(y/10));
			zoomManager.performAnimatedZoom(rect, true, 15);
		} else if (e.count < 0) {
			zoomManager.zoomOut();
			
		}
	}
}