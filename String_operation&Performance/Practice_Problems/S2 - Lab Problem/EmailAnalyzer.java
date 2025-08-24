import java.util.*;

public class EmailAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> emails = new ArrayList<>();
        while (true) {
            String email = sc.nextLine();
            if (email.equals("stop")) break;
            emails.add(email);
        }
        List<EmailInfo> results = new ArrayList<>();
        for (String e : emails) results.add(processEmail(e));
        displayTable(results);
        analyze(results);
    }

    static EmailInfo processEmail(String email) {
        boolean valid = validate(email);
        String username = "", domain = "", domainName = "", extension = "";
        if (valid) {
            int at = email.indexOf('@');
            int dot = email.indexOf('.', at);
            username = email.substring(0, at);
            domain = email.substring(at + 1);
            domainName = domain.substring(0, domain.indexOf('.'));
            extension = domain.substring(domain.indexOf('.') + 1);
        }
        return new EmailInfo(email, username, domain, domainName, extension, valid);
    }

    static boolean validate(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        if (at == -1 || at != lastAt) return false;
        if (at == 0 || at == email.length() - 1) return false;
        int dot = email.indexOf('.', at);
        if (dot == -1 || dot == email.length() - 1) return false;
        return true;
    }

    static void displayTable(List<EmailInfo> list) {
        System.out.printf("%-30s %-15s %-20s %-15s %-10s %-10s%n", 
                          "Email", "Username", "Domain", "DomainName", "Extension", "Valid");
        for (EmailInfo e : list) {
            System.out.printf("%-30s %-15s %-20s %-15s %-10s %-10s%n",
                              e.email, e.username, e.domain, e.domainName, e.extension, e.valid);
        }
    }

    static void analyze(List<EmailInfo> list) {
        int validCount = 0, invalidCount = 0, totalUsernameLength = 0;
        Map<String, Integer> domainCount = new HashMap<>();
        for (EmailInfo e : list) {
            if (e.valid) {
                validCount++;
                totalUsernameLength += e.username.length();
                domainCount.put(e.domain, domainCount.getOrDefault(e.domain, 0) + 1);
            } else {
                invalidCount++;
            }
        }
        String mostCommonDomain = "";
        int max = 0;
        for (String d : domainCount.keySet()) {
            if (domainCount.get(d) > max) {
                max = domainCount.get(d);
                mostCommonDomain = d;
            }
        }
        double avgUsernameLength = validCount > 0 ? (double) totalUsernameLength / validCount : 0;
        System.out.println("\nAnalysis:");
        System.out.println("Valid Emails: " + validCount);
        System.out.println("Invalid Emails: " + invalidCount);
        System.out.println("Most Common Domain: " + mostCommonDomain);
        System.out.println("Average Username Length: " + avgUsernameLength);
    }
}

class EmailInfo {
    String email, username, domain, domainName, extension;
    boolean valid;
    EmailInfo(String e, String u, String d, String dn, String ex, boolean v) {
        email = e; username = u; domain = d; domainName = dn; extension = ex; valid = v;
    }
}
