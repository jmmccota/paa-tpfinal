
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {
	// public ArrayList<Integer> id;
	// public ArrayList<Integer> idWork;
	// public ArrayList<Integer> idAuthor;

	public HashMap<Integer, ArrayList<Integer>> trabalhos;

	public Data() {
		// id = new ArrayList<>();
		// idWork = new ArrayList<>();
		// idAuthor = new ArrayList<>();
		trabalhos = new HashMap<>();
	}

	// ler arquivo contendo o conjunto inicial
	void readData(String file) {
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
				try {
					ArrayList<Integer> tList = trabalhos.get(Integer.parseInt(l[1]));
					if (tList == null) {
						trabalhos.put(Integer.parseInt(l[1]), new ArrayList<>());
						tList = trabalhos.get(Integer.parseInt(l[1]));
					} else {
						tList.add(Integer.parseInt(l[2]));
					}
				} catch (Exception e) {

				}

			}
			buffer.close();
		} catch (IOException e) {
			System.err.printf("Sorry, it was not possible to open the file.\nERROR %s.\n", e.getMessage());
		}
	}

	public String toString() {
		String s = "";
		// for (int i = 0; i < id.size(); i++) {
		// s += this.id.get(i) + " " + this.idWork.get(i) + " " +
		// this.idAuthor.get(i) + "\n";
		// }

		for (Map.Entry<Integer, ArrayList<Integer>> entry : trabalhos.entrySet()) {
			Integer key = entry.getKey();
			ArrayList<Integer> value = entry.getValue();
			s += key + ": [";
			for (Integer m : value) {
				s += m + ", ";
			}
			s += "]\n";
		}
		return String.format(s);
	}
}