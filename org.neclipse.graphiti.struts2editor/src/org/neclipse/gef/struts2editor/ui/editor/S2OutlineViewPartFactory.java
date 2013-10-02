package org.neclipse.gef.struts2editor.ui.editor;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.neclipse.gef.struts2editor.model.IDiagram;
import org.neclipse.gef.struts2editor.model.IDiagramChild;
import org.neclipse.gef.struts2editor.model.IInclude;
import org.neclipse.gef.struts2editor.model.IInterceptorsChild;
import org.neclipse.gef.struts2editor.model.IPackageChild;
import org.neclipse.gef.struts2editor.ui.editparts.S2ChildTreeEditpart;
import org.neclipse.gef.struts2editor.ui.editparts.S2DiagramTreeEditPart;
import org.neclipse.gef.struts2editor.ui.editparts.S2IncludeTreeEditPart;

public class S2OutlineViewPartFactory implements EditPartFactory {

	private EditPartViewer viewer;
	private IProject project;

	public S2OutlineViewPartFactory(EditPartViewer viewer, IProject project) {
		this.viewer = viewer;
		this.project = project;
	}
	
	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof IDiagram) {
			part = new S2DiagramTreeEditPart();
			part.setModel(model);
		} else if (model instanceof IInclude) {
			return createIncludeEditPart((IInclude) model);
		} 
		else if ((model instanceof IDiagramChild)
				|| (model instanceof IPackageChild)
				|| (model instanceof IInterceptorsChild)) {
			part = new S2ChildTreeEditpart(project);
			part.setModel(model);
		}
		return part;
	}

	/**
	 * Creates a tree editpart for Include model element. If a file exists at
	 * the path specified, the file contents are read and the root element
	 * (Diagram) is set as the model for the edit part. If the path doesn't
	 * match, the model element is set as it is.
	 * 
	 * @param model
	 * @return
	 */
	private EditPart createIncludeEditPart(IInclude model) {
		String path = model.getPath();
		IFile iFile = project.getFile(path);
		
		S2IncludeTreeEditPart part = new S2IncludeTreeEditPart(project);
		part.setOutlineItemText(model.getPath());
		if (iFile != null && iFile.exists()) {
			ObjectInputStream in;
			try {
				in = new ObjectInputStream(iFile.getContents());
				IDiagram sharedModel = (IDiagram) in.readObject();
				in.close();
				
				part.setOutlineItemText(path);
				part.setModel(sharedModel);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			part.setModel(model);
		}
		return part;
	}
}