package equinoobstim1.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;

import equinoobstim1.dialogs.FindDialog;

public class FindHandler {
	
	@Execute
	public void execute() {
		
		Display display = Display.getCurrent();

		FindDialog f = new FindDialog(display.getActiveShell());
		f.open();
		
	}

}
