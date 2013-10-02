package org.simplestruts2.core.model.old;

/**
 * Objects that are <code>Reparentabe</code> provide a method to set the parent.
 * 
 * @author nbhusare
 * @param <T>
 */
public interface Reparentabe<T> {
	
	/**
	 * Set the new parent to the object. 
	 * @param parent the parent of the object to set
	 */
	public void setParent(T parent);
}
