/**
 * 
 */
package org.neclipse.gef.struts2editor.model.rewrite;

/**
 * @author nbhusare
 *
 */
public class S2ModelCngConstants {
	
	/* Constant representing the change in the model attribute */
	public static final String NAME_CNG = "Name changed";
	public static final String DIMENSION_CHG = "Dimension changed";
	public static final String LOCATION_CNG = "Location changed";
	
	/* Constants representing the change in the model container attributes */
	public static final String CHILD_ADDED = "Child added";
	public static final String CHILD_REMOVED = "Child removed";
	
	/* Constants representing the change in the connectable model attribute */
	public static final String SOURCE_CONN_ADDED = "Source Connection added";
	public static final String TARGET_CONN_ADDED = "Target Connection added";
	public static final String SOURCE_CONN_REMOVED = "Source Connection removed";
	public static final String TARGET_CONN_REMOVED = "Target Connection removed";
	
	/* Constants representing the change in the Package model */
	public static final String PKG_NAMESPACE_CNG = "Namespace changed";
	public static final String PKG_ABSTRACT_CNG = "Abstract changed";
	public static final String PKG_EXTENDS_CNG = "Extends changed";
}
