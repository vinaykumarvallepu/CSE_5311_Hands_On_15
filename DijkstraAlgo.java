import java.util.*;

public class DijkstraAlgo {
    public static Map<String, Integer> dijkstra(Map<String, Map<String, Integer>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        pq.offer(start);

        while (!pq.isEmpty()) {
            String currentNode = pq.poll();

            for (Map.Entry<String, Integer> neighborEntry : graph.get(currentNode).entrySet()) {
                String neighbor = neighborEntry.getKey();
                int weight = neighborEntry.getValue();
                int distance = distances.get(currentNode) + weight;

                if (distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    pq.offer(neighbor);
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("A", Map.of("B", 3, "C", 1));
        graph.put("B", Map.of("A", 3, "C", 7, "D", 5));
        graph.put("C", Map.of("A", 1, "B", 7, "D", 2));
        graph.put("D", Map.of("B", 5, "C", 2));

        String startNode = "A";

        Map<String, Integer> shortestDistances = dijkstra(graph, startNode);

        System.out.println("Shortest distances from node " + startNode + ":");
        for (Map.Entry<String, Integer> entry : shortestDistances.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}


