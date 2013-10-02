/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model.rewrite;

/**
 * @author nbhusare
 *
 */
public interface IS2JSPHTML extends IS2Model, IS2ConnectableModel {
	
	/**
	 * Return the path to the JSP/HTML file.
	 * 
	 * @return the path to the JSP/HTML file.
	 */
	String getPath();
	
	/**
	 * Set the path to the JSP/HTML file.
	 * 
	 * @param path the path to the JSP/HTML file.
	 */
	void setPath(String path);
	
}
