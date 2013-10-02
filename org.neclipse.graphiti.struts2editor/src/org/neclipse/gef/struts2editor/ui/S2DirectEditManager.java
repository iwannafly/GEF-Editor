/**
 * 
 */
package org.neclipse.gef.struts2editor.ui;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;

/**
 * Manager for handling the direct editing operations on the Labels.
 * 
 * @author nbhusare
 * 
 */
public class S2DirectEditManager extends DirectEditManager {

	private Label label;
	private Font figureFont;
	private String originalTextValue;
	private boolean commiting = false;

	private VerifyListener verifyListener;
	private ICellEditorValidator validator;

	public S2DirectEditManager(GraphicalEditPart editPart, Class editorType,
			CellEditorLocator locator, Label label,
			ICellEditorValidator validator) {
		super(editPart, editorType, locator);
		this.label = label;
		this.originalTextValue = label.getText();
		this.validator = validator;
	}

	@Override
	protected void bringDown() {
		Font disposeFont = figureFont;
		figureFont = null;
		super.bringDown();
		if (disposeFont != null)
			disposeFont.dispose();
	}
	
	@Override
	protected void unhookListeners() {
		super.unhookListeners();
		Text text = (Text) getCellEditor().getControl();
		text.removeVerifyListener(verifyListener);
		verifyListener = null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.DirectEditManager#initCellEditor()
	 */
	@Override
	protected void initCellEditor() {
		Text text = (Text) getCellEditor().getControl();
		
		/* Add the verifier to apply changes to the control size */
		verifyListener = new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent event) {
				Text text = (Text) getCellEditor().getControl();
				String oldText = text.getText();
				String leftText = oldText.substring(0, event.start);
				String rightText = oldText.substring(event.end, oldText.length());
				GC gc = new GC(text);
				if (leftText == null) leftText = "";
				if (rightText == null) rightText = "";
				
				String s = leftText + event.text + rightText;
				Point size = gc.textExtent(s);
				gc.dispose();
				
				if (size.x != 0) {
					size = text.computeSize(size.x, SWT.DEFAULT);
				} else {
					size.x = size.y;
				}
				getCellEditor().getControl().setSize(size.x, size.y);
			}
		};
		text.addVerifyListener(verifyListener);
		
		originalTextValue = label.getText();
		
		int index = originalTextValue.indexOf(":");
		originalTextValue = originalTextValue.substring(index + 1, originalTextValue.length());
		getCellEditor().setValue(originalTextValue);
		
		//calculate the font size of the underlying
		IFigure figure = ((GraphicalEditPart) getEditPart()).getFigure();
		figureFont = figure.getFont();
		FontData data = figureFont.getFontData()[0];
		Dimension fontSize = new Dimension(0, data.getHeight());

		//set the font to be used
		this.label.translateToAbsolute(fontSize);
		data.setHeight(fontSize.height);
		figureFont = new Font(null, data);

		//set the validator for the CellEditor
		getCellEditor().setValidator(validator);

		text.setFont(figureFont);
		text.selectAll();
	}
	
	@Override
	protected void commit() {
		if (commiting)
			return;
		commiting = true;
		try {
			getCellEditor().getControl().setVisible(false);
			if (isDirty()) { 
				CommandStack stack = getEditPart().getViewer().getEditDomain().getCommandStack();
				Command command = getEditPart().getCommand(getDirectEditRequest());
				if (command != null && command.canExecute()) {
					stack.execute(command);
				}
			}
		} finally {
			bringDown();
			commiting = false;
		}
	}
}
