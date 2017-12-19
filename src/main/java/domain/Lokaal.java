package domain;

public class Lokaal {

	private String lokaalID;
	private String naam;
	private int stopcontact;
	private int stoelen;
	private boolean status;
	private boolean[] lokaalbezetting = new boolean[17]; 
	
	public Lokaal(String lokaalID, String naam, int stopcontact, int stoelen, boolean status) {
		this.setLokaalID(naam);
		this.setNaam(naam);
		this.setStopcontact(stopcontact);
		this.setStoelen(stoelen);
		this.setStatus(status);
	}
	
	public boolean[] getLokaalbezetting() {
		return lokaalbezetting;
	}

	public void setLokaalbezetting(boolean[] lokaalbezetting) {
		this.lokaalbezetting = lokaalbezetting;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
