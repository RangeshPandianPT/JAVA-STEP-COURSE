import java.util.Scanner;

public class UniqueChars {

    static int getLength(String s) {
        int i = 0;
        try {
            while (true) {
                s.charAt(i);
                i++;
            }
        } catch (Exception e) {}
        return i;
    }

    static char[] getUnique(String s) {
        int n = getLength(s);
        char[] temp = new char[n];
        int k = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            boolean found = false;

            for (int j = 0; j < k; j++) {
                if (temp[j] == c) {
                    found = true;
                    break;
                }
            }

            if (!found) temp[k++] = c;
        }

        char[] res = new char[k];
        for (int i = 0; i < k; i++) res[i] = temp[i];
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String txt = sc.nextLine();

        char[] unique = getUnique(txt);

        System.out.print("Unique chars: ");
        for (char c : unique) System.out.print(c + " ");
    }
}
