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
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.neclipse.gef.struts2editor.model.S2ConnectionBendpoint;


public class S2CreateBendpointCommand extends S2BendpointCommand {

	public void execute() {
		S2ConnectionBendpoint bedpoint = new S2ConnectionBendpoint();
		bedpoint.setRelativeDimensions(getFirstRelativeDimension(),
				getSecondRelativeDimension());
		getConnecion().insertBendpoint(index, bedpoint);
		super.execute();
	}

	public void undo() {
		super.undo();
		getConnecion().removeBendpoint(getIndex());
	}

}
