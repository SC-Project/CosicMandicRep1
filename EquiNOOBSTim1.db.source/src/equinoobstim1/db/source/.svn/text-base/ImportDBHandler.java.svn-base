package equinoobstim1.db.source;

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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.model.PrvenstvoContainer;
import equinoobstim1.source.ISource;

public class ImportDBHandler { 
	
	@Inject
	EPartService partService;
	
	@Inject
	MApplication application;
    
	@Inject
	EModelService modelService;
	
	 @SuppressWarnings("unchecked")
	@Execute
	  public void execute() {
		 DBSourceDialog dbSourceDialog = new DBSourceDialog(Display.getCurrent().getActiveShell());
		 if(dbSourceDialog.open() == Window.OK){
		 try {
			 ISource loader = new DBSource();
			 Prvenstvo prvenstvo = new Prvenstvo();
			 prvenstvo = loader.load(dbSourceDialog.getImeBaze());
			 prvenstvo.setNaziv(dbSourceDialog.getImeBaze());
			 PrvenstvoContainer.setAktivnoPrvenstvo(prvenstvo.getNaziv());
			 PrvenstvoContainer.getInstance().addPrvenstvo(prvenstvo);
			 createPart(partService, application, modelService, prvenstvo.getNaziv());
			 //TreeView.getInstance().getViewer().setInput(PrvenstvoContainer.getInstance());
			 System.out.println(prvenstvo.getUtakmice());
		 } catch (Exception e) {
			 e.printStackTrace();
			 MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Info", "Ne posoji baza sa tim imenom");
		} 	
		 }
		 
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
