import java.util.Scanner;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        for (char ch : input.toCharArray()) {
            int ascii = (int) ch;
            System.out.println("Character: " + ch + " | ASCII: " + ascii);
            String type = classifyCharacter(ch);
            System.out.println("Type: " + type);
            if (Character.isLetter(ch)) {
                char upper = Character.toUpperCase(ch);
                char lower = Character.toLowerCase(ch);
                System.out.println("Upper: " + upper + " (" + (int) upper + ")");
                System.out.println("Lower: " + lower + " (" + (int) lower + ")");
                System.out.println("Difference: " + Math.abs((int) upper - (int) lower));
            }
            System.out.println();
        }

        System.out.println("ASCII Table (65–90):");
        displayASCIITable(65, 90);

        int[] asciiArray = stringToASCII(input);
        System.out.print("ASCII Array: ");
        for (int val : asciiArray) System.out.print(val + " ");
        System.out.println();

        String reconverted = asciiToString(asciiArray);
        System.out.println("Reconverted String: " + reconverted);

        System.out.print("Enter shift value for Caesar Cipher: ");
        int shift = scanner.nextInt();
        String encrypted = caesarCipher(input, shift);
        System.out.println("Encrypted: " + encrypted);

        scanner.close();
    }

    public static String classifyCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z') return "Uppercase Letter";
        else if (ch >= 'a' && ch <= 'z') return "Lowercase Letter";
        else if (ch >= '0' && ch <= '9') return "Digit";
        else return "Special Character";
    }

    public static char toggleCase(char ch) {
        if (ch >= 'A' && ch <= 'Z') return (char) (ch + 32);
        else if (ch >= 'a' && ch <= 'z') return (char) (ch - 32);
        else return ch;
    }

    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                char shifted = (char) (((ch - 'A' + shift) % 26) + 'A');
                result.append(shifted);
            } else if (Character.isLowerCase(ch)) {
                char shifted = (char) (((ch - 'a' + shift) % 26) + 'a');
                result.append(shifted);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " -> " + (char) i);
        }
    }

    public static int[] stringToASCII(String text) {
        int[] asciiValues = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            asciiValues[i] = (int) text.charAt(i);
        }
        return asciiValues;
    }

    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int val : asciiValues) {
            sb.append((char) val);
        }
        return sb.toString();
    }
}
