import java.util.Scanner;

public class FrequencyNestedLoops {

    static String[] findFrequency(String text) {
        char[] chars = text.toCharArray();
        int n = chars.length;
        int[] freq = new int[n];

        for (int i = 0; i < n; i++) {
            freq[i] = 1; 
            if (chars[i] == '0') continue; {
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;
                    chars[j] = '0'; 
                }
            }
        }

        int uniqueCount = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] != '0') uniqueCount++;
        }

        String[] result = new String[uniqueCount];
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] != '0') {
                result[k++] = chars[i] + " -> " + freq[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = sc.nextLine();

        String[] frequencies = findFrequency(input);

        System.out.println("Character frequencies:");
        for (String entry : frequencies) {
            System.out.println(entry);
        }
    }
}
