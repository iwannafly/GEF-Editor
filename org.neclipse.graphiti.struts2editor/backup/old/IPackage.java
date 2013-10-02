/**
 * 
 */
package org.simplestruts2.core.model.old;

import java.util.List;


/**
 * Package stores a number of Actions. 
 * 
 * @author nbhusare
 *
 */
public interface IPackage extends IModelEntity, IContainer, Reparentabe<IDiagram>{
	
	/**
	 * Get a list of actions contained in the package.
	 * @return a list of actions contained in a package.
	 */
	List<IAction> getActions();
	
	/**
	 * Get a list of extended packages.
	 * 
	 * @return a list of extended packages.
	 */
	List<IPackage> getExtends();
	
	/**
	 * Get the namespace of with the package.
	 * 
	 * @return the namespace of the package.
	 */
	String getNamespace();
	
	/**
	 * Set the namespace of the package.
	 * 
	 * @param namespace the namespace of the package.
	 */
	void setNamespace(String namespace);
}