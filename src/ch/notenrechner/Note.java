package ch.notenrechner;
import java.time.*;

public class Note {	
	private double wert;
	private String fach;
	private LocalDate datum;
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

	
	public Note(double wert, String fach, LocalDate datum) {
		this.wert = wert;
		this.fach = fach;
        this.datum = datum;
	}
}
