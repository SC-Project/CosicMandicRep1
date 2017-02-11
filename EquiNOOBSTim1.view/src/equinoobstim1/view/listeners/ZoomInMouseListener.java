package equinoobstim1.view.listeners;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import equinoobstim1.view.EquiZoomManager;

@SuppressWarnings("restriction")
public class ZoomInMouseListener implements MouseListener {
	
	private EquiZoomManager zoomManager = null;

	public ZoomInMouseListener(EquiZoomManager zoomManager) {
		this.zoomManager = zoomManager;
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		zoomManager.zoomIn();
	}

}
