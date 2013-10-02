/**
 * 
 */
package org.simplestruts2.core.model.old;

/**
 * @author nbhusare
 *
 */
public interface IPackageChild extends IContainer, Reparentabe<IPackage> {
	
	@Override
	public void setParent(IPackage parent);
}
