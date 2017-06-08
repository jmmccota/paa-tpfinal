
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
	public ArrayList<Integer> id;
	public ArrayList<Integer> idWork;
	public ArrayList<Integer> idAuthor;

	public Data() {
		id = new ArrayList();
		idWork = new ArrayList();
		idAuthor = new ArrayList();
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < id.size(); i++) {
			s += this.id.get(i) + " " + this.idWork.get(i) + " " + this.idAuthor.get(i) + "\n";
		}
		return String.format(s);
	}
}