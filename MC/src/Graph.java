
import java.util.ArrayList;
import java.util.List;

public class Graph {
	
	public ArrayList<Vertex> vertex;
	public ArrayList<Edge> edge;
	public int n;

	public Graph() {
		this.vertex = new ArrayList();
		this.edge = new ArrayList();
		this.n = 0;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < this.vertex.size(); i++) {
			s += i + 1;
			for (int j = 0; j < this.vertex.get(i).adj.size(); j++)
				s += " " + this.vertex.get(i).adj.get(j);
			s += "\n";
		}
		return String.format(s);
	}
}
