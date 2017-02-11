package equinoobstim1.pluginMenager;

import java.util.ArrayList;

import org.eclipse.core.databinding.*;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.osgi.framework.BundleException;

public class SelectionListener extends SelectionAdapter {
	
	protected TableViewer tableViewer;
	protected DataBindingContext m_bindingContext;
	
	
	public SelectionListener(TableViewer tableViewer,DataBindingContext m_bindingContext) {
		this.tableViewer = tableViewer;
		this.m_bindingContext = m_bindingContext;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		super.widgetSelected(e);
		
		IStructuredSelection sel = (IStructuredSelection) tableViewer.getSelection();
		Plugin plugin = (Plugin) sel.getFirstElement();
		Button button = (Button) e.getSource();
		
		if (plugin != null) {
			
			try{
				
				if (button.getText().equalsIgnoreCase(PluginMenager.start)) {
					System.out.println("dosao uvde u start");
					PluginMenager.getPluginMenger().start(plugin.getName());
				}else if(button.getText().equalsIgnoreCase(PluginMenager.stop)){
					System.out.println("dosao uvde u stop");
					PluginMenager.getPluginMenger().stop(plugin.getName());
				}else{
					PluginMenager.getPluginMenger().uninstall(plugin.getName());
				}
				
				m_bindingContext.updateModels();
				m_bindingContext.updateTargets();
				tableViewer.refresh();
				
				ArrayList<Plugin> plugins = PluginMenager.getPluginMenger().getPlugins().getPlugins();
				
				for (Plugin pluginA : plugins) {
					Plugin p = pluginA;
					
					
					if(p.getName().contains("EquiNOOBSTim1"))
					{
						System.out.println(p.getName() + " " + p.getStatus());

					}
				}
				
			}catch (BundleException be) {
				be.printStackTrace();
			}	
		}
	}
}
