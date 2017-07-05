
import java.util.ArrayList;

public class Clique {
    public int cliqueSize;
    public ArrayList<Integer> cliqueVertex;
    public int access;
    private String file;
    public Clique(String file) {
    	this.file = file;
        this.cliqueSize = 0;
        this.cliqueVertex = new ArrayList();
        this.access = 0;
    }
    
    public void search(Graph g) {
        Save s = new Save(file);
        
        ArrayList<Integer> P = new ArrayList();
        ArrayList<Integer> C = new ArrayList();
        
        for(int i=0; i<g.n; i++)
            P.add(g.vertex.get(i).id);
        
        expand(g, C, P);
        
        s.saveClique(cliqueVertex);
        s.saveOthers(access, cliqueSize, g);
    }
    
    public void expand(Graph g, ArrayList<Integer> C, ArrayList<Integer> P) {
	for (int i=P.size()-1; i>=0; i--){
            access++;
	    if (C.size() + P.size() <= cliqueSize) return;
	    
            int v = P.get(i);
            C.add(v);
	    
            ArrayList<Integer> newP = new ArrayList<Integer>();
	    
            for(int u : P)
                if(g.vertex.get(v-1).isAdj(u))
                    newP.add(u);
            
	    if(newP.isEmpty() && C.size() > cliqueSize)
                saveSolution(C);
            
	    if(!newP.isEmpty())
                expand(g, C, newP);
            
	    C.remove((Integer)v);
	    P.remove((Integer)v);
	}
    }
    
    void saveSolution(ArrayList<Integer> C) {
	cliqueVertex = new ArrayList();
        for (int i : C)
            cliqueVertex.add(i);
        cliqueSize = C.size();
    }
    
    public String toString() {
        String s = "Vertex = ";
        for(int i=0; i<this.cliqueVertex.size(); i++)
            s += this.cliqueVertex.get(i) + " ";
        s += "\nClique Size = " + this.cliqueSize;
        
        return String.format(s);
    }
}