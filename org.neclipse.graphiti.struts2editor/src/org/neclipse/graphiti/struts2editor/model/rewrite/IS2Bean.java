/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model.rewrite;

/**
 * Interface representing the Struts 2 Bean model element.
 * 
 * @author nbhusare
 *
 */
public interface IS2Bean extends IS2Model {
	
	/**
	 * Return the Class attribute of the model.
	 * 
	 * @return the Class attribute of the model.
	 */
	String getBClass();

	/**
	 * Set the Class attribute of the model.
	 * 
	 * @param Class the class attribute of the model.
	 */
	void setBClass(String cls);

	
	/**
	 * Return the Type attribute of the model.
	 * 
	 * @return the Type attribute of the model.
	 */
	String getBType();
	
	/**
	 * Set the Type attribute of the model.
	 * 
	 * @param typ the Type attribute of the model.
	 */
	void setBType(String typ);
	
	/**
	 * Return the value of the static attribute of the model. 
	 * 
	 * @return the value of the static attribute of the model.
	 */
	boolean isStatic();
	
	/**
	 * Set the value of the static attribute of the model.
	 * 
	 * @param stk the value of the static attribute of the model.
	 */
	void setStatic(boolean stk);
	
	/**
	 * Return the value of the Optional attribute of the model. 
	 * 
	 * @return the value of the Optional attribute of the model.
	 */
	boolean isOptional();
	
	/**
	 * Set the value of the Optional attribute of the model.
	 * 
	 * @param opt the value of the Optional attribute of the model.
	 */
	void setOptional(boolean opt);
	
	/**
	 * Return the value of the scope attribute of the model.
	 * 
	 * @return the value of the scope attribute of the model.
	 */
	String getScope();
	
	/**
	 * Set the value of the Scope attribute of the model.
	 * 
	 * @param scope the value of the Scope attribute of the model.
	 */
	void setScope(String scope);
}
