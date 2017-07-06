package UMC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
    public ArrayList<Integer> u;
    public ArrayList<Integer> v;
    
    Data() {
        u = new ArrayList();
        v = new ArrayList();
    }
    
    //ler arquivo contendo o conjunto inicial
    void readData(String file) {
        String line = "";
        String l[] = {};
                
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            
            while (true) {
                line = buffer.readLine();
                if(line == null) break;
                l = line.split(" ");
                u.add(Integer.parseInt(l[0]));
                v.add(Integer.parseInt(l[1]));
            }
            buffer.close();
        } 
        catch (IOException e) {
            System.err.printf("Sorry, it was not possible to open the file.\nERROR %s.\n", e.getMessage());
        }
    }
    
    public String toString() {
        String s = "";
        for(int i=0; i<u.size(); i++) {
            s += this.u.get(i) + " " +
                 this.v.get(i) + "\n";
        }
        return String.format(s);
    }
}