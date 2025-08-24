import java.util.*;
import java.text.SimpleDateFormat;

class FileInfo {
    String originalName;
    String baseName;
    String extension;
    String category;
    String subCategory;
    String suggestedName;
    boolean validName;

    FileInfo(String originalName) {
        this.originalName = originalName;
    }
}

public class FileOrganizer {
    public static FileInfo extractFileInfo(String fileName) {
        FileInfo file = new FileInfo(fileName);

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == 0 || dotIndex == fileName.length() - 1) {
            file.baseName = fileName;
            file.extension = "";
            file.validName = false;
        } else {
            file.baseName = fileName.substring(0, dotIndex);
            file.extension = fileName.substring(dotIndex + 1);
            file.validName = validateFileName(file.baseName);
        }
        return file;
    }
    public static boolean validateFileName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!(Character.isLetterOrDigit(c) || c == '_' || c == '-')) {
                return false;
            }
        }
        return true;
    }
    public static void categorize(FileInfo file) {
        String ext = file.extension.toLowerCase();
        if (ext.equals("txt") || ext.equals("doc") || ext.equals("pdf")) {
            file.category = "Document";
        } else if (ext.equals("jpg") || ext.equals("png") || ext.equals("gif")) {
            file.category = "Image";
        } else if (ext.equals("mp4") || ext.equals("avi") || ext.equals("mkv")) {
            file.category = "Video";
        } else if (ext.equals("mp3") || ext.equals("wav")) {
            file.category = "Audio";
        } else if (ext.equals("java") || ext.equals("py") || ext.equals("cpp")) {
            file.category = "Code";
        } else if (ext.isEmpty()) {
            file.category = "Unknown";
        } else {
            file.category = "Other";
        }
    }
    public static void analyzeContent(FileInfo file, String content) {
        if (!file.category.equals("Document") && !file.category.equals("Code")) {
            file.subCategory = "-";
            return;
        }

        String lowerContent = content.toLowerCase();

        if (file.extension.equals("txt") || file.extension.equals("doc")) {
            if (lowerContent.contains("resume") || lowerContent.contains("cv")) {
                file.subCategory = "Resume";
            } else if (lowerContent.contains("report")) {
                file.subCategory = "Report";
            } else if (lowerContent.contains("invoice")) {
                file.subCategory = "Invoice";
            } else {
                file.subCategory = "General Doc";
            }
        } else if (file.extension.equals("java") || file.extension.equals("py") || file.extension.equals("cpp")) {
            if (lowerContent.contains("class") || lowerContent.contains("public")) {
                file.subCategory = "Source Code";
            } else if (lowerContent.contains("import") || lowerContent.contains("def")) {
                file.subCategory = "Script";
            } else {
                file.subCategory = "General Code";
            }
        } else {
            file.subCategory = "-";
        }
    }
    public static void generateSuggestedName(FileInfo file, int counter) {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        StringBuilder sb = new StringBuilder();

        sb.append(file.category);
        if (!file.subCategory.equals("-")) sb.append("_").append(file.subCategory.replace(" ", ""));
        sb.append("_").append(date).append("_").append(counter);

        if (!file.extension.isEmpty()) sb.append(".").append(file.extension);

        file.suggestedName = sb.toString();
    }
    public static void displayReport(List<FileInfo> files) {
        System.out.printf("%-25s %-12s %-15s %-25s\n", "Original Name", "Category", "SubCategory", "Suggested Name");
        for (FileInfo f : files) {
            System.out.printf("%-25s %-12s %-15s %-25s\n",
                    f.originalName, f.category, f.subCategory, f.suggestedName);
        }
    }
    public static void displaySummary(List<FileInfo> files) {
        Map<String, Integer> counts = new HashMap<>();
        int invalid = 0;
        for (FileInfo f : files) {
            counts.put(f.category, counts.getOrDefault(f.category, 0) + 1);
            if (!f.validName || f.category.equals("Unknown")) invalid++;
        }

        System.out.println("\n=== CATEGORY SUMMARY ===");
        for (String c : counts.keySet()) {
            System.out.println(c + ": " + counts.get(c));
        }
        System.out.println("Invalid/Unknown Files: " + invalid);
        System.out.println("Total Files: " + files.size());
    }
    public static void batchRenameCommands(List<FileInfo> files) {
        for (FileInfo f : files) {
            System.out.println("rename \"" + f.originalName + "\" \"" + f.suggestedName + "\"");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FileInfo> files = new ArrayList<>();

        System.out.println("Enter file names (end with empty line):");
        int counter = 1;
        while (true) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) break;

            FileInfo f = extractFileInfo(line);
            categorize(f);
            String dummyContent = "This is a sample resume content for testing Java class report.";
            analyzeContent(f, dummyContent);

            generateSuggestedName(f, counter++);
            files.add(f);
        }

        displayReport(files);
        displaySummary(files);
        batchRenameCommands(files);
    }
}
