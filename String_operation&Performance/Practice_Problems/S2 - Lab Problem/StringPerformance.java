import java.util.*;

public class StringPerformance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int iterations = sc.nextInt();
        Result concat = testConcat(iterations);
        Result builder = testBuilder(iterations);
        Result buffer = testBuffer(iterations);
        displayResults(Arrays.asList(concat, builder, buffer));
    }

    static Result testConcat(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) s += "x";
        long end = System.currentTimeMillis();
        return new Result("String Concatenation", end - start, s.length());
    }

    static Result testBuilder(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("x");
        long end = System.currentTimeMillis();
        return new Result("StringBuilder", end - start, sb.length());
    }

    static Result testBuffer(int n) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("x");
        long end = System.currentTimeMillis();
        return new Result("StringBuffer", end - start, sb.length());
    }

    static void displayResults(List<Result> results) {
        System.out.printf("%-25s %-15s %-15s%n", "Method", "Time (ms)", "Length");
        for (Result r : results) {
            System.out.printf("%-25s %-15d %-15d%n", r.method, r.time, r.length);
        }
    }
}

class Result {
    String method;
    long time;
    int length;
    Result(String m, long t, int l) {
        method = m; time = t; length = l;
    }
}
