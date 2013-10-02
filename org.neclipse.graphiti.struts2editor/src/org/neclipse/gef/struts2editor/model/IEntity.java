/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

/**
 * @author nbhusare
 * 
 */
public interface IEntity extends INamedEntity, IConstraintEnity, IPropertyChangeSupport, IPersistable, IClonable {

	void setId(Long id);

	Long getId();

}
