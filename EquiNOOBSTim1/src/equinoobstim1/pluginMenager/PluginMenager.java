package equinoobstim1.pluginMenager;

import org.eclipse.core.internal.runtime.InternalPlatform;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class PluginMenager {
	
	public static final String stop = "Stop";
	public static final String start = "Start";
	public static final String uninstall = "Uninstall";
	
	protected static Plugins plugins = new Plugins();
	public static PluginMenager menager = null;
	protected static Bundle[] bundles = InternalPlatform.getDefault().getBundleContext().getBundles();
	protected BundleContext instaler = InternalPlatform.getDefault().getBundleContext();
	
	private PluginMenager() {
		
	}
	
	public static PluginMenager getPluginMenger(){
		if (menager == null) {
			menager = new PluginMenager();
		}
		
		fillInformations();
		
		return menager;
	}
	
	private static void fillInformations(){
		if (plugins != null) {
			plugins.clear();
		}
		
		for (Bundle bundle : bundles) {
			System.out.println(bundle.getBundleId());
			Plugin newPlugin = new Plugin(bundle.getSymbolicName(), Integer.toString(bundle.getState()) );
			plugins.add(newPlugin);
		}
	}
	
	private Bundle getBundle(String pluginName){
		for (Bundle bundle : bundles) {
			if (bundle.getSymbolicName().equalsIgnoreCase(pluginName)) {
				return bundle;
			}
		}
		
		return null;
	}
	
	public void stop(String pluginToStop) throws BundleException{
		plugins.stopPlugin(pluginToStop);//stop in model
		getBundle(pluginToStop).stop(Bundle.STOPPING);//stop in registry
		getBundle(pluginToStop.toString()).update(); // update plugin
	}
	
	public void start(String pluginToStart) throws BundleException{
		
		plugins.startPlugin(pluginToStart);//start in model
		getBundle(pluginToStart).start(Bundle.STARTING);//start in registry
		getBundle(pluginToStart).update(); // update plugin
	}
	
	public void uninstall(String pluginToUninstall) throws BundleException{
		plugins.uninstalPlugin(pluginToUninstall);
		getBundle(pluginToUninstall).uninstall();
	}
	
	public void installNewPlugin(String pathToPlugin){
		try {
			instaler.installBundle(pathToPlugin);//instal new plugin from path
			fillInformations();//add to registru o plugins
		} catch (BundleException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static Plugins getPlugins() {
		return plugins;
	}
	
}
