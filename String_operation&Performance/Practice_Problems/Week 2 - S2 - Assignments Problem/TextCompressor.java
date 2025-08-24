import java.util.*;

public class TextCompressor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        FrequencyResult freqResult = countFrequency(text);
        String[][] mapping = createCodes(freqResult.chars, freqResult.freqs);
        String compressed = compress(text, mapping);
        String decompressed = decompress(compressed, mapping);

        displayAnalysis(text, compressed, decompressed, freqResult, mapping);
    }

    static class FrequencyResult {
        char[] chars;
        int[] freqs;
        FrequencyResult(char[] c, int[] f) { chars = c; freqs = f; }
    }

    static FrequencyResult countFrequency(String text) {
        StringBuilder uniqueChars = new StringBuilder();
        List<Integer> freqList = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int idx = uniqueChars.indexOf(String.valueOf(c));
            if (idx == -1) {
                uniqueChars.append(c);
                freqList.add(1);
            } else {
                freqList.set(idx, freqList.get(idx) + 1);
            }
        }
        char[] chars = new char[uniqueChars.length()];
        int[] freqs = new int[freqList.size()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = uniqueChars.charAt(i);
            freqs[i] = freqList.get(i);
        }
        return new FrequencyResult(chars, freqs);
    }

    static String[][] createCodes(char[] chars, int[] freqs) {
        String[][] map = new String[chars.length][2];
        Character[] order = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) order[i] = chars[i];
        Arrays.sort(order, (a, b) -> Integer.compare(
            freqs[indexOf(chars, b)], freqs[indexOf(chars, a)]
        ));
        for (int i = 0; i < order.length; i++) {
            char c = order[i];
            int idx = indexOf(chars, c);
            map[i][0] = String.valueOf(c);
            map[i][1] = codeForIndex(i);
        }
        return map;
    }

    static String codeForIndex(int i) {
        if (i < 10) return String.valueOf(i); 
        if (i < 36) return String.valueOf((char)('A' + (i-10)));
        if (i < 62) return String.valueOf((char)('a' + (i-36)));
        return "#" + i;
    }

    static int indexOf(char[] arr, char c) {
        for (int i = 0; i < arr.length; i++) if (arr[i] == c) return i;
        return -1;
    }

    static String compress(String text, String[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String c = String.valueOf(text.charAt(i));
            for (String[] m : map) {
                if (m[0].equals(c)) {
                    sb.append(m[1]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    static String decompress(String compressed, String[][] map) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < compressed.length()) {
            boolean matched = false;
            for (String[] m : map) {
                String code = m[1];
                if (compressed.startsWith(code, i)) {
                    sb.append(m[0]);
                    i += code.length();
                    matched = true;
                    break;
                }
            }
            if (!matched) i++; 
        }
        return sb.toString();
    }

    static void displayAnalysis(String original, String compressed, String decompressed, FrequencyResult freq, String[][] map) {
        System.out.println("Character Frequency:");
        for (int i = 0; i < freq.chars.length; i++) {
            System.out.println(freq.chars[i] + " -> " + freq.freqs[i]);
        }
        System.out.println("\nCompression Mapping:");
        for (String[] m : map) {
            System.out.println(m[0] + " -> " + m[1]);
        }
        System.out.println("\nOriginal: " + original);
        System.out.println("Compressed: " + compressed);
        System.out.println("Decompressed: " + decompressed);
        double ratio = (1.0 * compressed.length() / original.length()) * 100;
        System.out.println("Compression Efficiency: " + (100 - ratio) + "%");
        System.out.println("Valid Decompression: " + original.equals(decompressed));
    }
}
