package org.simplestruts2.core.model.old;

import java.util.List;

/**
 * Diagram stores a number of packages, includes, JSP/HTML Pages...
 * 
 * @author nbhusare
 *
 */
public interface IDiagram extends IModelEntity, IContainer {
	
	/**
	 * Get a list of packages contained in the diagram.
	 * 
	 * @return list of packages contained in the diagram.
	 */
	List<IPackage> getPackages();
}
