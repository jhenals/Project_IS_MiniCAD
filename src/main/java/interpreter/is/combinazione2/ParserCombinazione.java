package Interpreter.is.combinazione2;

import java.io.Reader;

public class ParserCombinazione {
	private AnalizzatoreLessicale lexer;
	private Simboli simbolo;
	private Combinazione root;

	public ParserCombinazione(Reader in) {
		lexer = new AnalizzatoreLessicale(in);
		root = combinazione();
		atteso(Simboli.EOF);
	}

	private Combinazione combinazione() {

		ParteOr pOr = parteOr();
		Combinazione comb = new Combinazione(pOr);

		while (simbolo == Simboli.OR) {
			comb.addParteOr(parteOr());
		}
		return comb;
	}// combinazione

	private ParteOr parteOr() {
		ParteAnd pAnd = parteAnd();
		ParteOr pOr = new ParteOr(pAnd);

		while (simbolo == Simboli.AND) {
			pOr.addParteAnd(parteAnd());
		}
		return pOr;
	}// parteOr

	private ParteAnd parteAnd() {
		ParteNear left = parteNear();
		ParteNear right = null;
		if (simbolo == Simboli.NEAR)
			right = parteNear();
		return new ParteAnd(left, right);

	}// parteAnd

	private ParteNear parteNear() {
		simbolo = lexer.prossimoSimbolo();
		if (simbolo == Simboli.NOT) {

			simbolo = lexer.prossimoSimbolo();
			Elemento el = elemento();
			return new ParteNearElemento(true, el);
		}
		if (simbolo == Simboli.TONDA_APERTA) {
			Combinazione cb = combinazione();
			atteso(Simboli.TONDA_CHIUSA);
			return new ParteNearCombinazione(cb);
		}
		return new ParteNearElemento(false, elemento());

	}// parteNear

	private Elemento elemento() {
		Elemento res = null;
		if (simbolo == Simboli.STRINGA_QUOTATA) {
			res = new Elemento(lexer.getString(), true);

			simbolo = lexer.prossimoSimbolo();
		} else if (simbolo == Simboli.PAROLA) {
			res = new Elemento(lexer.getString(), false);

			simbolo = lexer.prossimoSimbolo();
		} else
			throw new SyntaxException(
					"Attesa STRINGA_QUOTATA o PAROLA in elemento()");
		return res;
	}// elemento

	private void atteso(Simboli s) {
		if (simbolo != s) {
			String msg = " trovato " + simbolo + " mentre si attendeva " + s;
			throw new SyntaxException(msg);
		}
		simbolo = lexer.prossimoSimbolo();
	}// atteso

	public Combinazione getCombinazione() {
		return root;
	}// ritorna il composite costruito

}
