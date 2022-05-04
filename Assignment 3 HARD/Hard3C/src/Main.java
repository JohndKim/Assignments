public class Main {
    public static void main(String[] args){ // SO CLEAN BHAHHAHHA
        Graph graph = new Graph(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        System.out.println(graph.tooString(graph.getMatrix()));
        System.out.println(graph.adjacencyMatrixMultiplication());
    }
}