/**
 * 
 */
package org.simplestruts2.core.model.old;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * @author nbhusare
 *
 */
public abstract class AbstractModelEntity implements IModelEntity{
	
	/* Attributes */
	private Long id;
	protected String name;
	private IModelEntity parent;
	private Dimension dimension;
	
	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IModelEntity#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IModelEntity#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		Assert.isNotNull(name, "The Entiry requires a Name that is not NULL"); //$NON-NLS-1$
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IModelEntity#getParent()
	 */
	@Override
	public IModelEntity getParent() {
		return parent;
	}

	/**
	 * Set the parent of the entity.  
	 * @param parent the parent of the entity.
	 */
	public void setParent(IModelEntity parent) {
		this.parent = parent;
	}
	
	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IModelEntity#getDimension()
	 */
	@Override
	public Dimension getDimension() {
		return dimension;
	}

	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IModelEntity#setDimension(org.eclipse.draw2d.geometry.Dimension)
	 */
	@Override
	public void setDimension(Dimension dimension) {
		Assert.isNotNull(dimension, "The Entiry requires a Dimensions that is not NULL"); //$NON-NLS-1$
		this.dimension = dimension;
	}
	
	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IEntity#getID()
	 */
	@Override
	public Long getID() {
		return id;
	}

	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IEntity#setID(java.lang.Long)
	 */
	@Override
	public void setID(Long id) {
		Assert.isNotNull(id, "The Entiry requires a ID that is not NULL"); //$NON-NLS-1$
		this.id = id;
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}
}
