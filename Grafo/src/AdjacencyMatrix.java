import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class AdjacencyMatrix {
	private final int vertices;
	private int[][] adjacency_matrix;

	public AdjacencyMatrix(int v) {
		vertices = v;
		adjacency_matrix = new int[vertices + 1][vertices + 1];
	}

	public void makeEdge(int to, int from, int edge) {
		try {
			adjacency_matrix[to][from] = edge;
		} catch (ArrayIndexOutOfBoundsException index) {
			System.out.println("The vertices does not exists");
		}
	}

	public int getEdge(int to, int from) {
		try {
			return adjacency_matrix[to][from];
		} catch (ArrayIndexOutOfBoundsException index) {
			System.out.println("The vertices does not exists");
		}
		return -1;
	}

	public static void main(String args[]) {
		int v, e, count = 1;
		AdjacencyMatrix graph;
		try {

			v = 1480;

			e = 1480;

			graph = new AdjacencyMatrix(v);
			String file = "C:/Users/JMMCC/Downloads/Trabalho Final/Workspace/work_author.csv";
			System.out.println("Enter the edges: <to> <from>");
			Data d = new Data();
			d.readData(file);
			int maior = 0;
			for (Map.Entry<Integer, ArrayList<Integer>> entry : d.trabalhos.entrySet()) {
				ArrayList<Integer> value = entry.getValue();
				for (int i = 0; i < value.size(); i++) {
					for (int j = i + 1; j < value.size(); j++) {
						graph.makeEdge(value.get(i), value.get(j), 1);
					}
				}
				if (value.size() > maior) {
					maior = value.size();
				}
			}
			System.out.println(maior);

			System.out.println("The adjacency matrix for the given graph is: ");
			System.out.print("  ");
			// for (int i = 1; i <= v; i++)
			// System.out.print(i + " ");
			// System.out.println();
			FileWriter writer = new FileWriter(file + "Matrix.txt");
			PrintWriter print = new PrintWriter(writer);
			for (int i = 1; i <= v; i++) {
				// System.out.print(i + " ");
				for (int j = 1; j <= v; j++) {
					print.printf(graph.getEdge(i, j) + " ");
					// System.out.print(graph.getEdge(i, j) + " ");
				}
				print.printf("\r\n");
				// System.out.println();
			}
			print.close();
			writer.close();

		} catch (Exception E) {
			System.out.println("Somthing went wrong");
		}

	}
}