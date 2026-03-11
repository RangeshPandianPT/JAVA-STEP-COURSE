import java.util.*;

public class RealTimeAnalyticsDashboard {

    private Map<String, Integer> pageViews;
    private Map<String, Set<String>> uniqueVisitors;
    private Map<String, Integer> trafficSources;

    public RealTimeAnalyticsDashboard() {
        pageViews = new HashMap<>();
        uniqueVisitors = new HashMap<>();
        trafficSources = new HashMap<>();
    }

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    public void getDashboard() {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(pageViews.entrySet());

        System.out.println("Top Pages:");

        int count = 0;

        while (!pq.isEmpty() && count < 10) {

            Map.Entry<String, Integer> entry = pq.poll();

            String page = entry.getKey();
            int views = entry.getValue();
            int unique = uniqueVisitors.get(page).size();

            System.out.println(page + " - " + views + " views (" + unique + " unique)");

            count++;
        }

        System.out.println("Traffic Sources:");

        for (String source : trafficSources.keySet()) {

            System.out.println(source + " : " + trafficSources.get(source));
        }
    }

    public static void main(String[] args) {

        RealTimeAnalyticsDashboard dashboard = new RealTimeAnalyticsDashboard();

        dashboard.processEvent("/article/breaking-news", "user_123", "google");
        dashboard.processEvent("/article/breaking-news", "user_456", "facebook");
        dashboard.processEvent("/sports/championship", "user_789", "google");
        dashboard.processEvent("/sports/championship", "user_123", "direct");
        dashboard.processEvent("/article/breaking-news", "user_123", "google");

        dashboard.getDashboard();
    }
}