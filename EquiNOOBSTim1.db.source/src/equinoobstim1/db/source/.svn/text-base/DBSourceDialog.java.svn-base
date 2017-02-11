package equinoobstim1.db.source;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class DBSourceDialog extends Dialog {
	
	private Label lblImeBaze;
	private Text txtImeBaze;
	private String imeBaze;

	public DBSourceDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.getShell().setText("Citanje iz baze");
		
		GridLayout layout = new GridLayout(1, false); 
		layout.verticalSpacing = 10; 
		composite.setLayout(layout);
		
		Composite buttonComposite = new Composite(composite, SWT.NONE); 
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL); 
		fillLayout.spacing = 10; 
		buttonComposite.setLayout(fillLayout);
		
		lblImeBaze = new Label(buttonComposite, SWT.HORIZONTAL);
		lblImeBaze.setText("Ime Baze");
		txtImeBaze = new Text(buttonComposite, SWT.SINGLE | SWT.BORDER); 
		
		
		return composite;
	}
	
	@Override
	public void okPressed()
    {
		saveImput();
		super.okPressed();
    }
	
	public void saveImput() {
		imeBaze = txtImeBaze.getText();
	}

	public String getImeBaze() {
		return imeBaze;
	}

	public void setImeBaze(String imeBaze) {
		this.imeBaze = imeBaze;
	}
	
	

}
