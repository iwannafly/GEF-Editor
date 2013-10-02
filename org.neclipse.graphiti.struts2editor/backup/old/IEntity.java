package org.simplestruts2.core.model.old;

/**
 * Implementors of <code>IEntity</code> add a certain model-type to the
 * application. Any entity is <em>uniquely<em> identified by its ID.
 * Implementors have to make sure that no entity of any kind will have the same ID as another entity.
 * 
 */
public interface IEntity extends IPersistable {

	/**
	 * 
	 * @return
	 */
	Long getID();

	/**
	 * 
	 */
	void setID(Long id);

	//TODO: Think about adding properties here...
	
}
