/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

/**
 * @author nbhusare
 *
 */
public interface IResultConnection{

	String NAME_PREFIX = "Name : ";
	
	String DEFAULT_NAME = "success";
	
	String TYPE_PREFIX = "Type :";
	
	String DEFAULT_TYPE = "dispatcher";
	
	String NAME_CHANGED = "Name Changed";
	
	String TYPE_CHANGED = "Type Changed";
	
	void setName(String name);
	
	String getName();
	
	void setType(String type);
	
	String getType();
}
