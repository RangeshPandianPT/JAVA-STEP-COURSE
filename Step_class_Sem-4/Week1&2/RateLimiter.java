import java.util.*;

public class RateLimiter {

    class TokenBucket {
        int tokens;
        int maxTokens;
        long lastRefillTime;
        int refillRate;

        TokenBucket(int maxTokens, int refillRate) {
            this.tokens = maxTokens;
            this.maxTokens = maxTokens;
            this.refillRate = refillRate;
            this.lastRefillTime = System.currentTimeMillis();
        }

        synchronized boolean allowRequest() {

            long now = System.currentTimeMillis();
            long elapsed = now - lastRefillTime;

            int tokensToAdd = (int)(elapsed / 3600000.0 * refillRate);

            if(tokensToAdd > 0){
                tokens = Math.min(maxTokens, tokens + tokensToAdd);
                lastRefillTime = now;
            }

            if(tokens > 0){
                tokens--;
                return true;
            }

            return false;
        }
    }

    private Map<String, TokenBucket> clients;

    public RateLimiter(){
        clients = new HashMap<>();
    }

    public boolean checkRateLimit(String clientId){

        clients.putIfAbsent(clientId, new TokenBucket(1000,1000));

        TokenBucket bucket = clients.get(clientId);

        return bucket.allowRequest();
    }

    public String getRateLimitStatus(String clientId){

        TokenBucket bucket = clients.get(clientId);

        if(bucket == null){
            return "Client not found";
        }

        return "used: " + (bucket.maxTokens - bucket.tokens) +
               ", limit: " + bucket.maxTokens;
    }

    public static void main(String[] args){

        RateLimiter limiter = new RateLimiter();

        String client = "abc123";

        for(int i=0;i<1005;i++){

            boolean allowed = limiter.checkRateLimit(client);

            if(!allowed){
                System.out.println("Request denied at request #" + (i+1));
                break;
            }
        }

        System.out.println(limiter.getRateLimitStatus(client));
    }
}