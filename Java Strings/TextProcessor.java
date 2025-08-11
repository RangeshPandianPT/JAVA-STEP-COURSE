import java.util.*;

public class TextProcessor {

    public static String cleanInput(String input) {
        input = input.trim().replaceAll("\\s+", " ");
        if (!input.isEmpty()) {
            input = input.substring(0, 1).toUpperCase() + input.substring(1);
        }
        return input;
    }

       public static void analyzeText(String text) {
       
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        String[] sentences = text.split("[.!?]");
        int sentenceCount = (text.trim().isEmpty()) ? 0 : sentences.length;

        int charCount = text.replace(" ", "").length();
        String longestWord = "";
        for (String w : words) {
            String cleanedWord = w.replaceAll("[^a-zA-Z]", "");                 
        if (cleanedWord.length() > longestWord.length()) 
        {
                longestWord = cleanedWord;
            }
        }

        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }
        char mostCommonChar = ' ';
        int maxFreq = 0;
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                mostCommonChar = entry.getKey();
                maxFreq = entry.getValue();
            }
        }

        System.out.println("\n--- Text Analysis ---");
        System.out.println("Words: " + wordCount);
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Characters (excluding spaces): " + charCount);
        System.out.println("Longest Word: " + longestWord);
        System.out.println("Most Common Character: '" + mostCommonChar + "' (" + maxFreq + " times)");
    }
    public static String[] getWordsSorted(String text) {
        String[] words = text.replaceAll("[^a-zA-Z\\s]", "").toLowerCase().split("\\s+");
        Arrays.sort(words);
        return words;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a paragraph of text: ");
        String paragraph = scanner.nextLine();

        String cleaned = cleanInput(paragraph);

        if (cleaned.isEmpty()) {
            System.out.println("Error: No text entered.");
            scanner.close();
            return;
        }
        analyzeText(cleaned);
        String[] sortedWords = getWordsSorted(cleaned);
        System.out.println("\n--- Words in Alphabetical Order ---");
        for (String word : sortedWords) {
            System.out.println(word);
        }
        System.out.print("\nEnter a word to search for: ");
        String searchWord = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (String word : sortedWords) {
            if (word.equals(searchWord)) {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("The word \"" + searchWord + "\" was found in the text.");
        } else {
            System.out.println("The word \"" + searchWord + "\" was NOT found in the text.");
        }

        scanner.close();
    }
}

