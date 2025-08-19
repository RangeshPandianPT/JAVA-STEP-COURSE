import java.util.Scanner;

public class FirstNonRepeating {

    static char firstNonRepeatingChar(String text) {
        int[] freq = new int[256]; // ASCII size

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            freq[c]++;
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (freq[c] == 1) return c;
        }

        return '\0';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = sc.nextLine();

        char result = firstNonRepeatingChar(input);

        if (result == '\0')
            System.out.println("No non-repeating character found.");
        else
            System.out.println("First non-repeating character: " + result);
    }
}
