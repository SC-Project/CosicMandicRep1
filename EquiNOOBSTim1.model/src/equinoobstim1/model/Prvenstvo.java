package equinoobstim1.model;

import java.util.ArrayList;
import java.util.Observable;

public class Prvenstvo extends Observable {

	private Timovi timovi = new Timovi();
	private Utakmice utakmice = new Utakmice();
	private String naziv;
	private Prvenstvo prvenstvo;

	public Prvenstvo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prvenstvo(Timovi timovi, Utakmice utakmice) {
		super();
		this.timovi = timovi;
		this.utakmice = utakmice;
	}

	public Timovi getTimovi() {
		return timovi;
	}

	public void setTimovi(Timovi timovi) {
		this.timovi = timovi;
	}

	public Utakmice getUtakmice() {
		return utakmice;
	}

	public void setUtakmice(Utakmice utakmice) {
		this.utakmice = utakmice;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Prvenstvo setSeletkovanTim(Tim tim, UpdateState enumeracija) {
		prvenstvo = new Prvenstvo();
		prvenstvo.getTimovi().getTimovi().add(tim);
		prvenstvo.setNaziv(this.getNaziv());

		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				enumeracija));

		return prvenstvo;

	}

	public Prvenstvo setSeletkovaneUtakmice(ArrayList<Utakmica> utakmice, UpdateState enumeracija) {
		prvenstvo = new Prvenstvo();
		prvenstvo.getUtakmice().getUtakmice().addAll(utakmice);
		prvenstvo.setNaziv(this.getNaziv());

		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo, enumeracija));

		return prvenstvo;

	}

	public void setSelektovaneStablo(ArrayList<Utakmica> utakmice,
			ArrayList<Tim> timovi) {
		
		prvenstvo = new Prvenstvo();
		prvenstvo.getTimovi().setTimovi(timovi);
		prvenstvo.getUtakmice().setUtakmice(utakmice);
		prvenstvo.setNaziv(this.getNaziv());
		
		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				UpdateState.StabloSelekcijaIzModela));
	}
	
	public Prvenstvo setFiltriranTim(Tim tim, ArrayList<Utakmica> utakmice, ArrayList<Tim> timovi, UpdateState enumeracija) {
		prvenstvo = new Prvenstvo();
		ArrayList<String> protivnici = new ArrayList<>();
		protivnici.add(tim.getNazivTima());
		for(Utakmica u : utakmice){
			if(u.getNazivDomaci().equals(tim.getNazivTima())){
				protivnici.add(u.getNazivGosti());
			}else if(u.getNazivGosti().equals(tim.getNazivTima())){
				protivnici.add(u.getNazivDomaci());
			}if(u.getNazivDomaci().equals(tim.getNazivTima()) || u.getNazivGosti().equals(tim.getNazivTima()) ){
				prvenstvo.getUtakmice().getUtakmice().add(u);
			}
		}
		for(Tim t : timovi){
			for(String s : protivnici){
				if(t.getNazivTima().equals(s))
					prvenstvo.getTimovi().getTimovi().add(t);
			}
		}
		prvenstvo.setNaziv(this.getNaziv());

		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				enumeracija));
		return prvenstvo;
	}
	
	public Prvenstvo addTimPrvenstvo(Tim tim) {
		Prvenstvo prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(PrvenstvoContainer.getAktivnoPrvenstvo());
		prvenstvo.getTimovi().getTimovi().add(tim);
		
		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				UpdateState.DodatTimIzKonzole));
		return prvenstvo;
	}

	public Prvenstvo addUtakmicaPrvenstvo(Utakmica utakmica) {
		Prvenstvo prvenstvo = UtilVratiPrvenstvo.vratiPrvenstvo(PrvenstvoContainer.getAktivnoPrvenstvo());
		prvenstvo.getUtakmice().getUtakmice().add(utakmica);
		
		setChanged();
		notifyObservers(new UpdateStatePrvenstvo(prvenstvo,
				UpdateState.DodataUtakmicaIzKonzole));
		
		return prvenstvo;
	}
}
