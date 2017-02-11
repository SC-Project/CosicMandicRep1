package equinoobstim1.dialogs;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.model.PrvenstvoContainer;
import equinoobstim1.model.Tim;
import equinoobstim1.model.Utakmica;
import equinoobstim1.model.UtilVratiPrvenstvo;

public class FilterDialog extends Dialog {

	private Label lblFind;
	private Text txtFind;
	private String find;
	JRadioButton tim;
	JRadioButton utakmica;
	Button timButton;
	Button utakmicaButton;
	private Prvenstvo prvenstvo = null;

	public FilterDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.getShell().setText("Filtriranje");

		Composite radioButtonGroupContainer = new Composite(composite, SWT.NONE);
		radioButtonGroupContainer.setLayout(new GridLayout());
		Label question = new Label(radioButtonGroupContainer, SWT.NONE);
		question.setText("Filtriraj po:");

		timButton = new Button(radioButtonGroupContainer, SWT.RADIO);
		timButton.setText("Timu");
		timButton.setSelection(true);
		utakmicaButton = new Button(radioButtonGroupContainer, SWT.RADIO);
		utakmicaButton.setText("Rezultatu utakmice");
		timButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblFind.setText(" Naziv tima:");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		utakmicaButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblFind.setText(" Rezultat:");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		tim = new JRadioButton("Tim", false);
		utakmica = new JRadioButton("Utakmica", true);

		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(tim);
		bgroup.add(utakmica);

		GridLayout layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		composite.setLayout(layout);

		Composite buttonComposite = new Composite(composite, SWT.NONE);
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		fillLayout.spacing = 10;
		buttonComposite.setLayout(fillLayout);

		lblFind = new Label(buttonComposite, SWT.HORIZONTAL);
		lblFind.setText("Naziv tima");
		txtFind = new Text(buttonComposite, SWT.SINGLE | SWT.BORDER);

		return composite;
	}

	@Override
	public void okPressed() {
		find();
		super.okPressed();

	}

	public void find() {

		String aktivno = PrvenstvoContainer.getAktivnoPrvenstvo();
		prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(aktivno);
		find = txtFind.getText().toLowerCase();

		if (timButton.getSelection() == true) {
			Tim t = null;
			for (int i = 0; i < prvenstvo.getTimovi().getTimovi().size(); i++) {
				Tim temp = prvenstvo.getTimovi().getTimovi().get(i);
				if (temp.getNazivTima().toLowerCase().equals(find)) {
					t = temp;
					break;
				}
			}

			if (t != null)
				PrvenstvoContainer.getInstance().filtriranjeTim(t);
			else
				MessageDialog.openWarning(
						Display.getCurrent().getActiveShell(), "!",
						"Ne postoji tim sa nazivom: " + find);
		}

		if (utakmicaButton.getSelection() == true) {
			String rez[] = find.split(":");

			String domaci = rez[0];
			String gosti = rez[1];

			int domaciRez = Integer.parseInt(domaci);
			int gostiRez = Integer.parseInt(gosti);

			ArrayList<Utakmica> utakmice = new ArrayList<Utakmica>();

			for (int i = 0; i < prvenstvo.getUtakmice().getUtakmice().size(); i++) {
				Utakmica utak = prvenstvo.getUtakmice().getUtakmice().get(i);
				if (utak.getGoloviDomaci() == domaciRez
						&& utak.getGoloviGosti() == gostiRez) {
					utakmice.add(utak);

				}
			}

			if (!utakmice.isEmpty() && utakmice != null)
				PrvenstvoContainer.getInstance().filtriranjeUtakmice(utakmice);
			else
				MessageDialog.openWarning(
						Display.getCurrent().getActiveShell(), "!",
						"Ne postoje utakmice sa rezultatom " + domaci + " : " + gosti);

		}

	}

}
