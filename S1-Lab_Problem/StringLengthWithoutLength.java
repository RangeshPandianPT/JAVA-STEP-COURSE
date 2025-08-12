import java.util.Scanner;

public class StringLengthWithoutLength {

   
    public static int findLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count); 
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.next(); 

        int len1 = findLength(input);
        int len2 = input.length();

        System.out.println("Length without length() method: " + len1);
        System.out.println("Length using length() method: " + len2);

        sc.close();
    }
}
