/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model.rewrite;

import java.io.Serializable;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.neclipse.graphiti.struts2editor.model.IPropertyChangeSupport;

/**
 * Interface representing a Model element.
 * 
 * @author nbhusare
 */
public interface IS2Model extends Serializable, IPropertyChangeSupport {

	/**
	 * Constant representing an Unknown element type.
	 */
	Object type = "Element_type_unknown";
	
	/**
	 * Constant for the model Name attribute. 
	 */
	String ATTRIB_NAME = "name";
	
	/**
	 * Return the ID of the Model element.
	 * 
	 * @return the ID of the Model element.
	 */
	int getID();
	
	/**
	 * Set the ID of the Model element.
	 *  
	 * @param id the ID of the Model element.
	 */
	void setID(int id);
	
	/**
	 * Return the type of the Model element.
	 * 
	 * @return the type of the Model element.
	 */
	Object getType();
	
	/**
	 * Set the type of the Model element.
	 * 
	 * @param type the type of the Model element.
	 */
	void setType(Object type);
	
	/**
	 * Return the name of the Model element.
	 * 
	 * @return the name of the Model element.
	 */
	String getName();
	
	/**
	 * Set the name of the Model element.
	 * 
	 * @param name the name of the Model element.
	 */
	void setName(String name);
	
	/**
	 * Return the location of the Model element.
	 * 
	 * @return the location of the Model element.
	 */
	Point getLocation();

	/**
	 * Set the location of the Model element.
	 * 
	 * @param loc the location of the Model element.
	 */
	void setLocation(Point loc);
	
	/**
	 * Return the Dimension of the Model element.
	 * 
	 * @return the Dimension of the Model element.
	 */
	Rectangle getDimension();
	
	/**
	 * Set the Dimension of the Model element.
	 * 
	 * @param dimension the Dimension of the Model element.
	 */
	void setDimension(Rectangle dimension);
	
	/**
	 * Return the parent of the Model element.
	 *  
	 * @return the parent of the Model element.
	 */
	IS2Model getParent();
	
	/**
	 * Set the Parent of the Model element.  
	 * 
	 * @param parent the Parent of the Model element.
	 */
	void setParent(IS2Model parent);
}
