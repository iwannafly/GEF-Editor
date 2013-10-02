package org.neclipse.graphiti.struts2editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class S2Plugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.neclipse.graphiti.struts2editor"; //$NON-NLS-1$

	// The shared instance
	private static S2Plugin plugin;
	
	/**
	 * The constructor
	 */
	public S2Plugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static S2Plugin getDefault() {
		return plugin;
	}

	/**
	 * Returns a {@code ImageDescriptor} for the image at the given
	 * plugin-relative path.
	 * 
	 * @param pluginId the ID of the plug-in containing the image.
	 * @param path the path of the Image in the plug-in.
	 * @return the {@code ImageDescriptor} for the image.
	 */
	public static ImageDescriptor getImageDescriptor(String pluginId, String path) { 
		return AbstractUIPlugin.imageDescriptorFromPlugin(pluginId, path);
	}
	
	/**
	 * 
	 * @param context
	 */
	public IConfigurationElement[] getPaletteCategories() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] cElements = registry.getConfigurationElementsFor("o.ss.palette.extension");
		return cElements;
	}
}
