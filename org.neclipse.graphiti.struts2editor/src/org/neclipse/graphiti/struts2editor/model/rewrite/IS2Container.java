/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model.rewrite;

import java.util.List;

/**
 * Interface representing the Container Model element. 
 * 
 * @author nbhusare
 */
public interface IS2Container extends IS2Model {

	/**
	 * Return all the children of this container model element.
	 * 
	 * @return all the children of this container model element.
	 */
	List<IS2Model> getChildren();
	
	/**
	 * Add a list of children in this model element.
	 * 
	 * @param children the children that are to be added to the container model element.   
	 */
	void setChildren(List<IS2Model> children);
	
	/**
	 * Add a <code>IS2Element</code> to the children list.
	 * 
	 * @param child a <code>IS2Element</code> to the children list.
	 */
	void addChild(IS2Model child);

	/**
	 * Remove a child <code>IS2Element</code> from the container.
	 * @param child the child to be removed.
	 * @return true of the child is successfully removed.
	 */
	boolean removeChild(IS2Model child);
}
