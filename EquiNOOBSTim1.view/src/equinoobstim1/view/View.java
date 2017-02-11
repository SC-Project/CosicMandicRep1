package equinoobstim1.view;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.CGraphNode;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphItem;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.osgi.framework.Bundle;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.model.PrvenstvoContainer;
import equinoobstim1.model.Tim;
import equinoobstim1.model.UpdateState;
import equinoobstim1.model.UpdateStatePrvenstvo;
import equinoobstim1.model.Utakmica;
import equinoobstim1.model.UtilVratiPrvenstvo;
import equinoobstim1.view.figure.NodeFigure;
import equinoobstim1.view.listeners.CustomSelectionChangedListener;
import equinoobstim1.view.listeners.ItemDoubleClickListener;
import equinoobstim1.view.listeners.ItemSelectionListener;
import equinoobstim1.view.listeners.ZoomInMouseListener;
import equinoobstim1.view.listeners.ZoomOutMouseListener;
import equinoobstim1.view.listeners.ZoomWheelListener;

@SuppressWarnings("deprecation")
public class View extends ViewPart implements Observer {
	private Prvenstvo prvenstvo = null;
	private Graph graph;
	private GraphViewer viewer = null;
	private SashForm sashForm = null;
	private Composite commands = null;
	private Button zoomOut = null;
	private Button zoomIn = null;
	private EquiZoomManager zoomManager = null;

	HashMap<String, GraphNode> iscrtaniTimoviNodes = null;
	HashMap<String, Tim> timoviUBazi = null;
	ArrayList<GraphConnection> utakmiceConnections = null;

	@Inject
	public View(Composite parent) {
		initView(parent);
		String aktivno = PrvenstvoContainer.getAktivnoPrvenstvo();
		prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(aktivno);
		prvenstvo.addObserver(this);
		if (prvenstvo != null)
			createPartControl(parent);
	}

	public void initView(Composite parent) {
		sashForm = new SashForm(parent, SWT.HORIZONTAL);
		commands = new Composite(sashForm, SWT.NONE);
		commands.setLayout(new org.eclipse.swt.layout.GridLayout());
		commands.setBackground(new Color(null, 255, 255, 255));

		zoomIn = new Button(commands, SWT.PUSH);
		zoomOut = new Button(commands, SWT.PUSH);
		zoomIn.setText("+");
		zoomOut.setText(" -");

		viewer = new GraphViewer(sashForm, SWT.NONE);
		graph = viewer.getGraphControl();
		graph.setPreferredSize(1366, 768);
		sashForm.setWeights(new int[] { 1, 20 });
	}

	@SuppressWarnings("deprecation")
	@Override
	public void createPartControl(Composite parent) {

		iscrtaniTimoviNodes = new HashMap<>();
		timoviUBazi = new HashMap<>();
		utakmiceConnections = new ArrayList<>();

		for (int i = 0; i < prvenstvo.getTimovi().getTimovi().size(); i++)
			timoviUBazi.put(prvenstvo.getTimovi().getTimovi().get(i)
					.getNazivTima(), prvenstvo.getTimovi().getTimovi().get(i));

		for (int i = 0; i < prvenstvo.getUtakmice().getUtakmice().size(); i++) {
			Utakmica u = prvenstvo.getUtakmice().getUtakmice().get(i);
			String rezultat = prvenstvo.getUtakmice().getUtakmice().get(i)
					.getGoloviDomaci()
					+ ":"
					+ prvenstvo.getUtakmice().getUtakmice().get(i)
							.getGoloviGosti();

			if (!iscrtaniTimoviNodes.containsKey(u.getNazivDomaci())) {
				Image img1 = new Image(null, getPath(u.getNazivDomaci()));
				CGraphNode domaci = new CGraphNode(graph, SWT.NONE, getFigure(
						null, img1, null, 50, 50));
				domaci.getFigure().setToolTip(
						new Label("\n " + u.getNazivDomaci() + " \n"));
				domaci.setData(timoviUBazi.get(u.getNazivDomaci()));
				iscrtaniTimoviNodes.put(u.getNazivDomaci(), domaci);
			}

			if (!iscrtaniTimoviNodes.containsKey(u.getNazivGosti())) {
				Image img2 = new Image(null, getPath(u.getNazivGosti()));
				CGraphNode gosti = new CGraphNode(graph, SWT.NONE, getFigure(
						null, img2, null, 50, 50));
				gosti.getFigure().setToolTip(
						new Label("\n " + u.getNazivGosti() + " \n"));
				gosti.setData(timoviUBazi.get(u.getNazivGosti()));
				iscrtaniTimoviNodes.put(u.getNazivGosti(), gosti);
			}

			GraphConnection utakmica = new GraphConnection(graph,
					ZestStyles.CONNECTIONS_DIRECTED, iscrtaniTimoviNodes.get(u
							.getNazivDomaci()), iscrtaniTimoviNodes.get(u
							.getNazivGosti()));

			utakmiceConnections.add(utakmica);
			utakmica.setCurveDepth(7);
			Image imgtooltip = new Image(null, getPath("teren_tooltip"));
			utakmica.setTooltip(getFigure(null, imgtooltip, rezultat, 128, 128));
			utakmica.setData(u);
		}

		zoomManager = new EquiZoomManager(graph.getRootLayer(), graph.getViewport());

		// LISTENERS
		graph.addMouseWheelListener(new ZoomWheelListener(zoomManager, graph));
		zoomIn.addMouseListener(new ZoomInMouseListener(zoomManager));
		zoomOut.addMouseListener(new ZoomOutMouseListener(zoomManager));
		graph.addSelectionListener(new ItemSelectionListener(graph));
		viewer.addSelectionChangedListener(new CustomSelectionChangedListener(
				graph));
		viewer.addDoubleClickListener(new ItemDoubleClickListener());

		/*graph.setLayoutAlgorithm(new CompositeLayoutAlgorithm(
				LayoutStyles.NO_LAYOUT_NODE_RESIZING,
				new LayoutAlgorithm[] { new HorizontalTreeLayoutAlgorithm(
						LayoutStyles.NO_LAYOUT_NODE_RESIZING) }), true);*/
	}

	public String getPath(String s) {
		String retVal = "";
		Bundle bundle = Platform.getBundle("EquiNOOBSTim1.view");
		URL fileURL = bundle.getEntry("icons");
		File file = null;
		try {
			file = new File(FileLocator.resolve(fileURL).toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		retVal = file.getAbsolutePath();
		retVal = retVal + "/" + s + ".png";
		retVal = retVal.replaceAll(" ", "_");
		return retVal;
	}

	@Override
	public void setFocus() {

	}

	public IFigure getFigure(Object element, Image image, String name,
			int sirina, int visina) {
		IFigure figure = new NodeFigure(image, name);
		figure.setSize(sirina, visina);
		return figure;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1) {
		UpdateStatePrvenstvo ups = (UpdateStatePrvenstvo) arg1;
		Prvenstvo p = (Prvenstvo) ups.getPrvenstvo();
		ArrayList<Utakmica> utakmice = ups.getPrvenstvo().getUtakmice()
				.getUtakmice();
		ArrayList<Tim> timovi = ups.getPrvenstvo().getTimovi().getTimovi();

		if (p.getNaziv().equals(prvenstvo.getNaziv())) {
			if (ups.getUpdateState().equals(UpdateState.TimFindSelekcija)) {
				graph.getSelection().clear();
				CGraphNode cg = (CGraphNode) iscrtaniTimoviNodes.get(timovi
						.get(0).getNazivTima());
				graph.getSelection().add(cg);
				SelectionUtil.izvrsiSelekcijuTimova(graph.getSelection(),
						graph.getNodes());
				SelectionUtil.proslediSelektovaneTimoveIUtakmice(graph);
			} else if (ups.getUpdateState().equals(
					UpdateState.UtakmicaFindSelekcija)) {
				List<GraphItem> selektovani = SelectionUtil
						.getSelektovaneUtakmice(graph, utakmice);
				graph.setSelection(selektovani
						.toArray(new GraphItem[selektovani.size()]));
				SelectionUtil.proslediSelektovaneTimoveIUtakmice(graph);
			} else if (ups.getUpdateState().equals(
					UpdateState.StabloSelekcijaIzModela)) {
				List<GraphItem> listaSelektovanih = SelectionUtil
						.getSelektovaneUtakmice(graph, utakmice);
				for (Tim t : timovi) {
					CGraphNode cg = (CGraphNode) iscrtaniTimoviNodes.get(t
							.getNazivTima());
					listaSelektovanih.add(cg);
				}
				graph.setSelection(listaSelektovanih
						.toArray(new GraphItem[listaSelektovanih.size()]));
				SelectionUtil.izvrsiSelekcijuTimova(graph.getSelection(),
						graph.getNodes());

			} else if (ups.getUpdateState().equals(UpdateState.FiltriranjeTim)) {

				Tim tim = timovi.get(0);
				ArrayList<String> protivnici = new ArrayList<>();
				protivnici.add(tim.getNazivTima());
				for (GraphConnection gc : utakmiceConnections) {
					Utakmica u = (Utakmica) gc.getData();
					if (u.getNazivDomaci().equals(tim.getNazivTima())) {
						protivnici.add(u.getNazivGosti());
					} else if (u.getNazivGosti().equals(tim.getNazivTima())) {
						protivnici.add(u.getNazivDomaci());
					} else if (!u.getNazivDomaci().equals(tim.getNazivTima())
							&& !u.getNazivGosti().equals(tim.getNazivTima()))
						gc.setVisible(false);
				}

				Iterator<String> iter = iscrtaniTimoviNodes.keySet().iterator();
				while (iter.hasNext()) {
					boolean b = true;
					String key = (String) iter.next();
					CGraphNode val = (CGraphNode) iscrtaniTimoviNodes.get(key);
					Tim t = ((Tim) val.getData());
					for (String s : protivnici) {
						if (t.getNazivTima().equals(s))
							b = false;
					}

					if (b)
						val.setVisible(false);

					if (t.getNazivTima().equals(tim.getNazivTima())) {
						graph.getSelection().clear();
						graph.getSelection().add(val);
					}

				}

				SelectionUtil.proslediSelektovaneTimoveIUtakmice(graph);

			} else if (ups.getUpdateState().equals(UpdateState.FiltriranjeUtakmica)) {
				ArrayList<String> timoviKojiIgrajuUtakmice = new ArrayList<>();
				for (GraphConnection gc : utakmiceConnections) {
					Utakmica u = (Utakmica) gc.getData();
					boolean b = true;
					for (Utakmica uFilter : utakmice) {
						if (u.equals(uFilter)) {
							b = false;
							timoviKojiIgrajuUtakmice.add(u.getNazivDomaci());
							timoviKojiIgrajuUtakmice.add(u.getNazivGosti());
						}
					}
					if (b)
						gc.setVisible(false);
				}

				Iterator<String> iter = iscrtaniTimoviNodes.keySet().iterator();
				while (iter.hasNext()) {
					boolean b = true;
					String key = (String) iter.next();
					CGraphNode val = (CGraphNode) iscrtaniTimoviNodes.get(key);
					Tim t = ((Tim) val.getData());
					for (String s : timoviKojiIgrajuUtakmice) {
						if (t.getNazivTima().equals(s))
							b = false;
					}

					if (b)
						val.setVisible(false);

				}
				List<GraphItem> listaSelektovanih = SelectionUtil
						.getSelektovaneUtakmice(graph, utakmice);
				graph.setSelection(listaSelektovanih
						.toArray(new GraphItem[listaSelektovanih.size()]));
				SelectionUtil.proslediSelektovaneTimoveIUtakmice(graph);

			}else if (ups.getUpdateState().equals(UpdateState.DodatTimIzKonzole)) {
				Tim t = timovi.get(timovi.size()-1);
				Image img1 = new Image(null, getPath(t.getNazivTima()));
				CGraphNode noviTim = new CGraphNode(graph, SWT.NONE, getFigure(
						null, img1, null, 50, 50));
				noviTim.getFigure().setToolTip(
						new Label("\n " + t.getNazivTima() + " \n"));
				noviTim.setData(t);
				iscrtaniTimoviNodes.put(t.getNazivTima(), noviTim);
				graph.setSelection(new GraphItem[] {noviTim});
				SelectionUtil.izvrsiSelekcijuTimova(graph.getSelection(), graph.getNodes());
				SelectionUtil.proslediSelektovaneTimoveIUtakmice(graph);
				
			}else if (ups.getUpdateState().equals(UpdateState.DodataUtakmicaIzKonzole)) {
				Utakmica u = utakmice.get(utakmice.size()-1);
				String rezultat = u.getGoloviDomaci() + ":" + u.getGoloviGosti();
				GraphConnection novaUtakmica = new GraphConnection(graph,
						ZestStyles.CONNECTIONS_DIRECTED, iscrtaniTimoviNodes.get(u
								.getNazivDomaci()), iscrtaniTimoviNodes.get(u
								.getNazivGosti()));

				utakmiceConnections.add(novaUtakmica);
				novaUtakmica.setCurveDepth(7);
				Image imgtooltip = new Image(null, getPath("teren_tooltip"));
				novaUtakmica.setTooltip(getFigure(null, imgtooltip, rezultat, 128, 128));
				novaUtakmica.setData(u);
				graph.setSelection(new GraphItem[] {novaUtakmica});
				SelectionUtil.proslediSelektovaneTimoveIUtakmice(graph);
			}

		}
	}
	

}