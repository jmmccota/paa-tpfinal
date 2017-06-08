import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MCQ {

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
				ArrayList<Integer> tList = d.trabalhos.get(Integer.parseInt(l[1]));
				if (tList == null) {
					d.trabalhos.put(Integer.parseInt(l[1]), new ArrayList<>());
					tList = d.trabalhos.get(Integer.parseInt(l[1]));
				} else {
					tList.add(Integer.parseInt(l[2]));
				}

			}
			buffer.close();
		} catch (IOException e) {
			System.err.printf("Sorry, it was not possible to open the file.\nERROR %s.\n", e.getMessage());
		}
	}

	public static void addVertex(Data d, Graph g) {
		for (Map.Entry<Integer, ArrayList<Integer>> entry : d.trabalhos.entrySet()) {
			Integer key = entry.getKey();
			ArrayList<Integer> value = entry.getValue();
			for (Integer m : value) {
				g.vertex.add(new Vertex(m, 0));
			}
		}
	}

	public static void addEdge(Data d, Graph g) {

		for (Map.Entry<Integer, ArrayList<Integer>> entry : d.trabalhos.entrySet()) {
			Integer key = entry.getKey();
			ArrayList<Integer> value = entry.getValue();
			for (int i = 0; i < value.size(); i++) {
				for (int j = i + 1; j < value.size(); j++) {

					g.edge.add(new Edge(value.get(i), value.get(j)));
					g.edge.add(new Edge(value.get(j), value.get(i)));
					g.vertex.get(i).addAjd(j + 1);
					g.vertex.get(j).addAjd(i + 1);

				}
				g.vertex.get(i).degree = g.vertex.get(i).adj.size();
			}
		}
	}

	public static void buildGraph(Data d, Graph g) {
		addVertex(d, g);
		addEdge(d, g);

		g.n = g.vertex.size();
	}

	public static void main(String[] args) {
		long timeGraph, timeClique;
		long memoryTotal1, memoryFree1;
		long memoryTotal2, memoryFree2;

		memoryTotal1 = Runtime.getRuntime().totalMemory();
		memoryFree1 = Runtime.getRuntime().freeMemory();
		String FILE = "C:/Users/JMMCC/Downloads/Trabalho Final/Workspace/work_author.csv";

		Save save = new Save(FILE);
		Data data = new Data();
		Graph graph = new Graph();
		Clique clique = new Clique();

		readData(FILE, data);

		timeGraph = System.currentTimeMillis();
		buildGraph(data, graph);
		timeGraph = System.currentTimeMillis() - timeGraph;

		timeClique = System.currentTimeMillis();
		clique.search(graph, FILE);
		timeClique = System.currentTimeMillis() - timeClique;

		memoryTotal2 = Runtime.getRuntime().totalMemory();
		memoryFree2 = Runtime.getRuntime().freeMemory();

		save.saveMedicoes(timeGraph, timeClique, memoryTotal1, memoryFree1, memoryTotal2, memoryFree2);
	}
}