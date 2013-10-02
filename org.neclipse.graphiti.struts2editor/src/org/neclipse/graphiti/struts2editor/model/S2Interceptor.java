/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

import org.eclipse.core.runtime.Assert;


/**
 * @author nbhusare
 *
 */
public class S2Interceptor extends S2AbstractEntity implements IInterceptor {

	/* Include attributes */
	private IEntity parent;
	private String intClass;
	
	public S2Interceptor() {
		setName(DEFAULT_NAME);
		setIntClass(DEFAULT_CLASS);
	}
	
	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.IDiagramChild#getParent()
	 */
	@Override
	public IEntity getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.Reparentable#setParent(java.lang.Object)
	 */
	@Override
	public void setParent(IEntity parent) {
		Assert.isNotNull(parent, "Parent for an Interceptor cannot be null");
		this.parent = parent;
	}

	@Override
	public void setName(String name) {
		Assert.isNotNull(name, "Interceptor Name Cannot be null");
		super.setName(NAME_PREFIX + name);
	}
	
	@Override
	public void setIntClass(String intClass) {
		Assert.isNotNull(intClass, "Interceptor Class cannot be null");
		this.intClass = CLASS_PREFIX + intClass;
		firePropertyChange(IInterceptor.CLASS_CHANGED, null, this.intClass);
	}
	
	@Override
	public String getIntClass() {
		return intClass;
	}
	
	@Override
	public Object cloneEntity(){
		return new S2Interceptor();
	}

}
