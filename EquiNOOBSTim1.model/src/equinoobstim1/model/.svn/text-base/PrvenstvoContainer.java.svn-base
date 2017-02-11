package equinoobstim1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PrvenstvoContainer extends Observable {

	public static PrvenstvoContainer instance = null;
	public static List<Prvenstvo> prvenstva = new ArrayList<Prvenstvo>();
	private static String aktivnoPrvenstvo = "";

	public static PrvenstvoContainer getInstance() {
		if (instance == null)
			instance = new PrvenstvoContainer();
		return instance;
	}

	public static String getAktivnoPrvenstvo() {
		return aktivnoPrvenstvo;
	}

	public static void setAktivnoPrvenstvo(String aktivnoPrvenstvo1) {
		aktivnoPrvenstvo = aktivnoPrvenstvo1;
	}

	public void test() {
		setChanged();
		notifyObservers();
	}

	public static List<Prvenstvo> getPrvenstva() {
		return prvenstva;
	}

	public static void setPrvenstva(List<Prvenstvo> prvenstva) {
		PrvenstvoContainer.prvenstva = prvenstva;
	}

	public void addPrvenstvo(Prvenstvo prvenstvno) {
		prvenstva.add(prvenstvno);
		aktivnoPrvenstvo = prvenstvno.getNaziv();
		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvno,
				UpdateState.AddPrvenstvno));
	}

	public void removePrevenstvno(Prvenstvo prvenstvno) {
		prvenstva.remove(prvenstvno);
		setChanged();
		notifyObservers();
	}

	public void pretragaSelekcijaTim(Tim tim) {
		Prvenstvo prvenstvo = new Prvenstvo();

		prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(aktivnoPrvenstvo)
				.setSeletkovanTim(tim, UpdateState.TimFindSelekcija);

		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				UpdateState.TimFindSelekcija));
	}

	public void pretragaSelekcijaUtakmice(ArrayList<Utakmica> utakmice) {
		Prvenstvo prvenstvo = new Prvenstvo();

		prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(aktivnoPrvenstvo)
				.setSeletkovaneUtakmice(utakmice, UpdateState.UtakmicaFindSelekcija);

		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				UpdateState.UtakmicaFindSelekcija));

	}
	
	public void filtriranjeTim(Tim tim) {
		Prvenstvo prvenstvo = new Prvenstvo();
		Prvenstvo aktivno = UtilVratiPrvenstvo.vratiPrvenstvo(aktivnoPrvenstvo);
		prvenstvo = aktivno.setFiltriranTim(tim, aktivno.getUtakmice().getUtakmice()
				, aktivno.getTimovi().getTimovi(), UpdateState.FiltriranjeTim);

		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				UpdateState.FiltriranjeTim));
	}

	public void filtriranjeUtakmice(ArrayList<Utakmica> utakmice) {
		Prvenstvo prvenstvo = new Prvenstvo();

		prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(aktivnoPrvenstvo)
				.setSeletkovaneUtakmice(utakmice, UpdateState.FiltriranjeUtakmica);

		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				UpdateState.FiltriranjeUtakmica));

	}
	
	public void setSelectedFromViewToModel(ArrayList<Tim> timovi, ArrayList<Utakmica> utakmice){
		Prvenstvo prvenstvo = new Prvenstvo();
		prvenstvo.getTimovi().setTimovi(timovi);
		prvenstvo.getUtakmice().setUtakmice(utakmice);
		UtilVratiPrvenstvo.vratiPrvenstvo(aktivnoPrvenstvo)
				.setSelektovaneStablo(utakmice, timovi);
		
		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				UpdateState.StabloSelekcijaIzModela));
	}
	

	public void addTimFromConsole(Tim tim){
		
		Prvenstvo prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(aktivnoPrvenstvo).addTimPrvenstvo(tim);
		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				UpdateState.DodatTimIzKonzole));
	}
	
	public void addUtakmicaFromConsole(Utakmica utakmica){
		
		Prvenstvo prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(aktivnoPrvenstvo).addUtakmicaPrvenstvo(utakmica);
		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				UpdateState.DodataUtakmicaIzKonzole));
	}
	
	
	
	
}
