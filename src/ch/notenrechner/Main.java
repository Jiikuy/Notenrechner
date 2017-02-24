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
		if(!fh.f.exists() || al.isEmpty()) {
			System.out.println("Es sind noch keine Noten vorhanden!");
		}else {
			for(Note j: al) {
				System.out.println(j.wert + " "+ j.fach);
			}
		}
		System.out.println();
		System.out.println("Welcher Befehl soll ausgeführt werden?");
		System.out.println("Folgendes ist möglich:");
		System.out.println("Durchschnitt aller Noten ausgeben: d");
		System.out.println("Eine Note hinzufügen: a");
		System.out.println("Eine bestimmte Note löschen: r");
		System.out.println("Alle Noten eines Fachs ausgeben: fp");
		System.out.println("Durchschnitt eines Fachs ausgeben: fd");
		System.out.println("Alle Noten eines Fachs löschen: fr"); 
		System.out.println("WARNUNG! ALLE Noten löschen: ar");
		System.out.println("Das Programm schliessen: x");
		String b = ui.next();
		/* Hier bestimmt die Methode, welcher Vorgang ausgeführt werden soll. Der String b ist dabei der Befehl.*/
		switch (b) {
		// Durchschnitt aller Noten
		case "d":
			if(!fh.f.exists() || al.isEmpty()) {
				System.out.println("Es sind noch keine Noten vorhanden!");
				befehl(ui, fh);
			}
			for(Note j: al) {
				durchschnitt += j.wert;
			}
			durchschnitt /= al.size();
			System.out.println("Der Durchschnitt beträgt " + durchschnitt + ".");
			befehl(ui, fh);
			break;
		// Note hinzufügen
		case "a":
			System.out.println("Geben sie den Notenwert ein!");
			String notenwert = ui.next();
			System.out.println("Geben sie das Fach ein!");
			fach = ui.next();
			String wr = notenwert + " " + fach;
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
				System.out.println(count+1 + j.wert + " "+ j.fach);
				count++;
			}
			fh.deleteSpecificLine(ui.nextInt());
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
				if(fach.equals(j.fach)) {
					durchschnitt += j.wert;
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
				if(fach.equals(j.fach)) {
					System.out.println(j.wert + " "+ j.fach);
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
				if(fach.equals(al.get(count).fach)) {
					dl.add(count+1);
				}
				count++;
			}
			fh.deleteSpecificLine(dl.stream().mapToInt(i->i).toArray());
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
}
