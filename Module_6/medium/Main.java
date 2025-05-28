package Module_6.medium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "/Users/zangar/IdeaProjects/ProductMastersBatch2/Module_6/medium/file.txt";

        HashSet<String> uniqueWords = new HashSet<>();
        HashMap<String, Integer> wordCount = new HashMap<>();
        List<String> allWords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {

                String[] words = line.toLowerCase().split("\\s+");

                for (String word : words) {
                    if (word.isEmpty()) continue;

                    uniqueWords.add(word);
                    allWords.add(word);
                }
            }


            for (String word : uniqueWords) {
                int frequency = Collections.frequency(allWords, word);
                wordCount.put(word, frequency);
            }


            System.out.println("Уникальные слова: " + uniqueWords);
            System.out.println();


            System.out.println("Уникальные слова и их частота:");
            for (String word : wordCount.keySet()) {
                System.out.println(word + ": " + wordCount.get(word));
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
