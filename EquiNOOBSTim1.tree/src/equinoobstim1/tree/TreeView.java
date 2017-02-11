package equinoobstim1.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.ViewPart;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.model.PrvenstvoContainer;
import equinoobstim1.model.Tim;
import equinoobstim1.model.UpdateState;
import equinoobstim1.model.UpdateStatePrvenstvo;
import equinoobstim1.model.Utakmica;

public class TreeView extends ViewPart implements Observer {

	protected TreeViewer viewer;
	protected List<Prvenstvo> prvenstva;
	private static TreeView instance;

	@SuppressWarnings("static-access")
	@Inject
	public TreeView(Composite parent) {
		this.prvenstva = PrvenstvoContainer.getInstance().getPrvenstva();
		createPartControl(parent);
		instance = this;
	}

	@Override
	public void createPartControl(Composite parent) {

		FilteredTree filteredTree = new FilteredTree(parent, SWT.MULTI,
				new PatternFilter(), true);
		viewer = filteredTree.getViewer();
		viewer.setContentProvider(new CustomTreeContentProvider());
		viewer.setLabelProvider(new CustomTreeLabelProvider());
		viewer.setUseHashlookup(true);
		viewer.setInput(prvenstva);
		PrvenstvoContainer.getInstance().addObserver(this);
		viewer.addSelectionChangedListener(new CustomSelectionChangedListener());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	public TreeViewer getViewer() {
		return viewer;
	}

	public void setViewer(TreeViewer viewer) {
		this.viewer = viewer;
	}

	public static TreeView getInstance() {
		return instance;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		UpdateStatePrvenstvo updateStatePrvenstvo = (UpdateStatePrvenstvo) arg1;
		List<Tim> timovi= updateStatePrvenstvo.getPrvenstvo().getTimovi().getTimovi();
		List<Utakmica> utakmice= updateStatePrvenstvo.getPrvenstvo().getUtakmice().getUtakmice();
		if (updateStatePrvenstvo.getUpdateState().equals(
				UpdateState.AddPrvenstvno )) {
			viewer.setInput(prvenstva);
		} else if (updateStatePrvenstvo.getUpdateState().equals(
				UpdateState.StabloSelekcijaIzModela)) {
			viewer.expandToLevel(3);
			setSelekcijaStablo(updateStatePrvenstvo.getPrvenstvo());
		}else if (updateStatePrvenstvo.getUpdateState().equals(
				UpdateState.DodatTimIzKonzole)) {
			viewer.refresh();
			selektujDodat(timovi.get(timovi.size()-1));
		}
		else if (updateStatePrvenstvo.getUpdateState().equals(
				UpdateState.DodataUtakmicaIzKonzole)) {
			viewer.refresh();
			selektujDodat(utakmice.get(utakmice.size()-1));
		}

	}

	public void setSelekcijaStablo(Prvenstvo prvenstvo) {
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(prvenstvo.getTimovi().getTimovi());
		objects.addAll(prvenstvo.getUtakmice().getUtakmice());
		StructuredSelection selekcija = new StructuredSelection(objects);
		viewer.setSelection(selekcija);
	}
	
	public void selektujDodat(Object obj){
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.add(obj);
		StructuredSelection selekcija = new StructuredSelection(objects);
		viewer.setSelection(selekcija);
	}

}
