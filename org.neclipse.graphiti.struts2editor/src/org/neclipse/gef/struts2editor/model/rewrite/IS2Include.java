/**
 * 
 */
package org.neclipse.gef.struts2editor.model.rewrite;

/**
 * @author nbhusare
 *
 */
public interface IS2Include extends IS2Model {
	
	/**
	 * Return the path of the included file.
	 * 
	 * @return the path of the included file.
	 */
	String getPath();
	
	/**
	 * Set the path to the file include file.
	 * 
	 * @param path the path to the file include file.
	 */
	void setPath(String path);
}
