/**
 * 
 */
package org.neclipse.gef.struts2editor.model.rewrite;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author nbhusare
 *
 */
public class S2Model implements IS2Model {

	/**
	 * Unknown model Type.
	 */
	public static final String MODEL_TYPE_UNKNOWN = "Unknown Model";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Common Model attributes.
	 */
	private int id;
	private String name;
	private IS2Model parent;
	
	private Point loc;
	private Rectangle dimension;
	
	private Object type = S2Model.MODEL_TYPE_UNKNOWN;
	
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	public S2Model() {}
	
	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#getID()
	 */
	@Override
	public int getID() {
		return id;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#setID(int)
	 */
	@Override
	public void setID(int id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#getType()
	 */
	@Override
	public Object getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#setType(java.lang.Object)
	 */
	@Override
	public void setType(Object type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#getLocation()
	 */
	@Override
	public Point getLocation() {
		return loc;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#setLocation(org.eclipse.draw2d.geometry.Point)
	 */
	@Override
	public void setLocation(Point loc) {
		this.loc = loc;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#getDimensions()
	 */
	@Override
	public Rectangle getDimension() {
		return dimension;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#setDimension(org.eclipse.draw2d.geometry.Rectangle)
	 */
	@Override
	public void setDimension(Rectangle dimension) {
		this.dimension = dimension;
		firePropertyChange(S2ModelCngConstants.DIMENSION_CHG, null, dimension);
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#getParent()
	 */
	@Override
	public IS2Model getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2Model#setParent(org.neclipse.gef.struts2editor.model.rewrite.IS2Model)
	 */
	@Override
	public void setParent(IS2Model parent) {
		this.parent = parent;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}

	@Override
	public boolean removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
		return true;
	}

	@Override
	public void firePropertyChange(String property, Object oldValue, Object newValue) {
		listeners.firePropertyChange(property, oldValue, newValue);
	}
}
