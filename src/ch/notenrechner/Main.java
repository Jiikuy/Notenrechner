package ch.notenrechner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner ui = new Scanner(System.in);
		FileHandler fh = null;
		System.out.println("Notenrechner v1 by Georg Rieger \\\\ Eyja \\\\ Jiikuy");
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
		int count = 0;
		int count1 = 0;
		String fach = "";
		double durchschnitt = 0;
		System.out.println();
		System.out.println("Welcher Befehl soll ausgeführt werden?");
		System.out.println("Folgendes ist möglich:");
		System.out.println("Alle Noten ausgeben: p");
		System.out.println("Durchschnitt aller Noten ausgeben: d");
		System.out.println("Eine Note hinzufügen: a");
		System.out.println("Eine bestimmte Note löschen: r");
		System.out.println("Alle Noten eines Faches ausgeben: fp");
		System.out.println("Durchschnitt eines Fachs ausgeben: fd");
		
		String b = ui.next();
		/* Hier bestimmt die Methode, welcher Vorgang ausgeführt werden soll. Der String b ist dabei der Befehl.*/
		switch (b) {
		// Alle Noten ausgeben
		case "p":
			if(!fh.f.exists() || al.isEmpty()) {
				System.out.println("Es sind noch keine Noten vorhanden!");
				befehl(ui, fh);
			}
			while(count<al.size()) {
				System.out.println(count+1 + " " + al.get(count).wert + " "+ al.get(count).fach);
				count++;
			}
					
			befehl(ui, fh);
			break;
		// Durchschnitt aller Noten
		case "d":
			if(!fh.f.exists() || al.isEmpty()) {
				System.out.println("Es sind noch keine Noten vorhanden!");
				befehl(ui, fh);
			}
			while(count<al.size()) {
				durchschnitt += al.get(count).wert;
				count++;
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
			System.out.println("Welche Note soll gelöscht werden?");
			while(count<al.size()) {
				System.out.println(count+1 + " " + al.get(count).wert + " "+ al.get(count).fach);
				count++;
			}
			fh.deleteSpecificLine(ui.nextInt()-1);
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
			while(count<al.size()) {
				if(fach.equals(al.get(count).fach)) {
					durchschnitt += al.get(count).wert;
					count1++;
				}
				count++;
			}
			durchschnitt /= count1;
			System.out.println("Der Durchschnitt des Faches " + fach + " beträgt " + durchschnitt + ".");
			befehl(ui, fh);
			break;
		case "fp":
			if(!fh.f.exists() || al.isEmpty()) {
				System.out.println("Es sind noch keine Noten vorhanden!");
				befehl(ui, fh);
			}
			System.out.println("Geben sie das Fach ein!");
			fach = ui.next();
			while(count<al.size()) {
				if(fach.equals(al.get(count).fach)) {
					count1++;
					System.out.println(count1 + " " + al.get(count).wert + " "+ al.get(count).fach);
				}
				count++;
			}
			break;
		}
		
	}
}
