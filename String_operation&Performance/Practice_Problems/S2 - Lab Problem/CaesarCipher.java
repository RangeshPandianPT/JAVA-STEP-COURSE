import java.util.*;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        int shift = sc.nextInt();
        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);
        System.out.println("Original: " + text);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Valid: " + text.equals(decrypted));
        displayAscii(text, encrypted);
    }

    static String encrypt(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append((char) ((c - 'A' + shift) % 26 + 'A'));
            } else if (Character.isLowerCase(c)) {
                sb.append((char) ((c - 'a' + shift) % 26 + 'a'));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    static void displayAscii(String original, String encrypted) {
        for (int i = 0; i < original.length(); i++) {
            System.out.println(original.charAt(i) + " -> " + (int) original.charAt(i) +
                               " | " + encrypted.charAt(i) + " -> " + (int) encrypted.charAt(i));
        }
    }
}
