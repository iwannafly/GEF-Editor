/**
 * 
 */
package org.neclipse.gef.struts2editor.model.rewrite;

/**
 * @author nbhusare
 *
 */
public class S2Result extends S2ConnectionModel implements IS2Result {

	private static final long serialVersionUID = 1L;

	public S2Result(IS2ConnectableModel src, IS2ConnectableModel tgt) {
		super(src, tgt);
	}

}
