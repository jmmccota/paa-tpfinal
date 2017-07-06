import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
	public ArrayList<Vertex> vertex;
	public ArrayList<Edge> edge;
	public int n;

	public Graph() {
		this.vertex = new ArrayList<>();
		this.edge = new ArrayList<>();
		this.n = 0;
	}

	public void addVertex(Data d) {
		for (Map.Entry<Integer, ArrayList<Integer>> entry : d.trabalhos.entrySet()) {
			Integer key = entry.getKey();
			ArrayList<Integer> value = entry.getValue();
			for (Integer m : value) {
				if(!vertex.contains(new Vertex(m, 0))){
					vertex.add(new Vertex(m, 0));
				}
			}
		}
		
	}

	public void addEdge(Data d) {
		for (Map.Entry<Integer, ArrayList<Integer>> entry : d.trabalhos.entrySet()) {
			Integer key = entry.getKey();
			ArrayList<Integer> value = entry.getValue();

			for (int i = 0; i < value.size(); i++) {
				for (int j = i + 1; j < value.size(); j++) {

					edge.add(new Edge(value.get(i), value.get(j)));
					edge.add(new Edge(value.get(j), value.get(i)));
					vertex.get(i).addAdj(j + 1);
					vertex.get(j).addAdj(i + 1);

				}
				vertex.get(i).degree = vertex.get(i).adj.size();
			}
		}
	}
	public static Vertex findVertex(ArrayList<Vertex> vList, int v) {
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