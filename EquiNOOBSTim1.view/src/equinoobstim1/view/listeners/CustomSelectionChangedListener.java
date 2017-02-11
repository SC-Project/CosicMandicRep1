package equinoobstim1.view.listeners;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.zest.core.widgets.Graph;

import equinoobstim1.view.SelectionUtil;

public class CustomSelectionChangedListener implements
		ISelectionChangedListener {

	private Graph graph;
	
	public CustomSelectionChangedListener(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		SelectionUtil.proslediSelektovaneTimoveIUtakmice(graph);
	}
}
