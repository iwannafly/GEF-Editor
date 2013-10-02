/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import java.util.List;

/**
 * @author nbhusare
 * 
 */
public interface IPackage extends IDiagramChild {

	String NAME_PREFIX = "Name: ";

	String DEFAULT_NAME = "Foo";
	
	String NAMESPACE_PREFIX = "Namespace: ";

	String DEFAULT_NAMESPACE = "/Foo";
	
	String ABSTRACT_PREFIX = "Abstract: ";
	
	String DEFAULT_ABSTRACT = "false"; 

	String NAMESPACE_CHANGED = "Namespace Changed";
	
	String EXTENDS_CHANGED = "Extends Changes";
	
	String ABSTRACT_CHANGE = "Abstarct Value Change";
	
	String getNamespace();

	void setNamespace(String namespace);

	String getAbstractValue();
	
	void setAbstractValue(String abstractValue);
	
	List<IPackage> getExtends();
	
	void addExtend(IPackage pExtends);
	
	void removeExtend(IPackage pExtend);

	void setExtends(List<IPackage> pExtends);
	
	boolean isExtended(IPackage ePackage);

	void addAction(IAction action);
	
	List<IAction> getActions();
}
