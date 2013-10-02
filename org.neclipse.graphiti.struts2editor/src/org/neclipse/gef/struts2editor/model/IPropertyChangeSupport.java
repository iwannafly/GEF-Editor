/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import java.beans.PropertyChangeListener;

/**
 * @author nbhusare
 *
 */
public interface IPropertyChangeSupport {
	
	void addPropertyChangeListener(PropertyChangeListener listener);
	
	boolean removePropertyChangeListener(PropertyChangeListener listener);
	
	void firePropertyChange(String property, Object oldValue, Object newValue);
}
