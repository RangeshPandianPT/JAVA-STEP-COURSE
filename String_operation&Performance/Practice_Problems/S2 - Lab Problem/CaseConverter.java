import java.util.Scanner;

public class CaseConverter {

    public static char toUpperCaseChar(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 32);
        }
        return ch;
    }

    public static char toLowerCaseChar(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch + 32);
        }
        return ch;
    }

    public static String toUpperCase(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(toUpperCaseChar(text.charAt(i)));
        }
        return sb.toString();
    }

    public static String toLowerCase(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(toLowerCaseChar(text.charAt(i)));
        }
        return sb.toString();
    }

    public static String toTitleCase(String text) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                newWord = true;
                sb.append(ch);
            } else {
                if (newWord) {
                    sb.append(toUpperCaseChar(ch));
                    newWord = false;
                } else {
                    sb.append(toLowerCaseChar(ch));
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = scanner.nextLine();

        String upper = toUpperCase(text);
        String lower = toLowerCase(text);
        String title = toTitleCase(text);

        String builtinUpper = text.toUpperCase();
        String builtinLower = text.toLowerCase();

        System.out.println("\nConversion Results ");
        System.out.printf("%-20s %-40s%n", "Conversion", "Result");
        System.out.printf("%-20s %-40s%n", "Uppercase (Manual)", upper);
        System.out.printf("%-20s %-40s%n", "Uppercase (Built-in)", builtinUpper);
        System.out.printf("%-20s %-40s%n", "Lowercase (Manual)", lower);
        System.out.printf("%-20s %-40s%n", "Lowercase (Built-in)", builtinLower);
        System.out.printf("%-20s %-40s%n", "Title Case (Manual)", title);

        scanner.close();
    }
}
