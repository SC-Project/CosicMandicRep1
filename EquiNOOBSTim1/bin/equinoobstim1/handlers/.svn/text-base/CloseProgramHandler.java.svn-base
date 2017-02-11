package equinoobstim1.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;

public class CloseProgramHandler {
	@Execute
	public void execute() {
		Display display = Display.getCurrent();
		// may be null if outside the UI thread
		if (display == null)
			display = Display.getDefault();
		display.close();
		System.out.println((this.getClass().getSimpleName() + " called"));
	}

}
