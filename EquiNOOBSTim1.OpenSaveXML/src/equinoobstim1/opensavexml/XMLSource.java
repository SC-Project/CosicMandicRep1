package equinoobstim1.opensavexml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.source.ISource;

public class XMLSource implements ISource{

	@Override
	public Prvenstvo load(String dbName) {
		return null;
	}

	@Override
	public void save(Prvenstvo prvenstvo, String dbName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Prvenstvo load(Display display) {
		FileDialog fd = new FileDialog(display.getActiveShell(), SWT.OPEN);
        fd.setText("Open XML");
        fd.setFilterPath("C:/Users/Nenad/Desktop");
        String[] filterExt = { "*.xml" };
        fd.setFilterExtensions(filterExt);
        String fileName = fd.open();
        
        if(fileName!=null){
	        XStream xstream = new XStream(new DomDriver());
	
	        try
	        {
	                BufferedReader in = new BufferedReader(new InputStreamReader(
	                                new FileInputStream(fileName)));
	                Prvenstvo prvenstvo = (Prvenstvo) xstream.fromXML(in);
	                
	                int begin = fileName.lastIndexOf('\\');
	                int end = fileName.lastIndexOf('.');
	                
	                String naziv = fileName.substring(begin+1, end);
	                
	                prvenstvo.setNaziv(naziv);
	                
	                return prvenstvo;
	
	        } catch (FileNotFoundException e)
	        {
	                e.printStackTrace();
	                return null;
	        }
        } else {
        	return null;
        }
	}

	@Override
	public void save(Prvenstvo prvenstvo, Display display) {
		FileDialog fd = new FileDialog(display.getActiveShell(), SWT.SAVE);
        fd.setText("Save XML");
        fd.setFilterPath("C:/Users/Nenad/Desktop");
        String[] filterExt = { "*.xml" };
        fd.setFilterExtensions(filterExt);
        String fileName = fd.open();
		prvenstvo.deleteObservers();
        
        if(fileName!=null){
			XStream xstream = new XStream(new DomDriver());
	
			try {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(fileName), "UTF8"));
				xstream.toXML(prvenstvo, out);
				MessageBox messageBox = new MessageBox(display.getActiveShell(), SWT.ICON_INFORMATION |SWT.OK );
		    	messageBox.setMessage("Uspesno sacuvani podaci u XML fajl");
		    	messageBox.open();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        }
	}
}
