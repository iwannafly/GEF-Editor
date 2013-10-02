package org.neclipse.graphiti.struts2editor.ui.editor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.neclipse.graphiti.struts2editor.model.IAction;
import org.neclipse.graphiti.struts2editor.model.IBean;
import org.neclipse.graphiti.struts2editor.model.IConstant;
import org.neclipse.graphiti.struts2editor.model.IInclude;
import org.neclipse.graphiti.struts2editor.model.IInterceptor;
import org.neclipse.graphiti.struts2editor.model.IInterceptorRef;
import org.neclipse.graphiti.struts2editor.model.IInterceptorStack;
import org.neclipse.graphiti.struts2editor.model.IInterceptors;
import org.neclipse.graphiti.struts2editor.model.IJSPHTML;
import org.neclipse.graphiti.struts2editor.model.S2ExtendsConnection;
import org.neclipse.graphiti.struts2editor.model.S2ResultConnection;
import org.neclipse.graphiti.struts2editor.model.rewrite.IS2Diagram;
import org.neclipse.graphiti.struts2editor.model.rewrite.IS2Package;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2ActionEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2BeanEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2ConstantEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2DiagramEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2ExtendsEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2IncludeEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2InterceptorEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2InterceptorRefEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2InterceptorStackEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2InterceptorsEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2JSPHTMLEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2PackageEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2ResultEditPart;

/**
 * Factory for creating editparts for the Struts 2 Model elements.
 * 
 */
public class S2EditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart editPart, Object model) {
		EditPart part = null;
		if (model instanceof IS2Diagram) {
			part = new S2DiagramEditPart();
		} else if (model instanceof IS2Package) {
			part = new S2PackageEditPart();
		} else if (model instanceof IBean) {
			part = new S2BeanEditPart();
		} else if (model instanceof IConstant) {
			part = new S2ConstantEditPart();
		} else if (model instanceof IInclude) {
			part = new S2IncludeEditPart();
		} else if (model instanceof IAction) {
			part = new S2ActionEditPart();
		} else if (model instanceof IInterceptors) {
			part = new S2InterceptorsEditPart();
		} else if (model instanceof IInterceptor) {
			part = new S2InterceptorEditPart();
		} else if (model instanceof IInterceptorStack) { 
			part = new S2InterceptorStackEditPart();
		} else if (model instanceof IInterceptorRef) { 
			part = new S2InterceptorRefEditPart();
		} else if (model instanceof IJSPHTML) { 
			part = new S2JSPHTMLEditPart();
		} else if (model instanceof S2ExtendsConnection) {
			part = new S2ExtendsEditPart();
		} else if (model instanceof S2ResultConnection) {
			part = new S2ResultEditPart();
		} 
//		else if (model instanceof S2Comment) {
//			part = new S2CommentEditPart();
//		}
		if (part != null)
			part.setModel(model);
		return part;
	}

}
