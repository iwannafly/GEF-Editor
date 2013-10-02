package org.simplestruts2.core.model.old;

import org.eclipse.draw2d.geometry.Dimension;

/**
 * The base interface of the IDiagram children. Its basically a named entity.
 * 
 * @see IEntity
 */
public interface IModelEntity extends IEntity {
	
	/**
	 * Returns the name of the child.
	 * 
	 * @return the name of this child.
	 */
	String getName();
	
	/**
	 * Sets the name of the child.
	 * 
	 * @param name the name of the child.
	 */
	void setName(String name);
	
	/**
	 * Returns the parent of the child.
	 * 
	 * @return the parent of the child.
	 */
	IModelEntity getParent();

	/**
	 * Returns the <code>Dimension</code> of the child.
	 * 
	 * @return the <code>Dimension</code> of the child.
	 */
	Dimension getDimension();

	/**
	 * Sets the <code>Dimension</code> of the child.
	 * 
	 * @param dimension the <code>Dimension</code> of the child.
	 */
	void setDimension(Dimension dimension);
}
