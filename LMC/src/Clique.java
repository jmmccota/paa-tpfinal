
import java.util.ArrayList;

public class Clique {
	public int cliqueSize;
	public ArrayList<Integer> cliqueVertex;
	public int lowerBound;
	public int access;
	private String file;

	public Clique(String file) {
		this.cliqueSize = 0;
		this.cliqueVertex = new ArrayList();
		this.lowerBound = 0;
		this.access = 0;
		this.file = file;
	}

	void expand(Graph g, ArrayList<Integer> C, ArrayList<Integer> P) {
		for (int i = P.size() - 1; i >= 0; i--) {
			if (C.size() + P.size() <= cliqueSize)
				return;

			int v = P.get(i);
			access++;
			C.add(v);

			ArrayList<Integer> newP = new ArrayList<Integer>();

			for (int u : P){
				Vertex vT = g.findVertex(v);
				if (vT != null && vT.isAdj(u) && vT.id != u){
					newP.add(u);
				}
			}

			if (newP.isEmpty() && C.size() > cliqueSize)
				saveSolution(C);

			if (!newP.isEmpty())
				expand(g, C, newP);

			C.remove((Integer) v);
			P.remove((Integer) v);
		}
	}

	public void search(Graph g) {
		Save s = new Save(file);

		ArrayList<Integer> P = new ArrayList();
		ArrayList<Integer> C = new ArrayList();

		for (int i = 0; i < g.n; i++)
			P.add(g.vertex.get(i).id);

		LowerBound lb = new LowerBound();
		lowerBound = lb.calcLowerBound(g, C, P);

		for (int i = 0; i < P.size(); i++) {
			int v = P.get(i);
			Vertex vT = g.findVertex(v);
			if (vT!=null && vT.degree < this.lowerBound - 1) {
				P.remove(i);
				i--;
			}
		}

		expand(g, C, P);

		s.saveClique(cliqueVertex);
		s.saveOthers(access, cliqueSize, lowerBound, g);
	}

	void saveSolution(ArrayList<Integer> C) {
		cliqueVertex = new ArrayList();
		for (int i : C)
			cliqueVertex.add(i);
		cliqueSize = C.size();
	}

	public String toString() {
		String s = "Vertex = ";
		for (int i = 0; i < this.cliqueVertex.size(); i++)
			s += this.cliqueVertex.get(i) + " ";
		s += "\nClique Size = " + this.cliqueSize;
		s += "\nLower Bound = " + this.lowerBound;

		return String.format(s);
	}
}