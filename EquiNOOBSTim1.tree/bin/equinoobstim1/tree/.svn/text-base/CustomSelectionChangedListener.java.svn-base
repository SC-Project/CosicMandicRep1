package equinoobstim1.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import equinoobstim1.model.PrvenstvoContainer;
import equinoobstim1.model.Tim;
import equinoobstim1.model.Utakmica;
import equinoobstim1.model.UtilVratiPrvenstvo;

public class CustomSelectionChangedListener implements
		ISelectionChangedListener {

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		ArrayList<Utakmica> utakmice = new ArrayList<Utakmica>();
		ArrayList<Tim> timovi = new ArrayList<Tim>();

		IStructuredSelection thisSelection = (IStructuredSelection) event
				.getSelection();

		@SuppressWarnings("unchecked")
		List<Object> objectList = thisSelection.toList();

		for (Object object : objectList) {
			if (object instanceof Utakmica) {
				utakmice.add((Utakmica) object);
			} else if (object instanceof Tim) {
				timovi.add((Tim) object);
			}
		}
		
		UtilVratiPrvenstvo.vratiPrvenstvo(PrvenstvoContainer.getAktivnoPrvenstvo()).setSelektovaneStablo(utakmice, timovi);
		
	}

}
