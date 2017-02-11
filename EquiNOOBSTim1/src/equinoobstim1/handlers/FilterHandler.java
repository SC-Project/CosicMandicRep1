package equinoobstim1.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;

import equinoobstim1.dialogs.FilterDialog;


public class FilterHandler {

	@Execute
	public void execute() {
		
		FilterDialog f = new FilterDialog(Display.getCurrent().getActiveShell());
		f.open();
		
	}
}
