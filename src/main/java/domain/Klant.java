package domain;

import java.util.Date;

public class Klant {
	private String naam;
	private Date beginuur;
	private Date einduur;
	private boolean beschikbaar;
	
	
	public Klant(String naam, Date beginuur, Date einduur) {
		this.naam = naam;
		this.beginuur = beginuur;
		this.einduur = einduur;
	}
	
	private boolean isBeschikbaar() {
		return beschikbaar;
	}

	private void setBeschikbaar(boolean beschikbaar) {
		this.beschikbaar = beschikbaar;
	}

	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public Date getBeginuur() {
		return beginuur;
	}
	public void setBeginuur(Date beginuur) {
		beginuur = beginuur;
	}
	public Date getEinduur() {
		return einduur;
	}
	public void setEinduur(Date einduur) {
		this.einduur = einduur;
	}
	
	
}
