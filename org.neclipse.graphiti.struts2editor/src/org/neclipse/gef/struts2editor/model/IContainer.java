/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import java.util.List;

/**
 * @author nbhusare
 * 
 */
public interface IContainer extends IEntity {

	String ADD_CHILD = "Add Child";
	
	String REMOVE_CHILD = "Remove Child";
	
	boolean isEmpty();

	boolean containsChild(IEntity child);

	List<IEntity> getChildren();

	boolean removeChild(IEntity child);

	boolean addChild(IEntity child);
	
	boolean addChild(IEntity child, int index);
}
