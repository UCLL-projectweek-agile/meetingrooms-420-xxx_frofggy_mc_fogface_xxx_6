package domain;

public class Lokaal {

	private String lokaalID;
	private String naam;
	private int stopcontact;
	private int stoelen;
	

	public Lokaal(String lokaalID, String naam, int stopcontact, int stoelen) {
		this.setLokaalID(lokaalID);
		this.setNaam(naam);
		this.setStopcontact(stopcontact);
		this.setStoelen(stoelen);
	}
	public Lokaal(String lokaalID) {
		this.setLokaalID(lokaalID);
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
