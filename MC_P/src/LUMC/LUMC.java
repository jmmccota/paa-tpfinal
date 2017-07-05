package LUMC;


public class LUMC {

    public static void main(String[] args) {
        long timeGraph, timeClique;
        long memoryTotal1, memoryFree1;
        long memoryTotal2, memoryFree2;
        
        memoryTotal1 = Runtime.getRuntime().totalMemory();
        memoryFree1 = Runtime.getRuntime().freeMemory();
        String FILE = "C:/Users/JMMCC/Downloads/Trabalho Final/Workspace/Dados/johnson8-2-4.txt";
        Save save = new Save(FILE);
        Data data = new Data();
        Graph graph = new Graph();
        Clique clique = new Clique(FILE);
        
        
        data.readData(FILE);
        
        timeGraph = System.currentTimeMillis();
        graph.buildGraph(data, 256);
        timeGraph = System.currentTimeMillis() - timeGraph;
        
        timeClique = System.currentTimeMillis();
        clique.search(graph);
        timeClique = System.currentTimeMillis() - timeClique;
        
        memoryTotal2 = Runtime.getRuntime().totalMemory();
        memoryFree2 = Runtime.getRuntime().freeMemory();
        
        save.saveMedicoes(timeGraph, timeClique, memoryTotal1, memoryFree1, memoryTotal2, memoryFree2);
    }
}
