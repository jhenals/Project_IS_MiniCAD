package Interpreter.is.combinazione2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParteAnd implements CombinazioneIF {
	ParteNear left;
	ParteNear right;

	public ParteAnd(ParteNear left, ParteNear right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public List<Integer> interpreta(String contesto) {
		List<Integer> lRes = left.interpreta(contesto);

		if (right == null)
			return lRes;
		List<Integer> rRes = right.interpreta(contesto);

		if (lRes == null || rRes == null)
			return null;
		Set<Integer> result = new HashSet<>();
		for (int i : lRes) {
			for (int j : rRes) {
				if (numeroParole(i, j, contesto) <= 25)
					result.add(i);
			}
		}
		if (result.size() == 0)
			return null;
		List<Integer> ris = new ArrayList<>(result);
		Collections.sort(ris);
		return ris;
	}

	private int numeroParole(int x, int y, String contesto) {
		return 0; // TODO
	} // 

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(left);
		if (right != null) {
			sb.append(" NEAR ").append(right);
		}
		return sb.toString();
	}
}
