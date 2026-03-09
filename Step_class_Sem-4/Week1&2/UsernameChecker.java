import java.util.*;

public class UsernameChecker {

    private HashMap<String, Integer> usernameMap;

    private HashMap<String, Integer> attemptFrequency;

    public UsernameChecker() {
        usernameMap = new HashMap<>();
        attemptFrequency = new HashMap<>();
    }

    public boolean checkAvailability(String username) {

        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username, 0) + 1);

        return !usernameMap.containsKey(username);
    }

    public void registerUsername(String username, int userId) {
        usernameMap.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;

            if (!usernameMap.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        suggestions.add(username + "_official");

        return suggestions;
    }

    public String getMostAttempted() {

        String mostAttempted = "";
        int max = 0;

        for (String user : attemptFrequency.keySet()) {
            int count = attemptFrequency.get(user);

            if (count > max) {
                max = count;
                mostAttempted = user;
            }
        }

        return mostAttempted + " (" + max + " attempts)";
    }

    public static void main(String[] args) {

        UsernameChecker checker = new UsernameChecker();

        checker.registerUsername("john_doe", 1);
        checker.registerUsername("admin", 2);

        System.out.println("john_doe available: "
                + checker.checkAvailability("john_doe"));

        System.out.println("jane_smith available: "
                + checker.checkAvailability("jane_smith"));

        System.out.println("Suggestions for john_doe: "
                + checker.suggestAlternatives("john_doe"));

        System.out.println("Most attempted username: "
                + checker.getMostAttempted());
    }
}