import java.util.ArrayList;
import java.util.Scanner;

public class SubstringReplace {

    public static ArrayList<Integer> findOccurrences(String text, String find) {
        ArrayList<Integer> positions = new ArrayList<>();
        int index = text.indexOf(find);
        while (index != -1) {
            positions.add(index);
            index = text.indexOf(find, index + find.length());
        }
        return positions;
    }

    public static String manualReplace(String text, String find, String replace) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (i <= text.length() - find.length() && text.substring(i, i + find.length()).equals(find)) {
                result.append(replace);
                i += find.length();
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static boolean compareWithBuiltIn(String original, String find, String replace) {
        String manual = manualReplace(original, find, replace);
        String builtin = original.replace(find, replace);
        return manual.equals(builtin);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the main text:");
        String text = scanner.nextLine();
        System.out.println("Enter the substring to find:");
        String find = scanner.nextLine();
        System.out.println("Enter the replacement substring:");
        String replace = scanner.nextLine();
        ArrayList<Integer> occurrences = findOccurrences(text, find);
        System.out.println("Occurrences of substring found at positions: " + occurrences);
        String manualResult = manualReplace(text, find, replace);
        System.out.println("Manual Replace Result: " + manualResult);
        String builtinResult = text.replace(find, replace);
        System.out.println("Built-in Replace Result: " + builtinResult);
        boolean isSame = compareWithBuiltIn(text, find, replace);
        System.out.println("Do both results match? " + isSame);
        scanner.close();
    }
}
