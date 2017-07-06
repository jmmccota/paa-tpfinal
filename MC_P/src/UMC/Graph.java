package UMC;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public class Vertex {
        public int id;
        public int degree;        
        List<Integer> adj;
        
        public Vertex(int id, int degree) {
            this.id = id;
            this.degree = degree;
            this.adj = new ArrayList();
        }
        
        public void addAdj(int v) {
            this.adj.add(v);
        }
        
        public boolean isAdj(int v) {
            int i = 0;
            while(i<adj.size()) {
                if(adj.get(i) == v) return true;
                else i++;
            }
            return false;
        }
    }
    
    public class Edge {
        public int u;
        public int v;
        
        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
    
    public ArrayList<Vertex> vertex;
    public ArrayList<Edge> edge;
    public int n;
    
    public Graph() {
        this.vertex = new ArrayList();
        this.edge = new ArrayList();
        this.n = 0;
    }
    
    void addVertex(Data d, int q) {
        for(int i=0; i<q; i++) {
            vertex.add(new Vertex(i+1, 0));
        }
    }
    
    void addEdge(Data d) {
        for(int i=0; i<d.u.size(); i++) {
            this.edge.add(new Edge(d.u.get(i), d.v.get(i)));
            this.edge.add(new Edge(d.v.get(i), d.u.get(i)));
            this.vertex.get(d.u.get(i)-1).adj.add(d.v.get(i));
            this.vertex.get(d.v.get(i)-1).adj.add(d.u.get(i));
        }
    }
    
    public void buildGraph(Data d, int q) {
        addVertex(d, q);
        addEdge(d);
        this.n = this.vertex.size();
        for(int j=0; j<n; j++)
            this.vertex.get(j).degree = this.vertex.get(j).adj.size();
    }
    
    @Override
    public String toString() {
        String s = "";
        for(int i=0; i<this.vertex.size(); i++) {
            s += i+1;
            for(int j=0; j<this.vertex.get(i).adj.size(); j++)
                s += " " + this.vertex.get(i).adj.get(j);
            s += "\n";
        }
        return String.format(s);
    }
}