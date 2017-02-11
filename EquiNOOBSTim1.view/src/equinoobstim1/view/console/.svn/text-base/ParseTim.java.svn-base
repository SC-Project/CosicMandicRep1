package equinoobstim1.view.console;

import java.sql.Date;
import java.util.StringTokenizer;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.model.PrvenstvoContainer;
import equinoobstim1.model.Tim;
import equinoobstim1.model.UtilVratiPrvenstvo;

public class ParseTim {
	
	String text;
    Prvenstvo prvenstvo = new Prvenstvo();


	public ParseTim(String text) {
		super();
		this.text = text;
		
	}
	
	public void addTim() {
		Tim addTim = new Tim();
		
		String indikator = text.trim().substring(0,8);
		
		String utakmica = text.trim().substring(9).trim();
		
		
		if(indikator.equals("add->tim"))
		{
		
			
			String token[] = {"","","",""};
			int i = 0;
			StringTokenizer st = new StringTokenizer(utakmica, ",");
		     while (st.hasMoreTokens()) {
		    	 String a = st.nextToken();
		         token[i] = a.trim();
		         i++;
		     }
			
		     addTim.setNazivTima(token[0]);
		     
		     String strDate = token[1].trim();
			 Date sqlDate = Date.valueOf(strDate);
			 addTim.setPrvoPrvenstvo(sqlDate);
			 
			addTim.setNajveciUspeh(token[2]);
			
		     try
		     {
		     int brojUcesca = Integer.parseInt(token[3].trim());
		     addTim.setBrojUcesca(brojUcesca);
		     }
		     catch(Exception e)
		     {
		    	 System.out.println("ekssssssssssssssssssssssssssssssss");
		     }
		     
		     String aktivno = PrvenstvoContainer.getAktivnoPrvenstvo();
			prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(aktivno);
			
			boolean postojiTim = false;
			for (int a = 0; a < prvenstvo.getTimovi().getTimovi().size(); a++) {
				Tim timovi = prvenstvo.getTimovi().getTimovi().get(a);
				if(timovi.getNazivTima().equals(addTim.getNazivTima()))
				{
					postojiTim = true;
				}
					
			}
			
			if(postojiTim == false)
			{
				PrvenstvoContainer.getInstance().addTimFromConsole(addTim);
			}
			
		}
		
	
	}
	

}
