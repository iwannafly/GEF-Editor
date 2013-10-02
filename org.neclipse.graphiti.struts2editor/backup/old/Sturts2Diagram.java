/**
 * 
 */
package org.simplestruts2.core.model.old;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

/**
 * @author nbhusare
 *
 */
public class Sturts2Diagram extends AbstractModelEntity implements IDiagram  {

	private List<IModelEntity> children = new ArrayList<IModelEntity>();
	
	@Override
	public void setName(String name) {
		Assert.isNotNull(name, "The Diagram requires a Name that is not NULL"); //$NON-NLS-1$
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IContainer#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return children.isEmpty();
	}

	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IContainer#containsChild(org.simplestruts2.core.model.IModelEntity)
	 */
	@Override
	public boolean containsChild(IModelEntity child) {
		return children.contains(child);
	}

	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IContainer#getChildren()
	 */
	@Override
	public List<IModelEntity> getChildren() {
		return children;
	}

	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IContainer#removeChild(org.simplestruts2.core.model.IModelEntity)
	 */
	@Override
	public boolean removeChild(IModelEntity child) {
		return children.remove(child);
	}

	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IContainer#addChild(org.simplestruts2.core.model.IModelEntity)
	 */
	@Override
	public void addChild(IModelEntity child) {
		children.add(child);
	}

	/* (non-Javadoc)
	 * @see org.simplestruts2.core.model.IDiagram#getPackages()
	 */
	@Override
	public List<IPackage> getPackages() {
		List<IPackage> packages = new ArrayList<IPackage>();
		for (IModelEntity entity : children) {
			if (entity instanceof IPackage) {
				packages.add((IPackage) entity);
			}
		}
		return packages;
	}

}
