package Interpreter.is.combinazione2;

import java.util.LinkedList;
import java.util.List;

public class ParteNearElemento extends ParteNear {
	private boolean not;
	Elemento elemento;

	public ParteNearElemento(boolean not, Elemento elem) {
		this.not = not;
		elemento = elem;
	}

	@Override
	public List<Integer> interpreta(String contesto) {

		List<Integer> lista = elemento.interpreta(contesto);

		if (not) {
			if (lista == null)
				return new LinkedList<>();
			else
				return null;
		} else

			return lista;

	}

	@Override
	public String toString() {
		if (not)
			return "NOT " + elemento;
		return elemento.toString();

	}

}
