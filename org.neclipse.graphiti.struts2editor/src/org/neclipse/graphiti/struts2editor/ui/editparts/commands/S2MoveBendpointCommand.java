/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import org.eclipse.draw2d.Bendpoint;
import org.neclipse.graphiti.struts2editor.model.S2ConnectionBendpoint;

public class S2MoveBendpointCommand extends S2BendpointCommand {

	private Bendpoint oldBendpoint;

	public void execute() {
		S2ConnectionBendpoint bp = new S2ConnectionBendpoint();
		bp.setRelativeDimensions(getFirstRelativeDimension(),
				getSecondRelativeDimension());
		setOldBendpoint((Bendpoint) getConnecion().getBendpoints().get(getIndex()));
		getConnecion().setBendpoint(getIndex(), bp);
		super.execute();
	}

	protected Bendpoint getOldBendpoint() {
		return oldBendpoint;
	}

	public void setOldBendpoint(Bendpoint bp) {
		oldBendpoint = bp;
	}

	public void undo() {
		super.undo();
		getConnecion().setBendpoint(getIndex(), getOldBendpoint());
	}

}
