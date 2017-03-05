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

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	
	public Note(double wert2, String fach2, LocalDate datum2) {
		wert = wert2;
		fach = fach2;
        datum = datum2;
	}
}
