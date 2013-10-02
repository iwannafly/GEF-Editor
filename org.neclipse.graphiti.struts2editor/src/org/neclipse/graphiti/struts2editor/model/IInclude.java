/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

/**
 * @author nbhusare
 *
 */
public interface IInclude extends IDiagramChild {
	
	String PATH_PREFIX = "Path : ";
	
	String DEFAULT_PATH_VALUE = "Test2.struts";
	
	String PATH_CHANGED = "Path Changed";
	
	String getPath();
	
	void setPath(String path);
}
