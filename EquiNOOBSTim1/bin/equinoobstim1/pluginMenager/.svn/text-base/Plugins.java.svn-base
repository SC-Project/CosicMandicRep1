package equinoobstim1.pluginMenager;


import java.util.ArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;

public class Plugins {
	
	protected ArrayList<Plugin> plugins;
	
	public Plugins() {
		plugins = new ArrayList<Plugin>();
	}
	
	//add plugin -> START plugin
	public void add(Plugin newPlugin){
		plugins.add(newPlugin);
	}
	
	//modify plugin -> STOP plugin or else
	public void modifyPlugin(Plugin oldValue,Plugin newValue){
		
		for (Plugin plugin : plugins) {
			if (plugin.getName().equalsIgnoreCase(oldValue.getName())/* &&
					plugin.getStatus() == oldValue.getStatus()*/) {
				
				plugin.setName(newValue.getName());
				plugin.setStatus(newValue.getStatus());
			}
		}
	}
	
	//remove plugin -> UNINSTAL plugin
	public boolean removePlugin(Plugin oldPlugin) {
		return plugins.remove(oldPlugin);
	}

	public ArrayList<Plugin> getPlugins() {
		return plugins;
	}

	public void setPlugins(ArrayList<Plugin> plugins) {
		this.plugins = plugins;
	}
	
	public void clear(){
		plugins.clear();
	}
	
	/*PLUGIN operations*/
	
	//START the plugin for specific URL
	public boolean startPlugin(String pluginToStart){
		int start = Bundle.STARTING;
		
		for (Plugin plugin : plugins) {
			if (plugin.getName().equalsIgnoreCase(pluginToStart)) {
				plugin.setStatus(Integer.toString(start));
				return true;
			}
		}
		
		return false;
	}
	
	//STOP the plugin for specific URL
	public boolean stopPlugin(String pluginToStop){
		int stop = Bundle.STOPPING;
		
		for (Plugin plugin : plugins) {
			if (plugin.getName().equalsIgnoreCase(pluginToStop)) {
			plugin.setStatus(Integer.toString(stop));
				return true;
			}
		}
		
		return false;
	}
	
	//UNINSTALL the plugin for specific URL
	public boolean uninstalPlugin(String pluginToUninstall){
		int uninstall = BundleEvent.UNINSTALLED;
		
		for (Plugin plugin : plugins) {
			if (plugin.getName().equalsIgnoreCase(pluginToUninstall)) {
				plugin.setStatus(Integer.toString(uninstall));
				return true;
			}
		}
		
		return false;
	}
	
}
