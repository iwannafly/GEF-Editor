/**
 * 
 */
package org.neclipse.gef.struts2editor.model;


/**
 * @author nbhusare
 * 
 */
public interface IDiagramChild extends IEntity, Reparentable<IEntity> {

	IEntity getParent();
	
}
