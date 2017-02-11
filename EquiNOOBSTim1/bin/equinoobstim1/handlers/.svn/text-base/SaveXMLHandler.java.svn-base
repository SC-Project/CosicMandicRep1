package equinoobstim1.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;

import equinoobstim1.model.PrvenstvoContainer;
import equinoobstim1.model.UtilVratiPrvenstvo;
import equinoobstim1.opensavexml.XMLSource;
import equinoobstim1.source.ISource;

public class SaveXMLHandler  { 
	 @Execute
	  public void execute() {
		 Display display = Display.getCurrent();
			// may be null if outside the UI thread
			if (display == null)
				display = Display.getDefault();
		ISource source = new XMLSource();
		
		source.save(UtilVratiPrvenstvo.vratiPrvenstvo(PrvenstvoContainer.getAktivnoPrvenstvo()), display);
	
	    System.out.println((this.getClass().getSimpleName() + " called"));
	  }
}
