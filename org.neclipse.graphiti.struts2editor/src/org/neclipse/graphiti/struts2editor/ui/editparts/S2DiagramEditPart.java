package org.neclipse.graphiti.struts2editor.ui.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AutomaticRouter;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FanRouter;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.neclipse.graphiti.struts2editor.model.S2Bean;
import org.neclipse.graphiti.struts2editor.model.S2Constant;
import org.neclipse.graphiti.struts2editor.model.S2Include;
import org.neclipse.graphiti.struts2editor.model.S2Interceptors;
import org.neclipse.graphiti.struts2editor.model.S2JSPHTML;
import org.neclipse.graphiti.struts2editor.model.rewrite.IS2Container;
import org.neclipse.graphiti.struts2editor.model.rewrite.IS2Model;
import org.neclipse.graphiti.struts2editor.model.rewrite.S2Package;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2EntityCreationCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.policies.S2ContainerXYLayoutEditPolicy;
import org.neclipse.graphiti.struts2editor.ui.validators.IValidationMessageHandler;

/**
 * The controller for the Struts Diagram entity. 
 *  
 * @author nbhusare
 */
public class S2DiagramEditPart extends S2ContainerEditPart {

	/**
	 * The Diagram figure should expand in all four directions.
	 */
	@Override
	protected IFigure createFigure() {
		/* Creating a Layer that can expand in all four directions. */
		Figure figure = new FreeformLayer(); 
		figure.setBorder(new MarginBorder(3));
		figure.setLayoutManager(new FreeformLayout());
		
		/*
		 * The diagram will be containing the connections. The objects in the
		 * diagram will be the obstacles in the connection path
		 */
		ConnectionLayer connectionLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
		AutomaticRouter router = new FanRouter();
		router.setNextRouter(new ManhattanConnectionRouter());
		connectionLayer.setConnectionRouter(router);
		return figure;
	}

	/**
	 * Installing the Diagrams editing capabilities. The Diagram cannot be
	 * deleted and the diagram should allow dropping other elements into it.
	 */
	@Override
	protected void createEditPolicies() {
		/* Do not allow removing the diagram */
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		
		/* The Diagram should allow creation of child elements */
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new S2ContainerXYLayoutEditPolicy() {

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				Object child = request.getNewObjectType();
				if (child == S2Package.class || child == S2Bean.class
						|| child == S2Constant.class || child == S2Include.class
						|| child == S2Interceptors.class || child == S2JSPHTML.class) { // Or IInclude, IBean...
					IS2Container parent = (IS2Container) getHost().getModel();
					Rectangle dimension = (Rectangle) getConstraintFor(request);
					return new S2EntityCreationCommand((IS2Model) request.getNewObject(), parent, dimension);
				} 
				return null;
			}
			
		});
	}

	@Override
	protected boolean isLabelEditRequest(Point hitLocation) {
		return false;
	}

	@Override
	protected ICellEditorValidator getCellEditValidator(
			IValidationMessageHandler handler) {
		return null;
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == SnapToHelper.class) {
			List<SnapToHelper> snapStrategies = new ArrayList<SnapToHelper>();
			
			Boolean val = (Boolean) getViewer().getProperty(
					SnapToGeometry.PROPERTY_SNAP_ENABLED);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGeometry(this));
			
			val = (Boolean) getViewer().getProperty(
					SnapToGrid.PROPERTY_GRID_ENABLED);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGrid(this));

			if (snapStrategies.size() == 0)
				return null;
			
			if (snapStrategies.size() == 1)
				return snapStrategies.get(0);

			SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
			for (int i = 0; i < snapStrategies.size(); i++)
				ss[i] = (SnapToHelper) snapStrategies.get(i);
			return new CompoundSnapToHelper(ss);
		}
		return super.getAdapter(adapter);
	}
}
