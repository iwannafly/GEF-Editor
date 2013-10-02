/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author nbhusare
 *
 */
public abstract class S2AbstractEntity implements IEntity {
	
	private Long id;
	protected String name;
	private Rectangle dimension;
	private transient PropertyChangeSupport changeDeligate = new PropertyChangeSupport(this);
	
	/* (non-Javadoc)
	 * @see org.rssclient.core.model.IEntity#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		Assert.isNotNull(id, "id cannot be null");
		if (id.equals(this.id)) return;
		
		if (this.id != null) throw new IllegalStateException("Cannot change id after it's been set");
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see org.rssclient.core.model.IEntity#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see org.rssclient.core.model.INamedEntity#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		Assert.isNotNull(name, "Name cannot be null");
		this.name = name;
		firePropertyChange(INamedEntity.NAME_CHANGE, null, this.name);
	}

	/* (non-Javadoc)
	 * @see org.rssclient.core.model.INamedEntity#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}
	
	@Override
	public void setDimension(Rectangle dimension) {
		Assert.isNotNull(dimension, "Dimentions cannot be null");
		this.dimension = dimension;
		firePropertyChange(IConstraintEnity.DIMENSION_CHANGE, null, dimension);
	}
	
	@Override
	public Rectangle getDimension() {
		return dimension;
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException();
		}
		changeDeligate.addPropertyChangeListener(listener);
	}
	
	@Override
	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listener != null) {
			changeDeligate.removePropertyChangeListener(listener);
			return true;
		}
		return false;
	}
	
	@Override
	public void firePropertyChange(String property, Object oldValue,
			Object newValue) {
		if (changeDeligate.hasListeners(property)) {
			changeDeligate.firePropertyChange(property, oldValue, newValue);
		}
	}
	
	/**
	 * Deserialization constructor. Initializes transient fields.
	 * 
	 * @see java.io.Serializable
	 */
	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		changeDeligate = new PropertyChangeSupport(this);
	}
}
