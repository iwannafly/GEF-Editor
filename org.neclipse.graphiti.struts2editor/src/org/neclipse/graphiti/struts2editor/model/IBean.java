/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

/**
 * The interface representing the Bean element in Struts 2
 * 
 * @author nbhusare
 * 
 */
public interface IBean extends IDiagramChild {

	/* Default values */
	String CLASS_PREFIX = "Class :";
	
	String DEFAULT_CLASS_NAME = "com.foo.MyBeanClass";
	
	String TYPE_PREFIX = "Type :";
	
	String DEFAULT_TYPE = "com.foo.MyBeanType";

	String NAME_PREFIX = "Name :";
	
	String DEFAULT_NAME = "MyBeanName";
	
	String SCOPE_PREFIX = "Scope :";
	
	String DEFAULT_SCOPE = "default";
	
	String STATIC_PREFIX = "Static :";
	
	String DEFAULT_STATIC_VALUE = "false";
	
	String OPTIONAL_PREFIX = "Optional :";
	
	String DEFAULT_OPTIONAL_VALUE = "false";
	
	/* Bean attributes change */
	String CLASS_CHANGED = "Class Changed";

	String TYPE_CHANGED = "Type Changed";

	String NAME_CHANGED = "Name Changed";

	String SCOPE_CHANGED = "Scope Changed";

	String STATIC_CHANGED = "Static Changed";

	String OPTIONAL_CHANGED = "Optional Changed";

	/**
	 * Return the name of the Bean class. The class attribute is mandatory.
	 * 
	 * @return the name of the Bean class.
	 */
	String getBeanClass();

	/**
	 * 
	 * @param beanClass
	 */
	void setBeanClass(String beanClass);

	/**
	 * Return the Primary interface that the Bean implements.
	 * 
	 * @return the Primary interface that the Bean implements.
	 */
	String getType();

	/**
	 * 
	 * @param beanType
	 */
	void setType(String beanType);

	/**
	 * Return the static value that indicates whether to inject the static
	 * methods or not.
	 * 
	 * @return the static value that indicates whether to inject the static
	 *         methods or not.
	 */
	String getStatic();

	/**
	 * 
	 * @param value
	 */
	void setStatic(String value);

	/**
	 * Return the value indicating whether the bean is optional or not.
	 * 
	 * @return Return the value indicating whether the bean is optional or not.
	 */
	String getOptional();

	/**
	 * 
	 * @param optional
	 */
	void setoptional(String optional);
		
	/**
	 * Set the scope attribute value.
	 * @param scope the scope attribute value.
	 */
	void setScope(String scope);
	
	/**
	 * Return the value of the scope attribute. 
	 * @return the value of the scope attribute.
	 */
	String getScope();
}
