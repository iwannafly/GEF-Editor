/**
 * 
 */
package org.neclipse.gef.struts2editor.model.rewrite;

import java.util.List;

/**
 * Interface representing the Struts 2 Package Model element.
 * 
 * @author nbhusare
 *
 */
public interface IS2Package extends IS2ConnectableContainer {
	
	String ATTRIB_NAMESPACE = "namespace";
	String ATTRIB_ABSTRACT = "abstract";
	
	
	/**
	 * Return the Namespace attribute of the Package.
	 * 
	 * @return the Namespace attribute of the Package.
	 */
	String getNamespace();
	
	/**
	 * Set the Namespace attribute of the Package.
	 * 
	 * @param ns the Namespace attribute of the Package.
	 */
	void setNamespace(String ns);
	
	/**
	 * Return the Abstract attribute value of the Package.
	 * 
	 * @return the Abstract attribute value of the Package.
	 */
	boolean isAbstract();
	
	/**
	 * Set the Abstract attribute value of the Package. 
	 * 
	 * @param abs the Abstract attribute value of the Package.
	 */
	void setAbstract(boolean abs);
	
	/**
	 * Return the <code>IS2Model</code> extends list of this Package model.
	 * 
	 * @return the <code>IS2Model</code> extends list of this Package model.
	 */
	List<IS2Model> getExtends();
	
	/**
	 * Add the <code>IS2Model</code> model element in the extends list.
	 * 
	 * @param ext the <code>IS2Model</code> model element.
	 */
	void setExtends(IS2Model ext);
	
	/**
	 * Remove the <code>IS2Model</code> from the extends list.
	 * 
	 * @param ext the <code>IS2Model</code> model element.
	 */
	void removeExtends(IS2Model ext);
}
