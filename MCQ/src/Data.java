
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Data {
//	public ArrayList<Integer> id;
//	public ArrayList<Integer> idWork;
//	public ArrayList<Integer> idAuthor;
	
	public HashMap<Integer, ArrayList<Integer>> trabalhos;

	public Data() {
//		id = new ArrayList();
//		idWork = new ArrayList();
//		idAuthor = new ArrayList();
		trabalhos = new HashMap<>();
	}

	public String toString() {
		String s = "";
//		for (int i = 0; i < id.size(); i++) {
//			s += this.id.get(i) + " " + this.idWork.get(i) + " " + this.idAuthor.get(i) + "\n";
//		}
	
		for (Map.Entry<Integer, ArrayList<Integer>> entry : trabalhos.entrySet()) {
			Integer key = entry.getKey();
		    ArrayList<Integer> value = entry.getValue();
		    s += key +": [";
			for(Integer m : value){
				s += m +", ";
			}
			s +=  "]\n";
		}
		return String.format(s);
	}
}