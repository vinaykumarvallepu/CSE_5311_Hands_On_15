import java.util.*;

public class BellmanFordAlgo {
    public static Map<String, Integer> bellmanFord(Map<String, Map<String, Integer>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        
        for (int i = 0; i < graph.size() - 1; i++) {
            for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
                String currentNode = entry.getKey();
                Map<String, Integer> neighbors = entry.getValue();

                for (Map.Entry<String, Integer> neighborEntry : neighbors.entrySet()) {
                    String neighbor = neighborEntry.getKey();
                    int weight = neighborEntry.getValue();
                    int distance = distances.get(currentNode) == Integer.MAX_VALUE ? Integer.MAX_VALUE : distances.get(currentNode) + weight;

                    if (distance < distances.get(neighbor)) {
                        distances.put(neighbor, distance);
                    }
                }
            }
        }

        
        for (int i = 0; i < graph.size() - 1; i++) {
            for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
                String currentNode = entry.getKey();
                Map<String, Integer> neighbors = entry.getValue();

                for (Map.Entry<String, Integer> neighborEntry : neighbors.entrySet()) {
                    String neighbor = neighborEntry.getKey();
                    int weight = neighborEntry.getValue();
                    int distance = distances.get(currentNode) == Integer.MAX_VALUE ? Integer.MAX_VALUE : distances.get(currentNode) + weight;

                    if (distance < distances.get(neighbor)) {
                        throw new IllegalArgumentException("Graph contains a negative weight cycle");
                    }
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("A", Map.of("B", 6, "C", 7));
        graph.put("B", Map.of("C", 5, "D", -4, "E", 8));
        graph.put("C", Map.of("B", -2, "D", 9));
        graph.put("D", Map.of("A", 2, "E", 7));
        graph.put("E", Map.of("B", -3));

        String startNode = "A";

        try {
            Map<String, Integer> shortestDistances = bellmanFord(graph, startNode);

            System.out.println("Shortest distances from node " + startNode + ":");
            for (Map.Entry<String, Integer> entry : shortestDistances.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
