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

import org.eclipse.draw2d.Bendpoint;

public class S2DeleteBendpointCommand extends S2BendpointCommand {

	private Bendpoint bendpoint;

	public void execute() {
		bendpoint = (Bendpoint) getConnecion().getBendpoints().get(getIndex());
		getConnecion().removeBendpoint(getIndex());
		super.execute();
	}

	public void undo() {
		super.undo();
		getConnecion().insertBendpoint(getIndex(), bendpoint);
	}

}
