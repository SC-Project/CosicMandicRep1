package equinoobstim1.view.console;

import java.sql.Date;
import java.util.StringTokenizer;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.model.PrvenstvoContainer;
import equinoobstim1.model.Tim;
import equinoobstim1.model.Utakmica;
import equinoobstim1.model.UtilVratiPrvenstvo;

public class ParseUtakmice {
	
	String text;
    Prvenstvo prvenstvo = new Prvenstvo();


	public ParseUtakmice(String text) {
		super();
		this.text = text;
		
	}
	
	public void addUtakmica() {
		
		Utakmica addUtakmica = new Utakmica();
		
		String indikator = text.trim().substring(0,13);
		
		String utakmica = text.trim().substring(14).trim();
		
		
		if(indikator.equals("add->utakmica"))
		{
		
			
			String token[] = {"","","","","",""};
			int i = 0;
			StringTokenizer st = new StringTokenizer(utakmica, ",");
		     while (st.hasMoreTokens()) {
		    	 String a = st.nextToken();
		         token[i] = a;
		         i++;
		     }
			
		     String tim[] = token[0].split(":");
		     String rez[] = token[1].split(":");
		    
		     addUtakmica.setNazivDomaci(tim[0].trim());
		     addUtakmica.setNazivGosti(tim[1].trim());
		     
		     
		     
		     try
		     {
		     int goloviDomaci = Integer.parseInt(rez[0].trim());
		     int goloviGosti = Integer.parseInt(rez[1].trim());
		     addUtakmica.setGoloviDomaci(goloviDomaci);
		     addUtakmica.setGoloviDomaci(goloviGosti);
		     addUtakmica.setBrojCrvenihKartona(Integer.parseInt(token[2].trim()));
		     addUtakmica.setBrojZutihKartona(Integer.parseInt(token[3].trim()));
		     }
		     catch(Exception e)
		     {
		    	 System.out.println("ekssssssssssssssssssssssssssssssss");
		     }
		     addUtakmica.setStadion(token[4]);
		     
		     
		     String strDate = token[5].trim();
			 Date sqlDate = Date.valueOf(strDate);
			 addUtakmica.setDatum(sqlDate);
		     
		     String aktivno = PrvenstvoContainer.getAktivnoPrvenstvo();
		     prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(aktivno);
			
		     boolean domaci = false;
		     boolean gosti = false;
		     for (int a = 0; a < prvenstvo.getTimovi().getTimovi().size(); a++) {
				Tim timovi = prvenstvo.getTimovi().getTimovi().get(a);
				if(timovi.getNazivTima().equals(addUtakmica.getNazivDomaci()))
				{
					domaci = true;
				}
				if(timovi.getNazivTima().equals(addUtakmica.getNazivGosti()))
				{
					gosti = true;
				}

		     }
			

		     if(domaci == true && gosti == true)
		     {
		    	 PrvenstvoContainer.getInstance().addUtakmicaFromConsole(addUtakmica);
		     }
			
		}
		
	
	}
	

}
