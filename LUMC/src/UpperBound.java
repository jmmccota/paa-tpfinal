import java.util.ArrayList;

public class UpperBound {
	public int upper;

	public UpperBound() {
		this.upper = 0;
	}

	public int calcUpperBound(Graph g, ArrayList<Integer> C, ArrayList<Integer> P) {

		ArrayList<Integer>[] colour = new ArrayList[g.n];
		for (int i = 0; i < g.n; i++)
			colour[i] = new ArrayList<>();

		Sort(g, P, 0, P.size());

		for (int i = P.size() - 1; i >= 0; i--) {
			int v = P.get(i);
			int k = 0;

			while (containAdj(g, v, colour[k]))
				k++;

			colour[k].add(v);
			upper = Math.max(upper, k + 1);
		}

		return upper;
	}

	boolean containAdj(Graph g, int v, ArrayList<Integer> Cl) {
		for (int i = 0; i < Cl.size(); i++) {
			int u = Cl.get(i);
			Vertex vT = g.findVertex(v);
			if (vT != null && vT.isAdj(u) && vT.id != u)
				return true;
		}
		return false;
	}

	public void Merge(Graph g, ArrayList<Integer> P, int p, int q, int r) {
		ArrayList<Integer> w = new ArrayList<>();
		int i = p; // itera pelo primeiro vetor ordenado
		int j = q; // itera pelo segundo vetor ordenado
		int k = 0;

		while (i < q && j < r) {
			Vertex vT1 = g.findVertex(P.get(i));
			Vertex vT2 = g.findVertex(P.get(j));
			if (vT1 != null && vT2 != null && vT1.degree <= vT2.degree)
				w.add(k++, P.get(i++));
			else {
				w.add(k++, P.get(j++));
			}
		}

		while (i < q)
			w.add(k++, P.get(i++));
		while (j < r)
			w.add(k++, P.get(j++));

		for (i = p; i < r; ++i)
			P.set(i, w.get(i - p));
	}

	public void Sort(Graph g, ArrayList<Integer> P, int p, int r) {
		if (p < r - 1) {
			int q = (p + r) / 2;
			Sort(g, P, p, q); // primeira metade
			Sort(g, P, q, r); // segunda metade
			Merge(g, P, p, q, r);
		}
	}
}
