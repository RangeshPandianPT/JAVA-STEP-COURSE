import java.util.Scanner;

public class StringMethods {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your full name (first and last): ");
        String fullName = scanner.nextLine().trim();

        System.out.print("Enter your favorite programming language: ");
        String language = scanner.nextLine().trim();

        System.out.print("Enter a sentence about your programming experience: ");
        String experience = scanner.nextLine().trim();

        String[] nameParts = fullName.split(" ");
        String firstName = nameParts.length > 0 ? nameParts[0] : "";
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        String sentenceWithoutSpaces = experience.replace(" ", "");
        int characterCount = sentenceWithoutSpaces.length();

        String languageUpper = language.toUpperCase();

        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Favorite Programming Language (UPPERCASE): " + languageUpper);
        System.out.println("Character Count in Experience Sentence (excluding spaces): " + characterCount);

        scanner.close();
    }
}
