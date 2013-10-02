/**
 * 
 */
package org.neclipse.gef.struts2editor.model;


/**
 * Interface representing the Struts 2 Constant entity.
 * 
 * @author nbhusare
 */
public interface IJSPHTML extends IDiagramChild {
	
	String PATH_CHANGE = "Path Change";
	
	String PATH_PREFIX = "Path: ";
	
	String DEFAULT_PATH = "myProject/webroot/MyJSP.jsp";
	
	/**
	 * Set the path of the Constant. 
	 * @param path the path of the Constant.
	 */
	void setPath(String path);
	
	/**
	 * Return the path of the constant.
	 * @return the path of the constant.
	 */
	String getPath();
	
}
