import java.io.BufferedReader;
import java.io.FileReader;
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

	public void saveFile(String file) throws Exception {
		FileWriter writer = new FileWriter(file + "Matrix.txt");
		PrintWriter print = new PrintWriter(writer);
		int v = vertices;
		for (int i = 1; i <= v; i++) {
			// System.out.print(i + " ");
			for (int j = 1; j <= v; j++) {
				print.printf(getEdge(i, j) + " ");
				// System.out.print(graph.getEdge(i, j) + " ");
			}
			print.printf("\r\n");
			// System.out.println();
		}
		print.close();
		writer.close();
	}

	public static void carregarSBBD() {
		int v;
		AdjacencyMatrix graph;
		try {

			v = 1480;

			graph = new AdjacencyMatrix(v);
			String file = "C:/Users/JMMCC/Downloads/Trabalho Final/Workspace/work_author.csv";
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
			System.out.println("Enter the edges: <to> <from>");

			System.out.println("The adjacency matrix for the given graph is: ");
			System.out.print("  ");
			// for (int i = 1; i <= v; i++)
			// System.out.print(i + " ");
			// System.out.println();

			graph.saveFile(file);

		} catch (Exception E) {
			System.out.println("Somthing went wrong");
		}
	}
	
	
	public static void carregarBenchmark(String file) {
		AdjacencyMatrix graph;
		try {

			
			dimacs.Data d = new dimacs.Data();
			int v = d.readData(file);
			System.out.println(v);
			graph = new AdjacencyMatrix(v);
			for (int i = 0; i<d.u.size(); i++){
				for(int j = i+1; j < d.v.size(); j++){
					graph.makeEdge(d.u.get(i), d.v.get(j), 1);
					graph.makeEdge(d.v.get(i), d.u.get(j), 1);
				}
			}
			
			
			// for (int i = 1; i <= v; i++)
			// System.out.print(i + " ");
			// System.out.println();

			graph.saveFile(file);

		} catch (Exception E) {
			System.out.println("Somthing went wrong");
		}
	}

	public static void main(String args[]) {

		//carregarSBBD();
		carregarBenchmark("C:/Users/JMMCC/Downloads/Trabalho Final/Workspace/Dados/johnson8-4-4.txt");

	}
}