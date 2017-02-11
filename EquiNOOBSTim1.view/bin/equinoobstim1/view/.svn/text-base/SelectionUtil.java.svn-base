package equinoobstim1.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.zest.core.widgets.CGraphNode;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphItem;

import equinoobstim1.model.PrvenstvoContainer;
import equinoobstim1.model.Tim;
import equinoobstim1.model.Utakmica;
import equinoobstim1.view.figure.RoundedRectangleBorder;

public class SelectionUtil {
	
	public static void izvrsiSelekcijuTimova(List<Object> listaSelektovanih, List<CGraphNode> listaSvihNodova){
		List<CGraphNode> listaSelektovanihNodova = new ArrayList<CGraphNode>();
		for (Object ob : listaSelektovanih) {
			if (ob instanceof CGraphNode) {
				listaSelektovanihNodova.add((CGraphNode) ob);
			}
		}

		for (CGraphNode cgn : listaSvihNodova)
			if (listaSelektovanihNodova.contains(cgn))
				cgn.getFigure().setBorder(
						new RoundedRectangleBorder(50, 50));
			else
				cgn.getFigure().setBorder(null);
	}
	
	public static List<GraphItem> getSelektovaneUtakmice(Graph graph, ArrayList<Utakmica> utakmice){
		List<GraphItem> retVal = new ArrayList<GraphItem>();
		for(Object gc : graph.getConnections()){
			for(Utakmica u : utakmice){
				if(((GraphConnection) gc).getData().equals(u)){
					retVal.add((GraphConnection) gc);
				}
			}
		}
		return retVal;
	}
	
	public static void proslediSelektovaneTimoveIUtakmice(Graph graph){
		ArrayList<Tim> selektovaniTimovi = new ArrayList<>();
		ArrayList<Utakmica> selektovaneUtakmice = new ArrayList<>();
		
		for(Object obj : graph.getSelection()){
			if(obj instanceof CGraphNode)
				selektovaniTimovi.add((Tim)((CGraphNode)obj).getData());
			else if(obj instanceof GraphConnection)
				selektovaneUtakmice.add((Utakmica)((GraphConnection)obj).getData());
		}
		
		PrvenstvoContainer.getInstance().setSelectedFromViewToModel(selektovaniTimovi, selektovaneUtakmice);
	}
}
