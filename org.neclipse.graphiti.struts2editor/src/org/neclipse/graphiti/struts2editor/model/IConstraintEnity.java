/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author nbhusare
 *
 */
public interface IConstraintEnity {
	
	String DIMENSION_CHANGE = "Dimension Change";
	
	void setDimension(Rectangle dimension);
	
	Rectangle getDimension();
}
