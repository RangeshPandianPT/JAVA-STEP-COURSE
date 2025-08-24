import java.util.*;

public class TextFormatter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        int width = sc.nextInt();
        List<String> words = extractWords(text);
        List<String> justified = justify(words, width);
        List<String> centered = center(words, width);
        System.out.println("Original Text:\n" + text + "\n");
        System.out.println("Justified Text:");
        display(justified);
        System.out.println("\nCentered Text:");
        display(centered);
        performanceTest(words, width);
    }

    static List<String> extractWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words;
    }

    static List<String> justify(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.size()) {
            int len = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && len + 1 + words.get(j).length() <= width) {
                len += 1 + words.get(j).length();
                j++;
            }
            StringBuilder sb = new StringBuilder();
            int gaps = j - i - 1;
            if (j == words.size() || gaps == 0) {
                for (int k = i; k < j; k++) {
                    if (k > i) sb.append(" ");
                    sb.append(words.get(k));
                }
                while (sb.length() < width) sb.append(" ");
            } else {
                int totalSpaces = width - (len - gaps);
                int space = totalSpaces / gaps;
                int extra = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    sb.append(words.get(k));
                    if (k < j - 1) {
                        for (int s = 0; s < space + (k - i < extra ? 1 : 0); s++) sb.append(" ");
                    }
                }
            }
            lines.add(sb.toString());
            i = j;
        }
        return lines;
    }

    static List<String> center(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.size()) {
            int len = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && len + 1 + words.get(j).length() <= width) {
                len += 1 + words.get(j).length();
                j++;
            }
            StringBuilder sb = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            for (int k = i; k < j; k++) {
                if (k > i) temp.append(" ");
                temp.append(words.get(k));
            }
            int pad = (width - temp.length()) / 2;
            for (int p = 0; p < pad; p++) sb.append(" ");
            sb.append(temp);
            while (sb.length() < width) sb.append(" ");
            lines.add(sb.toString());
            i = j;
        }
        return lines;
    }

    static void display(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            System.out.println((i + 1) + " | " + line + " | " + line.length());
        }
    }

    static void performanceTest(List<String> words, int width) {
        long start1 = System.nanoTime();
        justify(words, width);
        long end1 = System.nanoTime();
        long start2 = System.nanoTime();
        justifyConcat(words, width);
        long end2 = System.nanoTime();
        System.out.println("\nPerformance Comparison:");
        System.out.println("StringBuilder Time: " + (end1 - start1) + " ns");
        System.out.println("Concatenation Time: " + (end2 - start2) + " ns");
    }

    static List<String> justifyConcat(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.size()) {
            int len = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && len + 1 + words.get(j).length() <= width) {
                len += 1 + words.get(j).length();
                j++;
            }
            String line = "";
            int gaps = j - i - 1;
            if (j == words.size() || gaps == 0) {
                for (int k = i; k < j; k++) {
                    if (k > i) line += " ";
                    line += words.get(k);
                }
                while (line.length() < width) line += " ";
            } else {
                int totalSpaces = width - (len - gaps);
                int space = totalSpaces / gaps;
                int extra = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    line += words.get(k);
                    if (k < j - 1) {
                        for (int s = 0; s < space + (k - i < extra ? 1 : 0); s++) line += " ";
                    }
                }
            }
            lines.add(line);
            i = j;
        }
        return lines;
    }
}
