package org.neclipse.graphiti.struts2editor.ui.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.MatchHeightAction;
import org.eclipse.gef.ui.actions.MatchWidthAction;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.neclipse.graphiti.struts2editor.model.rewrite.IS2Diagram;
import org.neclipse.graphiti.struts2editor.model.rewrite.S2Diagram;
import org.neclipse.graphiti.struts2editor.ui.action.S2CopyAction;
import org.neclipse.graphiti.struts2editor.ui.action.S2PasteAction;

/**
 * Editor used for creating Struts 2 Diagrams.
 *
 */
public class S2Editor extends GraphicalEditorWithFlyoutPalette {
	
	/* Editor Objects */
	private PaletteRoot paletteRoot;
	private IS2Diagram diagram;
	
	/**
	 * Creates a new instance of the <code>Struts2</code> Editor.
	 */
	public S2Editor() {
		/* Set the edit domain for the editor */
		setEditDomain(new DefaultEditDomain(this));
	}
	
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		
		/* Configure the graphical viewer before it receives the contents */
		GraphicalViewer graphicalViewer = getGraphicalViewer();
		graphicalViewer.setEditPartFactory(new S2EditPartFactory());
		graphicalViewer.setRootEditPart(new ScalableFreeformRootEditPart());
		
		/* Set the Ket Handler for the Graphical viewer */
		graphicalViewer.setKeyHandler(new GraphicalViewerKeyHandler(graphicalViewer));
		
		/* Create a Context menu and register it with the viewer */
		ContextMenuProvider menuProvider = new S2EditorContextMenuProvider(
				graphicalViewer, getActionRegistry());
		graphicalViewer.setContextMenu(menuProvider);
		getSite().registerContextMenu(
				"org.neclipse.graphiti.struts2editor.ui.editor.context.menu", menuProvider, graphicalViewer); //$NON-NLS-1$
		
		loadProperties();
		
		/* Zoom support */
		createZoomActions();
		
		/* Grid support */
		createGridActions();
	}

	/**
	 * Load the properties that have been saved during the last save operation
	 * and initialize the graphical editor with the same.
	 */
	private void loadProperties() {
//		getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED,
//				new Boolean(diagram.isGridEnabled()));
//		
//		getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE,
//				new Boolean(diagram.isGridEnabled()));
//		
//		getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_SPACING,
//				new Dimension(15, 15));
//		
//		getGraphicalViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED,
//				new Boolean(diagram.isSnapToGeometry()));
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		
		/* Initialize the graphical viewer with the contents */
		GraphicalViewer graphicalViewer = getGraphicalViewer();
		graphicalViewer.setContents(diagram);
		
	}
	
	/**
	 * 
	 */
	private void createGridActions() {
		/* Toggle Grid action */
		IAction action = new ToggleGridAction(getGraphicalViewer());
		getActionRegistry().registerAction(action);
		
		/* Snap to Geometry action */
		action = new ToggleSnapToGeometryAction(getGraphicalViewer());
		getActionRegistry().registerAction(action);
	}
	
	/**
	 * 
	 */
	private void createZoomActions() {
		/* Zoom actions */
		ScalableFreeformRootEditPart rootEditPart = (ScalableFreeformRootEditPart) getGraphicalViewer()
				.getRootEditPart();
		IAction action = new ZoomInAction(rootEditPart.getZoomManager());
		getActionRegistry().registerAction(action);
		
		action = new ZoomOutAction(rootEditPart.getZoomManager());
		getActionRegistry().registerAction(action);
		
		List<String> zoomLevels = new ArrayList<String>(3);
		zoomLevels.add(ZoomManager.FIT_ALL);
		zoomLevels.add(ZoomManager.FIT_WIDTH);
		zoomLevels.add(ZoomManager.FIT_HEIGHT);
		rootEditPart.getZoomManager().setZoomLevelContributions(zoomLevels);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createActions() {
		super.createActions();
		
		/* Get the registery of actions for registering the new actions*/
		ActionRegistry registry = getActionRegistry();
		
		/* Copy action */
		IAction action = new S2CopyAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		/* Paste action */
		action = new S2PasteAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		/* Align Left action */
		action = new AlignmentAction((IWorkbenchPart)this, PositionConstants.LEFT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		/* Align Right action */
		action = new AlignmentAction((IWorkbenchPart)this, PositionConstants.RIGHT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		/* Align Top action */
		action = new AlignmentAction((IWorkbenchPart)this, PositionConstants.TOP);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		/* Align Bottom action */
		action = new AlignmentAction((IWorkbenchPart)this, PositionConstants.BOTTOM);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		/* Align Center action */
		action = new AlignmentAction((IWorkbenchPart)this, PositionConstants.CENTER);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		/* Align Middle action */
		action = new AlignmentAction((IWorkbenchPart)this, PositionConstants.MIDDLE);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		/* Match Width Action */
		action = new MatchWidthAction((IWorkbenchPart)this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		/* Match Height Action */
		action = new MatchHeightAction((IWorkbenchPart)this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}
	
	
	
	@Override
	public void commandStackChanged(EventObject event) {
		/* Change the state of the editor to dirty */
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}
	
	@Override
	//TODO: Take a look at PaletteContainer#moveXX() methods.
	protected PaletteRoot getPaletteRoot() {
		if (paletteRoot == null) {
			paletteRoot = S2EditorPaletteFactory.createPaletteRoot();
		}
		return paletteRoot;
	}

	/**
	 * Set the model input to the editor. This method will fail if the input
	 * model file in empty, which will happen for the first time. On failure, it
	 * calls #handleLoadException() which initialized the {@code IDiagram} model.
	 */
	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		String fileName = ""; //$NON-NLS-1$
		try {
			IFile file = ((IFileEditorInput)input).getFile();
			fileName = file.getName();
			fileName = fileName.substring(0, fileName.indexOf(".")); //$NON-NLS-1$
			ObjectInputStream in = new ObjectInputStream(file.getContents());
			diagram = (IS2Diagram) in.readObject();
			in.close();
			setPartName(fileName);
		} catch (IOException e){
			handleLoadException(e, fileName);
		} catch (CoreException e){
			handleLoadException(e, fileName);
		} catch (ClassNotFoundException e) {
			handleLoadException(e, fileName);
		} 
	}
	
	/**
	 * This method initializes the {@code IDiagram} element.
	 * @param e exception indicating an issue while loading the model.
	 * @param fileName 
	 */
	private void handleLoadException(Exception e, String fileName) {
		System.err.println("** Load failed. Using default model. **");
		e.printStackTrace();
		diagram = new S2Diagram(); 
		diagram.setName(fileName);
		setPartName(diagram.getName());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			/* Save the Properties */
			saveProperties();
			
			createOutputStream(out);
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			file.setContents(new ByteArrayInputStream(out.toByteArray()), true, 
					false, // dont keep history
					monitor); // progress monitor
			getCommandStack().markSaveLocation();
		} catch (CoreException ce) {
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void saveProperties() {
		/* Set the values for Grid enabled and snaptogeometry in the Diagram object */
//		diagram.setGridEnabled(((Boolean) getGraphicalViewer().getProperty(
//				SnapToGrid.PROPERTY_GRID_ENABLED)).booleanValue());
//		diagram.setSnapToGepmetry(((Boolean) getGraphicalViewer().getProperty(
//				SnapToGeometry.PROPERTY_SNAP_ENABLED)).booleanValue());
	}

	private void createOutputStream(OutputStream os) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(diagram);
		oos.close();
	}
	
	@Override
	public void doSaveAs() {
		super.doSaveAs();
	}
	
	@Override
	public Object getAdapter(Class type) {
		if (type == IContentOutlinePage.class)
			return new S2EditorOutlinePage(new TreeViewer());
		if (type == ZoomManager.class) {
			return getGraphicalViewer().getProperty(ZoomManager.class.toString());
		}
		return super.getAdapter(type);
	}
	
	public IProject getProject() {
		IFile inputFile = ((IFileEditorInput)getEditorInput()).getFile();
		IProject project = inputFile.getProject();
		return project;
	}
	
	/**
	 * Outlint page for the Struts 2 Editor
	 * 
	 * @author nbhusare
	 */
	public class S2EditorOutlinePage extends ContentOutlinePage {

		public S2EditorOutlinePage(EditPartViewer viewer) {
			super(viewer);
		}
		
		@Override
		public void createControl(Composite parent) {
			/* Create the Outline view page */
			getViewer().createControl(parent);
			
			/* Configure the outline viewer */
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new S2OutlineViewPartFactory(getViewer(), getProject()));
			getViewer().setContents(diagram);
			
			/* Adding the viewer to the selection synchronizer */
			getSelectionSynchronizer().addViewer(getViewer());
			
			/* Hook a listener to the drag events in the Outline view */
			getViewer().addDragSourceListener(new S2EntityTransferDragSrcListener(getViewer()));
		}
		
		@Override
		public void dispose() {
			super.dispose();
		}
		
		@Override
		public Control getControl() {
			return getViewer().getControl();
		}
	}
}
