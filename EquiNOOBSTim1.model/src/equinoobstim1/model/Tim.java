package equinoobstim1.model;

import java.sql.Date;

public class Tim {
	
	private String nazivTima;
	private Date prvoPrvenstvo;
	private String najveciUspeh;
	private int brojUcesca;
	
	public Tim() {
		super();
	}
	
	public Tim(String nazivTima, Date prvoPrvenstvo, String najveciUspeh,
			int brojUcesca) {
		super();
		this.nazivTima = nazivTima;
		this.prvoPrvenstvo = prvoPrvenstvo;
		this.najveciUspeh = najveciUspeh;
		this.brojUcesca = brojUcesca;
	}
	public String getNazivTima() {
		return nazivTima;
	}
	public void setNazivTima(String nazivTima) {
		this.nazivTima = nazivTima;
	}
	public Date getPrvoPrvenstvo() {
		return prvoPrvenstvo;
	}
	public void setPrvoPrvenstvo(Date prvoPrvenstvo) {
		this.prvoPrvenstvo = prvoPrvenstvo;
	}
	public String getNajveciUspeh() {
		return najveciUspeh;
	}
	public void setNajveciUspeh(String najveciUspeh) {
		this.najveciUspeh = najveciUspeh;
	}
	public int getBrojUcesca() {
		return brojUcesca;
	}
	public void setBrojUcesca(int brojUcesca) {
		this.brojUcesca = brojUcesca;
	}
	public String toString() {
		return "Naziv: " + nazivTima + "\nBr. Ucesca: " + brojUcesca + 
				"\nPrvi nastup: " + prvoPrvenstvo + "\nNajveci uspeh: " + 
				najveciUspeh + ".";
	}
	

	

}
