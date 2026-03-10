import java.util.*;

public class AutocompleteSystem {

    private Map<String, Integer> queryFrequency;
    private Map<String, List<String>> prefixMap;

    public AutocompleteSystem() {
        queryFrequency = new HashMap<>();
        prefixMap = new HashMap<>();
    }

    public void addQuery(String query) {

        queryFrequency.put(query, queryFrequency.getOrDefault(query, 0) + 1);

        for (int i = 1; i <= query.length(); i++) {

            String prefix = query.substring(0, i);

            prefixMap.putIfAbsent(prefix, new ArrayList<>());

            if (!prefixMap.get(prefix).contains(query)) {
                prefixMap.get(prefix).add(query);
            }
        }
    }

    public List<String> search(String prefix) {

        List<String> queries = prefixMap.getOrDefault(prefix, new ArrayList<>());

        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> queryFrequency.get(b) - queryFrequency.get(a)
        );

        pq.addAll(queries);

        List<String> result = new ArrayList<>();

        int count = 0;

        while (!pq.isEmpty() && count < 10) {
            result.add(pq.poll());
            count++;
        }

        return result;
    }

    public void updateFrequency(String query) {
        addQuery(query);
    }

    public static void main(String[] args) {

        AutocompleteSystem system = new AutocompleteSystem();

        system.addQuery("java tutorial");
        system.addQuery("java tutorial");
        system.addQuery("javascript");
        system.addQuery("java download");
        system.addQuery("java compiler");
        system.addQuery("javascript");
        system.addQuery("javascript");

        List<String> suggestions = system.search("jav");

        for (String s : suggestions) {
            System.out.println(s);
        }

        system.updateFrequency("java tutorial");

        System.out.println("Updated suggestions:");

        suggestions = system.search("jav");

        for (String s : suggestions) {
            System.out.println(s);
        }
    }
}