package equinoobstim1.view.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class RoundedRectangleBorder extends LineBorder {
	
	/**
	 * Width of the corner arc in logic points
	 */
	int arcWidth;

	/**
	 * Height of the corner arc in logic points
	 */	
	int arcHeight;	

	/**
	 * Creates an instance of this class with the given arc width and arc height in pixels
	 * @param arcWidth The width of the corner arc
	 * @param arcHeight The height of the corner arc
	 */
	public RoundedRectangleBorder(int arcWidth, int arcHeight) {
		super();
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;		
	}	
	
	/**
	 * Insets are defined by the line width.
	 * @see org.eclipse.draw2d.LineBorder#getInsets(org.eclipse.draw2d.IFigure)
	 */
	public Insets getInsets(IFigure figure) {
		return new Insets(getWidth());
	}	

	/**
	 * Gets the arc width.
	 * @return arc width
	 */
	public int getArcWidth() {
		return arcWidth;
	}

	/**
	 * Sets the arc width
	 * @param arcWidth 
	 */
	public void setArcWidth(int arcWidth) {
		this.arcWidth = arcWidth;
	}

	/**
	 * Gets the arc height.
	 * @return arc height
	 */	
	public int getArcHeight() {
		return arcHeight;
	}

	/**
	 * Sets the arc height
	 * @param arcHeight 
	 */
	public void setArcHeight(int arcHeight) {
		this.arcHeight = arcHeight;
	}

	/**
	 * Paints rounded rectangular border taking into account arcWidth, arcHeight, line width and line style
	 * 
	 * @see org.eclipse.draw2d.LineBorder#paint(org.eclipse.draw2d.IFigure, org.eclipse.draw2d.Graphics, org.eclipse.draw2d.geometry.Insets)
	 */
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		tempRect.setBounds(getPaintRectangle(figure, insets));
		// Shrink to accommodate for the line width
		tempRect.shrink(getWidth() / 2+3, getWidth() / 2+3);

		graphics.setLineWidth(4);
		graphics.setLineStyle(getStyle());
		// If the color for this border is specified, then use it. 
		// Otherwise, use the figure's foreground color
		if (getColor() != null) {
			graphics.setForegroundColor(getColor());
		} else {
			graphics.setForegroundColor(new Color(Display.getDefault(), 189, 96, 255));
		}
	
		graphics.drawRoundRectangle(tempRect, arcWidth, arcHeight);
	}	
}

