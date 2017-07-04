
import java.util.ArrayList;

public class Clique {
	public int cliqueSize;
	public ArrayList<Integer> cliqueVertex;
	public ArrayList<Integer>[] colour;
	public int access;

	Clique() {
		this.cliqueSize = 0;
		this.cliqueVertex = new ArrayList();
		this.access = 0;
	}

	void expand(Graph g, ArrayList<Integer> C, ArrayList<Integer> P) {
		numberSort(g, P);

		for (int i = P.size() - 1; i >= 0; i--) {
			if (C.size() + P.size() <= cliqueSize)
				return;

			int v = P.get(i);
			C.add(v);
			access++;

			ArrayList<Integer> newP = new ArrayList<Integer>(i);

			for (int j = 0; j <= i; j++) {
				int u = P.get(j);

				Vertex vT = findVertex(g.vertex, v - 1);
				if (vT != null && vT.isAdj(u))
					newP.add(u);
			}

			if (newP.isEmpty() && C.size() > cliqueSize)
				saveSolution(C);

			if (!newP.isEmpty())
				expand(g, C, newP);

			C.remove(C.size() - 1);
			P.remove(i);
		}
	}

	public void search(Graph g, String file) {
		Save s = new Save(file);

		ArrayList<Integer> P = new ArrayList();
		ArrayList<Integer> C = new ArrayList();

		colour = new ArrayList[g.n];
		for (int i = 0; i < g.n; i++)
			colour[i] = new ArrayList();

		for (int i = 0; i < g.n; i++)
			P.add(g.vertex.get(i).id);

		expand(g, C, P);

		s.saveClique(cliqueVertex);
		s.saveOthers(access, cliqueSize, g);
	}

	public void numberSort(Graph g, ArrayList<Integer> P) {
		int colours = 0;

		for (int i = 0; i < P.size(); i++)
			colour[i].clear();

		for (int i = 0; i < P.size(); i++) {
			int v = P.get(i);
			int k = 0;
			// System.out.println(v);
			while (containAdj(g, v, colour[k]))
				k++;

			colour[k].add(v);
			colours = Math.max(colours, k + 1);
		}

		P.clear();
		// sort
		for (int k = 0; k < colours; k++)
			for (int j = 0; j < colour[k].size(); j++) {
				int v = (colour[k].get(j));
				P.add(v);
			}
	}

	boolean containAdj(Graph g, int v, ArrayList<Integer> Cl) {
		for (int i = 0; i < Cl.size(); i++) {
			int u = Cl.get(i);

			Vertex vT = findVertex(g.vertex, v - 1);
			if (vT != null && vT.isAdj(u))
				return true;
		}
		return false;
	}

	Vertex findVertex(ArrayList<Vertex> vList, int v) {
		// for (Vertex vertex : vList) {
		// if(vertex.id == v){
		// //System.gc();
		// return vertex;
		// }
		// }
		for (int i = 0; i < vList.size(); i++) {
			if (vList.get(i).id == v) {
				// System.gc();
				return vList.get(i);
			}
		}
		return null;
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

		return String.format(s);
	}
}