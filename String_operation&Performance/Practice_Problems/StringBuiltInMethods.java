public class StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";

        System.out.println("1. Original length (with spaces): " + sampleText.length());

        String trimmedText = sampleText.trim();
        System.out.println("2. Trimmed length (without leading/trailing spaces): " + trimmedText.length());

        System.out.println("3. Character at index 5: " + sampleText.charAt(5));

        String sub = trimmedText.substring(5, 16);
        System.out.println("4. Substring: " + sub);

        int indexFun = trimmedText.indexOf("Fun");
        System.out.println("5. Index of 'Fun': " + indexFun);

        System.out.println("6. Contains 'Java'? " + trimmedText.contains("Java"));

        System.out.println("7. Starts with 'Java'? " + trimmedText.startsWith("Java"));

        System.out.println("8. Ends with '!'? " + trimmedText.endsWith("!"));

        System.out.println("9. Uppercase: " + trimmedText.toUpperCase());

        System.out.println("10. Lowercase: " + trimmedText.toLowerCase());

        int vowelCount = countVowels(trimmedText);
        System.out.println("11. Number of vowels: " + vowelCount);

        System.out.println("12. Occurrences of 'a':");
        findAllOccurrences(trimmedText, 'a');
    }

    public static int countVowels(String text) {
        int count = 0;
        String vowels = "AEIOUaeiou";
        for (int i = 0; i < text.length(); i++) {
            if (vowels.indexOf(text.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }

    public static void findAllOccurrences(String text, char target) {
        boolean found = false;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                System.out.println("   Found at index: " + i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("   Character '" + target + "' not found in the text.");
        }
    }
}
