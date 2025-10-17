interface MathOperation {
    int operate(int a, int b);
}

public class Calculator {

    static class Operation {
        int add(int a, int b) {
            return a + b;
        }
    }

    void performSubtraction(int a, int b) {
        class Subtraction {
            int subtract(int x, int y) {
                return x - y;
            }
        }
        Subtraction sub = new Subtraction();
        System.out.println("Subtraction: " + sub.subtract(a, b));
    }

    void performMultiplication(int a, int b) {
        MathOperation multiply = new MathOperation() {
            public int operate(int x, int y) {
                return x * y;
            }
        };
        System.out.println("Multiplication: " + multiply.operate(a, b));
    }

    public static void main(String[] args) {
        Calculator.Operation op = new Calculator.Operation();
        System.out.println("Addition: " + op.add(10, 5));

        Calculator calc = new Calculator();
        calc.performSubtraction(10, 5);
        calc.performMultiplication(10, 5);
    }
}
