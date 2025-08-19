import java.util.Scanner;

public class ManualSplit {

    public static int findLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {}
        return count;
    }

    public static String[] manualSplit(String text) {
        int len = findLength(text);
        int spaces = 0;

        for (int i = 0; i < len; i++) if (text.charAt(i) == ' ') spaces++;

        String[] words = new String[spaces + 1];
        int start = 0, w = 0;

        for (int i = 0; i <= len; i++) {
            if (i == len || text.charAt(i) == ' ') {
                String word = "";
                for (int j = start; j < i; j++) word += text.charAt(j);
                words[w++] = word;
                start = i + 1;
            }
        }
        return words;
    }

    public static boolean compare(String[] a, String[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) if (!a[i].equals(b[i])) return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String text = sc.nextLine();

        String[] arr1 = manualSplit(text);
        String[] arr2 = text.split(" ");

        System.out.println("Manual split:");
        for (String s : arr1) System.out.println(s);

        System.out.println("Built-in split:");
        for (String s : arr2) System.out.println(s);

        System.out.println("Same result: " + compare(arr1, arr2));
        sc.close();
    }
}
