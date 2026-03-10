import java.util.*;

class VideoData {
    String videoId;
    String content;

    VideoData(String videoId, String content) {
        this.videoId = videoId;
        this.content = content;
    }
}

public class MultiLevelCache {

    private LinkedHashMap<String, VideoData> L1;
    private LinkedHashMap<String, VideoData> L2;
    private HashMap<String, VideoData> L3;
    private HashMap<String, Integer> accessCount;

    private int L1_CAPACITY = 10000;
    private int L2_CAPACITY = 100000;

    private int L1_hits = 0;
    private int L2_hits = 0;
    private int L3_hits = 0;

    public MultiLevelCache() {

        L1 = new LinkedHashMap<String, VideoData>(L1_CAPACITY, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, VideoData> eldest) {
                return size() > L1_CAPACITY;
            }
        };

        L2 = new LinkedHashMap<String, VideoData>(L2_CAPACITY, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, VideoData> eldest) {
                return size() > L2_CAPACITY;
            }
        };

        L3 = new HashMap<>();
        accessCount = new HashMap<>();
    }

    public VideoData getVideo(String videoId) {

        if (L1.containsKey(videoId)) {
            L1_hits++;
            incrementAccess(videoId);
            return L1.get(videoId);
        }

        if (L2.containsKey(videoId)) {
            L2_hits++;
            incrementAccess(videoId);
            VideoData data = L2.get(videoId);
            promoteToL1(videoId, data);
            return data;
        }

        if (L3.containsKey(videoId)) {
            L3_hits++;
            incrementAccess(videoId);
            VideoData data = L3.get(videoId);
            promoteToL2(videoId, data);
            return data;
        }

        return null;
    }

    public void addVideoToDatabase(String videoId, String content) {
        L3.put(videoId, new VideoData(videoId, content));
    }

    private void promoteToL1(String videoId, VideoData data) {
        L1.put(videoId, data);
    }

    private void promoteToL2(String videoId, VideoData data) {
        L2.put(videoId, data);
    }

    private void incrementAccess(String videoId) {
        accessCount.put(videoId, accessCount.getOrDefault(videoId, 0) + 1);
    }

    public void invalidate(String videoId) {
        L1.remove(videoId);
        L2.remove(videoId);
        L3.remove(videoId);
        accessCount.remove(videoId);
    }

    public void getStatistics() {

        int total = L1_hits + L2_hits + L3_hits;

        double L1_rate = total == 0 ? 0 : (double)L1_hits / total * 100;
        double L2_rate = total == 0 ? 0 : (double)L2_hits / total * 100;
        double L3_rate = total == 0 ? 0 : (double)L3_hits / total * 100;

        System.out.println("L1 Hit Rate: " + L1_rate + "%");
        System.out.println("L2 Hit Rate: " + L2_rate + "%");
        System.out.println("L3 Hit Rate: " + L3_rate + "%");
    }

    public static void main(String[] args) {

        MultiLevelCache cache = new MultiLevelCache();

        cache.addVideoToDatabase("video_123", "video data 123");
        cache.addVideoToDatabase("video_999", "video data 999");

        cache.getVideo("video_123");
        cache.getVideo("video_123");
        cache.getVideo("video_999");

        cache.getStatistics();
    }
}