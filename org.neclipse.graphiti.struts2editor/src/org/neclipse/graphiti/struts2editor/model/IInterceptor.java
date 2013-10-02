/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

/**
 * @author nbhusare
 *
 */
public interface IInterceptor extends IInterceptorsChild{
	
	String NAME_PREFIX = "Name : ";
	
	String DEFAULT_NAME = "security";
	
	String CLASS_PREFIX = "Class : ";
	
	String DEFAULT_CLASS = "com.company.security.SecurityInterceptor";

	String CLASS_CHANGED = "Class Changed";
	
	/**
	 * Return the Class attribute of the Interceptor.
	 * @return the Class attribute of the Interceptor.
	 */
	String getIntClass();
	
	/**
	 * Set the Class attribute of the Interceptor.
	 * @param aClass the Class attribute of the Interceptor.	
	 */
	void setIntClass(String intClass);
}
