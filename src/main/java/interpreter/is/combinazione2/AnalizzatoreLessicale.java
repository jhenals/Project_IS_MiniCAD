package Interpreter.is.combinazione2;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class AnalizzatoreLessicale {

	private StreamTokenizer input;
	private Simboli simbolo;

	public AnalizzatoreLessicale(Reader in) {

		input = new StreamTokenizer(in);
		input.resetSyntax();
		input.eolIsSignificant(false);
		input.wordChars('a', 'z');
		input.wordChars('A', 'Z');
		input.wordChars('0', '9');
		input.whitespaceChars('\u0000', ' ');
		input.ordinaryChar('(');
		input.ordinaryChar(')');
		input.quoteChar('"');
	}

	public String getString() {
		return input.sval;
	}

	public Simboli prossimoSimbolo() {
		try {
			switch (input.nextToken()) {
			case StreamTokenizer.TT_EOF:
				simbolo = Simboli.EOF;
				break;
			case StreamTokenizer.TT_WORD:
				// verifica prima se la parola e' riservata
				if (input.sval.equalsIgnoreCase("or"))
					simbolo = Simboli.OR;
				else if (input.sval.equalsIgnoreCase("and"))
					simbolo = Simboli.AND;
				else if (input.sval.equalsIgnoreCase("not"))
					simbolo = Simboli.NOT;
				else if (input.sval.equalsIgnoreCase("near"))
					simbolo = Simboli.NEAR;
				else
					// parola normale - nome o numero
					simbolo = Simboli.PAROLA;
				break;
			case '"':
				simbolo = Simboli.STRINGA_QUOTATA;
				break;
			case '(':
				simbolo = Simboli.TONDA_APERTA;
				break;
			case ')':
				simbolo = Simboli.TONDA_CHIUSA;
				break;
			default:
				simbolo = Simboli.CHAR_INVALIDO;
			}
		} catch (IOException e) {
			simbolo = Simboli.EOF;
		}
		return simbolo;
	}// prossimoSimbolo
	
}// AnalizzatoreLessicale

