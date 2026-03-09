import java.util.*;

class DNSCache {

    class DNSEntry {
        String domain;
        String ipAddress;
        long expiryTime;

        DNSEntry(String domain, String ipAddress, long ttl) {
            this.domain = domain;
            this.ipAddress = ipAddress;
            this.expiryTime = System.currentTimeMillis() + ttl * 1000;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    private int capacity;
    private LinkedHashMap<String, DNSEntry> cache;
    private int hits;
    private int misses;

    public DNSCache(int capacity) {

        this.capacity = capacity;

        cache = new LinkedHashMap<String, DNSEntry>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> eldest) {
                return size() > DNSCache.this.capacity;
            }
        };

        hits = 0;
        misses = 0;
    }

    public String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null && !entry.isExpired()) {
            hits++;
            return "Cache HIT -> " + entry.ipAddress;
        }

        misses++;

        String newIP = queryUpstreamDNS(domain);

        cache.put(domain, new DNSEntry(domain, newIP, 300));

        return "Cache MISS -> " + newIP;
    }

    private String queryUpstreamDNS(String domain) {

        Random r = new Random();

        return "172.217.14." + (r.nextInt(100) + 1);
    }

    public String getCacheStats() {

        int total = hits + misses;

        double hitRate = total == 0 ? 0 : ((double) hits / total) * 100;

        return "Hit Rate: " + String.format("%.2f", hitRate) + "%";
    }

    public static void main(String[] args) throws Exception {

        DNSCache cache = new DNSCache(5);

        System.out.println(cache.resolve("google.com"));

        System.out.println(cache.resolve("google.com"));

        Thread.sleep(2000);

        System.out.println(cache.resolve("google.com"));

        System.out.println(cache.getCacheStats());
    }
}