package Interpreter.is.combinazione2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ParteOr implements CombinazioneIF {
	List<ParteAnd> partiAnd = new LinkedList<>();

	public ParteOr(ParteAnd pAnd) {
		partiAnd.add(pAnd);
	}

	@Override
	public List<Integer> interpreta(String contesto) {
		List<Integer> result = new LinkedList<>();
		for (ParteAnd pAnd : partiAnd) {
			List<Integer> risultatoParziale = pAnd.interpreta(contesto);
			if (risultatoParziale == null)
				return null;
			else
				result.addAll(risultatoParziale);

		}
		return result;
	}

	public void addParteAnd(ParteAnd pAnd) {
		partiAnd.add(pAnd);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<ParteAnd> it = partiAnd.iterator();

		sb.append(it.next());
		while (it.hasNext()) {
			sb.append(" AND ");
			sb.append(it.next());

		}
		return sb.toString();

	}
}
