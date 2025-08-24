import java.util.*;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence with mixed formatting: ");
        String input = scanner.nextLine();

        String trimmed = input.trim();
        String replacedSpaces = trimmed.replace(" ", "_");
        String noDigits = replacedSpaces.replaceAll("\\d", "");
        String[] words = noDigits.split("_+");
        String rejoined = String.join(" | ", words);

        System.out.println("1. Trimmed: " + trimmed);
        System.out.println("2. Spaces replaced: " + replacedSpaces);
        System.out.println("3. Digits removed: " + noDigits);
        System.out.println("4. Words split: " + Arrays.toString(words));
        System.out.println("5. Rejoined: " + rejoined);

        String noPunctuation = removePunctuation(trimmed);
        System.out.println("6. Without punctuation: " + noPunctuation);

        String capitalized = capitalizeWords(noPunctuation);
        System.out.println("7. Capitalized: " + capitalized);

        String reversedOrder = reverseWordOrder(noPunctuation);
        System.out.println("8. Reversed order: " + reversedOrder);

        System.out.println("9. Word Frequency:");
        countWordFrequency(noPunctuation);

        scanner.close();
    }

    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }

    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1).toLowerCase())
                      .append(" ");
            }
        }
        return result.toString().trim();
    }

    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            System.out.println("   " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
