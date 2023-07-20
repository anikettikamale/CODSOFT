import java.io.*;
import java.util.*;

public class task2 {
    private static final String STOP_WORDS_FILE = "stopwords.txt";

    private Set<String> stopWords;

    public task2() {
        stopWords = new HashSet<>();
        loadStopWords();
    }

    private void loadStopWords() {
        try (Scanner scanner = new Scanner(new File(STOP_WORDS_FILE))) {
            while (scanner.hasNextLine()) {
                stopWords.add(scanner.nextLine().trim().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Stop words file not found: " + e.getMessage());
        }
    }

    private String readTextFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text:");
        return scanner.nextLine();
    }

    private String readTextFromFile(String filePath) {
        try {
            StringBuilder text = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
            reader.close();
            return text.toString();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    private String[] splitTextIntoWords(String text) {
        return text.split("\\W+");
    }

    private int countWords(String[] words) {
        int count = 0;
        for (String word : words) {
            if (!stopWords.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    private Map<String, Integer> countWordFrequency(String[] words) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase();
            if (!stopWords.contains(word)) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }
        return frequencyMap;
    }

    public void displayWordStatistics(String text) {
        String[] words = splitTextIntoWords(text);
        int wordCount = countWords(words);
        System.out.println("Total word count: " + wordCount);

        Map<String, Integer> wordFrequency = countWordFrequency(words);
        System.out.println("Number of unique words: " + wordFrequency.size());

        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
    task2 wordCounter = new task2();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Enter text manually");
        System.out.println("2. Provide a file");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            String text = wordCounter.readTextFromUser();
            wordCounter.displayWordStatistics(text);
        } else if (choice.equals("2")) {
            System.out.println("Enter the file path:");
            String filePath = scanner.nextLine();
            String text = wordCounter.readTextFromFile(filePath);
            if (text != null) {
                wordCounter.displayWordStatistics(text);
            }
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
