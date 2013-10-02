/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.tools.CreationTool;
import org.neclipse.gef.struts2editor.S2Plugin;
import org.neclipse.gef.struts2editor.model.S2ExtendsConnection;
import org.neclipse.gef.struts2editor.model.S2ResultConnection;
import org.neclipse.gef.struts2editor.ui.S2EditorUI;

/**
 * Utility class for creating the GEF palette and the containing entries.
 *
 */
public class S2EditorPaletteFactory {

	/**
	 * Creates the new {@code PaletteRoot} instance and populates it with the
	 * child {@code PaletteEntry}
	 * 
	 * @return the {@code PaletteRoot}
	 */
	public static PaletteRoot createPaletteRoot() {
		PaletteRoot paletteRoot = new PaletteRoot();
		createControlGroup(paletteRoot);
		createStrutsGroup(paletteRoot);
		return paletteRoot;
	}
	
	/**
	 * Creates {@code ToolEntry} for the Struts components.
	 * @param paletteRoot the {@code PaletteRoot} entry
	 * @return the {@code PaletteContainer} instance
	 */
	private static void createStrutsGroup(PaletteRoot paletteRoot) {
		//PaletteDrawer drawer = new PaletteDrawer("Administrative Elements"); 
		
		IConfigurationElement[] cElements = S2Plugin.getDefault().getPaletteCategories();
		for (IConfigurationElement cElement : cElements) {
			boolean collapsable = Boolean.valueOf(cElement.getAttribute("collapsible"));
			PaletteContainer container = null;
			if (collapsable) {
				container = createCollapsableCategory(cElement);
			} else {
				container = createCategory(cElement);
			}
			paletteRoot.add(container);
		}
	}
	
	private static PaletteContainer createCategory(IConfigurationElement cElement) {
		String categoryName = cElement.getAttribute("name");
		PaletteGroup group = new PaletteGroup(categoryName);
		
		IConfigurationElement[] cEntries = cElement.getChildren();
		for (IConfigurationElement cEntry : cEntries) {
			
		}
		return group;
	}

	private static PaletteContainer createCollapsableCategory(IConfigurationElement cElement) {
		String categoryName = cElement.getAttribute("name");
		PaletteDrawer drawer = new PaletteDrawer(categoryName);
		
		IConfigurationElement[] cEntries = cElement.getChildren();
		for (IConfigurationElement cEntry : cEntries) {
			try {
				String label = cEntry.getAttribute("name");
				Object model = cEntry.createExecutableExtension("modelclass");
				Object toolentry = cEntry.createExecutableExtension("toolentryclass");
				String iSmall = cEntry.getAttribute("smallimage");
				String iLarge = cEntry.getAttribute("largeimage");
				String description = cElement.getAttribute("description");
				
				
				if (toolentry instanceof CreationToolEntry) {
					CreationToolEntry entry = (CreationToolEntry) toolentry;
					entry.setLabel(label);
					entry.setDescription(description);
					entry.setToolProperty(CreationTool.PROPERTY_CREATION_FACTORY, new SimpleFactory(model.getClass()));
					entry.setSmallIcon(S2EditorUI.getImageDescriptor(iSmall));
					entry.setLargeIcon(S2EditorUI.getImageDescriptor(iLarge));
					drawer.add(entry);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return drawer;
	}

	/**
	 *  Creates {@code ToolEntry}. 
	 * @param paletteRoot the {@code PaletteRoot} entry
	 * @return the {@code PaletteContainer} instance
	 */
	private static void createControlGroup(PaletteRoot paletteRoot) {
		PaletteGroup controlGroup = new PaletteGroup("Components");
		
		/* Group for storing all the <code>PaletteEntry</code> */
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		
		/* Selection tool */
		PaletteEntry entry = new SelectionToolEntry();
		entries.add(entry);
		paletteRoot.setDefaultEntry((ToolEntry) entry);
		
		/* Marquee tool */
		entry = new MarqueeToolEntry();
		entries.add(entry);
		
		/* Separate the palette */
		entry = new PaletteSeparator("org.neclipse.gef.struts2editor.ui.editor.palette.sep1");
		entry.setUserModificationPermission(PaletteEntry.PERMISSION_NO_MODIFICATION);
		entries.add(entry);
		
		/* Tool Entry for Result connection creation */
		entry = new ConnectionCreationToolEntry("Result Connection",
				"Connect to Struts 2 Results", new SimpleFactory(S2ResultConnection.class),
				S2EditorUI.getImageDescriptor("icons/connection16.gif"),
				S2EditorUI.getImageDescriptor("icons/connection24.gif"));
		entries.add(entry);
		
		/* Tool Entry for Extends connection creation */
		entry = new ConnectionCreationToolEntry("Extends Connection",
				"Connect to Struts 2 Package", new SimpleFactory(S2ExtendsConnection.class),
				S2EditorUI.getImageDescriptor("icons/connection16.gif"),
				S2EditorUI.getImageDescriptor("icons/connection24.gif"));
		entries.add(entry);
		
		/* Tool Entry for Extends connection creation */
		entry = new ConnectionCreationToolEntry("Comment Connection",
				"Connect to Struts 2 Comment", new SimpleFactory(Object.class),
				S2EditorUI.getImageDescriptor("icons/connection16.gif"),
				S2EditorUI.getImageDescriptor("icons/connection24.gif"));
		entries.add(entry);
		
		controlGroup.addAll(entries);
		paletteRoot.add(controlGroup);
	}

}
