package ch.notenrechner;

import java.text.DecimalFormat;
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
		String notenwert = "";
        String datum = "";
        String kommentar = "";
        String wr = "";
        System.out.println();
        //Alle Noten und Durchschnitt aller Noten ausgeben
		if(!fh.f.exists() || al.isEmpty()) {
			System.out.println("Es sind keine Noten vorhanden!");
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
		case "h":
			System.out.println("Geben sie den Notenwert ein!");
			notenwert = ui.next();
			System.out.println("Geben sie das Fach ein!");
			fach = ui.next();
            System.out.println("Geben sie das Datum des Tests ein! (YYYY-MM-TT)");
            datum = ui.next();
            datum = datum.replace(".", "-");
            datum = datum.replace(":", "-");
            System.out.println("Geben sie einen Kommentar ein!");
            ui.nextLine();
            kommentar = ui.nextLine();
            wr = (notenwert + " " + fach + " " + datum + " " + kommentar);
			wr = wr.replace(",", ".");
			fh.writeFile(wr);
			befehl(ui,fh);
			break;
		//Eine Note nach Punkte berchnen
		case "b":
			DecimalFormat df = new DecimalFormat("#.###");
			System.out.println("Geben sie die Anzahl erreichten Punkte ein!");
			double ePunkte = ui.nextInt();
			System.out.println("Geben sie die Maximalpunktzahl ein!");
			double mPunkte = ui.nextInt();
			double x = (ePunkte * 5) / mPunkte + 1;
			notenwert = df.format(x);
			System.out.println(notenwert);
			System.out.println("Wollen sie die Note speichern? (j/n)");
			if(ui.next().equals("j")) {
				System.out.println("Geben sie das Fach ein!");
				fach = ui.next();
	            System.out.println("Geben sie das Datum des Tests ein! (YYYY-MM-TT)");
	            datum = ui.next();
	            datum = datum.replace(".", "-");
	            datum = datum.replace(":", "-");
	            System.out.println("Geben sie einen Kommentar ein!");
	            ui.nextLine();
	            kommentar = ui.nextLine();
	            wr = (notenwert + " " + fach + " " + datum + " " + kommentar);
				wr = wr.replace(",", ".");
				fh.writeFile(wr);
			}else if(ui.next().equals("n")) befehl(ui,fh);
			else {
				System.err.println("Bitte geben sie eine gültige Eingabe ein!");
			}
			befehl(ui,fh);
			break;
		//Bestimmte Noten löschen
		case "l":
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
		case "fa":
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
		case "fl":
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
		case "al":
			if(!fh.f.exists() || al.isEmpty()) {
				System.out.println("Es sind noch keine Noten vorhanden!");
				befehl(ui, fh);
			}
			System.out.println("Wollen sie wirklich alle Noten löschen? (j/n)");
			if(ui.next().equals("j")) {
				fh.f.delete();
				System.out.println("Alle Noten wurden gelöscht!");
			}else if(ui.next().equals("n")) {
				System.out.println("Der Vorgang wurde abgebrochen!");
				befehl(ui, fh);
			}else
				System.out.println("Bitte geben sie eine korrekte Eingabe ein!");
			
			befehl(ui, fh);
			break;
		//Das Programm schliessen
		case "x":
			ui.close();
			System.exit(0);
			break;
		/* //Die Einstellungen öffnen
		case "e":
			System.out.println("Einstellungen");
			System.out.println("Ungenügende Noten doppelt kompensieren: k");
			befehl(ui, fh);
			break;*/
		default: 
			System.out.println("Der Befehl wurde nicht gefunden!");
			befehl(ui, fh);
		}
		
		
	}
	
/*	public static void settings(Scanner ui) {
		
	}*/
	
	public static String userDialog(Scanner ui) {
		System.out.println();
		System.out.println("Welcher Befehl soll ausgeführt werden?");
		System.out.println("Folgendes ist möglich:");
		System.out.println("Eine Note hinzufügen: h");
		System.out.println("Eine Note nach Punkten berechnen: b");
		System.out.println("Eine bestimmte Note löschen: l");
		System.out.println("Alle Noten eines Fachs ausgeben: fa");
		System.out.println("Durchschnitt eines Fachs ausgeben: fd");
		System.out.println("Alle Noten eines Fachs löschen: fl"); 
		System.out.println("WARNUNG! ALLE Noten löschen: al");
		/* TODO: add settings
		 * System.out.println("Die Einstellungen öffnen: e"); */
		System.out.println("Das Programm schliessen: x");
		return ui.next();
	}
}
