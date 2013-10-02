/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

/**
 * 
 * @author nbhusare
 *
 */
public interface IInterceptorsChild extends IEntity, Reparentable<IEntity> {

	IEntity getParent();
}
