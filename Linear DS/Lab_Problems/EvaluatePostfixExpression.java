import java.util.*;

public class EvaluatePostfixExpression {
    public static int evaluate(String expression) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            } else {
                int val2 = stack.pop();
                int val1 = stack.pop();

                switch (token) {
                    case "+": stack.push(val1 + val2); break;
                    case "-": stack.push(val1 - val2); break;
                    case "*": stack.push(val1 * val2); break;
                    case "/": stack.push(val1 / val2); break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter postfix expression: ");
        String expr = sc.nextLine();

        System.out.println("Result: " + evaluate(expr));
    }
}
