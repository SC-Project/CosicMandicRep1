package equinoobstim1.view.listeners;


import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.zest.core.widgets.Graph;

import equinoobstim1.view.SelectionUtil;

public class ItemSelectionListener extends SelectionAdapter {
	
	private Graph graph = null;

	public ItemSelectionListener(Graph graph) {
		this.graph = graph;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void widgetSelected(SelectionEvent e) {
		SelectionUtil.izvrsiSelekcijuTimova(((Graph) e.widget).getSelection(), graph.getNodes());
	}
	
	

}
