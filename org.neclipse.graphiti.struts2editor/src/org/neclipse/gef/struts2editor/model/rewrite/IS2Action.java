package org.neclipse.gef.struts2editor.model.rewrite;

import java.util.List;

/**
 * Interface representing the Struts 2 Action model element. 
 * 
 * @author nbhusare
 *
 */
public interface IS2Action extends IS2ConnectableContainer {
	
	/**
	 * Constant representing the default entry method to the Handler.
	 */
	String default_entry_method = "execute";
	
	/**
	 * Return the name of the Action Handler. 
	 * 
	 * @return the name of the Action Handler.
	 */
	String getHandler();
	
	/**
	 * Set the name of the Action Handler.
	 * 
	 * @param handler the name of the Action Handler.
	 */
	void setHandler(String handler);
	
	/**
	 * Return the Entry Method to the Handler
	 * 
	 * @return the Entry Method to the Handler
	 */
	String getEntryMethod();
	
	/**
	 * Set the Entry Method to the Handler.
	 * 
	 * @param entMethod the Entry Method to the Handler.
	 */
	void setEntryMethod(String entMethod);
	
	/**
	 * Return the <code>IS2Result</code> present in the Action model.
	 * 
	 * @return the <code>IS2Result</code> present in the Action model.
	 */
	List<IS2Result> getResults();
	
	/**
	 * Set the <code>IS2Result</code> model element.
	 *  
	 * @param res the <code>IS2Result</code> model element.
	 */
	void setResult(IS2Result res);
	
	
	/**
	 * Return the <code>IResultParam</code> list.
	 * 
	 * @return the <code>IResultParam</code> list.
	 */
	List<IS2ActionParam> getActionParams();
	
	/**
	 * Set the <code>IResultParam</code> model element.
	 * 
	 * @param resParam the <code>IResultParam</code> model element.
	 */
	void setActionParam(IS2ActionParam resParam);
	
	/**
	 * Return the <code>IS2InterceptorRef</code> list.
	 * 
	 * @return the <code>IS2InterceptorRef</code> list.
	 */
	List<IS2InterceptorRef> getInterceptorRefs();
	
	/**
	 * Set the <code>IS2InterceptorRef</code> model element.
	 * 
	 * @param the <code>IS2InterceptorRef</code> model element.
	 */
	void setInterceptorRef(IS2InterceptorRef intRef);
}
