import java.util.ArrayList;
import java.util.List;

public class Vertex {
	public int id;
	public int degree;
	List<Integer> adj;

	public Vertex(int id) {
		this.id = id;
		this.adj = new ArrayList();
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
}