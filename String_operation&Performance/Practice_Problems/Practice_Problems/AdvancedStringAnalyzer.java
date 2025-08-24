import java.util.Scanner;

public class AdvancedStringAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ADVANCED STRING ANALYZER ===");
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();
        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();

        performAllComparisons(str1, str2);

        System.out.println("\n=== Performance Analysis ===");
        String[] inputs = {"Java", "is", "fast", "and", "powerful"};
        long startTime = System.nanoTime();
        String inefficient = "";
        for (String s : inputs) {
            inefficient += s + " ";
        }
        long endTime = System.nanoTime();
        System.out.println("Inefficient concatenation: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        String optimized = optimizedStringProcessing(inputs);
        endTime = System.nanoTime();
        System.out.println("Optimized with StringBuilder: " + (endTime - startTime) + " ns");

        analyzeMemoryUsage(str1, str2, inefficient, optimized);

        demonstrateStringIntern();

        scanner.close();
    }

    public static double calculateSimilarity(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) dp[i][0] = i;
        for (int j = 0; j <= len2; j++) dp[0][j] = j;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }
        int distance = dp[len1][len2];
        int maxLen = Math.max(len1, len2);
        return (1 - (double) distance / maxLen) * 100;
    }

    public static void performAllComparisons(String str1, String str2) {
        System.out.println("\n=== Comparison Analysis ===");
        System.out.println("Reference equality (==): " + (str1 == str2));
        System.out.println("Content equality (equals): " + str1.equals(str2));
        System.out.println("Case-insensitive equality: " + str1.equalsIgnoreCase(str2));
        System.out.println("Lexicographic comparison: " + str1.compareTo(str2));
        System.out.println("Case-insensitive lexicographic comparison: " + str1.compareToIgnoreCase(str2));
        System.out.printf("Similarity percentage: %.2f%%\n", calculateSimilarity(str1, str2));
    }

    public static void analyzeMemoryUsage(String... strings) {
        System.out.println("\n=== Memory Usage Analysis ===");
        Runtime runtime = Runtime.getRuntime();
        long before = runtime.totalMemory() - runtime.freeMemory();
        for (String s : strings) {
            s.length();
        }
        long after = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Approximate memory used: " + (after - before) + " bytes");
    }

    public static String optimizedStringProcessing(String[] inputs) {
        StringBuilder sb = new StringBuilder();
        for (String s : inputs) {
            sb.append(s).append(" ");
        }
        return sb.toString();
    }

    public static void demonstrateStringIntern() {
        System.out.println("\n=== String Intern Demo ===");
        String s1 = new String("Hello");
        String s2 = "Hello";
        String s3 = s1.intern();
        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("s2 == s3: " + (s2 == s3));
    }
}
