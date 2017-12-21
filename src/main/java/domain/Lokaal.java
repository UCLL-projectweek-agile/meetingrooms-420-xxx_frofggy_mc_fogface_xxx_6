package domain;

public class Lokaal {

	private String lokaalID;
	private String naam;
	private int stopcontact;
	private int stoelen;
	private String lokaalnummer;
	

	public Lokaal(String lokaalID, String naam, int stopcontact, int stoelen) {
		this.setLokaalID(lokaalID);
		this.setNaam(naam);
		this.setStopcontact(stopcontact);
		this.setStoelen(stoelen);
	}
	public Lokaal(String lokaalID) {
		this.setLokaalID(lokaalID);
	}

	public Lokaal(String lokaalid2, String naam2, String lokaalnummer, int stopcontact2, int stoelen2) {
		this.setLokaalID(lokaalid2);
		this.setNaam(naam2);
		this.setLokaalnummer(lokaalnummer);
		this.setStopcontact(stopcontact2);
		this.setStoelen(stoelen2);
	}
	
	public String getLokaalnummer() {
		return lokaalnummer;
	}
	public void setLokaalnummer(String lokaalnummer) {
		this.lokaalnummer = lokaalnummer;
	}
	
	public String getLokaalID() {
		return lokaalID;
	}

	public void setLokaalID(String lokaalID) {
		this.lokaalID = lokaalID;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int getStopcontact() {
		return stopcontact;
	}

	public void setStopcontact(int stopcontact) {
		this.stopcontact = stopcontact;
	}

	public int getStoelen() {
		return stoelen;
	}

	public void setStoelen(int stoelen) {
		this.stoelen = stoelen;
	}

	@Override
	public String toString() {
		return naam;
	}

}
