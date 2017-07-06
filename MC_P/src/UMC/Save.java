package UMC;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Save {
	private String data;// ; = "C:/Users/JMMCC/Downloads/Trabalho
						// Final/Workspace/Dados/johnson8-4-4.txt";

	public Save(String file) {
		data = file;
	}

	public void saveClique(ArrayList<Integer> cliqueVertex) {
		try {
			FileWriter writer = new FileWriter(data + "_Clique.txt");
			PrintWriter print = new PrintWriter(writer);

			for (int i = cliqueVertex.size() - 1; i >= 0; i--)
				print.printf(cliqueVertex.get(i) + "\n");

			print.close();
			writer.close();
		} catch (IOException e) {
			System.err.printf("Sorry, it was not possible to save the file.\nERROR %s.\n", e.getMessage());
		}
	}

	public void saveMedicoes(long tg, long tc, long mt1, long mf1, long mt2, long mf2) {
		try {
			FileWriter writer = new FileWriter(data + "_Medicoes.txt");
			PrintWriter print = new PrintWriter(writer);

			print.printf("Time Graph: " + tg + "\n");
			print.printf("Time Clique: " + tc + "\n");
			print.printf("Memory Total 1: " + mt1 + "\n");
			print.printf("Memory Free 1: " + mf1 + "\n");
			print.printf("Memory Total 2: " + mt2 + "\n");
			print.printf("Memory Free 2: " + mf2 + "\n");

			print.close();
			writer.close();
		} catch (IOException e) {
			System.err.printf("Sorry, it was not possible to save the file.\nERROR %s.\n", e.getMessage());
		}
	}

	public void saveOthers(int access, int cliqueSize, int upperBound, Graph g) {
		float degree = 0;
		for (int i = 0; i < g.n; i++)
			degree += g.vertex.get(i).degree;
		degree /= g.n;

		try {
			FileWriter writer = new FileWriter(data + "_Others.txt");
			PrintWriter print = new PrintWriter(writer);

			print.printf("Access: " + access + "\n");
			print.printf("Clique Size: " + cliqueSize + "\n");
			print.printf("Upper Bound: " + upperBound + "\n");
			print.printf("Average Degree: " + degree + "\n");

			print.close();
			writer.close();
		} catch (IOException e) {
			System.err.printf("Sorry, it was not possible to save the file.\nERROR %s.\n", e.getMessage());
		}
	}
}
