package org.neclipse.graphiti.struts2editor.ui.editparts.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.neclipse.graphiti.struts2editor.model.IConstraintEnity;
import org.neclipse.graphiti.struts2editor.model.rewrite.IS2Model;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2EntitySetConstCommand;

/**
 * Installing the policy to allow dropping other elements into the diagram.
 * 
 * @author nbhusare
 */
public abstract class S2ContainerXYLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command createChangeConstraintCommand(EditPart part, Object model) {return null;}
	
	@Override
	protected Command createChangeConstraintCommand(
			ChangeBoundsRequest request, EditPart childPart, Object constraint) {
		IS2Model child = (IS2Model) childPart.getModel();
		return new S2EntitySetConstCommand(request, child, (Rectangle) constraint);
	}
}