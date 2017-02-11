package equinoobstim1.view.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;

public class NodeFigure extends ImageFigure {
	private String rezultat;

	public NodeFigure(Image image, String rezultat) {
		super(image);
		this.rezultat = rezultat;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (rezultat != null) {
			Font myFont = new Font(null,
					new FontData("Serif", 24, SWT.BOLD));
			g.setFont(myFont);
			g.setForegroundColor(org.eclipse.draw2d.ColorConstants.yellow);
			g.drawText(rezultat, super.getLocation().x + 40,
					super.getLocation().y + 40);
		}
	}

}