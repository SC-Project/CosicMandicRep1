package equinoobstim1.model;

import java.util.ArrayList;

public class Utakmice {
	
	ArrayList<Utakmica> utakmice;

	public Utakmice() {
		utakmice = new ArrayList<Utakmica>();
	}

	public String toString() {
		
		String retVal = "Lista utakmica:\n";
		
		int i=1;
		for(Utakmica u : utakmice){
			retVal += "\t" + i + ". " + u.toString() + "\n";
			i++;
		}
		
		return retVal;
	}
	
	public void addUtakmica (Utakmica utakmica) {
		utakmice.add(utakmica);
	}

	public ArrayList<Utakmica> getUtakmice() {
		return utakmice;
	}

	public void setUtakmice(ArrayList<Utakmica> utakmice) {
		this.utakmice = utakmice;
	}
	
	
	
}
