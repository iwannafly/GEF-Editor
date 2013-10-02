/**
 * 
 */
package org.simplestruts2.core.model.old;

import java.util.List;

/**
 * The interface representing the container object.
 * 
 * @author nbhusare
 */
public interface IContainer {

	/**
	 * 
	 * @return {@code true} if the folder has no children. otherwise, it returns
	 *         {@code} false.
	 */
	public boolean isEmpty();

	/**
	 * Returns {@code true} if the folder contains a child and {@code false}
	 * otherwise.
	 * 
	 * @param child
	 *            element whose presence has to be tested.
	 * @return {@code true} if the folder contains a child and {@code false}
	 *         otherwise.
	 */
	public boolean containsChild(IModelEntity child);

	/**
	 * Get the list if children contained in the container. Typically these
	 * children will be of type IPackage, IAction or IDiagramChildren.
	 * 
	 * @return a list of children contains in this container.
	 */
	List<IModelEntity> getChildren();

	/**
	 * If there is an instance of <code>IDiagramChild</code> that is equal to
	 * <code>child</code> in the list of children, removes it and returns
	 * <code>true</code>. Otherwise, returns <code>false</code>.
	 * 
	 * @param child an instance of <code>IDiagramChild</code> to be removed.
	 * @return <code>true</code> if the child is removed from children,
	 *         <code>false</code> otherwise.
	 */
	boolean removeChild(IModelEntity child);
	
	/**
	 * Adds a child to the container.
	 * 
	 * @param child
	 *            the child to be added to the container.
	 */
	void addChild(IModelEntity child);
}
