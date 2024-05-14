package Interpreter.is.combinazione2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Ricerca {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Fornisci la combinazione: ");
		String combinazione = sc.nextLine();
		//sc.close();

		StringReader sr = new StringReader(combinazione);

		ParserCombinazione p = new ParserCombinazione(sr);
		System.out.println(p.getCombinazione());
		System.out.print("Fornisci il nome del file col testo per la ricerca: ");
		String nomeFileRicerca = sc.nextLine();
		StringBuilder testo = new StringBuilder(10000);
		File f = new File(nomeFileRicerca);
		
		try(BufferedReader r = new BufferedReader(new FileReader(f));){
			for (;;) {

				String linea = r.readLine();
				if (linea == null)
					break;
				testo.append(linea);
				testo.append('\n');
			}
		}catch (IOException e) {
			System.out.println("errore di lettura");
			System.out.println(e);
			System.exit(1);
		}
		
	

		String contesto = testo.toString();
		List<Integer> match = p.getCombinazione().interpreta(contesto);
		if (match == null)
			System.out.println("No match!");
		else
			System.out.println(match);
	}// main
}// Ricerca
