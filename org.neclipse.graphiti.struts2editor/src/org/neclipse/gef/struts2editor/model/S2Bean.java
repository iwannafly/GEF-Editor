/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import org.eclipse.core.runtime.Assert;


/**
 * @author nbhusare
 *
 */
public class S2Bean extends S2AbstractEntity implements IBean {

	/* Bean attributes */
	private IEntity parent;
	private String beanClass;
	private String beanType;
	private String sstatic;
	private String soptional;
	private String sscope;
	
	public S2Bean() {
		/* Set the default class value for the Bean */
		setBeanClass(DEFAULT_CLASS_NAME);
		setType(DEFAULT_TYPE);
		setName(DEFAULT_NAME);
		setScope(DEFAULT_SCOPE);
		setStatic(DEFAULT_STATIC_VALUE);
		setoptional(DEFAULT_OPTIONAL_VALUE);
	}
	
	@Override
	public IEntity getParent() {
		return parent;
	}

	@Override
	public void setParent(IEntity parent) {
		Assert.isNotNull(parent, "Parent for a Bean cannot be null");
		this.parent = parent;
	}

	@Override
	public String getBeanClass() {
		return beanClass;
	}

	@Override
	public String getType() {
		return beanType;
	}

	@Override
	public String getStatic() {
		return sstatic;
	}

	@Override
	public String getOptional() {
		return soptional;
	}

	@Override
	public String getScope() {
		return sscope;
	}
	
	@Override
	public void setBeanClass(String beanClass) {
		Assert.isNotNull(beanClass, "Bean Class cannot be null");
		this.beanClass = beanClass;
		firePropertyChange(IBean.CLASS_CHANGED, null, this.beanClass);
	}

	@Override
	public void setType(String beanType) {
		Assert.isNotNull(beanType, "Bean Type cannot be null");
		this.beanType = beanType;
		firePropertyChange(IBean.TYPE_CHANGED, null, this.beanType);
	}

	@Override
	public void setStatic(String sstatic) {
		Assert.isNotNull(sstatic, "Bean Type cannot be null");
		this.sstatic = sstatic;
		firePropertyChange(IBean.STATIC_CHANGED, null, this.sstatic);
	}

	@Override
	public void setoptional(String soptional) {
		Assert.isNotNull(soptional, "Bean Optional field cannot be null");
		this.soptional= soptional;
		firePropertyChange(IBean.OPTIONAL_CHANGED, null, this.soptional);
	}
	
	@Override
	public void setScope(String scope) {
		Assert.isNotNull(scope, "Bean Scope field cannot be null");
		this.sscope = scope;
		firePropertyChange(IBean.SCOPE_CHANGED, null, this.sscope);
	}

	@Override
	public void setName(String name) {
		Assert.isNotNull(name, "Bean Name field cannot be null");
		this.name = name;
		firePropertyChange(IBean.NAME_CHANGED, null, this.name);
	}
	
	@Override
	public Object cloneEntity() {
		// TODO Auto-generated method stub
		return null;
	}
}
