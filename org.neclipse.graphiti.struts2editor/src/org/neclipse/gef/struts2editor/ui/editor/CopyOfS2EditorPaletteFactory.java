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
import org.neclipse.gef.struts2editor.S2Plugin;
import org.neclipse.gef.struts2editor.model.S2ExtendsConnection;
import org.neclipse.gef.struts2editor.model.S2ResultConnection;
import org.neclipse.gef.struts2editor.ui.S2EditorUI;

/**
 * Utility class for creating the GEF palette and the containing entries.
 *
 */
public class CopyOfS2EditorPaletteFactory {

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
			boolean collapsable = Boolean.getBoolean(cElement.getAttribute("collapsible"));
			PaletteContainer container = null;
			if (collapsable) {
				container = createCollapsableCategory(cElement);
			} else {
				container = createCategory(cElement);
			}
			paletteRoot.add(container);
			
			String categoryName = cElement.getAttribute("name");
			PaletteDrawer drawer = new PaletteDrawer(categoryName);
			
			IConfigurationElement[] cEntries = cElement.getChildren();
			for (IConfigurationElement cEntry : cEntries) {
				try {
					String eName = cEntry.getAttribute("name");
					Object eClass = cEntry.createExecutableExtension("class");
					String iSmall = cEntry.getAttribute("smallimage");
					String iLarge = cEntry.getAttribute("largeimage");
					String description = cElement.getAttribute("description");
					PaletteEntry entry = new CombinedTemplateCreationEntry(eName,
							description, new SimpleFactory(eClass.getClass()),
							S2EditorUI.getImageDescriptor(iSmall),
							S2EditorUI.getImageDescriptor(iLarge));
					drawer.add(entry);
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
			paletteRoot.add(drawer);
		}
		
		
//		/* Tool Entry for Bean creation */
//		PaletteEntry entry = new CombinedTemplateCreationEntry("Bean",
//				"Create Struts 2 Bean", new SimpleFactory(S2Bean.class),
//				S2EditorUI.getImageDescriptor("icons/11880.bmp.gif"),
//				S2EditorUI.getImageDescriptor("icons/11880.bmp.gif"));
//		drawer.add(entry);
//		
//		/* Tool Entry for Constant creation */
//		entry = new CombinedTemplateCreationEntry("Constant",
//				"Create Struts 2 Constant", new SimpleFactory(S2Constant.class),
//				S2EditorUI.getImageDescriptor("icons/17405.thread_view.png"),
//				S2EditorUI.getImageDescriptor("icons/17405.thread_view.png"));
//		drawer.add(entry);
//		
//		/* Tool Entry for package creation */
//		entry = new CombinedTemplateCreationEntry("Package",
//				"Create Struts 2 Package", S2Package.class, new SimpleFactory(S2Package.class),
//				S2EditorUI.getImageDescriptor("icons/10464.package_obj.gif"),
//				S2EditorUI.getImageDescriptor("icons/10464.package_obj.gif"));
//		drawer.add(entry);
//		
//		/* Tool Entry for Include creation */
//		entry = new CombinedTemplateCreationEntry("Include",
//				"Create Struts 2 Include", new SimpleFactory(S2Include.class),
//				S2EditorUI.getImageDescriptor("icons/showchild_mode.gif"),
//				S2EditorUI.getImageDescriptor("icons/showchild_mode.gif"));
//		drawer.add(entry);
//		paletteRoot.add(drawer);
//		
//		drawer = new PaletteDrawer("Request Handling"); 
//		
//		/* Tool Entry for Action Creation */
//		entry = new CombinedTemplateCreationEntry("Action",
//				"Create Struts 2 Action", new SimpleFactory(S2Action.class),
//				S2EditorUI.getImageDescriptor("icons/innerclass_public_obj.gif"),
//				S2EditorUI.getImageDescriptor("icons/innerclass_public_obj.gif"));
//		drawer.add(entry);
//		
//		/* Tool Entry for default-Action Creation */
//		entry = new CombinedTemplateCreationEntry("Default Action Reference",
//				"Create Struts 2 Default Action Reference", new SimpleFactory(Object.class),
//				S2EditorUI.getImageDescriptor("icons/innerclass_public_obj.gif"),
//				S2EditorUI.getImageDescriptor("icons/innerclass_public_obj.gif"));
//		drawer.add(entry);
//		
//		/* Tool Entry for Interceptors creation */
//		entry = new CombinedTemplateCreationEntry("Interceptors",
//				"Create Struts 2 Interceptor", new SimpleFactory(S2Interceptors.class),
//				S2EditorUI.getImageDescriptor("icons/arraypartition_obj.gif"),
//				S2EditorUI.getImageDescriptor("icons/arraypartition_obj.gif"));
//		drawer.add(entry);
//		
//		/* Tool Entry for Interceptor creation */
//		entry = new CombinedTemplateCreationEntry("Interceptor",
//				"Create Struts 2 Interceptor", new SimpleFactory(S2Interceptor.class),
//				S2EditorUI.getImageDescriptor("icons/remove_correction.gif"),
//				S2EditorUI.getImageDescriptor("icons/remove_correction.gif"));
//		drawer.add(entry);
//		
//		/* Tool Entry for Interceptor-stack creation */
//		entry = new CombinedTemplateCreationEntry("Interceptor-Stack",
//				"Create Struts 2 Interceptor-stack", new SimpleFactory(S2InterceptorStack.class),
//				S2EditorUI.getImageDescriptor("icons/thin_max_view.gif"),
//				S2EditorUI.getImageDescriptor("icons/thin_max_view.gif"));
//		drawer.add(entry);
//		
//		/* Tool Entry for Interceptor-ref creation */
//		entry = new CombinedTemplateCreationEntry("Interceptor-ref",
//				"Create Struts 2 Interceptor-ref", new SimpleFactory(S2InterceptorRef.class),
//				S2EditorUI.getImageDescriptor("icons/change.gif"),
//				S2EditorUI.getImageDescriptor("icons/change.gif"));
//		drawer.add(entry);
//		
//		paletteRoot.add(drawer);
//		
//		drawer = new PaletteDrawer("Error Handling"); 
//		/* Tool Entry for Exception creation */
//		entry = new CombinedTemplateCreationEntry("Exception",
//				"Create Struts 2 Exception mappings", new SimpleFactory(Object.class),
//				S2EditorUI.getImageDescriptor("icons/10377.enum_obj.gif"),
//				S2EditorUI.getImageDescriptor("icons/10377.enum_obj.gif"));
//		drawer.add(entry);
//		paletteRoot.add(drawer);
//		
//		drawer = new PaletteDrawer("Resources");
//		/* Tool Entry for JSP/HTML Page creation */
//		entry = new CombinedTemplateCreationEntry("JSP/HTML",
//				"Create JSP/HTML pages", new SimpleFactory(S2JSPHTML.class),
//				S2EditorUI.getImageDescriptor("icons/14474.PD_Plugin.gif"),
//				S2EditorUI.getImageDescriptor("icons/14474.PD_Plugin.gif"));
//		drawer.add(entry);
//		paletteRoot.add(drawer);
//		
//		drawer = new PaletteDrawer("Comments");
//		
//		/* Tool Entry for Comment creation */
//		entry = new CombinedTemplateCreationEntry("Comment",
//				"Create a new Comment", new SimpleFactory(S2Comment.class),
//				S2EditorUI.getImageDescriptor("icons/10377.enum_obj.gif"),
//				S2EditorUI.getImageDescriptor("icons/10377.enum_obj.gif"));
//		drawer.add(entry);
//		paletteRoot.add(drawer);
	}
	
	private static PaletteContainer createCategory(
			IConfigurationElement cElement) {
		// TODO Auto-generated method stub
		return null;
	}

	private static PaletteContainer createCollapsableCategory(
			IConfigurationElement cElement) {
		
		return null;
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
