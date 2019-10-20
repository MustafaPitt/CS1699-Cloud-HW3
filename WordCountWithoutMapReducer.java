import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.StringTokenizer;

public class WordCountWithoutMapReducer {


    // run like "java WordCountWithoutMapReducer output data1.txt data2.txt data3.txt ..... dataN.txt"

    public static HashMap <String, Integer> wordsCount = new HashMap<>(1714760);

    public static void count(String[] fileNames){

        BufferedReader reader;
        for(int i = 1 ; i < fileNames.length ; i ++ ) {
            try {
                reader = new BufferedReader(new FileReader(fileNames[i]));
                String line = reader.readLine();
                while (line != null) {
                    line = reader.readLine();

                    try {
                        if (line != null)
                        addLineWordsToHashMap(line, wordsCount);
                    } catch (Exception ignored) {
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        printWordsToFile(fileNames[0],wordsCount);

    }

    private static void printWordsToFile(String fileName, HashMap<String, Integer> wordsCount) {
        try {
            FileWriter fileWriter = new FileWriter(new File(fileName));
            fileWriter.write("word\tcount\n" );
            for (String word: wordsCount.keySet()){
                fileWriter.write(word + ':' + wordsCount.get(word) + '\n');
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void addLineWordsToHashMap(String line, HashMap<String, Integer> wordsCount) {

        StringTokenizer st1 = new StringTokenizer(line, " ");

        while (st1.hasMoreTokens()){
            String word = st1.nextToken();
            try {
                wordsCount.put(word,wordsCount.get(word) + 1);
            } catch (Exception ignore) {
                wordsCount.put(word,1);
            }
        }

    }


    public static void main (String args [] ){



        Instant start = Instant.now();
        //Measure execution time for this method
        count(args);
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
        System.out.println("total time im millis  " + timeElapsed);
    }
}
