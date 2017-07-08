
import java.util.ArrayList;

public class LowerBound {
	public int lower;

	public void LowerBound() {
		this.lower = 0;
	}

	public int calcLowerBound(Graph g, ArrayList<Integer> C, ArrayList<Integer> P) {
		int maxDegree = 0;
		int vertexMax = -1;

		for (int i = 0; i < g.n; i++) {
			if (g.vertex.get(i).degree > maxDegree) {
				vertexMax = g.vertex.get(i).id;
				maxDegree = g.vertex.get(i).degree;
			}
		}

		ArrayList<Integer> newP = new ArrayList<Integer>();

		for (int u : P)
			if (g.vertex.get(vertexMax - 1).isAdj(u))
				newP.add(u);
		newP.add(vertexMax);

		expand(g, C, newP, vertexMax);

		return lower;
	}

	public void expand(Graph g, ArrayList<Integer> C, ArrayList<Integer> P, int v) {
		if (C.size() + P.size() <= lower)
			return;
		C.add(v);

		ArrayList<Integer> newP = new ArrayList<Integer>();

		for (int u : P){
			Vertex vT = g.findVertex(v);
				if (vT != null && vT.isAdj(u) && vT.id != u){
					newP.add(u);
				}
		}

		if (newP.isEmpty() && C.size() > lower)
			lower = C.size();

		if (!newP.isEmpty())
			expand(g, C, newP, newP.get(newP.size() - 1));

		C.remove((Integer) v);
		P.remove((Integer) v);
	}

}
