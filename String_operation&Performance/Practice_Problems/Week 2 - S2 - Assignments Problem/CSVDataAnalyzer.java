import java.util.*;

public class CSVDataAnalyzer {
    public static String[][] parseCSV(String input) {
        List<String[]> rows = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();

        boolean inQuotes = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '\"') {
                inQuotes = !inQuotes; // toggle quote mode
            } else if (c == ',' && !inQuotes) {
                fields.add(field.toString());
                field.setLength(0);
            } else if ((c == '\n' || c == '\r') && !inQuotes) {
                if (field.length() > 0 || !fields.isEmpty()) {
                    fields.add(field.toString());
                    field.setLength(0);
                    rows.add(fields.toArray(new String[0]));
                    fields.clear();
                }
            } else {
                field.append(c);
            }
        }
        if (field.length() > 0 || !fields.isEmpty()) {
            fields.add(field.toString());
            rows.add(fields.toArray(new String[0]));
        }

        return rows.toArray(new String[0][]);
    }
    public static String[][] cleanData(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != null) {
                    data[i][j] = data[i][j].trim().replaceAll("^\"|\"$", "");
                } else {
                    data[i][j] = "";
                }
            }
        }
        return data;
    }
    public static boolean isNumeric(String s) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c >= '0' && c <= '9' || c == '.')) return false;
        }
        return true;
    }
    public static void analyzeData(String[][] data) {
        int cols = data[0].length;
        for (int j = 0; j < cols; j++) {
            boolean numeric = true;
            List<Double> numbers = new ArrayList<>();
            Set<String> unique = new HashSet<>();

            for (int i = 1; i < data.length; i++) {
                String val = data[i][j];
                if (isNumeric(val)) {
                    numbers.add(Double.parseDouble(val));
                } else {
                    numeric = false;
                    unique.add(val);
                }
            }

            System.out.println("\nColumn: " + data[0][j]);
            if (numeric && !numbers.isEmpty()) {
                double min = Collections.min(numbers);
                double max = Collections.max(numbers);
                double avg = numbers.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                System.out.printf("Type: Numeric | Min: %.2f | Max: %.2f | Avg: %.2f\n", min, max, avg);
            } else {
                System.out.println("Type: Categorical | Unique values: " + unique.size());
            }
        }
    }
    public static void formatTable(String[][] data) {
        System.out.println("\n=== FORMATTED DATA TABLE ===");
        int cols = data[0].length;
        int[] colWidths = new int[cols];

        for (int j = 0; j < cols; j++) {
            int maxLen = 0;
            for (int i = 0; i < data.length; i++) {
                maxLen = Math.max(maxLen, data[i][j].length());
            }
            colWidths[j] = maxLen + 2;
        }

        for (int i = 0; i < data.length; i++) {
            StringBuilder row = new StringBuilder("|");
            for (int j = 0; j < cols; j++) {
                row.append(String.format(" %-" + colWidths[j] + "s|", data[i][j]));
            }
            System.out.println(row);
        }
    }

    public static void summaryReport(String[][] data) {
        int totalRecords = data.length - 1;
        int totalFields = (data.length - 1) * data[0].length;
        int missing = 0;

        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].isEmpty()) missing++;
            }
        }

        double completeness = 100.0 * (totalFields - missing) / totalFields;

        System.out.println("\n=== DATA SUMMARY REPORT ===");
        System.out.println("Total Records: " + totalRecords);
        System.out.println("Total Fields: " + totalFields);
        System.out.println("Missing/Invalid Fields: " + missing);
        System.out.printf("Data Completeness: %.2f%%\n", completeness);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV-like data (end with an empty line):");

        StringBuilder input = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) break;
            input.append(line).append("\n");
        }

        String[][] data = parseCSV(input.toString());
        data = cleanData(data);

        formatTable(data);
        analyzeData(data);
        summaryReport(data);
    }
}
