package Interpreter.is.combinazione2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Combinazione implements CombinazioneIF {

	private List<ParteOr> partiOr = new LinkedList<>();

	public Combinazione(ParteOr pOr) {
		partiOr.add(pOr);
	}

	
	@Override
	public List<Integer> interpreta(String contesto) {
		List<Integer> result = null;
		for (ParteOr pOr : partiOr) {
			List<Integer> risultatoParziale = pOr.interpreta(contesto);
			if (risultatoParziale != null) {
				if (result == null)
					result = new LinkedList<>();
				result.addAll(risultatoParziale);
			}
		}
		return result;
	}

	public void addParteOr(ParteOr p) {
		partiOr.add(p);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<ParteOr> it = partiOr.iterator();

		sb.append(it.next());
		while (it.hasNext()) {
			sb.append(" OR ");
			sb.append(it.next());

		}
		return sb.toString();

	}
}
