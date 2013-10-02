/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

import java.util.List;

/**
 * @author nbhusare
 *
 */
public interface IDiagram extends IEntity {
	
	List<IPackage> getPackages();
	
	void addPackag(IPackage sPackage);
	
	List<IBean> getBeans();
	
	void setBeans(IBean bean);
	
	List<IInclude> getIncludes();
	
	void setInclude(IInclude include);
	
	List<IConstant> getConstants();
	
	void setConstant(IConstant constant);
	
	void setGridEnabled(boolean value);
	
	boolean isGridEnabled();
	
	void setSnapToGepmetry(boolean value);
	
	boolean isSnapToGeometry();
}
