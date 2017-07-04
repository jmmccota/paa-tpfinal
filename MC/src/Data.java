
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {
	public HashMap<Integer, ArrayList<Integer>> trabalhos;

	public Data() {
		trabalhos = new HashMap<>();
	}

	@Override
	public String toString() {
		String s = "";

		for (Map.Entry<Integer, ArrayList<Integer>> entry : trabalhos.entrySet()) {
			Integer key = entry.getKey();
			ArrayList<Integer> value = entry.getValue();
			s += key + ": [";
			for (Integer m : value) {
				s += m + ", ";
			}
			s += "]\r\n";
		}
		return String.format(s);
	}
}