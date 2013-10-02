/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import org.eclipse.core.runtime.Assert;


/**
 * @author nbhusare
 *
 */
public class S2Constant extends S2AbstractEntity implements IConstant {

	private IEntity parent;
	private String value;  
	
	public S2Constant() {
		setName(DEFAULT_NAME);
		setValue(DEFAULT_VALUE);
	}
	
	@Override
	public IEntity getParent() {
		return parent;
	}

	@Override
	public void setParent(IEntity parent) {
		Assert.isNotNull(parent, "Parent for a Constant cannot be null");
		this.parent = parent;
	}

	@Override
	public void setName(String name) {
		Assert.isNotNull(name, "Name of the Constant cannot be null");
		this.name = name;
		firePropertyChange(IConstant.NAME_CHANGE, null, name);
	}
	
	@Override
	public void setValue(String value) {
		Assert.isNotNull(value, "Value of the Constant cannot be null");
		this.value = value;
		firePropertyChange(IConstant.VALUE_CHANGE, null, value);
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public Object cloneEntity() {
		// TODO Auto-generated method stub
		return null;
	}
}
