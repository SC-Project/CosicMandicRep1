package equinoobstim1.view.console;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;


public class ConsoleView  extends ViewPart{

	private Text text;
	
	@SuppressWarnings("static-access")
	@Inject
	public ConsoleView(Composite parent) {
		initView(parent);
	}
	
	public void initView(Composite parent) {
			
			    text = new Text(parent, SWT.HORIZONTAL | SWT.BORDER);
			    text.setBounds(100, 75, 100, 20);
			    
			    text.addFocusListener(focusListener);
			    text.addKeyListener(enterListener);
			   // text.setText("");
			    
		
		}
		
		KeyListener enterListener = new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				//System.out.println(e.keyCode);
				if(e.keyCode == 13)
				{
					text = (Text) e.widget;
					text.selectAll();
			          String console = text.getText();
			          text.getSelectionCount();
			          System.out.println(  text.getLineDelimiter());
			          text.clearSelection();
			          text.setText("add->");
			          
			          if(console.substring(0, 13).equals("add->utakmica"))
			          {
			        	  ParseUtakmice parseUtakmice = new ParseUtakmice(console);
				          parseUtakmice.addUtakmica();
			          }
			          
			          if(console.substring(0,8).equals("add->tim"))
			          {
			        	  ParseTim parseTim = new ParseTim(console);
				          parseTim.addTim();
			        	  
			          }
			          
			          
			        	  
			         
			          
			          
			          
			        //  t.clearSelection();
					
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println(e.keyCode + "pres");
				
				// TODO Auto-generated method stub
			/*	System.out.println( e.character);
			//	if
				
				Text t = (Text) e.widget;
		          t.selectAll();
		          String baja = t.getText();
		          char[] c = t.getTextChars();
		          System.out.println( c[c.length-1] + "karakter");
		        // String value = t.getValue();
		          System.out.println(baja + "baja1");
				*/
			}
		};
		
		FocusListener focusListener = new FocusListener() {
	        public void focusGained(FocusEvent e) {
	      /*    Text t = (Text) e.widget;
	          t.selectAll();
	          String baja = t.getText();
	        // String value = t.getValue();
	          System.out.println(baja + "baja");
	          */
	        }
		
	        public void focusLost(FocusEvent e) {
	        /*  Text t = (Text) e.widget;
	          if (t.getSelectionCount() > 0) {
	            t.clearSelection();
	          }
	          */
	          
	          
	        }
	       
	      };

	
	
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
