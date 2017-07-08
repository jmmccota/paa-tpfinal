import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MC {

	public static void readData(String file, Data d) {
		String line = "";
		String l[] = {};
		int i = 0;
		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);

			while (true) {
				i++;
				line = buffer.readLine();
				if (line == null)
					break;
				l = line.split(";");
				try {
					ArrayList<Integer> tList = d.trabalhos.get(Integer.parseInt(l[1]));
					if (tList == null) {
						d.trabalhos.put(Integer.parseInt(l[1]), new ArrayList<>());
						tList = d.trabalhos.get(Integer.parseInt(l[1]));
					} else {
						if (!tList.contains(Integer.parseInt(l[2]))) {
							tList.add(Integer.parseInt(l[2]));
						}
					}
				} catch (Exception e) {

				}

			}
			System.out.println("Tamanho do arquivo:" + i);
			buffer.close();
		} catch (IOException e) {
			System.err.printf("Sorry, it was not possible to open the file.\nERROR %s.\n", e.getMessage());
		}
	}

	public static void buildGraph(Data d, Graph g) {
		g.addVertex(d);
		g.addEdge(d);

		g.n = g.vertex.size();
	}

	public static void main(String[] args) {
		long timeGraph, timeClique;
		long memoryTotal1, memoryFree1;
		long memoryTotal2, memoryFree2;

		memoryTotal1 = Runtime.getRuntime().totalMemory();
		memoryFree1 = Runtime.getRuntime().freeMemory();
		String FILE = "C:/Users/JMMCC/Downloads/Trabalho Final/Workspace/work_author.csv";
		// String FILE = "C:\\Users\\JMMCC\\Downloads\\Trabalho
		// Final\\Dados\\hamming8-2.txt";

		Save save = new Save(FILE.replace("work_author.csv", "mc"));
		Data data = new Data();

		Graph graph = new Graph();
		Clique clique = new Clique(FILE.replace("work_author.csv", "mc"));

		readData(FILE, data);
		// System.out.println(data.toString());

		timeGraph = System.currentTimeMillis();
		buildGraph(data, graph);
		timeGraph = System.currentTimeMillis() - timeGraph;

		// System.out.println(graph.toString());

		timeClique = System.currentTimeMillis();
		clique.search(graph);
		timeClique = System.currentTimeMillis() - timeClique;

		memoryTotal2 = Runtime.getRuntime().totalMemory();
		memoryFree2 = Runtime.getRuntime().freeMemory();

		save.saveMedicoes(timeGraph, timeClique, memoryTotal1, memoryFree1, memoryTotal2, memoryFree2);
	}
}
