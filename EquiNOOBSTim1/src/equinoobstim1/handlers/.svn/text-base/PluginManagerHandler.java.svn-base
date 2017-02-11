package equinoobstim1.handlers;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;

import equinoobstim1.pluginDialog.PluginMenagerDialog;


public class PluginManagerHandler {

	@Execute
	public void execute() {
		PluginMenagerDialog pluginManagerDialog = new PluginMenagerDialog(Display.getCurrent().getActiveShell());
		pluginManagerDialog.open();
	}
}
