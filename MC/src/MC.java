import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MC {

	public static void readData(String file, Data d) {
		String line = "";
		String l[] = {};

		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);

			while (true) {
				line = buffer.readLine();
				if (line == null)
					break;
				l = line.split(";");
				d.id.add(Integer.parseInt(l[0]));
				d.idWork.add(Integer.parseInt(l[1]));
				d.idAuthor.add(Integer.parseInt(l[2]));
			}
			buffer.close();
		} catch (IOException e) {
			System.err.printf("Sorry, it was not possible to open the file.\nERROR %s.\n", e.getMessage());
		}
	}
	
	public static void addVertex(Data d, Graph g) {
		for (int i = 0; i < d.id.size(); i++) {
			g.vertex.add(new Vertex(d.id.get(i)));
		}
	}

	public static void addEdge(Data d,Graph g) {
		for (int i = 0; i < d.id.size() - 1; i++) {
			for (int j = i + 1; j < d.id.size(); j++) {
				if ((d.idWork.get(i) == d.idWork.get(j)) || (d.idAuthor.get(i) == d.idAuthor.get(j))) {
					g.edge.add(new Edge(d.id.get(i), d.id.get(j)));
					g.edge.add(new Edge(d.id.get(j), d.id.get(i)));
					g.vertex.get(i).addAdj(j + 1);
					g.vertex.get(j).addAdj(i + 1);
				}
			}
			g.vertex.get(i).degree = g.vertex.get(i).adj.size();
		}
	}

	public static  void buildGraph(Data d,Graph g) {
		addVertex(d,g);
		addEdge(d,g);
		g.n = g.vertex.size();
	}
	
	public static void main(String[] args) {
		Data data = new Data();
		long timeGraph, timeClique;
		long memoryTotal1, memoryFree1;
		long memoryTotal2, memoryFree2;

		memoryTotal1 = Runtime.getRuntime().totalMemory();
		memoryFree1 = Runtime.getRuntime().freeMemory();

		Save save = new Save();
		Graph graph = new Graph();
		Clique clique = new Clique();

		String FILE = "../../work_author.csv";
		readData(FILE, data);

		timeGraph = System.currentTimeMillis();
		buildGraph(data, graph);
		timeGraph = System.currentTimeMillis() - timeGraph;

		timeClique = System.currentTimeMillis();
		clique.search(graph);
		timeClique = System.currentTimeMillis() - timeClique;

		memoryTotal2 = Runtime.getRuntime().totalMemory();
		memoryFree2 = Runtime.getRuntime().freeMemory();

		save.saveMedicoes(timeGraph, timeClique, memoryTotal1, memoryFree1, memoryTotal2, memoryFree2);
	}
}
