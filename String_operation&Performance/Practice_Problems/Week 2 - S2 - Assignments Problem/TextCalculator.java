import java.util.*;

public class TextCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();
        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("stop")) break;
            inputs.add(line);
        }
        for (String expr : inputs) {
            System.out.println("\nExpression: " + expr);
            boolean valid = isValid(expr);
            System.out.println("Valid: " + valid);
            if (!valid) continue;
            StringBuilder steps = new StringBuilder();
            try {
                long result = evaluate(expr, steps);
                System.out.println("\nSteps:");
                System.out.print(steps.toString());
                System.out.println("\nResult: " + result);
                System.out.println("Evaluation Successful: " + true);
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
                System.out.println("Evaluation Successful: " + false);
            }
        }
    }

    static boolean isValid(String s) {
        String expr = trimSpaces(s);
        if (expr.length() == 0) return false;
        int bal = 0;
        boolean lastWasOp = true;
        char lastToken = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            int a = (int) c;
            boolean digit = a >= 48 && a <= 57;
            boolean op = c == '+' || c == '-' || c == '*' || c == '/';
            if (!(digit || op || c == '(' || c == ')')) return false;
            if (c == '(') {
                if (!lastWasOp && lastToken != '(') return false;
                bal++;
                lastWasOp = true;
                lastToken = '(';
            } else if (c == ')') {
                if (bal == 0 || lastWasOp) return false;
                bal--;
                lastWasOp = false;
                lastToken = ')';
            } else if (op) {
                if (lastWasOp || lastToken == '(') return false;
                lastWasOp = true;
                lastToken = c;
            } else {
                lastWasOp = false;
                lastToken = '0';
                while (i + 1 < expr.length()) {
                    char n = expr.charAt(i + 1);
                    int an = (int) n;
                    if (an >= 48 && an <= 57) i++;
                    else break;
                }
            }
        }
        if (bal != 0 || lastWasOp) return false;
        for (int i = 0; i < expr.length() - 1; i++) {
            char c = expr.charAt(i), n = expr.charAt(i + 1);
            boolean cDigit = c >= '0' && c <= '9';
            boolean nDigit = n >= '0' && n <= '9';
            if ((cDigit && n == '(') || (c == ')' && nDigit) || (c == ')' && n == '(')) return false;
        }
        return true;
    }

    static long evaluate(String s, StringBuilder steps) {
        String expr = normalize(s);
        while (expr.indexOf(')') != -1) {
            int start = expr.lastIndexOf('(');
            int end = expr.indexOf(')', start);
            if (start == -1 || end == -1) throw new RuntimeException("Mismatched parentheses");
            String inner = expr.substring(start + 1, end);
            steps.append("Evaluate (").append(inner).append(")\n");
            long val = evalFlat(inner, steps);
            String before = expr;
            expr = expr.substring(0, start) + val + expr.substring(end + 1);
            steps.append(before).append(" => ").append(expr).append("\n");
        }
        return evalFlat(expr, steps);
    }

    static long evalFlat(String s, StringBuilder steps) {
        String exp = normalize(s);
        Tokens t = tokenize(exp);
        String current = format(t.nums, t.ops);
        for (int i = 0; i < t.ops.size(); ) {
            char op = t.ops.get(i);
            if (op == '*' || op == '/') {
                long a = t.nums.get(i);
                long b = t.nums.get(i + 1);
                if (op == '/' && b == 0) throw new ArithmeticException("Division by zero");
                long r = op == '*' ? a * b : a / b;
                String prev = current;
                t.nums.set(i, r);
                t.nums.remove(i + 1);
                t.ops.remove(i);
                current = format(t.nums, t.ops);
                steps.append(prev).append(" => ").append(current).append("\n");
            } else {
                i++;
            }
        }
        for (int i = 0; i < t.ops.size(); ) {
            char op = t.ops.get(i);
            long a = t.nums.get(i);
            long b = t.nums.get(i + 1);
            long r = op == '+' ? a + b : a - b;
            String prev = current;
            t.nums.set(i, r);
            t.nums.remove(i + 1);
            t.ops.remove(i);
            current = format(t.nums, t.ops);
            steps.append(prev).append(" => ").append(current).append("\n");
        }
        return t.nums.get(0);
    }

    static Tokens tokenize(String exp) {
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        int i = 0;
        while (i < exp.length()) {
            int j = i;
            while (j < exp.length()) {
                char c = exp.charAt(j);
                int a = (int) c;
                if (a >= 48 && a <= 57) j++;
                else break;
            }
            long val = Long.parseLong(exp.substring(i, j));
            nums.add(val);
            if (j < exp.length()) {
                char op = exp.charAt(j);
                ops.add(op);
                j++;
            }
            i = j;
        }
        return new Tokens(nums, ops);
    }

    static String format(List<Long> nums, List<Character> ops) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.size(); i++) {
            sb.append(nums.get(i));
            if (i < ops.size()) sb.append(ops.get(i));
        }
        return sb.toString();
    }

    static String trimSpaces(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') sb.append(c);
        }
        return sb.toString();
    }

    static String normalize(String s) {
        String noSpaces = trimSpaces(s);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < noSpaces.length(); i++) {
            char c = noSpaces.charAt(i);
            if (c != '\t' && c != '\n' && c != '\r') sb.append(c);
        }
        return sb.toString();
    }

    static class Tokens {
        List<Long> nums;
        List<Character> ops;
        Tokens(List<Long> n, List<Character> o) { nums = n; ops = o; }
    }
}
