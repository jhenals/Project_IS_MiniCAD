package Interpreter.is.combinazione2;

import java.util.List;

public class ParteNearCombinazione extends ParteNear {
	private Combinazione comb;

	public ParteNearCombinazione(Combinazione comb) {

		this.comb = comb;
	}

	@Override
	public List<Integer> interpreta(String contesto) {

		return comb.interpreta(contesto);
	}

	@Override
	public String toString() {

		return '(' + comb.toString() + ')';
	}

}
