import java.util.*;

public class SpellChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();
        String[] dictionary = {"hello","world","java","programming","simple","spell","checker","correct","example","text"};
        List<String> words = extractWords(sentence);
        System.out.printf("%-15s %-15s %-10s %-15s%n", "Word", "Suggestion", "Distance", "Status");
        for (String w : words) {
            if (isInDictionary(w, dictionary)) {
                System.out.printf("%-15s %-15s %-10d %-15s%n", w, "-", 0, "Correct");
            } else {
                String suggestion = findClosest(w, dictionary);
                int dist = suggestion.equals("-") ? -1 : distance(w, suggestion);
                System.out.printf("%-15s %-15s %-10d %-15s%n", w, suggestion, dist, "Misspelled");
            }
        }
    }

    static List<String> extractWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isLetter(c)) {
                if (start < i) words.add(text.substring(start, i).toLowerCase());
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start).toLowerCase());
        return words;
    }

    static boolean isInDictionary(String word, String[] dict) {
        for (String d : dict) if (d.equals(word)) return true;
        return false;
    }

    static int distance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[a.length()][b.length()];
    }

    static String findClosest(String word, String[] dict) {
        int minDist = Integer.MAX_VALUE;
        String closest = "-";
        for (String d : dict) {
            int dist = distance(word, d);
            if (dist < minDist) {
                minDist = dist;
                closest = d;
            }
        }
        return minDist <= 2 ? closest : "-";
    }
}
