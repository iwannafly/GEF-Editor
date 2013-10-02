/**
 * 
 */
package org.neclipse.gef.struts2editor.model;


/**
 * Interface representing the Struts 2 Constant entity.
 * 
 * @author nbhusare
 */
public interface IConstant extends IDiagramChild {
	
	String NAME_CHANGE = "Name Change";
	
	String VALUE_CHANGE = "Value Change";
	
	String NAME_PREFIX = "Name: ";
	
	String DEFAULT_NAME = "struts.devMode";
	
	String VALUE_PREFIX = "Value: ";
	
	String DEFAULT_VALUE = "true";
	
	/**
	 * Set the value of the Constant. 
	 * @param value the value of the Constant.
	 */
	void setValue(String value);
	
	/**
	 * Return the value of the constant.
	 * @return the value of the constant.
	 */
	String getValue();
	
}
