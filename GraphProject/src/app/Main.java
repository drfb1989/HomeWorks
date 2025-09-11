package app;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Додавання вершин
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        // Додавання ребер
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        // Вивід графа
        graph.printGraph();

        // Перевірка вершини
        System.out.println("\nЧи існує вершина 2? " + graph.hasVertex(2));
        System.out.println("Чи існує вершина 5? " + graph.hasVertex(5));

        // Перевірка ребра
        System.out.println("\nЧи існує ребро між 1 і 3? " + graph.hasEdge(1, 3));
        System.out.println("Чи існує ребро між 2 і 3? " + graph.hasEdge(2, 3));

        // Видалення ребра
        graph.removeEdge(1, 3);
        System.out.println("\nПісля видалення ребра між 1 і 3:");
        graph.printGraph();

        // Видалення вершини
        graph.removeVertex(2);
        System.out.println("\nПісля видалення вершини 2:");
        graph.printGraph();
    }
}
