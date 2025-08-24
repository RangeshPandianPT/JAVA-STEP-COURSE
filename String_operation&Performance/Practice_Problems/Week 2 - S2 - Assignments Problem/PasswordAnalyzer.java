import java.util.*;

public class PasswordAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> passwords = new ArrayList<>();
        while (true) {
            String p = sc.nextLine();
            if (p.equals("stop")) break;
            passwords.add(p);
        }
        System.out.printf("%-15s %-8s %-8s %-8s %-8s %-8s %-8s %-10s%n", 
                          "Password", "Length", "Upper", "Lower", "Digits", "Special", "Score", "Strength");
        for (String p : passwords) {
            Analysis a = analyze(p);
            System.out.printf("%-15s %-8d %-8d %-8d %-8d %-8d %-8d %-10s%n",
                              p, p.length(), a.upper, a.lower, a.digits, a.special, a.score, a.level);
        }
        System.out.println("\nGenerated Strong Passwords:");
        for (int i = 0; i < 3; i++) {
            System.out.println(generate(12));
        }
    }

    static Analysis analyze(String pwd) {
        int upper = 0, lower = 0, digits = 0, special = 0;
        for (int i = 0; i < pwd.length(); i++) {
            char c = pwd.charAt(i);
            if (c >= 65 && c <= 90) upper++;
            else if (c >= 97 && c <= 122) lower++;
            else if (c >= 48 && c <= 57) digits++;
            else if (c >= 33 && c <= 126) special++;
        }
        int score = 0;
        if (pwd.length() > 8) score += (pwd.length() - 8) * 2;
        if (upper > 0) score += 10;
        if (lower > 0) score += 10;
        if (digits > 0) score += 10;
        if (special > 0) score += 10;
        String[] patterns = {"123", "abc", "qwerty"};
        for (String pat : patterns) if (pwd.toLowerCase().contains(pat)) score -= 10;
        String level = score <= 20 ? "Weak" : score <= 50 ? "Medium" : "Strong";
        return new Analysis(upper, lower, digits, special, score, level);
    }

    static String generate(int length) {
        Random r = new Random();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+[]{};:,.<>?";
        String all = upper + lower + digits + special;
        StringBuilder sb = new StringBuilder();
        sb.append(upper.charAt(r.nextInt(upper.length())));
        sb.append(lower.charAt(r.nextInt(lower.length())));
        sb.append(digits.charAt(r.nextInt(digits.length())));
        sb.append(special.charAt(r.nextInt(special.length())));
        for (int i = 4; i < length; i++) {
            sb.append(all.charAt(r.nextInt(all.length())));
        }
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < sb.length(); i++) chars.add(sb.charAt(i));
        Collections.shuffle(chars);
        StringBuilder shuffled = new StringBuilder();
        for (char c : chars) shuffled.append(c);
        return shuffled.toString();
    }
}

class Analysis {
    int upper, lower, digits, special, score;
    String level;
    Analysis(int u, int l, int d, int s, int sc, String lv) {
        upper = u; lower = l; digits = d; special = s; score = sc; level = lv;
    }
}
