package ch.notenrechner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;

public class FileHandler {
	
	private ArrayList<Note> al = new ArrayList<Note> ();
	File f;
	private BufferedWriter fo;
	private String fileName = "";
	
	public void deleteSpecificLine(int...line) {
		ArrayList<String> fc = new ArrayList<String>();
		File fn = new File(fileName + ".new");
		File f = new File(fileName);
		try {
			int count = 0;
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				fc.add(s.nextLine());
			}
			s.close();
			while(count<line.length) {
				fc.set(line[count]-1, "");
				count++;
			}
			count = 0;
			while(count<fc.size()) {
				if(fc.get(count).isEmpty()){
					fc.remove(count);
				} 
				else {
					count++;
				}
			}
			fo = new BufferedWriter(new FileWriter(fileName + ".new", true));
			if(fc.size() != 0) {
				fo.write(fc.get(0));
				for(int i = 1; i<fc.size(); i++) {
					fo.newLine();
					fo.write(fc.get(i));
				}
			}
			
			fo.close();
			f.delete();
			String fp = f.getAbsolutePath();
			f = new File(fp);
			fn.renameTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public void writeFile(String w) {
		try {
			if(f.exists()) {
				fo = new BufferedWriter(new FileWriter(f, true));
				fo.newLine();
			}else {
				f.createNewFile();
				fo = new BufferedWriter(new FileWriter(f, true));
			}
			
			fo.write(w);
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public ArrayList<Note> readFile() {
		if(!f.exists()) {
			return null;
		}
		try {
			if(!al.isEmpty()) {
				al = new ArrayList<Note>();
			}
			Scanner rf = new Scanner(f);
			Scanner lc = new Scanner(f);
			while(lc.hasNextLine()) {
				al.add(readNextNote(rf));
				lc.nextLine();
			}
			lc.close();
			return al;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Note readNextNote(Scanner s) {
		try {
			if(s.hasNext()) {
				return new Note(s.nextDouble(), s.next(), LocalDate.parse(s.next()), s.next());
			}else {
				return null;
			}
		}catch (Exception e) {
			System.out.println("Es ist ein Fehler aufgetreten!");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public FileHandler(String dateiname) {
		fileName = dateiname;
		f = new File(fileName);
		if(f.exists()) {
			System.out.println("Die Datei existiert schon!");
		}else {
			System.out.println("Die Datei existiert noch nicht. Sie wird beim hineinschreiben erstellt!");
		}
			
	}
	
}
