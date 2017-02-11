package equinoobstim1.view.listeners;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;

import equinoobstim1.model.Tim;
import equinoobstim1.model.Utakmica;

public class ItemDoubleClickListener implements IDoubleClickListener{

	@Override
	public void doubleClick(DoubleClickEvent event) {
		IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
        Object selectedNode = thisSelection.getFirstElement();
        if(selectedNode instanceof Utakmica){
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Info utakmice", selectedNode.toString());
        }else if(selectedNode instanceof Tim){
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Info tima", selectedNode.toString());
        }
	}

}
