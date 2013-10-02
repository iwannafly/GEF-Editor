/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;

/**
 * @author nbhusare
 *
 */
public abstract class S2ConnectionFigure extends PolylineConnection {
	
	/**
	 * Returns the label at a particular location on the figure.
	 * @param location	the location on the figure
	 * @return	the label at a particular location on the figure.
	 */
	public abstract Label getLabelAt(Point location);
	
	/**
	 * Checks if the hit location lies on the passed label.
	 * @param label the label on which the hit location is to be verified.
	 * @param hitLocation the location where the mouse is hit.
	 * @return the label if the hit location lies on it.
	 */
	protected boolean isLabelEdited(Label label, Point hitLocation) {
		/* Check if the hit location lies on the passed label */
		Point copy = hitLocation.getCopy();
		label.translateToRelative(copy);
		if (label.containsPoint(copy)) return true;
		return false;
	}
}	
