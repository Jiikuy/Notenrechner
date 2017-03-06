package ch.notenrechner;
import java.time.*;

public class Note {	
	private double wert;
	private String fach;
	private LocalDate datum;
	private String kommentar;
	public double getWert() {
		return wert;
	}

	public void setWert(double wert) {
		this.wert = wert;
	}

	public String getFach() {
		return fach;
	}

	public void setFach(String fach) {
		this.fach = fach;
	}

	public String getDatum() {
		return datum.getDayOfMonth() + "." + datum.getMonthValue() + "." + datum.getYear();
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	public Note(double wert, String fach, LocalDate datum, String kommentar) {
		this.wert = wert;
		this.fach = fach;
        this.datum = datum;
        this.kommentar = kommentar;
	}

}
