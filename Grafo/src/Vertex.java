import java.util.ArrayList;
import java.util.List;

public class Vertex {
	public int id;
	public int degree;
	List<Integer> adj;

	public Vertex(int id, int degree) {
		this.id = id;
		this.degree = degree;
		this.adj = new ArrayList<>();
	}

	public void addAdj(int v) {
		this.adj.add(v);
	}

	public boolean isAdj(int v) {
		int i = 0;
		while (i < adj.size()) {
			if (adj.get(i) == v)
				return true;
			else
				i++;
		}
		return false;
	}
	@Override
	public boolean equals(Object obj) {
		Vertex v = (Vertex) obj;
		return id == v.id;
	}
}