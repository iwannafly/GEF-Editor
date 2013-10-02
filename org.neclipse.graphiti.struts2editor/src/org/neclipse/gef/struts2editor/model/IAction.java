/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

/**
 * Interface representing the Struts 2 Action Class. Actions in Struts 2 have
 * name, class and method attributes, out of which Name is a mandatory
 * attribute.
 * 
 * @author nbhusare
 * @version 0.1
 */
public interface IAction extends IPackageChild {
	
	String NAME_PREFIX = "Name: ";
	
	String DEFAULT_NAME = "Foo";
	
	String CLASS_PREFIX = "Class: ";
	
	String DEFAULT_CLASS = "com.foo.Bar";
	
	String METHOD_PREFIX = "Method: ";
	
	String DEFAULT_METHOD = "execute";
	
	String CLASS_CHANGED = "Class Changed";
	
	String METHOD_CHANGED = "Method Changes";
	
	/**
	 * Return the Class attribute of the Action.
	 * @return the Class attribute of the Action.
	 */
	String getActionClass();
	
	/**
	 * Set the Class attribute of the Action.
	 * @param aClass the Class attribute of the Action.	
	 */
	void setActionClass(String aClass);
	
	/**
	 * Return the Method attribute of the Action.
	 * @return	the Method attribute of the Action.
	 */
	String getMethod();
	
	/**
	 * Set the Method attribute of the Action.
	 * @param method the Method attribute of the Action.	
	 */
	void setMethod(String method);
}
