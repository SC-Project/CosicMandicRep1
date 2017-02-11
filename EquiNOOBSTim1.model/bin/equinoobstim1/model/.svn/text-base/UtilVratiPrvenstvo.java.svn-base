package equinoobstim1.model;

import java.util.ArrayList;

public class UtilVratiPrvenstvo {

	public static Prvenstvo vratiPrvenstvo(String naziv){
		ArrayList<Prvenstvo> lista = (ArrayList<Prvenstvo>) PrvenstvoContainer.getInstance().getPrvenstva();
		for(Prvenstvo p : lista)
			if(p.getNaziv().equals(naziv))
				return p;
		
		return null;
	}
}
