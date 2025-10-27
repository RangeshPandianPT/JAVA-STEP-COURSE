import java.util.*;

public class ExpressionValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter expression: ");
        String exp = sc.nextLine();

        Stack<Character> stack = new Stack<>();
        boolean isBalanced = true;

        for (char ch : exp.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    isBalanced = false;
                    break;
                }
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    isBalanced = false;
                    break;
                }
            }
        }
        if (isBalanced && stack.isEmpty()) {
            System.out.println("Expression is Balanced");
        } else {
            System.out.println("Expression is NOT Balanced");
        }

        sc.close();
    }
}
