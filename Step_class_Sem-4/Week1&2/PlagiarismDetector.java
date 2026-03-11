import java.util.*;

public class PlagiarismDetector {

    private Map<String, Set<String>> ngramIndex;
    private Map<String, String> documents;
    private int n;

    public PlagiarismDetector(int n) {
        this.n = n;
        ngramIndex = new HashMap<>();
        documents = new HashMap<>();
    }

    public void addDocument(String docId, String text) {

        documents.put(docId, text);

        List<String> ngrams = generateNgrams(text);

        for (String gram : ngrams) {

            ngramIndex.putIfAbsent(gram, new HashSet<>());

            ngramIndex.get(gram).add(docId);
        }
    }

    public void analyzeDocument(String docId, String text) {

        List<String> ngrams = generateNgrams(text);

        Map<String, Integer> matchCounts = new HashMap<>();

        for (String gram : ngrams) {

            if (ngramIndex.containsKey(gram)) {

                for (String existingDoc : ngramIndex.get(gram)) {

                    matchCounts.put(existingDoc,
                            matchCounts.getOrDefault(existingDoc, 0) + 1);
                }
            }
        }

        for (String doc : matchCounts.keySet()) {

            int matches = matchCounts.get(doc);

            double similarity = ((double) matches / ngrams.size()) * 100;

            System.out.println("Document: " + doc + " Similarity: " + similarity + "%");
        }
    }

    private List<String> generateNgrams(String text) {

        String[] words = text.split("\\s+");

        List<String> ngrams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < n; j++) {

                sb.append(words[i + j]).append(" ");
            }

            ngrams.add(sb.toString().trim());
        }

        return ngrams;
    }

    public static void main(String[] args) {

        PlagiarismDetector detector = new PlagiarismDetector(5);

        detector.addDocument("essay_089",
                "machine learning improves systems by learning from data patterns");

        detector.addDocument("essay_092",
                "deep learning models learn patterns from large datasets");

        String newEssay =
                "machine learning improves systems by learning from large data patterns";

        detector.analyzeDocument("essay_123", newEssay);
    }
}