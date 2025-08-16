import java.util.Scanner;

public class CharFrequency 
{

    static Object[][] findFrequency(String text)
	{
        int[] freq = new int[256]; 

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            freq[c]++;
        }

        int uniqueCount = 0;
        for (int f : freq) if (f > 0) uniqueCount++;

        Object[][] result = new Object[uniqueCount][2];
        int k = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (freq[c] > 0) {
                result[k][0] = c;
                result[k][1] = freq[c];
                freq[c] = 0; 
                k++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = sc.nextLine();

        Object[][] frequencies = findFrequency(input);

        System.out.println("Character frequencies:");
        for (Object[] row : frequencies) {
            System.out.println(row[0] + " -> " + row[1]);
        }
    }
}
