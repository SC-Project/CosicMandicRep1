package equinoobstim1.model;

import java.util.ArrayList;

public class Timovi {
	
	ArrayList<Tim> timovi;

	public Timovi() {
		timovi = new ArrayList<Tim>();
	}
	
	public Tim getTim(String imeTima)
	{
		for (int i = 0; i < timovi.size(); i++) {
			if(timovi.get(i).equals(imeTima))
				return timovi.get(i);
		}
		return null;
	}

	public String toString() {
		String retVal = "Spisak timova:\n";
		
		int i=1;
		for(Tim t : timovi){
			retVal += "\t" + i + ". " + t.toString() + "\n";
			i++;
		}
		
		return retVal;
	}
	
	public void addTIm(Tim tim) {
		timovi.add(tim);
	}

	public ArrayList<Tim> getTimovi() {
		return timovi;
	}

	public void setTimovi(ArrayList<Tim> timovi) {
		this.timovi = timovi;
	}
	
	public void setSelektovanTim(Tim tim)
	{
		
	}
	
	

}
