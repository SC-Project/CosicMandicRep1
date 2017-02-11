package equinoobstim1.db.source;
import java.sql.SQLException;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.model.PrvenstvoContainer;
import equinoobstim1.model.UtilVratiPrvenstvo;
import equinoobstim1.source.ISource;


public class ExportDBHandler {

	@Execute
	public void execute(){
		// TODO Auto-generated method stub
		DBSourceDialog dbSourceDialog = new DBSourceDialog(Display.getCurrent().getActiveShell());
		 if(dbSourceDialog.open() == Window.OK){
		try {
			ISource source = new DBSource();
			Prvenstvo prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(PrvenstvoContainer.getAktivnoPrvenstvo());
			source.save(prvenstvo, dbSourceDialog.getImeBaze());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }
	}

}
