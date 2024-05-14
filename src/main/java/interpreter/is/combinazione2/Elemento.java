package Interpreter.is.combinazione2;

import java.util.ArrayList;
import java.util.List;

public class Elemento implements CombinazioneIF {
	private boolean quotata;
	private String elemento;

	public Elemento(String elemento, boolean quotata) {

		this.quotata = quotata;
		this.elemento = elemento;
	}

	@Override
	public List<Integer> interpreta(String contesto) {
		List<Integer> lista = new ArrayList<>();
		int i = 0;
		for (;;) {
			i = contesto.indexOf(elemento, i);
			if (i == -1)
				break;
			lista.add(i);
			i += elemento.length();
		}
		if (lista.isEmpty())
			return null;
		return lista;
	}

	@Override
	public String toString() {
		if (quotata)
			return '\"' + elemento + '\"';
		return elemento;
	}

}
