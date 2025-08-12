import java.util.Scanner;

public class WordsAndLengths {

    public static int strLength(String s) {
        int count = 0;
        try {
            while (true) {
                s.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {}
        return count;
    }

    public static String[] manualSplit(String text) {
        int len = strLength(text);
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

    public static String[][] wordsWithLengths(String[] words) {
        String[][] arr = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            arr[i][0] = words[i];
            arr[i][1] = String.valueOf(strLength(words[i]));
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String text = sc.nextLine();

        String[] words = manualSplit(text);
        String[][] result = wordsWithLengths(words);

        System.out.println("Word\tLength");
        for (String[] row : result) {
            System.out.println(row[0] + "\t" + Integer.parseInt(row[1]));
        }

        sc.close();
    }
}
