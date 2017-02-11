package equinoobstim1.tree;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.model.Tim;
import equinoobstim1.model.Timovi;
import equinoobstim1.model.Utakmica;
import equinoobstim1.model.Utakmice;

public class CustomTreeContentProvider implements ITreeContentProvider {

	private boolean treeInputSet = false;
	private Object[] EMPTY_ARRAY = new Object[0];
	private ArrayList<Prvenstvo> source;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		source = (ArrayList<Prvenstvo>) newInput;

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (treeInputSet) {
			return getChildren(inputElement);
		} else {
			treeInputSet = true;
			return new Object[] { inputElement };
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof ArrayList) {
			
			return ((ArrayList<Prvenstvo>)parentElement).toArray();
			
		}else if (parentElement instanceof Prvenstvo) {

			Prvenstvo prvenstvno = (Prvenstvo) parentElement;
			Object[] all = new Object[2];
			all[0] = prvenstvno.getTimovi();
			all[1] = prvenstvno.getUtakmice();

			return all;

		} else if (parentElement instanceof Timovi) {

			Timovi timovi = (Timovi) parentElement;

			return timovi.getTimovi().toArray();

		} else if (parentElement instanceof Utakmice) {

			Utakmice utakmice = (Utakmice) parentElement;

			return utakmice.getUtakmice().toArray();

		} else if (parentElement instanceof Tim) {

			Tim tim = (Tim) parentElement;

			Object[] all = new Object[3];

			all[0] = "Prvo prvenstvo: " + tim.getPrvoPrvenstvo().toString();
			all[1] = "Broj ucesca: " + tim.getBrojUcesca();
			all[2] = "Najveci uspeh: " + tim.getNajveciUspeh();

			return all;

		} else if (parentElement instanceof Utakmica) {

			Utakmica utakmica = (Utakmica) parentElement;

			Object[] all = new Object[6];

			all[0] = "Datum: " + utakmica.getDatum().toString();
			all[1] = "Stadion: " + utakmica.getStadion();
			all[2] = utakmica.getNazivDomaci() + ": "
					+ utakmica.getGoloviDomaci();
			all[3] = utakmica.getNazivGosti() + ": "
					+ utakmica.getGoloviGosti();
			all[4] = "Crveni kartoni: " + utakmica.getBrojCrvenihKartona();
			all[5] = "Zuti kartoni: " + utakmica.getBrojZutihKartona();

			return all;

		}

		return EMPTY_ARRAY;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Tim || element instanceof Utakmica) {
			return true;
		} else if (element instanceof Prvenstvo) {

			Prvenstvo prvenstvo = (Prvenstvo) element;

			if (prvenstvo.getTimovi() != null
					|| prvenstvo.getUtakmice() != null)
				return true;
			return false;

		} else if (element instanceof Utakmice) {
			return (((Utakmice) element).getUtakmice().size() > 0);
		} else if (element instanceof Timovi) {
			return (((Timovi) element).getTimovi().size() > 0);
		}
		return false;
	}

}
