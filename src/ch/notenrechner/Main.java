package ch.notenrechner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner ui = new Scanner(System.in);
		FileHandler fh = null;
		System.out.println("Notenrechner by Georg Rieger");
		System.out.println("");
		System.out.println("Geben sie den Dateinamen ein!");
		fh = new FileHandler("."+ System.getProperty("file.separator") +  ui.nextLine() + ".txt");
		try {
			befehl(ui, fh);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ui.close();
	}

	public static void befehl(Scanner ui, FileHandler fh) throws InterruptedException {
		Thread.sleep(1000);
		ArrayList<Note> al = fh.readFile();
		ArrayList<Integer> dl = new ArrayList<Integer>();
		int count = 0;
		String fach = "";
		double durchschnitt = 0;
        System.out.println();
        //Alle Noten und Durchschnitt aller Noten ausgeben
		if(!fh.f.exists() || al.isEmpty()) {
			System.out.println("Es sind noch keine Noten vorhanden!");
		}else {
			for(Note j: al) {
				System.out.println(j.toString());
                durchschnitt += j.getWert();
			}
            durchschnitt /= al.size();
			System.out.println("Durchschnitt: " + durchschnitt + ".");
		}
		String b = userDialog(ui);
		//Hier bestimmt die Methode, welcher Vorgang ausgeführt werden soll. Der String b ist dabei der Befehl.
		switch (b) {
		// Note hinzufügen
		case "a":
			System.out.println("Geben sie den Notenwert ein!");
			String notenwert = ui.next();
			System.out.println("Geben sie das Fach ein!");
			fach = ui.next();
            System.out.println("Geben sie das Datum des Tests ein! (YYYY-MM-TT)");
            String datum = ui.next();
            datum = datum.replace(".", "-");
            datum = datum.replace(":", "-");
            System.out.println("Geben sie einen Kommentar ein!");
            String kommentar = ui.next();
            String wr = (notenwert + " " + fach + " " + datum + " " + kommentar);
			wr = wr.replace(".", ",");
			fh.writeFile(wr);
			befehl(ui,fh);
			break;
		// Bestimmte Noten löschen
		case "r":
			if(!fh.f.exists() || al.isEmpty()) {
				System.out.println("Es sind noch keine Noten vorhanden!");
				befehl(ui, fh);
			}
			System.out.println("Geben sie die Zeile der Note ein, die gelöscht werden soll!");
			for(Note j: al) {
				System.out.println(count+1 + " " +  j.toString());
				count++;
			}
			dl.add(ui.nextInt());
			fh.deleteSpecificLine(dl);
			System.out.println("Die Note wurde gelöscht!");
			befehl(ui, fh);
			break;
		//Durchschnitt eines Fachs
		case "fd":
			if(!fh.f.exists() || al.isEmpty()) {
				System.out.println("Es sind noch keine Noten vorhanden!");
				befehl(ui, fh);
			}
			System.out.println("Geben sie das Fach ein!");
			fach = ui.next();
			for(Note j: al) {
				if(fach.equals(j.getFach())) {
					durchschnitt += j.getWert();
					count++;
				}
			}
			durchschnitt /= count;
			System.out.println("Der Durchschnitt des Fachs " + fach + " beträgt " + durchschnitt + ".");
			befehl(ui, fh);
			break;
		//Alle Noten eines Fachs ausgeben
		case "fp":
			if(!fh.f.exists() || al.isEmpty()) {
				System.out.println("Es sind noch keine Noten vorhanden!");
				befehl(ui, fh);
			}
			System.out.println("Geben sie das Fach ein!");
			fach = ui.next();
			for(Note j: al) {
				if(fach.equals(j.getFach())) {
					System.out.println(j.toString());
				}
			}

			befehl(ui, fh);
			break;
		//Alle Noten eines Fachs löschen
		case "fr":
			if(!fh.f.exists() || al.isEmpty()) {
				System.out.println("Es sind noch keine Noten vorhanden!");
				befehl(ui, fh);
			}
			System.out.println("Geben sie das Fach ein!");
			fach = ui.next();
			while(count<al.size()) {
				if(fach.equals(al.get(count).getFach())) {
					dl.add(count+1);
				}
				count++;
			}
			fh.deleteSpecificLine(dl);
			System.out.println("Die Noten wurden gelöscht!");
			befehl(ui, fh);
			break;
		//Alle Noten löschen
		case "ar":
			if(!fh.f.exists() || al.isEmpty()) {
				System.out.println("Es sind noch keine Noten vorhanden!");
				befehl(ui, fh);
			}
			System.out.println("Wollen sie wirklich alle Noten löschen? (y/n)");
			if(ui.next().equals("n")) {
				befehl(ui,fh);
			}
			fh.f.delete();
			System.out.println("Alle Noten wurden gelöscht!");
			befehl(ui, fh);
			break;
		case "x":
			System.exit(0);
			break;
		default: 
			System.out.println("Der Befehl wurde nicht gefunden!");
			befehl(ui, fh);
		}
		
		
	}
	
	public static String userDialog(Scanner ui) {
		System.out.println();
		System.out.println("Welcher Befehl soll ausgeführt werden?");
		System.out.println("Folgendes ist möglich:");
		System.out.println("Eine Note hinzufügen: a");
		System.out.println("Eine bestimmte Note löschen: r");
		System.out.println("Alle Noten eines Fachs ausgeben: fp");
		System.out.println("Durchschnitt eines Fachs ausgeben: fd");
		System.out.println("Alle Noten eines Fachs löschen: fr"); 
		System.out.println("WARNUNG! ALLE Noten löschen: ar");
		System.out.println("Das Programm schliessen: x");
		return ui.next();
	}
}
