/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

/**
 * @author nbhusare
 *
 */
public class S2Diagram extends S2AbstractContainer implements IDiagram {

	private boolean gridEnabled = false;
	private boolean snapToGeometry = false;
	
	@Override
	public List<IPackage> getPackages() {
		List<IPackage> packages = new ArrayList<IPackage>();
		for (IEntity child : children) {
			if (child instanceof IPackage) {
				packages.add((IPackage) child);
			}
		}
		return packages;
	}

	@Override
	public void addPackag(IPackage sPackage) {
		Assert.isNotNull(sPackage, "The child package cannot be null");
		if (!children.contains(sPackage)) {
			children.add(sPackage);
			firePropertyChange(IContainer.ADD_CHILD, null, sPackage);
		}
	}

	@Override
	public List<IBean> getBeans() {
		List<IBean> beans = new ArrayList<IBean>();
		for (IEntity child : children) {
			if (child instanceof IBean) {
				beans.add((IBean) child);
			}
		}
		return beans;
	}

	@Override
	public void setBeans(IBean bean) {
		Assert.isNotNull(bean, "The child Bean cannot be null");
		if (!children.contains(bean)) {
			children.add(bean);
			firePropertyChange(IContainer.ADD_CHILD, null, bean);
		}
	}

	@Override
	public List<IInclude> getIncludes() {
		List<IInclude> includes = new ArrayList<IInclude>();
		for (IEntity child : children) {
			if (child instanceof IInclude) {
				includes.add((IInclude) child);
			}
		}
		return includes;
	}

	@Override
	public void setInclude(IInclude include) {
		Assert.isNotNull(include, "The child Include cannot be null");
		if (!children.contains(include)) {
			children.add(include);
			firePropertyChange(IContainer.ADD_CHILD, null, include);
		}
	}

	@Override
	public Object cloneEntity() {
		return null;
	}

	@Override
	public List<IConstant> getConstants() {
		List<IConstant> constants = new ArrayList<IConstant>();
		for (IEntity child : children) {
			if (child instanceof IConstant) {
				constants.add((IConstant) child);
			}
		}
		return constants;
	}

	@Override
	public void setConstant(IConstant constant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGridEnabled(boolean value) {
		this.gridEnabled = value;
	}

	@Override
	public boolean isGridEnabled() {
		return gridEnabled;
	}

	@Override
	public void setSnapToGepmetry(boolean value) {
		this.snapToGeometry = value;
	}

	@Override
	public boolean isSnapToGeometry() {
		return snapToGeometry;
	}
}
