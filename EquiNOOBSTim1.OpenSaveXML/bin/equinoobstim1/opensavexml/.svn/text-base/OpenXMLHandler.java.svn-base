package equinoobstim1.opensavexml;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.model.PrvenstvoContainer;
import equinoobstim1.source.ISource;

public class OpenXMLHandler {

	@Inject
	EPartService partService;
	
	@Inject
	MApplication application;
    
	@Inject
	EModelService modelService;
	
	@Execute
	public void execute() {
		Display display = Display.getCurrent();
		// may be null if outside the UI thread
		if (display == null)
			display = Display.getDefault();
		
		ISource source = new XMLSource();
		Prvenstvo prvenstvo = source.load(display);
		
		if(prvenstvo!=null){
			PrvenstvoContainer.setAktivnoPrvenstvo(prvenstvo.getNaziv());
			PrvenstvoContainer.getInstance().addPrvenstvo(prvenstvo);
			MessageBox messageBox = new MessageBox(display.getActiveShell(), SWT.ICON_INFORMATION |SWT.OK);
	    	messageBox.setMessage("Uspesno importovani podaci iz XML fajla");
	    	messageBox.open();
	    	createPart(partService, application, modelService, prvenstvo.getNaziv());
		}
				
		System.out.println((this.getClass().getSimpleName() + " called"));
	}
	
	 public void createPart(EPartService partService, MApplication application,
	         EModelService modelService, String naziv) {
	     MPart part = MBasicFactory.INSTANCE.createPart();
	     part.setLabel(naziv);
	     part.setElementId("vizualizatorPartStack." + naziv);
	     part.setContributionURI("platform:/plugin/EquiNOOBSTim1.view/equinoobstim1.view.View");
	      List<MPartStack> stacks = modelService.findElements(application, null,
	             MPartStack.class, null);
	      stacks.get(0).getChildren().add(part);
	      partService.showPart(part, PartState.ACTIVATE);
	 }
}
